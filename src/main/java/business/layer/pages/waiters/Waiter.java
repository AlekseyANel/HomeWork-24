package business.layer.pages.waiters;

import business.layer.utils.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {
    WebDriver driver;

    protected WebDriverWait wait;

    public Waiter(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    //Ожидание элемента с локатором {locator} и клик на него
    protected void waitAndClick(WebElement locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    //Ожидание элемента с локатором {locator}
    public WebElement waitVisibility(WebElement locator){
        return wait.until(ExpectedConditions.visibilityOf(locator));
    }


}
