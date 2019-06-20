import io.github.bonigarcia.wdm.WebDriverManager;
import objects.NotePad;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class NotePadChromeTests
{

    private WebDriver driver;
    NotePad np;

    @Before
    public void openChromeBrowser()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        np = new NotePad(driver);
    }

    //Testing the possibility of creating a folder for registered user.

    @Test
    public void aNotepadInChromeTest()
    {
        np.openHomePage().openLoginPage();
        np.performLogin("testtesttest@gmail.com","Test1234");
        np.openManageFoldersDialog();
        np.createFolder("June");
        np.closeManageFoldersDialog();
        np.verifyIsCreatedFolderPresent("June");
    }

    @After
    public void closeChromeBrowser(){
        driver.quit();
    }
}
