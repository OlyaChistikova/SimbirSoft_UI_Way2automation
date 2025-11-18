package tests;

import data.InputData;
import data.OutputData;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BankingHomePage;
import pages.SampleFormPage;

public class SampleFormTest extends BaseTest{
    private WebDriver driver;
    private BankingHomePage bankingHomePage;
    private SampleFormPage sampleFormPage;

    @BeforeClass(description = "Открываем страницу Banking App")
    public void setUrl() {
        driver = getDriver();
        bankingHomePage = new BankingHomePage(driver);
    }

    @Test(description = "Проверка корректной регистрации")
    @Epic("Регистрация пользователя")
    @Feature("Форма регистрации")
    @Story("Успешная регистрация нового пользователя")
    @Severity(SeverityLevel.CRITICAL)
    public void checkRegistrationCustomerTest() {
        sampleFormPage = bankingHomePage.openSampleForm();
        Assert.assertTrue(sampleFormPage.checkVisibilityRegistrationFields());
        sampleFormPage.setFirstName(InputData.firstNameRegistration)
                        .setLastName(InputData.lastNameRegistration)
                        .setEmail(InputData.emailRegistration)
                        .setPassword(InputData.passwordRegistration)
                        .selectExpectedHobby(InputData.hobbyExpectedRegistration)
                        .selectGender(InputData.genderRegistration)
                        .setAboutTextArea(sampleFormPage.getLongestHobby())
                        .clickRegisterButton();
        Assert.assertTrue(sampleFormPage.checkVisibilitySuccessMessage());
        Assert.assertEquals(sampleFormPage.getMessage(), OutputData.successRegistrMessage);
    }

    @Test(description = "Проверка некорректной регистрации без заполнения полей")
    @Epic("Регистрация пользователя")
    @Feature("Форма регистрации")
    @Story("Регистрация нового пользователя без заполнения полей")
    @Severity(SeverityLevel.MINOR)
    public void checkUnSuccessRegistrationCustomerTest() {
        sampleFormPage = bankingHomePage.openSampleForm();
        Assert.assertTrue(sampleFormPage.checkVisibilityRegistrationFields());
        sampleFormPage.clickRegisterButton();
        Assert.assertEquals(sampleFormPage.getMessage(), OutputData.successRegistrMessage);
    }
}
