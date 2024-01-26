import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Homework24 extends BaseTest {

    @Test
    public void loginWithCorrectCredentials() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.provideEmail("demo@class.com").providePassword("te$t$tudent").clickSubmit();
        Assert.assertTrue(homePage.getUserAvatarIcon().isDisplayed());
    }
}