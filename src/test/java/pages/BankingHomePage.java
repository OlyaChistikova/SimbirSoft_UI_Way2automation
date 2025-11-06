package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BankingHomePage {
    private WebDriver driver;

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
    }

    /**
     * Открывает страницу формы (Sample Form)
     *
     * @return объект SampleFormPage
     */
    public SampleFormPage openSampleForm() {
        buttonSampleForm.click();
        return new SampleFormPage(driver);
    }

    /**
     * Открывает страницу входа для менеджера банка
     *
     * @return объект BankManagerLoginPage
     */
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
