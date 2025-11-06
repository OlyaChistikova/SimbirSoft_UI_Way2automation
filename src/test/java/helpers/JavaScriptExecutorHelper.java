package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

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
     * Выполнение произвольного JavaScript-кода.
     * @param script JavaScript-код.
     */
    public void executeScript(String script) {
        ((JavascriptExecutor) driver).executeScript(script);
    }
}
