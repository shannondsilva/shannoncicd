package com.example.stepDefinations;

import com.example.utils.Base;
import com.example.utils.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

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
}
