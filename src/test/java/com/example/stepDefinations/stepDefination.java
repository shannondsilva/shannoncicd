package com.example.stepDefinations;

import com.example.utils.*;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mysql.cj.log.Log;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.utils.Reusables.LogCapture;

public class stepDefination  {

    @Given("User launches browser {string}")
    public void user_launches_browser(String browser) {

        String vObjBrowser = Constants.config.getProperty("browser");
        Assert.assertEquals("PASSED",Constants.key.openBrowser(vObjBrowser,""));

    }
    @When("User enters {string} into the browser and lands on the page")
    public void user_enters_into_the_browser_and_lands_on_the_page(String url) {

        Assert.assertEquals("PASSED",Constants.key.navigateToURL(url,""));
    }
    @Then("User check for the home and books field")
    public void user_check_for_the_username_and_password_field() {

        String vObjbooks = Constants.applicationProperty.getProperty("books");
        String vObjhome = Constants.applicationProperty.getProperty("home");

        Assert.assertEquals("PASSED",Constants.key.visibleWaitCondition(vObjbooks,"3"));
        Assert.assertEquals("PASSED",Constants.key.visibleWaitCondition(vObjhome,"3"));

    }

    ArrayList<String> storeMultiLevelElements = new ArrayList<>();
    @Given("User needs to find the chemical element count for {string}")
    public void userNeedsToFindTheChemicalElementCountFor(String formula) throws ClassNotFoundException {
//        formula = Mg(OH)2
        Class<?> clazzsy = Class.forName("com.example.stepDefinations.stepDefination");
        Field[] fld = clazzsy.getDeclaredFields();
        Constructor<?>[] ctr = clazzsy.getDeclaredConstructors();
        Method[] mtd = clazzsy.getDeclaredMethods();
        for(Field f: fld){
            String dd = f.getName();
           System.out.println(dd);
        }for(Constructor<?> c: ctr){
           System.out.println(c.getName());
        }for(Method m: mtd){
           System.out.println(m.getName());
        }

        if(formula.contains("(") || formula.contains(")")){
            int firstOccur = formula.indexOf("(");
            int lastOccur = formula.lastIndexOf(")");
            int globalMultiplier1;
            String remainingSimpleString;
            if(Character.isDigit(formula.charAt(lastOccur+1))) {
                 globalMultiplier1 = Integer.parseInt(String.valueOf(formula.charAt(lastOccur + 1)));
                 remainingSimpleString = formula.replace(formula.substring(firstOccur,lastOccur+2),"");
                Map<String, Integer> elementCnt = simpleFormulaeExtraction(remainingSimpleString);
                LogCapture.info("");
            }else{
                remainingSimpleString = formula.replace(formula.substring(firstOccur,lastOccur+1),"");
            }
            String firstLevelString = formula.substring(firstOccur+1,lastOccur);
            userNeedsToFindTheChemicalElementCountFor(firstLevelString);
            if(Character.isDigit(formula.charAt(lastOccur+2))){

            }
            LogCapture.info("");
        }
        else {
            Map<String, Integer> elementCnt = simpleFormulaeExtraction(formula);
            LogCapture.info("Here is you element count --> " + elementCnt);
        }
    }

    private Map<String, Integer> simpleFormulaeExtraction(String formula) {
        Map<String, Integer> elementCnt = new HashMap<>();
        for(int i = 0; i< formula.length(); i++){
            if(Character.isUpperCase(formula.charAt(i)) ){
                int totalChar = formula.length();
                String eleShort;
                int eleCnt;
                if(totalChar> i+1 && Character.isLowerCase(formula.charAt(i+1))){
                    eleShort=Character.toString(formula.charAt(i))+Character.toString(formula.charAt(i+1));
                }else{
                    eleShort= Character.toString(formula.charAt(i));
                }

                if(totalChar> i+1 && Character.isDigit(formula.charAt(i+1))){
                    eleCnt = Character.getNumericValue(formula.charAt(i+1));
                }else if(totalChar> i+1 && totalChar> i+2 && Character.isDigit(formula.charAt(i+2)) && !Character.isUpperCase(formula.charAt(i+1))){
                    eleCnt = Character.getNumericValue(formula.charAt(i+2));
                }else {
                    if (elementCnt.containsKey(eleShort)) {
                        eleCnt = elementCnt.get(eleShort) + 1;
                    }
                    else{
                        eleCnt = 1;
                    }
                }
                elementCnt.put(eleShort,eleCnt);
            }

        }
        return elementCnt;
    }

