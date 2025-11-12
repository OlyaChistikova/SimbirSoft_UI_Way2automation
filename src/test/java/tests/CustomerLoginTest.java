package tests;

import data.InputData;
import io.qameta.allure.*;
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

    @BeforeClass(description = "Создаем нового клиента для тестов")
    public void createCustomer(){
        driver = getDriver();
        bankingHomePage = new BankingHomePage(driver);

        // Создаем нового клиента через менеджера банка
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin()
                .addCustomer();

        // Создаем счет для клиента через менеджера банка
        bankingHomePage = new BankingHomePage(driver);
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin()
                .openAccount();

        // Открываем страницу входа клиента и выбираем созданного клиента
        bankingHomePage = new BankingHomePage(driver);
        customerLoginPage = bankingHomePage.openCustomerLogin()
                .openCustomer(InputData.firstNameCustomer, InputData.lastNameCustomer);
    }

    @AfterClass(description = "Удаляем созданного для тестов клиента")
    public void deleteCustomer(){
        bankingHomePage = new BankingHomePage(driver);
        bankManagerLoginPage = bankingHomePage.openBankManagerLogin().deleteCustomer();
    }

    @Test(description = "Открытие страницы клиента и вход под выбранным пользователем")
    @Epic("Авторизация клиента")
    @Feature("Вход в аккаунт")
    @Story("Успешный вход под созданным клиентом")
    @Severity(SeverityLevel.CRITICAL)
    public void openCustomerTest(){
        customerLoginPage.logoutUser();
        Assert.assertTrue(customerLoginPage.checkVisibilityUserSelect());
        customerLoginPage.selectUser(InputData.firstNameCustomer + " " + InputData.lastNameCustomer);
        Assert.assertTrue(customerLoginPage.clickLoginButton());
        Assert.assertTrue(customerLoginPage.getWelcomeMessage().contains(InputData.firstNameCustomer + " " + InputData.lastNameCustomer));
    }

    @Test(description = "Успешное пополнение счета")
    @Epic("Финансовые операции")
    @Feature("Пополнение счета")
    @Story("Успешное пополнение")
    @Severity(SeverityLevel.CRITICAL)
    public void successfulRefillAccountTest() {
        customerLoginPage.clickDepositCatalog();
        customerLoginPage.setAmountField(InputData.amountDeposit).clickButtonDeposit();
        Assert.assertTrue(customerLoginPage.checkMessage("Deposit Successful"));
        Assert.assertTrue(customerLoginPage.clickTransactionsCatalog());
        Assert.assertEquals(customerLoginPage.getTransactionAmount(), InputData.amountDeposit);
        customerLoginPage.clickBackButton();
    }

    @Test(description = "Некорректное пополнение счета с нулевой суммой")
    @Epic("Финансовые операции")
    @Feature("Пополнение счета")
    @Story("Попытка пополнения нулевой суммой")
    @Severity(SeverityLevel.MINOR)
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
    @Epic("Финансовые операции")
    @Feature("Снятие средств")
    @Story("Успешное снятие")
    @Severity(SeverityLevel.CRITICAL)
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
    @Epic("Финансовые операции")
    @Feature("Снятие средств")
    @Story("Попытка снятия суммы больше баланса")
    @Severity(SeverityLevel.CRITICAL)
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
    @Epic("Финансовые операции")
    @Feature("Подсчет баланса")
    @Story("Проверка корректности отображения баланса")
    @Severity(SeverityLevel.NORMAL)
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
    @Epic("Финансовые операции")
    @Feature("Обнуление баланса")
    @Story("Снятие оставшихся средств")
    @Severity(SeverityLevel.CRITICAL)
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
    @Epic("Финансовые операции")
    @Feature("История транзакций")
    @Story("Очистка и проверка пустой таблицы")
    @Severity(SeverityLevel.MINOR)
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
