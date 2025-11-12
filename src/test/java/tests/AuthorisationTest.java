package tests;

import data.InputData;
import data.OutputData;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AuthorisationPage;
import pages.SuccessAuthorisationPage;

public class AuthorisationTest extends BaseTest{
    private WebDriver driver;
    private AuthorisationPage authorisationPage;

    @Override
    protected WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        return new ChromeDriver(options);
    }

    @BeforeClass(description = "Открываем страницу авторизации")
    public void setUrl(){
        useIncognito = true;
        driver = getDriver();
        authorisationPage = new AuthorisationPage(driver);
    }

    @Test(description = "Проверка полей ввода")
    @Epic("Авторизация пользователя")
    @Feature("Поля ввода")
    @Story("Проверка отображения и доступности полей")
    @Severity(SeverityLevel.CRITICAL)
    public void checkInputFieldsTest(){
        Assert.assertTrue(authorisationPage.checkDisplayUsername());
        Assert.assertTrue(authorisationPage.checkDisplayPassword());
        Assert.assertTrue(authorisationPage.checkDisplayUsernameDescription());
        Assert.assertTrue(authorisationPage.checkDisabledLoginButton());
    }

    @Test(description = "Проверка успешной авторизации")
    @Epic("Авторизация пользователя")
    @Feature("Вход в систему")
    @Story("Успешный вход с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    public void  checkSuccessfulAuthorizationTest(){
        authorisationPage.setAuthorisationFields(InputData.validUsernameAuthorisation, InputData.validPasswordAuthorisation, InputData.validUsernameAuthorisation);
        SuccessAuthorisationPage successAuthorisationPage = authorisationPage.successClickButtonLogin();
        Assert.assertEquals(successAuthorisationPage.getAuthResponse(), OutputData.successAuthTitle);
        successAuthorisationPage.clickLogout();
    }

    @DataProvider(name = "invalidDataAuth")
    public Object[][] dtmethod(){
        return new Object[][]{
                {"abc", "password", "abc"},            // username невалидный
                {"angular", "psw", "abc"}              // password невалидный
        };
    }

    @Test(description = "Проверка авторизации с невалидными данными", dataProvider = "invalidDataAuth")
    @Epic("Авторизация пользователя")
    @Feature("Обработка ошибок")
    @Story("Авторизация с недопустимыми данными")
    @Severity(SeverityLevel.MINOR)
    public void checkUnSuccessAuthorizationInvalidDataTest(String username, String password, String description){
        authorisationPage.setAuthorisationFields(username, password, description).clickButtonLogin();
        Assert.assertEquals(authorisationPage.getErrorMessage(), OutputData.unsuccessAuthMessage);
        Assert.assertTrue(authorisationPage.clearInputFields().checkDisabledLoginButton());
    }

    @Test(description = "Проверка успешного разлогирования")
    @Epic("Авторизация пользователя")
    @Feature("Выход из системы")
    @Story("Успешный выход")
    @Severity(SeverityLevel.NORMAL)
    public void checkSuccessUnLoggingTest(){
        authorisationPage.setAuthorisationFields(InputData.validUsernameAuthorisation, InputData.validPasswordAuthorisation, InputData.validUsernameAuthorisation);
        SuccessAuthorisationPage successAuthorisationPage = authorisationPage.successClickButtonLogin();
        AuthorisationPage authorisationPageAfter = successAuthorisationPage.clickLogout();
        Assert.assertTrue(authorisationPageAfter.checkDisplayUsername());
        Assert.assertTrue(authorisationPageAfter.checkDisplayPassword());
        Assert.assertTrue(authorisationPageAfter.checkDisplayUsernameDescription());
    }

    @DataProvider(name = "loginUsersData")
    public Object[][] loginUsersData() {
        return new Object[][]{
                {"angular", "password", "description", true},
                {"user1", "password1", "description1", false},
                {"", "password", "description", false},
                {"user", "", "description", false},
                {"user", "password", "", false},
                {"", "", "description", false},
                {"user", "", "", false},
                {"", "password", "", false},
                {"", "", "", false},
        };
    }

    @Test(description = "Тест авторизации различных данных ввода", dataProvider = "loginUsersData")
    @Epic("Авторизация пользователя")
    @Feature("Обработка ошибок")
    @Story("Авторизация с различными данными ввода")
    @Severity(SeverityLevel.CRITICAL)
    public void paramAuthorizationTest(String username, String password, String description, boolean isSuccessful){
        authorisationPage.setAuthorisationFields(username, password, description);
        if (isSuccessful) {
            SuccessAuthorisationPage successAuthorisationPage = authorisationPage.successClickButtonLogin();
            successAuthorisationPage.clickLogout();
        } else if (!username.isEmpty() && !password.isEmpty() && !description.isEmpty()) {
            authorisationPage.clickButtonLogin();
            Assert.assertEquals(authorisationPage.getErrorMessage(), OutputData.unsuccessAuthMessage);
            Assert.assertTrue(authorisationPage.clearInputFields().checkDisabledLoginButton());
        }
        else {
            Assert.assertTrue(authorisationPage.checkDisabledLoginButton());
            authorisationPage.clearInputFields();
        }
    }
}