    @Given("Test my POJO")
    public void testMyPOJO() throws JAXBException {

        String xmlBody = "<Product><id>1</id><name>Product A</name><price>20.0</price><categories><Category><id>1</id><name>Category 1</name></Category><Category><id>2</id><name>Category 2</name></Category></categories></Product>";


        JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xmlBody);
        Product POJOxml = (Product) unmarshaller.unmarshal(reader);
        List<Category> c = POJOxml.getCategories();
        LogCapture.info("---> "+POJOxml.getId());
        LogCapture.info("---> "+POJOxml.getName());
        Iterator<Category> i = c.iterator();

        LogCapture.info("Sure fire plugin properties --> "+System.getProperty("enviroment"));


    }

    @Given("Connecting to MySQL Using the JDBC DriverManager Interface")
    public void connectingToMySQLUsingTheJDBCDriverManagerInterface() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business?user=root&password=root");
            CustomerInfo cust = new CustomerInfo();
            DataArray da = new DataArray();
            ArrayList<CustomerInfo> arrC = new ArrayList<CustomerInfo>();
            ObjectMapper om = new ObjectMapper();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from CustomerInfo where Location ='Asia';");
            while(rs.next()){
                LogCapture.info("--------------------------------------------------------------");
                LogCapture.info(rs.getString(1));
                LogCapture.info(rs.getString(2));
                LogCapture.info(rs.getString(3));
                LogCapture.info(rs.getString(4));
                cust.setCourseName(rs.getString(1));
                cust.setPurchaseDate(rs.getString(2));
                cust.setAmount(Integer.parseInt(rs.getString(3)));
                cust.setLocation(rs.getString(4));
//                om.writeValue(new File(System.getProperty("user.dir")+File.separator+"CustRef"+System.currentTimeMillis()+".json"),cust);
                arrC.add(cust);
                LogCapture.info("--------------------------------------------------------------");
            }
            da.setCustData(arrC);
            om.writeValue(new File(System.getProperty("user.dir")+File.separator+"CustRef"+System.currentTimeMillis()+".json"),da);


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (StreamWriteException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Given("Find Duplicates in an Array")
    public void findDuplicatesInAnArray() {

        int[] x = {1,2,2,3,1,6,4,6,76,7,0};
        Map<Integer, Integer>  duplicateCount = new HashMap<>();

        for(int i=0; i<x.length; i++){
            if(duplicateCount.containsKey(x[i])){
                duplicateCount.put(x[i],duplicateCount.get(x[i])+1);
            }
            else{
                duplicateCount.put(x[i],1);
            }
        }
        LogCapture.info(duplicateCount);
        int i = 8;
        //1,3,5,7,11,13,17,19,23,29
        boolean isPrime=false;
        for(int y = 2; y<=i/2; y++){
            if(i%y==0){
                isPrime=false;
                break;
            }
            else{
                isPrime=true;
            }
        }
        LogCapture.info(isPrime ? "PRIME NUMBER" : "NOT PRIME");




    }

    @Given("To test my docker knowledge")
    public void toTestMyDockerKnowledge() throws MalformedURLException, InterruptedException {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browserName","chrome");
        Thread.sleep(10000);
        RemoteWebDriver rDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
        rDriver.get("https://goodgoodpiggy.com/");
        LogCapture.info(rDriver.getTitle());
        Thread.sleep(20000);
        rDriver.quit();

    }

    @Given("Run my naukri.com profile")
    public void runMyNaukriComProfile() {

        Constants.key.openBrowser("Chrome","");
        Constants.driver.get("https://www.naukri.com/");
        Constants.key.visibleWaitCondition("//a[@id='login_Layer']","10");
        Constants.key.click("//a[@id='login_Layer']","");

        Constants.key.writeToElement("//input[@placeholder='Enter your active Email ID / Username']","shannondsilva001@gmail.com");
        Constants.key.writeToElement("//input[@placeholder='Enter your password']","shannon2681994");
        Constants.key.click("//button[@type='submit']","");
        Constants.key.visibleWaitCondition("//div[text()='Profile performance']","10");



    }

    @Given("BAT file executions for docker up")
    public void batFileExecutionsForDockerUp() throws IOException {

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

        //Perform some actions

        rt.exec("cmd /c start dockerDown.bat");

        while(!fileDown.exists()){
            LogCapture.info("Waiting for outputDown.txt to generate.");
        }
        BufferedReader brClose = new BufferedReader(new FileReader("outputDown.txt"));

        while(brClose.readLine()==null){
            LogCapture.info("outputDown.txt is still empty");
        }
        while(output != null && !brClose.readLine().contains("Running 13/13")){
            LogCapture.info("Still waiting for outputDown nodes to come  up");
        }




    }

    @Given("Demo test program")
    public void demoTestProgram() throws FileNotFoundException {
        String str = "3409tnnj3t%#vonjjvnrshannonopi040un3tjceshanno8wntcwkc0949i9t4wcshannon094utcm";
        String strCompare="shannon";
        int numberOfOccurences=0;
        for(int i=0; i<str.length();i++){
            if(str.charAt(i)==strCompare.charAt(0)){
                boolean isFullTextMatch=false;
                for(int j = 1; j <strCompare.length(); j++){
                    if((i+j)<str.length() && str.charAt(i+j)==strCompare.charAt(j)){
                        isFullTextMatch=true;
                    }else {
                        isFullTextMatch=false;
                        break;
                    }
                }
                if(isFullTextMatch){
                    numberOfOccurences++;
                }
            }
        }
        LogCapture.info("Number of occurence --> "+numberOfOccurences);
    }

    public void test(){
//        Map<String,String> mp = new HashMap<>();
//        Set<Map.Entry<String,String>> es = mp.entrySet();
//        Map<String,String> ht = new Hashtable<>();
//         Iterator<Map.Entry<String,String>> io  = ht.entrySet().iterator();
//         io.next().getKey();
//
//        for(Map.Entry<String,String> etry:mp.entrySet()){
//
//        }
//
//        Set<String> st = new HashSet<>();
//        st.add(null);
//        st.add(null);
//
//        Set<String> ts = new TreeSet<>();
//        ts.add(null);

        List<Integer> arr = Arrays.asList(1,3,4,4,1,2,4,5,7);
        arr.stream().distinct().forEach(s->LogCapture.info(" ----------> "+s));

    }

    @Given("Demo test StreamsAndLamda")
    public void demoTestStreamsAndLamda() {

//        List<Integer> arr = Arrays.asList(1,3,4,4,1,2,4,5,7);
//        arr.stream().distinct().sorted().forEach(s->LogCapture.info(" ----------> "+s));
//
//
//        List<Integer> mp = arr.stream().distinct().sorted().collect(Collectors.toList());
//        LogCapture.info("---MP----->"+mp);
//
//        List<String> str = Arrays.asList("wrfsfsf","wcfsd","rfcaef","rfasfsd");
//        List<String> mp1 = Stream.of("dsf","tfds","gsrg","sgrsgs").filter(s->s.startsWith("s")).map(s->s.toUpperCase()).collect(Collectors.toList());
//        LogCapture.info("---MP----->"+mp1);


        String str = "publicissapient"; // first non repeated characterd // 1st repeated characters
        int totalLnt = str.length();
        for(int i=0; i< str.length(); i++){
            String x = String.valueOf(str.charAt(i));
            String newStr = str.replaceAll(x,"");
            if(newStr.length()<=totalLnt-2){
                System.out.println("1st repeating character = "+x);
                break;
            }
        }

        for(int i=0; i< str.length(); i++){
            String x = String.valueOf(str.charAt(i));
            String newStr = str.replaceAll(x,"");
            if(newStr.length()==totalLnt-1){
                System.out.println("1st NON-repeating character = "+x);
                break;
            }
        }

        //{1,2,4,1,0,9,2,0,8} --> {1,2,4,1,9,2,0,0,0}

        int[] arr = {1,2,4,1,0,9,2,0,0,8};
        int index=0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[index++] = arr[i];
            }
        }
        while (index < arr.length) {
            arr[index++] = 0;
        }

//        r=4
//
//            1
//           212
//          32123
//         4321234

        int pyramidSize=4;
        for(int i = 0 ; i < pyramidSize; i++){
            int cnt=0;
            for(int j=1; j<pyramidSize-i;j++){
                System.out.print(" ");
                cnt++;
            }
            for(int a = pyramidSize; a > cnt; a--){
                System.out.print(a-cnt);
            }
            for(int a = pyramidSize-1; a > cnt; a--){
                System.out.print(pyramidSize-a+1);
            }
            System.out.println("");
        }
    }




}
