package pages;

import helpers.Waiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorisationPage{
    private WebDriver driver;

    /**
     * Локатор поля ввода логина (username)
     */
    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;

    /**
     * Локатор поля ввода пароля (password)
     */
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    /**
     * Локатор поля ввода описания пользователя (usernameDescription)
     */
    @FindBy(xpath = "//input[@ng-model='model[options.key]']")
    private WebElement usernameDescriptionField;

    /**
     * Локатор кнопки входа (Login)
     */
    @FindBy(xpath = "//button[@class='btn btn-danger']")
    private WebElement loginButton;

    /**
     * Локатор сообщения об ошибке авторизации
     */
    @FindBy(xpath = "//div[@ng-if='Auth.error']")
    public WebElement errorMessage;

    /**
     * Конструктор страницы авторизации
     *
     * @param driver WebDriver
     */
    public AuthorisationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверка отображения поля ввода логина
     *
     * @return true, если поле отображается
     */
    public boolean checkDisplayUsername() {
        Waiters.waitTimeForVisibilityOfElement(driver, usernameField);
        return usernameField.isDisplayed();
    }

    /**
     * Проверка отображения поля ввода пароля
     *
     * @return true, если поле отображается
     */
    public boolean checkDisplayPassword() {
        Waiters.waitTimeForVisibilityOfElement(driver, passwordField);
        return passwordField.isDisplayed();
    }

    /**
     * Проверка отображения поля описания пользователя
     *
     * @return true, если поле отображается
     */
    public boolean checkDisplayUsernameDescription() {
        Waiters.waitTimeForVisibilityOfElement(driver, usernameDescriptionField);
        return usernameDescriptionField.isDisplayed();
    }

    /**
     * Очистка полей ввода и проверка, что кнопка входа становится неактивной (disabled)
     *
     * @return true, если кнопка disabled после очистки полей
     */
    public boolean clearFieldsAndCheckDisabledLoginButton() {
        clearInputFields();
        Waiters.waitTimeForVisibilityOfElement(driver, loginButton);
        String disabledAttribute = loginButton.getAttribute("disabled");
        return disabledAttribute != null && disabledAttribute.equals("true");
    }

    /**
     * Установка значений в поля ввода
     *
     * @param username    логин
     * @param password    пароль
     * @param description описание пользователя
     * @return текущий объект страницы для цепочки вызовов
     */
    public AuthorisationPage setAuthorisationFields(String username, String password, String description) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        usernameDescriptionField.sendKeys(description);
        return this;
    }

    /**
     * Нажатие кнопки входа и переход на страницу успешной авторизации
     *
     * @return объект страницы SuccessAuthorisationPage
     */
    public SuccessAuthorisationPage successClickButtonLogin() {
        loginButton.click();
        return new SuccessAuthorisationPage(driver);
    }

    /**
     * Просто клик по кнопке входа без перехода
     */
    public void clickButtonLogin() {
        loginButton.click();
    }

    /**
     * Получение текста сообщения об ошибке авторизации
     *
     * @return текст сообщения
     */
    public String getErrorMessage() {
        Waiters.waitTimeForVisibilityOfElement(driver, errorMessage);
        return errorMessage.getText();
    }

    /**
     * Очистка всех полей ввода
     */
    public void clearInputFields() {
        usernameField.clear();
        passwordField.clear();
        usernameDescriptionField.clear();
    }
}