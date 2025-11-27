package tests;

import data.InputData;
import data.OutputData;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BankManagerLoginPage;
import pages.BankingHomePage;
import pages.AllertPage;

public class BankManagerLoginTest extends BaseTest{
    private WebDriver driver;
    private BankingHomePage bankingHomePage;
    private BankManagerLoginPage bankManagerLoginPage;

    @BeforeMethod(description = "Открываем страницу Banking App")
    public void setUrl() {
        driver = getDriver();
        bankingHomePage = new BankingHomePage(driver);
    }

    @Test(description = "Проверка добавления покупателя")
    @Epic("Управление клиентами банка")
    @Feature("Добавление клиента")
    @Story("Добавление нового покупателя")
    @Severity(SeverityLevel.CRITICAL)
    public void addCustomerTest() {
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin();
        Assert.assertTrue(bankManagerLoginPage.openAddCustomerCatalog());
        AllertPage addCustAllert = bankManagerLoginPage.setAddCustomerFields(InputData.firstNameCustomer, InputData.lastNameCustomer, InputData.postCodeCustomer)
                .clickButtonAddCust();
        Assert.assertTrue(addCustAllert.getAlertText().contains(OutputData.addCutAllertMessage), "Alert text does not match expected value");
        addCustAllert.accept();

        bankingHomePage = new BankingHomePage(driver);
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin().deleteCustomer();
    }

    @Test(description = "Проверка открытия аккаунта")
    @Epic("Управление аккаунтами банка")
    @Feature("Создание аккаунта")
    @Story("Открытие нового счета для клиента")
    @Severity(SeverityLevel.CRITICAL)
    public void openCustomerTest() {
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin()
                .addCustomer();

        bankingHomePage = new BankingHomePage(driver);
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin();
        Assert.assertTrue(bankManagerLoginPage.openCatalogOpenAccount());
        AllertPage openCustAllert = bankManagerLoginPage.setOpenAccountFields(InputData.firstNameCustomer + " " + InputData.lastNameCustomer, InputData.currencyCustomer)
                .clickProcessButton();
        Assert.assertTrue(openCustAllert.getAlertText().contains(OutputData.openCustAllertMessage), "Alert text does not match expected value");
        openCustAllert.accept();

        bankingHomePage = new BankingHomePage(driver);
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin().deleteCustomer();
    }

    @Test(description = "Проверка удаления покупателя")
    @Epic("Управление клиентами банка")
    @Feature("Удаление клиента")
    @Story("Удаление существующего покупателя")
    @Severity(SeverityLevel.NORMAL)
    public void removeCustomerTest() {
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin()
                .addCustomer();

        bankingHomePage = new BankingHomePage(driver);
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin();
        Assert.assertTrue(bankManagerLoginPage.openCustomersCatalog());
        bankManagerLoginPage.setSearchCustomerField(InputData.firstNameCustomer);
        Assert.assertEquals(bankManagerLoginPage.getCustomerName(), InputData.firstNameCustomer);
        bankManagerLoginPage.clickButtonDeleteCustomer()
                .clearSearchCustomerField();
        Assert.assertFalse(bankManagerLoginPage.getAllCustomerNames().contains(InputData.firstNameCustomer));
    }

    @Test(description = "Проверка удаления несуществующего клиента")
    @Epic("Управление клиентами банка")
    @Feature("Удаление клиента")
    @Story("Удаление несуществующего клиента")
    @Severity(SeverityLevel.MINOR)
    public void deleteNonExistentCustomerTest() {
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin();
        Assert.assertTrue(bankManagerLoginPage.openCustomersCatalog());

        String nonExistentCustomer = "Аноним";
        bankManagerLoginPage.setSearchCustomerField(nonExistentCustomer);

        Assert.assertEquals(bankManagerLoginPage.getCustomerName(), nonExistentCustomer);
        bankManagerLoginPage.clickButtonDeleteCustomer()
                .clearSearchCustomerField();
        Assert.assertFalse(bankManagerLoginPage.getAllCustomerNames().contains(nonExistentCustomer));
    }
}
