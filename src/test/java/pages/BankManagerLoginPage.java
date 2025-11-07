package pages;

import data.EndPoint;
import data.InputData;
import helpers.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


import java.util.ArrayList;
import java.util.List;

public class BankManagerLoginPage {
    private WebDriver driver;

    /**
     * Локатор функции добавления пользователя страницы Bank Manager Login
     */
    @FindBy(xpath = "//button[@ng-click='addCust()']")
    private WebElement addCustCatalog;

    /**
     * Локатор функции открытия счета пользователя страницы Bank Manager Login
     */
    @FindBy(xpath = "//button[@ng-click='openAccount()']")
    private WebElement openAccountCatalog;

    /**
     * Локатор функции открытия списка пользователей страницы Bank Manager Login
     */
    @FindBy(xpath = "//button[@ng-click='showCust()']")
    private WebElement customersCatalog;

    /**
     * Контейнер для добавления клиента
     */
    @FindBy(xpath = "//div[@class='center']/following-sibling::div")
    private WebElement addCustContainer;

    /**
     * Поля для ввода данных клиента
     */
    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    private WebElement postCodeField;

    /**
     * Кнопка для добавления клиента
     */
    @FindBy(xpath = "//button[text()='Add Customer']")
    private WebElement addCustButton;

    /**
     * Контейнер для открытия счета
     */
    @FindBy(xpath = "//form[@ng-submit='process()']")
    private WebElement openAccountContainer;

    /**
     * Выпадающие списки для выбора клиента и валюты
     */
    @FindBy(xpath = "//select[@id='userSelect']")
    private WebElement customerNameSelect;

    @FindBy(xpath = "//select[@id='currency']")
    private WebElement currencySelect;

    /**
     * Кнопка для обработки открытия счета
     */
    @FindBy(xpath = "//button[text()='Process']")
    private WebElement processButton;

    /**
     * Контейнер со списком клиентов
     */
    @FindBy(css = ".marTop")
    private WebElement customersContainer;

    /**
     * Поле поиска клиента
     */
    @FindBy(xpath = "//input[@ng-model='searchCustomer']")
    private WebElement searchCustomerField;

    /**
     * Список строк с данными клиентов
     */
    @FindBy(xpath = "//table[@class='table table-bordered table-striped']/tbody/tr")
    private List<WebElement> rowsCustomers;

    /**
     * Объект страницы Bank Manager Login
     */
    public BankManagerLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Открывает окно добавления клиента и проверяет его отображение
     *
     * @return true, если окно отображается
     */
    public boolean openAddCustomerCatalog() {
        checkVisibilityElement(addCustCatalog, EndPoint.BANK_MANAGER.getUrl());
        addCustCatalog.click();
        return checkVisibilityElement(addCustContainer, EndPoint.ADD_CUSTOMER.getUrl());
    }

