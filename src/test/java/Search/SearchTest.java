package Search;

import business.layer.pages.MainPage;
import business.layer.pages.waiters.Waiter;
import business.layer.utils.ConfigFileReader;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.Keys.ENTER;

public class SearchTest extends Assert {
    WebDriver driver;
    ConfigFileReader configFileReader;
    MainPage mainPage;
    Waiter waiter;

    @DataProvider (name = "searchData"/*, parallel = true*/)
    public Object[][] browserData() {
        return new Object[][]{
                {"ноутбук", 3000},
                {"laptop", 300},
                {"мышь", 1200},
                {"iPhone", 2000},
        };
    }

    @BeforeMethod
    public void startUp() {
        configFileReader = new ConfigFileReader();
        //Initializing Driver
        System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
        mainPage = new MainPage(driver);
        mainPage.navigateToMainPage();
    }

    @AfterMethod
    public void tearDown() {
         driver.quit();
        }

    //Test controls time for search process of item on the site
    //compare actual load-time to expected
    @Test (dataProvider = "searchData")
    public void testBrowser(String searchWord, int t) {

        var search = driver.findElement(By.cssSelector("input[name='search']"));
        search.sendKeys(searchWord, ENTER);
        long start = System.currentTimeMillis();
        var element = driver.findElement(By
                .xpath("/html/body/app-root/div/div/rz-category/div/main/rz-catalog/div/div/section/rz-grid/ul/li[1]"));
        long finish = System.currentTimeMillis();
        System.out.println("Search time for '" +searchWord+ "' load = " + (finish-start)+ "ms");
        Assert.assertFalse("Time to find items '" +searchWord+ "' > than expected "+t+"ms",
                (finish-start) > t);
    }


}
