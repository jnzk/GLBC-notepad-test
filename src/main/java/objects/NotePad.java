package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    private WebDriver driver;
    private WebDriverWait wait;

    public NotePad (WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 5);
    }

    public NotePad open ()
        {
            driver.get("https://anotepad.com/");
            return this;
        }

    public void openLoginPage ()
    {
        (wait.until(ExpectedConditions.visibilityOfElementLocated(registerLoginButton))).click();
    }

    public void setLogin (String login)
    {
        driver.findElement(loginEmailField).sendKeys(login);
    }

    public void setPassword (String password)
    {
        driver.findElements(loginPasswordField).get(1).sendKeys(password);
    }

    public void login()
    {
        driver.findElements(loginButton).get(1).click();
    }

    public void openManageFoldersDialog()
    {
        wait.until(ExpectedConditions.textToBe(mySavedNotesBlock, "My Saved Notes"));
        driver.findElement(manageFoldersButton).click();
    }

    public void setFolderName(String folderName)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(folderNameField)).sendKeys(folderName);
    }

    public void saveFolder ()
    {
        driver.findElement(CreateNewFolderButton).click();
    }

    public void closeManageFoldersDialog ()
    {
        driver.findElement(closeManageFoldersButton).click();
    }
}
