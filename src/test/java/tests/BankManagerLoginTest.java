package tests;

import helpers.InputData;
import helpers.OutputData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BankManagerLoginPage;
import pages.BankingHomePage;
import pages.CustAllert;

import static helpers.EndPoint.BANKING;

public class BankManagerLoginTest extends BaseTest{
    private WebDriver driver;
    private BankingHomePage bankingHomePage;
    private BankManagerLoginPage bankManagerLoginPage;

    @BeforeMethod
    public void setUrl() {
        driver = getDriver();
        driver.get(BANKING.getUrl());
        bankingHomePage = new BankingHomePage(driver);
    }

    @Test(description = "Проверка добавления покупателя", priority = 1)
    public void addCustomerTest() {
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin();
        Assert.assertTrue(bankManagerLoginPage.openAddCustomerCatalog());
        CustAllert addCustAllert = bankManagerLoginPage.setAddCustomerFields(InputData.firstNameCustomer, InputData.lastNameCustomer, InputData.postCodeCustomer)
                .clickButtonAddCust();
        Assert.assertTrue(addCustAllert.getAlertText().contains(OutputData.addCutAllertMessage), "Alert text does not match expected value");
        addCustAllert.accept();
    }

    @Test(description = "Проверка открытия аккаунта", priority = 2)
    public void openCustomerTest() {
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin();
        Assert.assertTrue(bankManagerLoginPage.openCatalogOpenAccount());
        CustAllert openCustAllert = bankManagerLoginPage.setOpenAccountFields(InputData.firstNameCustomer + " " + InputData.lastNameCustomer, InputData.currencyCustomer)
                .clickProcessButton();
        Assert.assertTrue(openCustAllert.getAlertText().contains(OutputData.openCustAllertMessage), "Alert text does not match expected value");
        openCustAllert.accept();
    }

    @Test(description = "Проверка удаления покупателя", priority = 3)
    public void removeCustomerTest() {
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin();
        Assert.assertTrue(bankManagerLoginPage.openCustomersCatalog());
        bankManagerLoginPage.setSearchCustomerField(InputData.firstNameCustomer);
        Assert.assertEquals(bankManagerLoginPage.getCustomerName(), InputData.firstNameCustomer);
        bankManagerLoginPage.clickButtonDeleteCustomer()
                .clearSearchCustomerField();
        Assert.assertFalse(bankManagerLoginPage.getAllCustomerNames().contains(InputData.firstNameCustomer));
    }
}
