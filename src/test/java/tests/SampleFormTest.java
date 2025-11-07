package tests;

import data.InputData;
import data.OutputData;
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

    @BeforeClass
    public void setUrl() {
        driver = getDriver();
        bankingHomePage = new BankingHomePage(driver);
        bankingHomePage.openPage();
    }

    @Test(description = "Проверка регистрации")
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
}
