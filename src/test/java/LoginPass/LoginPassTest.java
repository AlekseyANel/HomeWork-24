package LoginPass;

import business.layer.pages.MainPage;
import business.layer.utils.ConfigFileReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static org.openqa.selenium.Keys.ENTER;

public class LoginPassTest {
    WebDriver driver;
    ConfigFileReader configFileReader;
    MainPage mainPage;
    public String rightUserName;
    public String rightPassword;
    SoftAssert asert = new SoftAssert();

    @DataProvider(name = "loginData")
    public static Object[][] dataSet() {
        Object[][] dataset = new Object[4][2];
        //right data
        dataset[0][0] = "tomsmith";
        dataset[0][1] = "SuperSecretPassword!";
        //right user, wrong pass
        dataset[1][0] = "tomsmith";
        dataset[1][1] = "pass1";
        //wrong user, right pass
        dataset[2][0] = "user1";
        dataset[2][1] = "SuperSecretPassword!";
        //wrong user and pass//right data for Test2
        dataset[3][0] = "admin";
        dataset[3][1] = "admin";

        return dataset;
    }

    @BeforeClass
    public void startUp() {
        configFileReader = new ConfigFileReader();
        //Initializing Driver
        System.setProperty("webDriver.chrome.driver", configFileReader.getDriverPath());

        driver = new ChromeDriver();
        //Opening search engine
        mainPage = new MainPage(driver);
        PageFactory.initElements(driver, MainPage.class);
        mainPage.navigateToMainPageHero();
        driver.findElement(By.linkText("Form Authentication")).click();

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    //Test01
    @Test(dataProvider = "loginData")
    public void loginByDataProvider(String username, String password) throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password, ENTER);
        System.out.println("You are trying to push '" + username + "' with '" + password + "'");
        Thread.sleep(2000);
        //Проверить, если крэдэншиалс подошли (тест - пасс), то надо Логаут сделать.
        //Если нет (тест - фейлд), то продолжать вводить данные
        if (driver.findElements(By.xpath("//*[@class='flash error']")).size() == 0) {
            driver.findElement(By.xpath("//i[text()=' Logout']/..")).click();
            System.out.println("Credentials: username '" + username + "' and  password '" + password + "' are right");
            rightUserName = username;
            rightPassword = password;
            System.out.println("Credentials are right: " + rightUserName + " and " + rightPassword);
            Assert.assertTrue("Credentials are right", true);
        } else {
            System.out.println("Failed credentials work as expected");
            Assert.assertTrue("Failed credentials work as expected", false);
        }
        asert.assertAll();
    }
/*    @Test(dataProvider = "loginData")
    public void checkRightCredentials(String username, String password)  {}*/
}
