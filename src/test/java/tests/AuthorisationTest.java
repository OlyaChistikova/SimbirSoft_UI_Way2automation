package tests;

import helpers.InputData;
import helpers.OutputData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AuthorisationPage;
import pages.SuccessAuthorisationPage;

import static helpers.EndPoint.AUTHORISATION;

public class AuthorisationTest extends BaseTest{
    private WebDriver driver;
    private AuthorisationPage authorisationPage;

    @BeforeClass
    public void setUrl(){

        driver = getDriver();
        driver.get(AUTHORISATION.getUrl());
        authorisationPage = new AuthorisationPage(driver);
    }

    @Test(description = "Проверка полей ввода")
    public void checkInputFieldsTest(){
        Assert.assertTrue(authorisationPage.checkDisplayUsername());
        Assert.assertTrue(authorisationPage.checkDisplayPassword());
        Assert.assertTrue(authorisationPage.checkDisplayUsernameDescription());
        Assert.assertTrue(authorisationPage.clearFieldsAndCheckDisabledLoginButton());
    }

    @Test(description = "Проверка успешной авторизации")
    public void  checkSuccessfulAuthorizationTest(){
        authorisationPage.setAuthorisationFields(InputData.validUsernameAuthorisation, InputData.validPasswordAuthorisation, InputData.validUsernameAuthorisation);
        SuccessAuthorisationPage successAuthorisationPage = authorisationPage.successClickButtonLogin();
        Assert.assertEquals(successAuthorisationPage.getAuthResponse(), OutputData.successAuthTitle);
    }

    @DataProvider(name = "invalidDataAuth")
    public Object[][] dtmethod(){
        return new Object[][]{
                {"abc", "password", "abc"},            // username невалидный
                {"angular", "psw", "abc"}              // password невалидный
        };
    }

    @Test(description = "Проверка авторизации с невалидными данными", dataProvider = "invalidDataAuth")
    public void checkUnSuccessAuthorizationInvalidDataTest(String username, String password, String description){
        authorisationPage.setAuthorisationFields(username, password, description).clickButtonLogin();
        Assert.assertEquals(authorisationPage.getErrorMessage(), OutputData.unsuccessAuthMessage);
        authorisationPage.clearInputFields();
    }

    @Test(description = "Проверка успешного разлогирования")
    public void checkSuccessUnLoggingTest(){
        authorisationPage.setAuthorisationFields(InputData.validUsernameAuthorisation, InputData.validPasswordAuthorisation, InputData.validUsernameAuthorisation);
        SuccessAuthorisationPage successAuthorisationPage = authorisationPage.successClickButtonLogin();
        AuthorisationPage authorisationPageAfter = successAuthorisationPage.clickLogout();
        Assert.assertTrue(authorisationPageAfter.checkDisplayUsername());
        Assert.assertTrue(authorisationPageAfter.checkDisplayPassword());
        Assert.assertTrue(authorisationPageAfter.checkDisplayUsernameDescription());
    }

    @AfterMethod
    public void clearCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}