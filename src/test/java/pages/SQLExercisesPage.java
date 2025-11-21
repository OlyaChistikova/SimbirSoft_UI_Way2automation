package pages;

import helpers.JavaScriptExecutorHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static data.EndPoint.SQL;

public class SQLExercisesPage {
    private WebDriver driver;
    JavaScriptExecutorHelper jsHelper;
    private static final String URL = SQL.getUrl();

    /**
     * Локатор поля ввода логина
     */
    @FindBy(xpath = "//input[@name='login' and @type='text']")
    private WebElement loginField;

    /**
     * Локатор поля ввода пароля
     */
    @FindBy(xpath = "//input[@name='psw' and @type='password']")
    private WebElement passwordField;

    /**
     * Локатор кнопки входа
     */
    @FindBy(xpath = "//input[@value='Вход']")
    private WebElement submitButton;

    /**
     * Локатор кнопки выхода (logout)
     */
    @FindBy(xpath = "//a[@href='/logout.php']")
    private WebElement logoutButton;

    /**
     * Конструктор страницы SQL упражнений
     *
     * @param driver WebDriver
     */
    public SQLExercisesPage(WebDriver driver) {
        this.driver = driver;
        driver.get(URL);
        PageFactory.initElements(driver, this);
        jsHelper = new JavaScriptExecutorHelper(driver);
    }

    /**
     * Установка значения в поле логина
     *
     * @param login значение логина
     * @return текущий объект страницы для цепочки вызовов
     */
    @Step("Задаем логин в поле логина")
    public SQLExercisesPage setLoginField(String login) {
        loginField.sendKeys(login);
        return this;
    }

    /**
     * Установка значения в поле пароля
     *
     * @param password значение пароля
     * @return текущий объект страницы для цепочки вызовов
     */
    @Step("Задаем пароль в поле пароля")
    public SQLExercisesPage setPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    /**
     * Нажатие на кнопку входа
     */
    @Step("Кликаем по кнопке входа")
    public void clickSubmitButton() {
        submitButton.click();
    }

    /**
     * Проверка отображения элемента выхода (иконки logout)
     *
     * @return true, если элемент отображается
     */
    @Step("Проверяем отображение кнопки выхода (logout)")
    public boolean checkVisibilityLogoutImg() {
        return logoutButton.isDisplayed();
    }

    /**
     * Клик по полю логина (для фокусировки)
     */
    @Step("Кликаем по полю логина")
    public void clickLoginField() {
        loginField.click();
    }

    /**
     * Сброс фокуса с поля логина с помощью JavaScript
     */
    @Step("Сбрасываем фокус с поля логина с помощью JavaScript")
    public void resetFocusFromField() {
        jsHelper.resetFocusFromField(driver, loginField);
    }

    /**
     * Проверка, что фокус сброшен с поля логина
     *
     * @return true, если фокус сброшен
     */
    @Step("Проверяем, что фокус сброшен с поля логина")
    public boolean checkResetFocus() {
        return jsHelper.checkResetFocusFromField(driver, loginField);
    }

    /**
     * Скролл страницы до нижней части
     */
    @Step("Прокручиваем страницу до нижней части")
    public void scrollToButton() {
        jsHelper.scrollToBottom();
    }

    /**
     * Проверка вертикальной прокрутки страницы
     *
     * @return true, если страница прокручена
     */
    @Step("Проверяем вертикальную прокрутку страницы")
    public boolean checkVerticalScroll() {
        return jsHelper.checkVerticalScroll(driver);
    }
}