import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;


import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;


    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @Parameters("BaseUrl")
    @BeforeMethod
    public void launchBrowser(String BaseUrl) throws MalformedURLException {

//            public void launchBrowser(String BaseUrl){
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--remote-allow-origins=*");
            driver = pickBrowser(System.getProperty("browser"));
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

    public WebDriver pickBrowser(String browser) throws MalformedURLException {
    DesiredCapabilities caps = new DesiredCapabilities();
    String gridURL = "http://192.168.1.13:4444/";
    switch (browser){
        case "firefox":
            WebDriverManager.firefoxdriver().setup();
            return driver = new FirefoxDriver();

        case "MicrosoftEdge":
            WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--remote-allow-origins=*");
            return driver = new EdgeDriver();

        case "grid-edge": // gradle clean test -Dbrowser=grid-edge
            caps.setCapability("browserName", "MicrosoftEdge");
            return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

        case "grid-firefox": // gradle clean test -Dbrowser=grid-firefox
            caps.setCapability("browserName", "firefox");
            return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

        case "grid-chrome": // gradle clean test -Dbrowser=grid-chrome
            caps.setCapability("browserName", "chrome");
            return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

        default:
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            return driver = new ChromeDriver(chromeOptions);
    }
  }
}


