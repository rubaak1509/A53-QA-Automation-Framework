import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest {

    @Test
    public void renamePlaylist() throws InterruptedException {

    String expectedPlaylistNameUpdateMsg = "Updated playlist \"ruba-456" +
            ".\"";

        provideEmail("rubaiyat.akram@testpro.io");
        providePassword("Te$tPro123");
        clickSubmit();
        doubleClickPlaylistName();
        editPlaylistName("ruba-456");
       //Assertion
        Assert.assertEquals(getPlaylistNameUpdateMsg(), expectedPlaylistNameUpdateMsg);
       //Reset playlist name back to original value in preparation for the next test run
        doubleClickPlaylistName();
        editPlaylistName("ruba-123");
    }

    public void doubleClickPlaylistName() throws InterruptedException {
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(6)")));
        actions.doubleClick(playlistElement).perform();
    }

    public void editPlaylistName(String playlistName) throws InterruptedException{
        WebElement renamePlaylistField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        //Clear does not work since element has an attribute of required.
        //Needed to use Command key to select the text to execute the clear field action on mac
        renamePlaylistField.sendKeys(Keys.chord(Keys.COMMAND, "A", Keys.BACK_SPACE));
        renamePlaylistField.sendKeys(playlistName);
        renamePlaylistField.sendKeys(Keys.ENTER);
    }

    public String getPlaylistNameUpdateMsg() throws InterruptedException{
        WebElement notificationMsg =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notificationMsg.getText();
    }
}

