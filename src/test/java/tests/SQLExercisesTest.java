package tests;

import data.InputData;
import helpers.CookieUtils;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SQLExercisesPage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SQLExercisesTest extends BaseTest{
    private WebDriver driver;
    private SQLExercisesPage sqlExercisesPage;

    @BeforeMethod(description = "Открываем страницу Banking App")
    public void setUrl() {
        driver = getDriver();
        sqlExercisesPage = new SQLExercisesPage(driver);
    }

    @Test(description = "Проверка авторизации и сохранения cookie")
    @Epic("Авторизация пользователя")
    @Feature("Работа с cookie")
    @Story("Сохранение cookie авторизированного пользователя")
    @Severity(SeverityLevel.CRITICAL)
    public void authorisationAndSaveCookiesTest()  {
        sqlExercisesPage.setLoginField(InputData.loginSQLAuthorisation)
                .setPasswordField(InputData.passwordRegistration)
                .clickSubmitButton();
        Assert.assertTrue(sqlExercisesPage.checkVisibilityLogoutImg());
        CookieUtils.saveCookiesToFile(driver, InputData.getCookieFilePath());

        Path path = Paths.get(InputData.getCookieFilePath());
        Assert.assertTrue(Files.exists(path), "Cookie file was not saved successfully.");
    }

    @Test(description = "Проверка авторизации с использованием сохраненных cookie")
    @Epic("Авторизация пользователя")
    @Feature("Работа с cookie")
    @Story("Авторизация с использованием сохраненных cookies пользователя")
    @Severity(SeverityLevel.CRITICAL)
    public void authorisationBySavedCookiesTest() {
        driver.manage().deleteAllCookies();
        CookieUtils.loadCookiesFromFile(driver, InputData.getCookieFilePath());
        driver.navigate().refresh();
        Assert.assertTrue(sqlExercisesPage.checkVisibilityLogoutImg());
    }


    @Test(description = "Проверка сброса фокуса из поля ввода")
    @Epic("Авторизация пользователя")
    @Feature("Работа с окном авторизации")
    @Story("Сброс фокуса из поля ввода")
    @Severity(SeverityLevel.NORMAL)
    public void resetFocusFromInputFieldTest() {
        sqlExercisesPage.clickLoginField();
        sqlExercisesPage.resetFocusFromField();
        Assert.assertFalse(sqlExercisesPage.checkResetFocus(), "Input field is in focus");
    }

    @Test(description = "Проверка сброса фокуса из поля ввода")
    @Epic("Авторизация пользователя")
    @Feature("Работа с окном авторизации")
    @Story("Наличие вертикального скролла")
    @Severity(SeverityLevel.NORMAL)
    public void checkVerticalScrollTest(){
        sqlExercisesPage.scrollToButton();
        Assert.assertTrue(sqlExercisesPage.checkVerticalScroll());
    }
}
