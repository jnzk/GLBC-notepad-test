package objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class NotePad
{
    private static final By registerLoginButton = By.xpath("//*[@id=\"bs-example-navbar-collapse-1\"]/div/ul/li[4]/a/span");
    private static final By loginEmailField = By.id("loginEmail");
    private static final By loginPasswordField = By.xpath("//*[@id=\"password\"]");
    private static final By loginButton = By.xpath("//*[@id=\"submit\"]");
    private static final By mySavedNotesBlock = By.xpath("/html/body/div[2]/div/div[3]/div[2]/div[1]");
    private static final By manageFoldersButton = By.xpath("//*[@id=\"folderOption\"]/li/a");
    private static final By folderNameField = By.xpath("//*[@id=\"newFolder\"]");
    private static final By CreateNewFolderButton = By.xpath("//*[@id=\"manageFolderContent\"]/div[1]/div[2]/input");
    private static final By closeManageFoldersButton = By.xpath("//*[@id=\"manageFolderModal\"]/div/div/div[3]/button");
    private static final By newFolderSelector = By.cssSelector(".droppable");

    private WebDriver driver;
    private WebDriverWait wait;

    public NotePad (WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 5);
    }

    public NotePad openHomePage ()
        {
            driver.get("https://anotepad.com/");
            return this;
        }

    public NotePad openLoginPage ()
    {
        (wait.until(ExpectedConditions.visibilityOfElementLocated(registerLoginButton))).click();
        return this;
    }

    public NotePad performLogin (String login, String password)
    {
        driver.findElement(loginEmailField).sendKeys(login);
        driver.findElements(loginPasswordField).get(1).sendKeys(password);
        driver.findElements(loginButton).get(1).click();
        return this;
    }

    public NotePad openManageFoldersDialog()
    {
        wait.until(ExpectedConditions.textToBe(mySavedNotesBlock, "My Saved Notes"));
        driver.findElement(manageFoldersButton).click();
        return this;
    }

    public NotePad createFolder(String folderName)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(folderNameField)).sendKeys(folderName);
        driver.findElement(CreateNewFolderButton).click();
        return this;
    }

    public NotePad closeManageFoldersDialog ()
    {
        driver.findElement(closeManageFoldersButton).click();
        return this;
    }

    public NotePad verifyIsCreatedFolderPresent (String folderName)
    {
        List<WebElement> folders = driver.findElements(newFolderSelector);
        while(folders.size() > 1){
            sleep(100);
            folders = driver.findElements(newFolderSelector);
        }

        WebElement newFolder = folders.get(1);
        Assert.assertEquals(folderName, newFolder.getText().trim());

        return this;
    }

    public void sleep(long ms){
        try {
            Thread.currentThread().sleep(ms );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
