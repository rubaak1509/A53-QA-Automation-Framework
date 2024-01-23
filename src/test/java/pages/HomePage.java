package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class HomePage extends BasePage {
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    private By firstPlaylist = By.cssSelector(".playlist:nth-child(6)");
    private By playlistNameField = By.cssSelector("[name='name']");
    private By renamePlaylistSuccessMsg = By.cssSelector("div.success.show");

    public void doubleClickPlaylist() {
        doubleClick(firstPlaylist);
    }

    public void enterPlaylistName(String playlistNAme) {
        findElement(playlistNameField).sendKeys(Keys.chord(Keys.COMMAND,"A", Keys.BACK_SPACE));
        findElement(playlistNameField).sendKeys(playlistNAme);
        findElement(playlistNameField).sendKeys(Keys.ENTER);
    }

    public String getRenamePlaylistSuccessMsg() {
        return findElement(renamePlaylistSuccessMsg).getText();
    }
}
