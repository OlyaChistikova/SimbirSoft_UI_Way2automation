package pages;

import helpers.EndPoint;
import helpers.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static helpers.EndPoint.CUSTOMER_LOGIN;

public class CustomerLoginPage {
    private WebDriver driver;

    /**
     *     Локатор формы входа/выбора клиента
     */
    @FindBy(xpath = "//form[@ng-submit='showAccount()']")
    private WebElement customerContainer;

    /**
     * Локатор выпадающего списка для выбора пользователя
     */
    @FindBy(xpath = "//select[@id='userSelect']")
    private WebElement userSelect;

    /**
     * Локатор кнопки входа
     */
    @FindBy(xpath = "//button[@ng-show=\"custId != ''\"]")
    private WebElement loginButton;

    /**
     * Локатор приветственного сообщения после входа
     */
    @FindBy(xpath = "//div[@class='borderM box padT20 ng-scope']/div[1]/strong")
    private WebElement welcomeMessage;

    /**
     * Локатор вкладки депозита
     */
    @FindBy(xpath = "//button[@ng-click='deposit()']")
    private WebElement depositCatalog;

    /**
     * Локатор поля для ввода суммы
     */
    @FindBy(xpath = "//input[@placeholder='amount']")
    private WebElement amountField;

    /**
     * Локатор кнопки подтверждения депозита
     */
    @FindBy(xpath = "//button[text()='Deposit']")
    private WebElement depositButton;

    /**
     * Локатор для отображения сообщения о результате операции
     */
    @FindBy(xpath = "//span[@ng-show='message']")
    private WebElement successfulMessage;

    /**
     * Локатор вкладки транзакций
     */
    @FindBy(xpath = "//button[@ng-click='transactions()']")
    private WebElement transactionsCatalog;

    /**
     * Локатор суммы последней транзакции
     */
    @FindBy(xpath = "//table/tbody/tr[last()]/td[2]")
    private WebElement transactionAmount;

    /**
     * Локатор поля отображения баланса
     */
    @FindBy(xpath = "//div[@class='center']/strong[2]")
    private WebElement balanceField;

    /**
     * Локатор вкладки снятия средств
     */
    @FindBy(xpath = "//button[@ng-click='withdrawl()']")
    private WebElement withdrawlCatalog;

    /**
     * Локатор кнопки подтверждения снятия
     */
    @FindBy(xpath = "//button[text()='Withdraw']")
    private WebElement withdrawlButton;

    /**
     * Локатор кнопки возврата
     */
    @FindBy(xpath = "//button[@ng-click='back()']")
    private WebElement buttonBack;

    /**
     * Локатор строк таблицы транзакций
     */
    @FindBy(xpath = "//table[@class='table table-bordered table-striped']/tbody/tr")
    private List<WebElement> rowsTransactions;

    /**
     * Локатор кнопки сброса
     */
    @FindBy(xpath = "//button[@ng-click='reset()']")
    private WebElement buttonReset;

    /**
     * Локатор тела таблицы транзакций
     */
    @FindBy(xpath = "//table[@class='table table-bordered table-striped']/tbody")
    private WebElement tbody;

    /**
     * Конструктор страницы Customer Login
     *
     * @param driver WebDriver
     */
    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Проверяет отображение контейнера клиента
     *
     * @return true, если контейнер видим
     */
    public boolean checkVisibilityCustomerContainer() {
        Waiters.waitTimeForVisibilityOfElement(driver, customerContainer);
        Waiters.waitTimeForCheckUrl(driver, CUSTOMER_LOGIN.getUrl());
        return customerContainer.isDisplayed();
    }

    /**
     * Выбирает пользователя из списка по имени
     *
     * @param userCustomer Имя пользователя
     */
    public void selectUser(String userCustomer) {
        Select select = new Select(userSelect);
        select.selectByVisibleText(userCustomer);
    }

    /**
     * Проверяет отображение кнопки входа
     *
     * @return true, если кнопка отображается
     */
    public boolean checkVisibilityLoginButton() {
        Waiters.waitTimeForVisibilityOfElement(driver, loginButton);
        Waiters.waitTimeForClickableElement(driver, loginButton);
        return loginButton.isDisplayed();
    }

    /**
     * Нажимает кнопку входа и проверяет успешный вход
     *
     * @return true, если успешное сообщение отображается
     */
    public boolean clickLoginButton() {
        checkVisibilityLoginButton();
        loginButton.click();
        return checkVisibilityOfElement(welcomeMessage, EndPoint.ACCOUNT.getUrl());
    }

    /**
     * Проверяет видимость элемента и соответствие URL
     *
     * @param element WebElement
     * @param endPoint URL-эндпоинт для проверки
     * @return true, если элемент отображается и URL совпадает
     */
    private boolean checkVisibilityOfElement(WebElement element, String endPoint) {
        Waiters.waitTimeForVisibilityOfElement(driver, element);
        Waiters.waitTimeForCheckUrl(driver, endPoint);
        return element.isDisplayed();
    }

