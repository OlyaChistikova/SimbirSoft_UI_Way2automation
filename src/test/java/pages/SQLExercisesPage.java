package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static data.EndPoint.SQL;

public class SQLExercisesPage {
    private WebDriver driver;
    private static final String URL = SQL.getUrl();

    @FindBy(xpath = "//input[@name='login' and @type='text']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@name='psw' and @type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@value='Вход']")
    private WebElement submitButton;

    @FindBy(xpath = "//a[@href='/logout.php']")
    private WebElement logoutButton;

    public SQLExercisesPage(WebDriver driver) {
        this.driver = driver;
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    public SQLExercisesPage setLoginField(String login) {
        loginField.sendKeys(login);
        return this;
    }

    public SQLExercisesPage setPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public boolean checkVisibilityLogoutImg(){
        return logoutButton.isDisplayed();
    }
}
