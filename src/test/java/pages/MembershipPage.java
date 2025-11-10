package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MembershipPage{
    private WebDriver driver;

    /**
     * Локатор заголовка страницы
     */
    @FindBy(xpath = "//h1[@class='elementor-heading-title elementor-size-default']")
    private WebElement titlePage;

    /**
     * Объект страницы Membership
     */
    public MembershipPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Получает текст заголовка текущей страницы.
     * @return строка с текстом заголовка страницы
     */
    @Step("Проверяем текст заголовка страницы Membership")
    public String getTitlePage() {
        return titlePage.getText();
    }
}
