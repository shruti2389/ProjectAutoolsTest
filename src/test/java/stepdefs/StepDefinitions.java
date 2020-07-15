package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class StepDefinitions {

    private static final Logger LOG = Logger.getLogger(StepDefinitions.class);
    private WebDriver theWebDriver;
    FluentWait<WebDriver> wait;

    @Given("User is on the login page")
    public void loginPage(){

        System.setProperty("webdriver.gecko.driver", "C:\\ShrutiData\\QATraining\\GeckoDriver\\geckodriver.exe");
        theWebDriver = new FirefoxDriver();
        theWebDriver.get("http://23.120.24.187:10080/autotools");
    }

    @When("User enters {string} as username")
    public void inputUsername(String aUserName){
        WebElement unElement = theWebDriver.findElement(By.id("j_username"));
        unElement.sendKeys(aUserName);
    }
    @And("User enters {string} as password")
    public void inputPWD(String aPWD){
        WebElement pwdElement = theWebDriver.findElement(By.xpath("//input[@type='password']"));
        pwdElement.sendKeys(aPWD);
    }
//
//    @And("User enters {string} blank as password")
//    public void inputPWDblank(String empty){
//        WebElement pwdElement = theWebDriver.findElement(By.xpath("//input[@type='password']"));
//        pwdElement.sendKeys(empty);
//
//    }
    @And("clicks Sign in")
    public void clickSignIn(){
        WebElement signIn = theWebDriver.findElement(By.className("btn-primary"));
        signIn.click();
    }

    @Then("User is shown error page with msg {string}")
    public void validateFailedLogin(String aErrorMsg){
        wait = new FluentWait<WebDriver>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("strong")));
        WebElement msgElement = theWebDriver.findElement(By.tagName("strong"));
        LOG.debug("The error msg from element: "+msgElement.getText());
        Assert.assertEquals(msgElement.getText(), aErrorMsg);

        msgElement = theWebDriver.findElement(By.xpath("//div[@class='alert alert-error']"));
        LOG.debug("The text in div tag error element: "+msgElement.getText());
        cleanup();

    }

    @Then("User is shown welcome page with msg {string}")
    public void validateWelcome(String aWelcomeMsg){
        wait = new FluentWait<WebDriver>(theWebDriver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")));
        WebElement msgElement = theWebDriver.findElement(By.tagName("p"));
        LOG.debug("The welcome msg from element: "+msgElement.getText());
        Assert.assertEquals(msgElement.getText(), aWelcomeMsg);
        cleanup();

    }

    @And("User closes the browser")
    public void cleanup(){
        theWebDriver.close();
    }

    @Given("User is on the home page")
    public void onHomePage(){
        loginPage();
        inputUsername("shruti@kubecloudsinc.com"); // get it from some where right now hard coding
        inputPWD("Testing1");
        clickSignIn();
        validateWelcome("Welcome to Auto Tools");
        //  home page
        // do you logic
    }

    @Then("User should see menu bar with background color {string}")
    public void validateColor(String backgroundColor){

        String color = theWebDriver.findElement(By.xpath("//div[@class='container']")).getCssValue("color");
        LOG.debug(color);
        Assert.assertEquals(color, backgroundColor);
        cleanup();
    }

    @Then("Validate that the third tab is {string}")
    public void validateEmployeeTab(String EmployeeTab){

        WebElement tabEmp = theWebDriver.findElement(By.xpath("//a[@href='/autotools/employee_details.html']"));
        LOG.debug(tabEmp.getText());
        Assert.assertEquals(tabEmp.getText(), EmployeeTab);
        cleanup();

    }

    @Then("Validate that the forth tab is {string}")
    public void validateRegionsTab(String RegionsTab){

        WebElement tabReg = theWebDriver.findElement(By.xpath("//a[@href='/autotools/regions.html']"));
        LOG.debug(tabReg.getText());
        Assert.assertEquals(tabReg.getText(), RegionsTab);
        cleanup();
    }
}
