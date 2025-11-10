package pages;

import helpers.JavaScriptExecutorHelper;
import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static data.EndPoint.HOME;

public class HomePage{
    private final WebDriver driver;
    JavaScriptExecutorHelper jsHelper;
    private static final String URL = HOME.getUrl();


    /**
     * Локатор контейнера хедера страницы с контактной информацией
     */
    @FindBy(xpath = "//div[@class='ast-above-header-wrap  ']")
    private WebElement headerContactInfoContainer;

    /**
     * Локатор списка контактов хедера страницы с контактной информацией
     */
    @FindBy(xpath = "//ul[@class='elementor-icon-list-items elementor-inline-items']/li")
    private List<WebElement> headerContactInfoList;

    /**
     * Локатор списка мессенджеров хедера страницы с контактной информацией
     */
    @FindBy(xpath = "//div[@data-section='section-hb-social-icons-1']//a")
    private List<WebElement> headerMessengerInfo;

    /**
     * Локатор блока с навигацией страницы
     */
    @FindBy(css = ".site-header-primary-section-right .ast-builder-menu-1")
    private WebElement headerBlockNavigationContainer;

    /**
     * Локатор списка разделов блока с навигацией страницы
     */
    @FindBy(xpath = "//div[@id='ast-desktop-header']//ul[@id='ast-hf-menu-1'][1]/li/a/span[@class='menu-text']")
    private List<WebElement> headerBlockNavigationList;

    /**
     * Локатор кнопки регистрации
     */
    @FindBy(linkText = "Register Now")
    private WebElement registrationButton;

    /**
     * Локатор списка курсов (под текстом Best Selenium Certification Course Online)
     */
    @FindBy(xpath = "//div[@class='elementor-widget-wrap elementor-element-populated' and .//h1[text()='Best Selenium Certification Course Online']]")
    private WebElement coursesList;

    /**
     * Локатор контейнера футера страницы
     */
    @FindBy(xpath = "//div[@data-elementor-type='footer']")
    private WebElement footerContainer;

    /**
     * Локатор списка элементов футера страницы с контактной информацией
     */
    @FindBy(xpath = "//div[@class='elementor-element elementor-element-695441a0 elementor-tablet-align-left elementor-mobile-align-left elementor-icon-list--layout-traditional elementor-list-item-link-full_width elementor-widget elementor-widget-icon-list']//li")
    private List<WebElement> footerContactInfo;

    /**
     * Локатор заголовка страницы
     */
    @FindBy(css = ".elementor-hidden-mobile h1")
    private WebElement headingTitle;

    /**
     * Локатор кнопки закрытия popUp окна
     */
    @FindBy(xpath = "//div[@id='elementor-popup-modal-26600']//i")
    private WebElement closePopUpButton;

    /**
     * Локатор каталога All courses блока с навигацией
     */
    @FindBy(xpath = "//div[@id='ast-desktop-header']//span[text()='All Courses']")
    private WebElement catalogAllCourses;

    /**
     * Локатор страницы Lifetime membership каталога All courses
     */
    @FindBy(xpath = "//li[@id='menu-item-27580']//span[text()='Lifetime Membership']")
    private WebElement membershipPage;

    /**
     * Объект страницы Home
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        jsHelper = new JavaScriptExecutorHelper(driver);
        driver.get(URL);
    }

    /**
     * Проверяет отображение контейнера контактной информации в хедере.
     * Использует явное ожидание, чтобы убедиться, что элемент видим.
     * @return true, если контейнер отображается, иначе false
     */
    @Step("Проверяем отображение контактной информации в хедере")
    public boolean checkDisplayHeader() {
        Waiters.waitTimeForVisibilityOfElement(driver, headerContactInfoContainer);
        return headerContactInfoContainer.isDisplayed();
    }

    /**
     * Получает список контактов из хедера.
     * @return список строк с контактной информацией
     */
    @Step("Проверяем список контактов из хедера")
    public List<String> getContactInfoHeader() {
        List<String> list = new ArrayList<>();
        for (WebElement item : headerContactInfoList) {
            list.add(item.getText());
        }
        return list;
    }

