package business.layer.pages;

import business.layer.pages.waiters.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
    @FindBy(css = "header > div > div > ul > li.header-actions__item.header-actions__item--user")
    WebElement headerAccountButton;

    @FindBy(xpath = "//input[@id='auth_email']")
    WebElement loginInputField;

    @FindBy(xpath = "//input[@formcontrolname='password']")
    WebElement passwordInputField;

    @FindBy(xpath = "//button[contains(text(),' Войти ')]")
    WebElement loginButton;

    @FindBy(css = "button[aria-label='Закрыть модальное окно']")
    WebElement closeButton;

    Waiter waiter;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void login(String login, String password)  {
        headerAccountButton.click();
        loginInputField.sendKeys(login);
        passwordInputField.sendKeys(password);
        loginButton.click();
        Assert.assertTrue(closeButton.isDisplayed());
        closeButton.click();
    }

}
