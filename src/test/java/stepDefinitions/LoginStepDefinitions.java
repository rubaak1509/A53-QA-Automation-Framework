package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginStepDefinitions {

    WebDriver driver;
    WebDriverWait wait;
        @Given("I open browser")
        @Before
        public void openBrowser() {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        }

        @After
        public void closeBrowser(){
            driver.quit();
        }

        @Given("I open Login page")
        public void openLogin(){
            driver.get("https://qa.koel.app/");
        }

        @When("I enter email {string}")
        public void enterEmail(String email){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='email']"))).sendKeys(email);
        }

    @And("I enter password {string}")
    public void enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='password']"))).sendKeys(password);
        }

    @And("I click submit")
    public void clickSubmit(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='submit']"))).click();
        }

    @Then("I am logged in")
    public boolean loggedIn() throws InterruptedException{
        WebElement userAvatar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar")));
        return userAvatar.isDisplayed();
        }
    }
