package pages;

import data.EndPoint;
import helpers.Waiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SampleFormPage {
    private WebDriver driver;

    /**
     * Локатор формы для регистрации
     */
    @FindBy(xpath = "//form[@onsubmit='registerUser(event)']")
    private WebElement registrationFields;

    /**
     * Локатор поля firstName
     */
    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstnameField;

    /**
     * Локатор поля lastName
     */
    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastNameField;

    /**
     * Локатор поля email
     */
    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    /**
     * Локатор поля password
     */
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    /**
     * Локатор списка хобби
     */
    @FindBy(xpath = "//input[@name='hobbies']")
    private List<WebElement> hobbiesList;

    /**
     * Локатор списка гендеров
     */
    @FindBy(xpath = "//select[@id='gender']")
    private WebElement genderList;

    /**
     * Локатор поля "О себе"
     */
    @FindBy(xpath = "//textarea[@id='about']")
    private WebElement aboutTextArea;

    /**
     * Локатор кнопки регистрации
     */
    @FindBy(xpath = "//button[text()='Register']")
    private WebElement registerButton;

    /**
     * Локатор сообщения
     */
    @FindBy(xpath = "//div[@id='successMessage']")
    private WebElement message;

    /**
     * Объект страницы Sample Form
     */
    public SampleFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверяет видимость формы регистрации и ожидает соответствующего URL
     * @return true, если форма отображается, иначе false
     */
    public boolean checkVisibilityRegistrationFields() {
        Waiters.waitTimeForVisibilityOfElement(driver, registrationFields);
        Waiters.waitTimeForCheckUrl(driver, EndPoint.REGISTRATION.getUrl());
        return registrationFields.isDisplayed();
    }

    /**
     * Заполняет поле имени
     * @param firstName Имя
     * @return текущая страница
     */
    public SampleFormPage setFirstName(String firstName) {
        firstnameField.sendKeys(firstName);
        return this;
    }

    /**
     * Заполняет поле фамилии
     * @param lastName Фамилия
     * @return текущая страница
     */
    public SampleFormPage setLastName(String lastName) {
        lastNameField.sendKeys(lastName);
        return this;
    }

    /**
     * Заполняет поле электронной почты
     * @param email Электронная почта
     * @return текущая страница
     */
    public SampleFormPage setEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    /**
     * Заполняет поле пароля
     * @param password Пароль
     * @return текущая страница
     */
    public SampleFormPage setPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    /**
     * Выбирает указанное хобби из списка чекбоксов
     * @param hobby Название хобби, которое нужно выбрать
     * @return текущий объект страницы
     */
    public SampleFormPage selectExpectedHobby(String hobby) {
        for (WebElement item : hobbiesList) {
            String value = item.getAttribute("value");
            if (hobby.equals(value) && !item.isSelected()) {
                item.click();
            }
        }
        return this;
    }

    /**
     * Выбирает пол из выпадающего списка
     * @param gender Пол, который нужно выбрать
     * @return текущий объект страницы
     */
    public SampleFormPage selectGender(String gender) {
        Select select = new Select(genderList);
        select.selectByVisibleText(gender);
        return this;
    }

    /**
     * Находит самое длинное хобби из списка и возвращает его название
     * @return самое длинное хобби
     */
    public String getLongestHobby() {
        String longestHobby = "";
        for (WebElement item : hobbiesList) {
            String hobby = item.getAttribute("value");
            assert hobby != null;
            if (hobby.length() > longestHobby.length()) {
                longestHobby = hobby;
            }
        }
        return longestHobby;
    }

    /**
     * Заполняет текстовую область "О себе" информацией о самом длинном хобби
     * @param longestHobby Название самого длинного хобби
     * @return текущий объект страницы
     */
    public SampleFormPage setAboutTextArea(String longestHobby) {
        aboutTextArea.sendKeys("Самое длинное слово из предложенных хобби - " + longestHobby);
        return this;
    }

    /**
     * Нажимает кнопку регистрации
     * @return текущий объект страницы
     */
    public SampleFormPage clickRegisterButton() {
        registerButton.click();
        return this;
    }

    /**
     * Проверяет отображение сообщения об успешной регистрации
     * @return true, если сообщение видно
     */
    public boolean checkVisibilitySuccessMessage() {
        Waiters.waitTimeForVisibilityOfElement(driver, message);
        return message.isDisplayed();
    }

    /**
     * Получает текст сообщения об успехе
     * @return текст сообщения
     */
    public String getMessage() {
        return message.getText();
    }
}
