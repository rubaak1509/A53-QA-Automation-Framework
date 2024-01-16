import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework20 extends BaseTest {

    @Test
    public void deletePlaylist() throws InterruptedException {
        String expectedPlaylistDeletedMsg = "Deleted playlist \"A Test Pro Playlist.\"";
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        openPlaylist();
        clickDeletePlaylistBtn();
        Assert.assertEquals(getDeletedPlaylistMsg(), expectedPlaylistDeletedMsg);
    }

    public void openPlaylist(){
        WebElement emptyPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='playlists']//li[6]")));
        emptyPlaylist.click();
    }

    public void clickDeletePlaylistBtn() {
        WebElement deletePlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-delete-playlist")));
        deletePlaylist.click();
    }

    public String getDeletedPlaylistMsg(){
        WebElement notificationMsg =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notificationMsg.getText();

    }


}

