package tests;

import data.InputData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

public class CustomerLoginTest extends BaseTest {
    private WebDriver driver;
    private BankingHomePage bankingHomePage;
    private CustomerLoginPage customerLoginPage;
    private BankManagerLoginPage bankManagerLoginPage;

    @BeforeClass
    public void createCustomer(){
        driver = getDriver();
        bankingHomePage = new BankingHomePage(driver);
        bankingHomePage.openPage();

        // Создаем нового клиента через менеджера банка
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin()
                .addCustomer();
        bankingHomePage.openPage();

        // Создаем счет для клиента через менеджера банка
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin()
                .openAccount();
        bankingHomePage.openPage();

        // Открываем страницу входа клиента и выбираем созданного клиента
        customerLoginPage = bankingHomePage.openCustomerLogin()
                .openCustomer(InputData.firstNameCustomer, InputData.lastNameCustomer);
    }

    @AfterClass
    public void deleteCustomer(){
        bankingHomePage.openPage();
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin().deleteCustomer();
    }

    @Test(description = "Открытие страницы клиента и вход под выбранным пользователем")
    public void openCustomerTest(){
        customerLoginPage.logoutUser();
        Assert.assertTrue(customerLoginPage.checkVisibilityUserSelect());
        customerLoginPage.selectUser(InputData.firstNameCustomer + " " + InputData.lastNameCustomer);
        Assert.assertTrue(customerLoginPage.clickLoginButton());
        Assert.assertTrue(customerLoginPage.getWelcomeMessage().contains(InputData.firstNameCustomer + " " + InputData.lastNameCustomer));
    }

    @Test(description = "Успешное пополнение счета")
    public void successfulRefillAccountTest() {
        customerLoginPage.clickDepositCatalog();
        customerLoginPage.setAmountField(InputData.amountDeposit).clickButtonDeposit();
        Assert.assertTrue(customerLoginPage.checkMessage("Deposit Successful"));
        Assert.assertTrue(customerLoginPage.clickTransactionsCatalog());
        Assert.assertEquals(customerLoginPage.getTransactionAmount(), InputData.amountDeposit);
        customerLoginPage.clickBackButton();
    }

    @Test(description = "Некорректное пополнение счета с нулевой суммой")
    public void unSuccessfulRefillAccountTest() {
        // Пополнение для установки баланса и проверки транзакций
        customerLoginPage.setRefillAccount(InputData.amountDeposit);

        customerLoginPage.clickDepositCatalog();
        customerLoginPage.setAmountField("0").clickButtonDeposit();
        Assert.assertTrue(customerLoginPage.checkInvisibilityMessage("Deposit Successful"));
        Assert.assertTrue(customerLoginPage.clickTransactionsCatalog());
        Assert.assertNotEquals(customerLoginPage.getTransactionAmount(), "0");
        customerLoginPage.clickBackButton();
    }

    @Test(description = "Успешное снятие денег со счета")
    public void successfulWithdrawalMoneyTest() {
        // Пополняем счет для обеспечения достаточного баланса
        customerLoginPage.setRefillAccount(InputData.amountDeposit);

        int randomSum = customerLoginPage.getRandomIntToBalance();
        customerLoginPage.clickWithdrawlCatalog();
        customerLoginPage.setAmountField(String.valueOf(randomSum)).clickWithdrawlButton();
        Assert.assertTrue(customerLoginPage.checkMessage("Transaction successful"));
        Assert.assertTrue(customerLoginPage.clickTransactionsCatalog());
        Assert.assertEquals(customerLoginPage.getTransactionAmount(), String.valueOf(randomSum));
        customerLoginPage.clickBackButton();
    }

    @Test(description = "Попытка снятия суммы, превышающей баланс")
    public void unSuccessfulWithdrawalMoneyTest() {
        // Пополняем счет для теста
        customerLoginPage.setRefillAccount(InputData.amountDeposit);

        customerLoginPage.clickWithdrawlCatalog();
        customerLoginPage.setAmountField("1000000").clickWithdrawlButton();
        Assert.assertTrue(customerLoginPage.checkMessage("Transaction Failed. You can not withdraw amount more than the balance."));
        Assert.assertTrue(customerLoginPage.clickTransactionsCatalog());
        Assert.assertNotEquals(customerLoginPage.getTransactionAmount(), "1000000");
        customerLoginPage.clickBackButton();
    }

    @Test(description = "Проверка правильности подсчета баланса по транзакциям")
    public void checkBalanceTest() {
        // Пополняем счет для обеспечения достаточного баланса
        customerLoginPage.setRefillAccount(InputData.amountDeposit);

        // Снимаем со счета сгенерированную сумму
        int randomSum = customerLoginPage.getRandomIntToBalance();
        customerLoginPage.setWithdrawalMoney(String.valueOf(randomSum));

        String balanceText = customerLoginPage.getBalance();
        Assert.assertTrue(customerLoginPage.clickTransactionsCatalog());
        Assert.assertEquals(customerLoginPage.getTotalAccountBalance(), balanceText);
        customerLoginPage.clickBackButton();
    }

    @Test(description = "Снятие всех оставшихся средств, баланс становится 0")
    public void withdrawalRemainingMoneyTest() {
        // Пополняем счет для теста
        customerLoginPage.setRefillAccount(InputData.amountDeposit);

        String balanceText = customerLoginPage.getBalance();
        customerLoginPage.clickWithdrawlCatalog();
        customerLoginPage.setAmountField(balanceText).clickWithdrawlButton();
        Assert.assertTrue(customerLoginPage.checkMessage("Transaction successful"));
        Assert.assertEquals(customerLoginPage.getBalance(), "0");
    }

    @Test(description = "Очистка истории транзакций и проверка, что таблица пуста")
    public void clearTransactionHistoryTest() {
        // Пополняем счет для теста
        customerLoginPage.setRefillAccount(InputData.amountDeposit);

        // Снимаем со счета сумму для теста
        int randomSum = customerLoginPage.getRandomIntToBalance();
        customerLoginPage.setWithdrawalMoney(String.valueOf(randomSum));

        Assert.assertTrue(customerLoginPage.clickTransactionsCatalog());
        Assert.assertTrue(customerLoginPage.getCountTransaction() > 0);
        Assert.assertTrue(customerLoginPage.clickButtonReset().isTableBodyEmpty());
        customerLoginPage.clickBackButton();
        Assert.assertEquals(customerLoginPage.getBalance(), "0");
    }
}
