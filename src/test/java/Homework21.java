import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest {

    String newPlaylistName = "new-ruba";
    @Test
    public void renamePlaylist() throws InterruptedException {

    String expectedPlaylistNameUpdateMsg = "Updated playlist \"new-ruba" +
            ".\"";

        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        doubleClickPlaylistName();
        editPlaylistName();
       //Assertion
        Assert.assertEquals(getPlaylistNameUpdateMsg(), expectedPlaylistNameUpdateMsg);
    }

    public void doubleClickPlaylistName() throws InterruptedException {
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(8)")));
        actions.doubleClick(playlistElement).perform();
        System.out.println("Button is double clicked");
    }

    public void editPlaylistName() throws InterruptedException{
        WebElement renamePlaylistField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        actions.doubleClick(renamePlaylistField).perform();
        renamePlaylistField.sendKeys(Keys.BACK_SPACE);
        renamePlaylistField.sendKeys(newPlaylistName);
        renamePlaylistField.sendKeys(Keys.ENTER);
    }

    public String getPlaylistNameUpdateMsg() throws InterruptedException{
        WebElement notificationMsg =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notificationMsg.getText();
    }
    //I add a System.out.println("Button is double clicked"); in doubleClickPlaylist() and it worked

}

