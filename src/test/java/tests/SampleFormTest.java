package tests;

import helpers.InputData;
import helpers.OutputData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BankingHomePage;
import pages.SampleFormPage;

import static helpers.EndPoint.BANKING;

public class SampleFormTest extends BaseTest{
    private WebDriver driver;
    private BankingHomePage bankingHomePage;
    private SampleFormPage sampleFormPage;

    @BeforeClass
    public void setUrl() {
        driver = getDriver();
        driver.get(BANKING.getUrl());
        bankingHomePage = new BankingHomePage(driver);
    }

    @Test(description = "Проверка регистрации")
    public void checkRegistrationCustomerTest() {
        sampleFormPage = bankingHomePage.openSampleForm();
        Assert.assertTrue(sampleFormPage.checkVisibilityRegistrationFields());
        sampleFormPage.setValuesFields(InputData.firstNameRegistration, InputData.lastNameRegistration, InputData.emailRegistration, InputData.passwordRegistration)
                        .selectExpectedHobby(InputData.hobbyExpectedRegistration)
                        .selectGender(InputData.genderRegistration)
                        .setAboutTextArea(sampleFormPage.getLongestHobby())
                        .clickRegisterButton();
        Assert.assertTrue(sampleFormPage.checkVisibilitySuccessMessage());
        Assert.assertEquals(sampleFormPage.getMessage(), OutputData.successRegistrMessage);
    }
}
