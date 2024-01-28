import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Homework25 extends BaseTest {

    @Test
    public void loginWithCorrectCredentials() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmail("demo@class.com").providePassword("te$t$tudent").clickSubmit();
        Assert.assertTrue(homePage.getUserAvatarIcon().isDisplayed());
    }

    @Test
    public void loginWithCorrectCredentials2() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmail("demo@class.com").providePassword("te$t$tudent").clickSubmit();
        Assert.assertTrue(homePage.getUserAvatarIcon().isDisplayed());
    }
}