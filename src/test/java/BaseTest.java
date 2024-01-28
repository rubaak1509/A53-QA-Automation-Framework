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
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {

    public WebDriver driver = null;
    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
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
        // driver = pickBrowser(System.getProperty("browser"));
        threadDriver.set(pickBrowser(System.getProperty("browser")));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        actions = new Actions(getDriver());
        //  getDriver().manage().window().maximize();
        navigateToUrl(BaseUrl);
    }

    public static WebDriver getDriver() {
        return threadDriver.get();
    }


    @AfterMethod
    public void tearDown() {
        threadDriver.get().close();
        threadDriver.get().quit();
    }

//        @AfterMethod
//        public void closeBrowser(){
//            driver.quit();
//        }


    public void navigateToUrl(String givenUrl) {
        getDriver().get(givenUrl);
    }


    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.1.13:4444/";
        switch (browser) {
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

            case "cloud":
                return lambdatest();

            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(chromeOptions);
        }
    }

    public WebDriver lambdatest() throws MalformedURLException {

        String hubUrl = "https://hub.lambdatest.com/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();

//        ChromeOptions browserOptions = new ChromeOptions();
//        browserOptions.setPlatformName("macOS Monterey");
//        browserOptions.setBrowserVersion("120.0");

        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "120.0");


        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "ruba.akram");
        ltOptions.put("accessKey", "6tmbCwndj5cBC7dxrGT0odG9hLQcjVzmIjIiBtokPdTcmZqWU1");
        ltOptions.put("geoLocation", "CA");
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("resolution", "2560x1440");
        ltOptions.put("project", "Untitled");
        ltOptions.put("name", "Demo test");
        ltOptions.put("selenium_version", "4.5.0");
        ltOptions.put("driver_version", "120.0");
        ltOptions.put("w3c", true);
    //    browserOptions.setCapability("LT:Options", ltOptions);
        capabilities.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubUrl), capabilities);
    }
}


