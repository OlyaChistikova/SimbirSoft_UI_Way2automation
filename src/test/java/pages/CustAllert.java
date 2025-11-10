package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс для управления алертами в веб-приложении.
 */
public class CustAllert {
    private WebDriver driver;

    public CustAllert(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Принимает (закрывает) всплывающее сообщение (алерт).
     * Перед закрытием алерта ожидает его появления.
     */
    @Step("Принимаем (закрываем) всплывающее окно")
    public void accept() {
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    /**
     * Возвращает текст алерта.
     *
     * @return текст алерта.
     */
    @Step("Проверяем текст окна подтверждения операции")
    public String getAlertText() {
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    /**
     * Ожидает появления алерта.
     *
     * @return allert
     * @throws NoAlertPresentException если алерт отсутствует
     */
    public void waitForAlert() {
        try {
            driver.switchTo().alert();
        } catch (NoAlertPresentException e) {
            throw new NoAlertPresentException("No allert present");
        }
    }
}
