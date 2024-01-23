import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Homework22 extends BaseTest {

    @Test
    public void renamePlaylist() {

        String expectedPlaylistNameUpdateMsg = "Updated playlist \"ruba-456" +
                ".\"";

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login();
        homePage.doubleClickPlaylist();
        homePage.enterPlaylistName("ruba-456");
        Assert.assertEquals(homePage.getRenamePlaylistSuccessMsg(), expectedPlaylistNameUpdateMsg);
        //Reset playlist name back to original value in preparation for the next test run
        homePage.doubleClickPlaylist();
        homePage.enterPlaylistName("ruba-123");
    }
}