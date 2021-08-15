package business.layer.pages;

import business.layer.utils.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    WebDriver driver;
    ConfigFileReader configFileReader;

    public MainPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        configFileReader= new ConfigFileReader();
    }
    public void navigateToMainPage() {
        driver.get(configFileReader.getUrl());
    }

    public void navigateToMainPageHero() {
        driver.get(configFileReader.getUrlHero());
    }
}
