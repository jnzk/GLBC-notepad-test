package objects;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class NotePad
{
    private static final By registerLoginButton = By.cssSelector("a[href='/create_account'] > span");
    private static final By loginEmailField = By.id("loginEmail");
    private static final By loginPasswordField = By.cssSelector("input[placeholder='Enter Password']");
    private static final By loginButton = By.xpath("//button[contains(text(), 'Login')]");
    private static final By manageFoldersButton = By.xpath("//a[contains(text(), 'Manage Folders')]");
    private static final By folderNameField = By.id("newFolder");
    private static final By CreateNewFolderButton = By.cssSelector("input[value='Create New']");
    private static final By closeManageFoldersButton = By.cssSelector("#manageFolderModal > div > div > div.modal-footer > button");

    private WebDriver driver;
    private WebDriverWait wait;


    public NotePad (WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 5);
    }

    @Step
    public NotePad openHomePage ()
        {
            driver.get("https://anotepad.com/");
            return this;
        }

    @Step
    public NotePad openLoginPage ()
    {
        (wait.until(ExpectedConditions.visibilityOfElementLocated(registerLoginButton))).click();
        return this;
    }

    @Step
    public NotePad performLogin (String login, String password)
    {
        driver.findElement(loginEmailField).sendKeys(login);
        driver.findElement(loginPasswordField).sendKeys(password);
        driver.findElement(loginButton).click();
        return this;
    }

    @Step
    public NotePad openManageFoldersDialog()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(manageFoldersButton)).click();
        return this;
    }

    @Step
    public NotePad createFolder(String folderName)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(folderNameField)).sendKeys(folderName);
        driver.findElement(CreateNewFolderButton).click();
        return this;
    }

    @Step
    public NotePad closeManageFoldersDialog ()
    {
        driver.findElement(closeManageFoldersButton).click();
        return this;
    }

    @Step
    public NotePad verifyIsCreatedFolderPresent (String folderName)
    {
        By newFolderSelector = By.xpath("//a[contains(text(), '" + folderName + "')]");
        WebElement newFolder = wait.until(ExpectedConditions.visibilityOfElementLocated(newFolderSelector));

        Assert.assertEquals(folderName, newFolder.getText().trim());
        return this;
    }

}
