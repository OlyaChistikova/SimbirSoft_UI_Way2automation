package helpers;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class JavaScriptExecutorHelper {
    private WebDriver driver;

    /**
     * Конструктор.
     * @param driver WebDriver, с которым работает скрипт.
     */
    public JavaScriptExecutorHelper(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Скролл страницы до нижней части.
     */
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Скролл страницы до верхней части.
     */
    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Скролл страницы с помощью клавиатуры.
     */
    public void scrollByKeyboard() {
        ((JavascriptExecutor) driver).executeScript("var event = new KeyboardEvent('keydown', {'key':'ArrowDown'}); document.dispatchEvent(event);");
    }

    /**
     * Функция создания скриншота всей страницы
     * @param driver = объект вебдрайвера
     * */
    @Step("Делаем скриншот страницы")
    public static void takeScreenshot(WebDriver driver) {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screenshot.getImage(), "PNG", baos);
            byte[] imageBytes = baos.toByteArray();
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(imageBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция сброса фокуса из поля ввода
     * @param driver = объект вебдрайвера
     * @param inputField = элемент поля ввода
     * */
    @Step("Сброс фокуса из поля ввода")
    public void resetFocusFromField(WebDriver driver, WebElement inputField){
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", inputField);
    }

    /**
     * Функция проверки отсутствия фокуса в поле
     * @param driver = объект вебдрайвера
     * @param inputField = элемент поля ввода
     * */
    @Step("Проверка сброса фокуса из поля ввода")
    public boolean checkResetFocusFromField(WebDriver driver, WebElement inputField){
        Boolean isFocused = (Boolean) ((JavascriptExecutor) driver).executeScript(
                "return document.activeElement === arguments[0];", inputField
        );
        return isFocused != null && isFocused;
    }

    /**
     * Функция проверки наличия вертикального скрола на странице
     * @param driver = объект вебдрайвера
     * @return возвращает булевое значение
     * */
    @Step("Проверка наличия вертикального скрола")
    public boolean checkVerticalScroll(WebDriver driver) {
        Boolean hasVerticalScroll = (Boolean) ((JavascriptExecutor) driver).executeScript(
                "return document.documentElement.scrollHeight > document.documentElement.clientHeight;"
        );
        return hasVerticalScroll != null && hasVerticalScroll;
    }
}