    /**
     * Получает приветственное сообщение
     *
     * @return текст сообщения
     */
    public String getWelcomeMessage() {
        return welcomeMessage.getText();
    }

    /**
     * Открывает вкладку депозита
     *
     * @return true, если вкладка и поле суммы отображаются
     */
    public boolean clickDepositCatalog() {
        checkVisibilityOfElement(depositCatalog, EndPoint.ACCOUNT.getUrl());
        depositCatalog.click();
        return checkVisibilityOfElement(amountField, EndPoint.ACCOUNT.getUrl());
    }

    /**
     * Открывает вкладку снятия средств
     *
     * @return true, если вкладка и поле суммы отображаются
     */
    public boolean clickWithdrawlCatalog() {
        checkVisibilityOfElement(withdrawlCatalog, EndPoint.ACCOUNT.getUrl());
        withdrawlCatalog.click();
        return checkVisibilityOfElement(amountField, EndPoint.ACCOUNT.getUrl());
    }

    /**
     * Устанавливает сумму для операции
     *
     * @param amountDeposit сумма
     * @return текущий объект страницы
     */
    public CustomerLoginPage setAmountField(String amountDeposit) {
        amountField.sendKeys(amountDeposit);
        return this;
    }

    /**
     * Нажимает кнопку депозита
     */
    public void clickButtonDeposit() {
        depositButton.click();
    }

    /**
     * Проверяет успешное сообщение
     *
     * @param message ожидаемое сообщение
     * @return true, если сообщение совпадает
     */
    public boolean checkMessage(String message) {
        Waiters.waitTimeForVisibilityOfElement(driver, successfulMessage);
        return successfulMessage.getText().equals(message);
    }

    /**
     * Проверяет, исчезло ли сообщение
     *
     * @param message сообщение для сравнения
     * @return true, если сообщение исчезло
     */
    public boolean checkInvisibilityMessage(String message) {
        Waiters.waitTimeForElementToBeInvisible(driver, successfulMessage);
        return !successfulMessage.getText().equals(message);
    }

    /**
     * Открывает вкладку транзакций
     *
     * @return true, если отображается сумма транзакции
     */
    public boolean clickTransactionsCatalog() {
        Waiters.setImplicitWait();
        transactionsCatalog.click();
        return checkVisibilityTransactionAmount();
    }

    /**
     * Проверяет отображение суммы транзакции
     *
     * @return true, если сумма видна
     */
    private boolean checkVisibilityTransactionAmount() {
        Waiters.waitTimeForCheckUrl(driver, EndPoint.TRANSACTIONS.getUrl());
        try {
            return transactionAmount.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Получает сумму последней транзакции
     *
     * @return сумма или "-1", если не найдено
     */
    public String getTransactionAmount() {
        try {
            return transactionAmount.getText();
        } catch (NoSuchElementException e) {
            return "-1";
        }
    }

    /**
     * Получает баланс счета
     *
     * @return баланс как строку
     */
    public String getBalance() {
        return balanceField.getText();
    }

    /**
     * Нажимает кнопку снятия средств
     */
    public void clickWithdrawlButton() {
        withdrawlButton.click();
    }

    /**
     * Возврат на предыдущую страницу
     */
    public void clickBackButton() {
        buttonBack.click();
    }

    /**
     * Расчет общего баланса по транзакциям
     *
     * @return сумма баланса как строка
     */
    public String getTotalAccountBalance() {
        int totalBalance = 0;

        for (WebElement row : rowsTransactions) {
            List<WebElement> cells = row.findElements(By.tagName("td"));

            String amountStr = cells.get(1).getText();
            String operationType = cells.get(2).getText();

            int amount = Integer.parseInt(amountStr);

            if (operationType.equalsIgnoreCase("Credit")) {
                totalBalance += amount;
            } else if (operationType.equalsIgnoreCase("Debit")) {
                totalBalance -= amount;
            }
        }
        return String.valueOf(totalBalance);
    }

    /**
     * Получает количество транзакций
     *
     * @return число транзакций
     */
    public int getCountTransaction() {
        return rowsTransactions.size();
    }

    /**
     * Сбросить состояние транзакций
     *
     * @return текущий объект
     */
    public CustomerLoginPage clickButtonReset() {
        buttonReset.click();
        return this;
    }

    /**
     * Проверяет, пуста ли таблица транзакций
     *
     * @return true, если таблица пуста
     */
    public boolean isTableBodyEmpty() {
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));
        return rows.isEmpty();
    }

    /**
     * Получает случайное число, не превышающее баланс
     *
     * @return случайное число
     */
    public int getRandomIntToBalance() {
        String balanceText = this.getBalance();
        int maxBalance = Integer.parseInt(balanceText);
        Random rand = new Random();
        return rand.nextInt(maxBalance) + 1;
    }
}
