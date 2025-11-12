package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static data.EndPoint.BANKING;

public class BankingHomePage {
    private WebDriver driver;
    private static final String URL = BANKING.getUrl();

    /**
     * Локатор каталога "Sample Form"
     */
    @FindBy(xpath = "//a[text()='Sample Form']")
    private WebElement buttonSampleForm;

    /**
     * Локатор каталога Customer Login
     */
    @FindBy(xpath = "//button[text()='Customer Login']")
    private WebElement buttonCustomerLogin;

    /**
     * Локатор каталога Bank Manager Login
     */
    @FindBy(xpath = "//button[text()='Bank Manager Login']")
    private WebElement buttonBankManagerLogin;

    /**
     * Конструктор страницы
     *
     * @param driver WebDriver
     */
    public BankingHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get(URL);
    }

    /**
     * Открывает страницу формы (Sample Form)
     *
     * @return объект SampleFormPage
     */
    @Step("Открываем страницу Sample Form")
    public SampleFormPage openSampleForm() {
        buttonSampleForm.click();
        return new SampleFormPage(driver);
    }

    /**
     * Открывает страницу входа для менеджера банка
     *
     * @return объект BankManagerLoginPage
     */
    @Step("Открываем страницу входа для менеджера банка")
    public BankManagerLoginPage openBankManagerLogin() {
        buttonBankManagerLogin.click();
        return new BankManagerLoginPage(driver);
    }

    /**
     * Открывает страницу входа для клиента
     *
     * @return объект CustomerLoginPage
     */
    public CustomerLoginPage openCustomerLogin() {
        buttonCustomerLogin.click();
        return new CustomerLoginPage(driver);
    }
}
