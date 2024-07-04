package com.example.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/featureFileDirectory" ,
        glue = {"com.example.stepDefinations","com.example.utils"},
        monochrome = true,
        plugin = {"html:target/cucumber.html"},
        tags = "@TC_1"

)
public class testNGtestRunner extends AbstractTestNGCucumberTests {

}