    /**
     * Получает список ссылок на мессенджеры из хедера.
     * @return список URL-адресов (атрибут href) мессенджеров
     */
    @Step("Проверяем список ссылок на мессенджеры из хедера")
    public List<String> getMessengerInfoHeader() {
        List<String> list = new ArrayList<>();
        for (WebElement item : headerMessengerInfo) {
            list.add(item.getAttribute("href"));
        }
        return list;
    }

    /**
     * Проверяет отображение блока навигации в шапке сайта.
     * Использует явное ожидание кликабельности элемента.
     * @return true, если блок навигации отображается, иначе false
     */
    @Step("Проверяем отображение блока навигации в шапке сайта")
    public boolean checkDisplayBlockNavigation() {
        Waiters.waitTimeForClickableElement(driver, headerBlockNavigationContainer);
        return headerBlockNavigationContainer.isDisplayed();
    }

    /**
     * Получает список элементов блока навигации.
     * @return список названий пунктов меню
     */
    @Step("Проверяем список элементов блока навигации")
    public List<String> getBlockNavigationText() {
        List<String> list = new ArrayList<>();
        for (WebElement item : headerBlockNavigationList) {
            list.add(item.getText());
        }
        return list;
    }

    /**
     * Проверяет отображение кнопки регистрации.
     * Использует явное ожидание кликабельности.
     * @return true, если кнопка отображается, иначе false
     */
    @Step("Проверяем отображение кнопки регистрации")
    public boolean checkDisplayRegistrationButton() {
        Waiters.waitTimeForClickableElement(driver, registrationButton);
        return registrationButton.isDisplayed();
    }

    /**
     * Проверяет отображение блока с курсами.
     * Использует явное ожидание видимости элемента.
     * @return true, если блок отображается, иначе false
     */
    @Step("Проверяем отображение блока с курсами")
    public boolean checkDisplayCoursesList() {
        Waiters.waitTimeForVisibilityOfElement(driver, coursesList);
        return coursesList.isDisplayed();
    }

    /**
     * Проверяет, отображается ли контейнер футера страницы.
     * @return true, если футер виден
     */
    @Step("Проверяем отображение футера страницы")
    public boolean checkDisplayFooter() {
        return footerContainer.isDisplayed();
    }

    /**
     * Получает список элементов контактной информации из футера.
     * Также очищает первый элемент, удаляя лишний текст.
     * @return список строк с контактной информацией
     */
    @Step("Проверяем контактную информацию из футера")
    public List<String> getFooterContactInfo() {
        List<String> list = new ArrayList<>();
        for (WebElement item : footerContactInfo) {
            list.add(item.getText());
        }
        list.set(0, list.get(0).replaceAll("Way2Automation\n", ""));
        return list;
    }

    /**
     * Скроллит страницу до нижней части.
     * Использует helper для выполнения JavaScript
     */
    @Step("Скролл страницы до нижней части.")
    public void scrollToBottom() {
        jsHelper.scrollToBottom();
    }

    /**
     * Скроллит страницу до верхней части.
     * Использует helper для выполнения JavaScript
     */
    @Step("Скролл страницы до верхней части.")
    public void scrollToTop() {
        jsHelper.scrollToTop();
    }

    /**
     * Выполняет скрипт для закрытия всплывающего окна.
     * В данном случае использует прокрутку клавишами.
     */
    @Step("Закрываем всплывающее окно")
    public HomePage closePopUp() {
        jsHelper.scrollByKeyboard();
        Waiters.waitTimeForVisibilityOfElement(driver, closePopUpButton);
        closePopUpButton.click();
        return this;
    }

    /**
     * Переходит на страницу "Lifetime Membership" через меню "All Courses".
     * Использует Actions для перемещения мыши и клик по элементу.
     * @return объект страницы MembershipPage
     */
    @Step("Открываем страницу Lifetime Membership через меню All Courses")
    public MembershipPage getMembershipPage() {
        Actions actions = new Actions(driver);
        actions.moveToElement(catalogAllCourses).perform();
        membershipPage.click();
        return new MembershipPage(driver);
    }
}