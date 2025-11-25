package pages;

import data.EndPoint;
import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class TabsExercisesPage {
    private WebDriver driver;
    private static String URL = EndPoint.TABS.getUrl();

    @FindBy(xpath = "//iframe[@src='frames-windows/defult1.html']")
    private WebElement iFrame;

    @FindBy(xpath = "//a[text()='New Browser Tab']")
    private WebElement link;

    @FindBy(xpath = "//h1[text()='Frames And Windows ']")
    private WebElement titleWindow;

    /**
     * Объект страницы Frames And Windows
     */
    public TabsExercisesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get(URL);
    }

    /**
     * Переключение в iframe, содержащий ссылку
     */
    @Step("Переключиться в iframe с ссылкой")
    public void switchToFrame() {
        driver.switchTo().frame(this.iFrame);
    }

    /**
     * Переход по ссылке в другой вкладке
     */
    @Step("Переключиться по ссылке на другую вкладку")
    public void switchToTab() {
        Waiters.waitTimeForVisibilityOfElement(driver, link);
        Waiters.waitTimeForClickableElement(driver, link);
        link.click();
    }

    /**
     * Получение дескриптора вкладки браузера номера n
     */
    @Step("Получение дескриптора вкладки №")
    public String getBrowserTabByNumber(Iterator<String> iterator, int tabIndex){
        for (int i = 1; i < tabIndex; i++) {
            iterator.next();
        }
        return iterator.next();
    }

    /**
     * Получение номера текущей вкладки
     */
    @Step("Получение номера текущей вкладки")
    public int getTabIndexByHandle(Set<String> handles) {
        String currentHandle = driver.getWindowHandle();
        int index = 0;
        for (String handle : handles) {
            index++;
            if (handle.equals(currentHandle)) {
                return index;
            }
        }
        throw new NoSuchElementException("Дескриптор вкладки не найден: " + currentHandle);
    }
}