    /**
     * Заполняет поля для добавления нового клиента
     *
     * @param firstName Имя клиента
     * @param lastName Фамилия клиента
     * @param postCode Почтовый индекс
     * @return текущий объект страницы (для цепочки вызовов)
     */
    public BankManagerLoginPage setAddCustomerFields(String firstName, String lastName, String postCode) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postCodeField.sendKeys(postCode);
        return this;
    }

    /**
     * Нажимает кнопку добавления клиента и возвращает объект для подтверждения
     *
     * @return объект CustAllert
     */
    public CustAllert clickButtonAddCust() {
        addCustButton.click();
        return new CustAllert(driver);
    }

    /**
     * Открывает окно открытия счета и проверяет его отображение
     *
     * @return true, если окно отображается
     */
    public boolean openCatalogOpenAccount() {
        checkVisibilityElement(openAccountCatalog, EndPoint.BANK_MANAGER.getUrl());
        openAccountCatalog.click();
        return checkVisibilityElement(openAccountContainer, EndPoint.OPEN_ACCOUNT.getUrl());
    }

    /**
     * Заполняет поля для открытия счета
     *
     * @param customerName Имя клиента для счета
     * @param currency Валюта счета
     * @return текущий объект страницы
     */
    public BankManagerLoginPage setOpenAccountFields(String customerName, String currency) {
        selectCustomerName(customerName);
        selectCurrency(currency);
        return this;
    }

    /**
     * Нажимает кнопку обработки открытия счета и возвращает объект подтверждения
     *
     * @return объект CustAllert
     */
    public CustAllert clickProcessButton() {
        processButton.click();
        return new CustAllert(driver);
    }

    /**
     * Открывает окно отображения клиентов и проверяет его отображение
     *
     * @return true, если окно отображается
     */
    public boolean openCustomersCatalog() {
        checkVisibilityElement(customersCatalog, EndPoint.BANK_MANAGER.getUrl());
        customersCatalog.click();
        return checkVisibilityElement(customersContainer, EndPoint.CUSTOMERS.getUrl());
    }

    /**
     * Проверяет видимость элемента и соответствие URL
     *
     * @param element WebElement
     * @param endPoint URL-эндпоинт для проверки
     * @return true, если элемент видим и URL соответствует
     */
    private boolean checkVisibilityElement(WebElement element, String endPoint) {
        Waiters.waitTimeForVisibilityOfElement(driver, element);
        Waiters.waitTimeForCheckUrl(driver, endPoint);
        return element.isDisplayed();
    }

    /**
     * Выбирает клиента по имени из списка
     *
     * @param customerName Имя клиента
     */
    private void selectCustomerName(String customerName) {
        Select select = new Select(customerNameSelect);
        select.selectByVisibleText(customerName);
    }

    /**
     * Выбирает валюту из списка
     *
     * @param currency Валюта
     */
    private void selectCurrency(String currency) {
        Select select = new Select(currencySelect);
        select.selectByVisibleText(currency);
    }

    /**
     * Вводит имя клиента в поле поиска
     *
     * @param firstName Имя клиента
     */
    public void setSearchCustomerField(String firstName) {
        searchCustomerField.sendKeys(firstName);
    }

    /**
     * Очищает поле поиска клиента
     */
    public void clearSearchCustomerField() {
        searchCustomerField.clear();
    }

    /**
     * Получает имя первого клиента из таблицы
     *
     * @return Имя клиента или null, если таблица пустая
     */
    public String getCustomerName() {
        for (WebElement row : rowsCustomers) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            return cells.get(0).getText();
        }
        return null;
    }

    /**
     * Получает список всех имен клиентов из таблицы
     *
     * @return список имен
     */
    public List<String> getAllCustomerNames() {
        List<String> names = new ArrayList<>();
        for (WebElement row : rowsCustomers) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty()) {
                names.add(cells.get(0).getText());
            }
        }
        return names;
    }

    /**
     * Удаляет клиентов по строкам таблицы
     *
     * @return текущий объект страницы
     */
    public BankManagerLoginPage clickButtonDeleteCustomer() {
        for (WebElement row : rowsCustomers) {
            WebElement deleteButton = row.findElement(By.tagName("button"));
            deleteButton.click();
        }
        return this;
    }

    /**
     * Добавляет нового клиента, заполняя форму и подтверждая
     *
     * @return текущий объект страницы
     */
    public BankManagerLoginPage addCustomer() {
        openAddCustomerCatalog();
        setAddCustomerFields(InputData.firstNameCustomer, InputData.lastNameCustomer, InputData.postCodeCustomer);
        CustAllert addCustAllert = this.clickButtonAddCust();
        addCustAllert.accept();
        return this;
    }

    /**
     * Открывает счет для клиента, заполняет форму и подтверждает
     *
     * @return текущий объект страницы
     */
    public BankManagerLoginPage openAccount() {
        openCatalogOpenAccount();
        setOpenAccountFields(InputData.firstNameCustomer + " " + InputData.lastNameCustomer, InputData.currencyCustomer);
        CustAllert openCustAllert = this.clickProcessButton();
        openCustAllert.accept();
        return this;
    }

    /**
     * Удаляет клиента по имени
     *
     * @return текущий объект страницы
     */
    public BankManagerLoginPage deleteCustomer() {
        openCustomersCatalog();
        setSearchCustomerField(InputData.firstNameCustomer);
        clickButtonDeleteCustomer().clearSearchCustomerField();
        return this;
    }
}
