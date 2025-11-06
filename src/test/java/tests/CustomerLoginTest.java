package tests;

import helpers.InputData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import static helpers.EndPoint.*;

public class CustomerLoginTest extends BaseTest {
    private WebDriver driver;
    private BankingHomePage bankingHomePage;
    private CustomerLoginPage customerLoginPage;
    private BankManagerLoginPage bankManagerLoginPage;

    @BeforeClass
    public void createCustomer(){
        driver = getDriver();
        driver.get(BANKING.getUrl());
        bankingHomePage = new BankingHomePage(driver);
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin()
                .addCustomer();
        driver.get(BANKING.getUrl());

        bankManagerLoginPage = bankingHomePage.openBankManagerLogin()
                .openAccount();
        driver.get(BANKING.getUrl());

        customerLoginPage = bankingHomePage.openCustomerLogin();
        Assert.assertTrue(customerLoginPage.checkVisibilityCustomerContainer());
        customerLoginPage.selectUser(InputData.firstNameCustomer + " " + InputData.lastNameCustomer);
        Assert.assertTrue(customerLoginPage.clickLoginButton());
        Assert.assertTrue(customerLoginPage.getWelcomeMessage().contains(InputData.firstNameCustomer + " " + InputData.lastNameCustomer));
    }

    @AfterClass
    public void deleteCustomer(){
        driver.get(BANKING.getUrl());
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin().deleteCustomer();
    }

    @Test(priority = 1)
    public void successfulRefillAccountTest() {
        Assert.assertTrue(customerLoginPage.clickDepositCatalog());
        customerLoginPage.setAmountField(InputData.amountDeposit).clickButtonDeposit();
        Assert.assertTrue(customerLoginPage.checkMessage("Deposit Successful"));
        Assert.assertTrue(customerLoginPage.clickTransactionsCatalog());
        Assert.assertEquals(customerLoginPage.getTransactionAmount(), InputData.amountDeposit);
        customerLoginPage.clickBackButton();
    }

    @Test(priority = 2)
    public void unSuccessfulRefillAccountTest() {
        Assert.assertTrue(customerLoginPage.clickDepositCatalog());
        customerLoginPage.setAmountField("0").clickButtonDeposit();
        Assert.assertTrue(customerLoginPage.checkInvisibilityMessage("Deposit Successful"));
        Assert.assertTrue(customerLoginPage.clickTransactionsCatalog());
        Assert.assertNotEquals(customerLoginPage.getTransactionAmount(), "0");
        customerLoginPage.clickBackButton();
    }

    @Test(priority = 3)
    public void successfulWithdrawalMoneyTest() {
        int randomSum = customerLoginPage.getRandomIntToBalance();
        Assert.assertTrue(customerLoginPage.clickWithdrawlCatalog());
        customerLoginPage.setAmountField(String.valueOf(randomSum)).clickWithdrawlButton();
        Assert.assertTrue(customerLoginPage.checkMessage("Transaction successful"));
        Assert.assertTrue(customerLoginPage.clickTransactionsCatalog());
        //дописать проверки на тип операции
        Assert.assertEquals(customerLoginPage.getTransactionAmount(), String.valueOf(randomSum));
        customerLoginPage.clickBackButton();
    }

    @Test(priority = 4)
    public void unSuccessfulWithdrawalMoneyTest() {
        Assert.assertTrue(customerLoginPage.clickWithdrawlCatalog());
        customerLoginPage.setAmountField("1000000").clickWithdrawlButton();
        Assert.assertTrue(customerLoginPage.checkMessage("Transaction Failed. You can not withdraw amount more than the balance."));
        Assert.assertTrue(customerLoginPage.clickTransactionsCatalog());
        //дописать проверки на тип операции
        Assert.assertNotEquals(customerLoginPage.getTransactionAmount(), "1000000");
        customerLoginPage.clickBackButton();
    }

    @Test(priority = 5)
    public void checkBalanceTest() {
        String balanceText = customerLoginPage.getBalance();
        Assert.assertTrue(customerLoginPage.clickTransactionsCatalog());
        Assert.assertEquals(customerLoginPage.getTotalAccountBalance(), balanceText);
        customerLoginPage.clickBackButton();
    }

    @Test(priority = 6)
    public void withdrawalRemainingMoneyTest() {
        String balanceText = customerLoginPage.getBalance();
        Assert.assertTrue(customerLoginPage.clickWithdrawlCatalog());
        customerLoginPage.setAmountField(balanceText).clickWithdrawlButton();
        Assert.assertTrue(customerLoginPage.checkMessage("Transaction successful"));
        Assert.assertEquals(customerLoginPage.getBalance(), "0");
    }

    @Test(priority = 7)
    public void clearTransactionHistoryTest() {
        Assert.assertTrue(customerLoginPage.clickTransactionsCatalog());
        int countTransactions = customerLoginPage.getCountTransaction();
        Assert.assertTrue(customerLoginPage.clickButtonReset().isTableBodyEmpty());
        customerLoginPage.clickBackButton();
        Assert.assertEquals(customerLoginPage.getBalance(), "0");
    }
}
