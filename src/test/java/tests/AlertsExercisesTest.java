package tests;

import data.InputData;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AlertsExercisesPage;
import pages.AllertPage;

public class AlertsExercisesTest extends BaseTest{
    private WebDriver driver;
    AlertsExercisesPage alertsExercisesPage;

    @BeforeMethod(description = "Открываем страницу Alert")
    public void setUrl() {
        driver = getDriver();
        alertsExercisesPage = new AlertsExercisesPage(driver);
    }

    @Test(description = "Проверка alert с вводом текста и подтверждением")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Работа с алертами")
    @Feature("Обработка alert окон")
    @Story("Проверка alert с вводом текста и ответом")
    public void checkAlertTest() {
        alertsExercisesPage.clickInputAlertLink();
        alertsExercisesPage.switchToFrame();

        AllertPage alertPage = alertsExercisesPage.clickButton();
        alertPage.setAlertText(InputData.nameAlert);

        Assert.assertTrue(alertsExercisesPage.getResponseText().contains(InputData.nameAlert));
    }
}