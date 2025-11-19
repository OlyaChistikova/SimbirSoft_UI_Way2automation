package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static data.EndPoint.SQL;

public class SQLExercisesPage {
    private WebDriver driver;
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
    public SQLExercisesPage setPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    /**
     * Нажатие на кнопку входа
     */
    public void clickSubmitButton() {
        submitButton.click();
    }

    /**
     * Проверка отображения элемента выхода (иконки logout)
     *
     * @return true, если элемент отображается
     */
    public boolean checkVisibilityLogoutImg() {
        return logoutButton.isDisplayed();
    }
}
