package com.cc.practicaltest.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationFinderSteps {

    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
    }

    @Given("^I am on the Location Finder page$")
    public void visitLocationFinderPage() {
        driver.get("https://www.costcutter.co.uk/location-finder/");
    }

    @When("^I search for \"(.*)\"$")
    public void searchFor(String query) {
    	WebElement element = driver.findElement(By.xpath("//input[@placeholder='Your town, city or postcode']"));
        element.sendKeys(query);
        element.submit();
    }
    
    @Then("^the search result displays a map$")
    public void checkForMap() {
    	 new WebDriverWait(driver,10L).until((ExpectedCondition<Boolean>) d -> d.findElement(By.id("map")).isDisplayed());
    }
    
    @Then("^the number of Stores (.*) is displayed$")
    public void checkNumberOfStores(String storeCount) {
    	new WebDriverWait(driver,10L).until((ExpectedCondition<Boolean>) d -> d.findElement(By.xpath("//div[@id='totals']//div[@class='span12 expand']//h3")).getText().contains(storeCount));
        }
    
    @And("^location results are displayed$")
    public void checkForResults() {
    	 new WebDriverWait(driver,10L).until((ExpectedCondition<Boolean>) d -> d.findElement(By.id("results")).isDisplayed());
    }
    
    @And("^the search result should have \"(.*)\"$")
    public void checkTitle(String titleStartsWith) {
    	 
      new WebDriverWait(driver,10L).until((ExpectedCondition<Boolean>) d -> d.findElement(By.xpath("//div[@id='totals']//div[@class='span12 expand']//h3//span")).getText().contains(titleStartsWith));
    
    }
    
    @After
    public void closeBrowser() {
        driver.quit();
    }
}
