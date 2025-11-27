package pages;

import data.EndPoint;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertsExercisesPage {
    private WebDriver driver;
    private static String URL = EndPoint.ALERTS.getUrl();

    /**
     * Локатор ссылки "Input Alert"
     */
    @FindBy(xpath = "//a[text()='Input Alert']")
    private WebElement inputAlertLink;

    /**
     * Локатор iframe
     */
    @FindBy(xpath = "//iframe[@src='alert/input-alert.html']")
    private WebElement iFrame;

    /**
     * Локатор кнопки, вызывающей alert
     */
    @FindBy(xpath = "//button[@onclick='myFunction()']")
    private WebElement button;

    /**
     * Локатор элемента, отображающего ответ
     */
    @FindBy(xpath = "//p[@id='demo']")
    private WebElement responseText;

    /**
     * Объект страницы Alert
     */
    public AlertsExercisesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get(URL);
    }

    /**
     * Клик по ссылке "Input Alert"
     */
    @Step("Клик по ссылке 'Input Alert'")
    public void clickInputAlertLink(){
        inputAlertLink.click();
    }

    /**
     * Переключение в iframe, содержащий ссылку
     */
    @Step("Переключиться в iframe с ссылкой")
    public void switchToFrame() {
        driver.switchTo().frame(this.iFrame);
    }

    /**
     * Клик по кнопке, вызывающей alert, и возвращение объекта страницы alert
     * @return объект AllertPage для дальнейших действий с alert
     */
    @Step("Клик по кнопке для вызова alert")
    public AllertPage clickButton(){
        button.click();
        return new AllertPage(driver);
    }

    /**
     * Получение текста ответа
     * @return текст ответа
     */
    @Step("Получить текст ответа")
    public String getResponseText(){
        return responseText.getText();
    }
}
