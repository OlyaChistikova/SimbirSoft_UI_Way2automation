package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters {

    /**
     * Ожидает, пока элемент станет видимым.
     */
    public static void waitTimeForVisibilityOfElement(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Ожидает, пока URL станет равен заданному.
     */
    public static void waitTimeForCheckUrl(WebDriver driver, String endPoint){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.urlToBe(endPoint));
    }

    /**
     * Ожидает, пока элемент станет невидимым.
     */
    public static void waitTimeForElementToBeInvisible(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Ожидает, пока элемент станет кликабельным.
     */
    public static void waitTimeForClickableElement(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Установка паузы
     */
    public static void setPause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
