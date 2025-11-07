package tests;

import data.InputData;
import data.OutputData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BankManagerLoginPage;
import pages.BankingHomePage;
import pages.CustAllert;

public class BankManagerLoginTest extends BaseTest{
    private WebDriver driver;
    private BankingHomePage bankingHomePage;
    private BankManagerLoginPage bankManagerLoginPage;

    @BeforeMethod
    public void setUrl() {
        driver = getDriver();
        bankingHomePage = new BankingHomePage(driver);
        bankingHomePage.openPage();
    }

    @Test(description = "Проверка добавления покупателя")
    public void addCustomerTest() {
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin();
        Assert.assertTrue(bankManagerLoginPage.openAddCustomerCatalog());
        CustAllert addCustAllert = bankManagerLoginPage.setAddCustomerFields(InputData.firstNameCustomer, InputData.lastNameCustomer, InputData.postCodeCustomer)
                .clickButtonAddCust();
        Assert.assertTrue(addCustAllert.getAlertText().contains(OutputData.addCutAllertMessage), "Alert text does not match expected value");
        addCustAllert.accept();

        // Удаляем нового клиента через менеджера банка
        bankingHomePage.openPage();
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin().deleteCustomer();
    }

    @Test(description = "Проверка открытия аккаунта")
    public void openCustomerTest() {
        // Создаем нового клиента через менеджера банка
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin()
                .addCustomer();
        bankingHomePage.openPage();

        bankManagerLoginPage = bankingHomePage.openBankManagerLogin();
        Assert.assertTrue(bankManagerLoginPage.openCatalogOpenAccount());
        CustAllert openCustAllert = bankManagerLoginPage.setOpenAccountFields(InputData.firstNameCustomer + " " + InputData.lastNameCustomer, InputData.currencyCustomer)
                .clickProcessButton();
        Assert.assertTrue(openCustAllert.getAlertText().contains(OutputData.openCustAllertMessage), "Alert text does not match expected value");
        openCustAllert.accept();

        // Удаляем нового клиента через менеджера банка
        bankingHomePage.openPage();
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin().deleteCustomer();
    }

    @Test(description = "Проверка удаления покупателя", priority = 3)
    public void removeCustomerTest() {
        // Создаем нового клиента через менеджера банка
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin()
                .addCustomer();
        bankingHomePage.openPage();

        bankManagerLoginPage = bankingHomePage.openBankManagerLogin();
        Assert.assertTrue(bankManagerLoginPage.openCustomersCatalog());
        bankManagerLoginPage.setSearchCustomerField(InputData.firstNameCustomer);
        Assert.assertEquals(bankManagerLoginPage.getCustomerName(), InputData.firstNameCustomer);
        bankManagerLoginPage.clickButtonDeleteCustomer()
                .clearSearchCustomerField();
        Assert.assertFalse(bankManagerLoginPage.getAllCustomerNames().contains(InputData.firstNameCustomer));
    }
}
