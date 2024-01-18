import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;

    public Actions actions;


    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Parameters("BaseUrl")
    @BeforeMethod
            public void launchBrowser(String BaseUrl){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            actions = new Actions(driver);
            driver.manage().window().maximize();
            navigateToUrl(BaseUrl);
        }

        @AfterMethod
        public void closeBrowser(){
            driver.quit();
        }


        public void navigateToUrl(String givenUrl){
            driver.get(givenUrl);
        }

        public void provideEmail(String email){
            WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
            emailField.clear();
            emailField.sendKeys(email);
        }

        public void providePassword(String password){
            WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
            passwordField.clear();
            passwordField.sendKeys(password);
        }

        public void clickSubmit(){
            WebElement submit = driver.findElement(By.cssSelector("button[type='submit']"));
            submit.click();
        }

        public void renameProfileToOriginal() throws InterruptedException {
            WebElement renamePlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
            renamePlaylist.sendKeys(Keys.chord(Keys.COMMAND, "A", Keys.BACK_SPACE));
            renamePlaylist.sendKeys("test-playlist");
            renamePlaylist.sendKeys(Keys.ENTER);
        }
    }
