package com.example.runner;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.example.utils.Reusables.LogCapture;

@CucumberOptions(
        features = "src/test/featureFileDirectory" ,
        glue = {"com.example.stepDefinations","com.example.utils"},
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        tags = "@Docker"

)
public class testNGtestRunner extends AbstractTestNGCucumberTests {
        @BeforeTest
        public void startNodes() throws IOException {
                Runtime rt = Runtime.getRuntime();
                File file = new File("output.txt");
                File fileDown = new File("outputDown.txt");
                if(file.exists()){
                        LogCapture.info("Deleting output.txt file...");
                        file.delete();
                }
                if(fileDown.exists()){
                        LogCapture.info("Deleting outputDown.txt file...");
                        fileDown.delete();
                }
                rt.exec("cmd /c start dockerUp.bat");
                while(!file.exists()){
                        LogCapture.info("Waiting for output.txt to generate.");
                        file = new File("output.txt");
                }
                BufferedReader br = new BufferedReader(new FileReader("output.txt"));
                String output = br.readLine();
                while(output==null){
                        LogCapture.info("output.txt is still empty");
                        output = br.readLine();
                }
                while(output != null && !output.contains("Node has been added")){
                        LogCapture.info(output);
                        output = br.readLine();
                }
        }

        @AfterTest
        public void stopNodes() throws IOException {
                Runtime rt = Runtime.getRuntime();
                rt.exec("cmd /c start dockerDown.bat");
                File fileDown = new File("outputDown.txt");
                while(!fileDown.exists()){
                        LogCapture.info("Waiting for outputDown.txt to generate.");
                }
                BufferedReader brClose = new BufferedReader(new FileReader("outputDown.txt"));

                while(brClose.readLine()==null){
                        LogCapture.info("outputDown.txt is still empty");
                }
                String outputDown= brClose.readLine();
                while(outputDown != null && !brClose.readLine().contains("Running 13/13")){
                        LogCapture.info("Still waiting for outputDown nodes to come  up");
                        outputDown= brClose.readLine();
                }
        }

}
