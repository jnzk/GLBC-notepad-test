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
        np.open();
        np.openLoginPage();
        np.setLogin("testtesttest@gmail.com");
        np.setPassword("Test1234");
        np.login();
        np.openManageFoldersDialog();
        np.setFolderName("June");
        np.saveFolder();
        np.closeManageFoldersDialog();

    }

    @After
    public void closeChromeBrowser(){
        driver.quit();
    }
}
