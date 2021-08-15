package business.layer.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class LogoutPage {

//условный логаут
    @FindBy (xpath = "//button[@aria-label='Открыть меню']")
    WebElement burgerButton;
/*    @FindBy (xpath = "//button[contains(text(),' Выйти из аккаунта ')]")
    WebElement logoutButton;*/



    public LogoutPage (WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void logout()  {
        burgerButton.click();
//        logoutButton.click();

    }

}
