package tests;

import data.EndPoint;
import data.OutputData;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MembershipPage;

public class HomePageTest extends BaseTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod(description = "Открываем страницу way2automation")
    public void setUrl() {
        driver = getDriver();
        homePage = new HomePage(driver);
    }

    @Test(description = "Проверка наличия всех основных элементов страницы")
    @Epic("Главная страница сайта")
    @Feature("Основные элементы страницы")
    @Story("Проверка отображения ключевых элементов")
    @Severity(SeverityLevel.CRITICAL)
    public void checkOpenPageTest() {
        Assert.assertTrue(homePage.checkDisplayHeader(), "Header not found");
        Assert.assertTrue(homePage.checkDisplayBlockNavigation(), "Block Navigation not found");
        Assert.assertTrue(homePage.checkDisplayRegistrationButton(), "Registration Button not found");
        Assert.assertTrue(homePage.checkDisplayCoursesList(), "Courses List not found");
        homePage.closePopUp().scrollToBottom();
        Assert.assertTrue(homePage.checkDisplayFooter(), "Footer not found");
    }

    @Test(description = "Проверка наличия контактной информации верхнего меню")
    @Epic("Главная страница сайта")
    @Feature("Контактная информация")
    @Story("Проверка отображения контактных данных в верхней части страницы")
    @Severity(SeverityLevel.NORMAL)
    public void displayAndCheckContactInfoHeaderTest() {
        Assert.assertTrue(homePage.checkDisplayHeader(), "Header not found");
        Assert.assertEquals(homePage.getContactInfoHeader(), OutputData.EXPECTED_CONTACTS_HEADER);
        Assert.assertEquals(homePage.getMessengerInfoHeader(), OutputData.EXPECTED_MESSENGER_HEADER);
    }

    @Test(description = "Проверка наличия верхнего меню (блока с навигацией)")
    @Epic("Главная страница сайта")
    @Feature("Навигационное меню")
    @Story("Проверка отображения блока навигации")
    @Severity(SeverityLevel.NORMAL)
    public void checkNavigationBlock() {
        Assert.assertTrue(homePage.checkDisplayBlockNavigation());
        Assert.assertEquals(homePage.getBlockNavigationText(), OutputData.EXPECTED_BLOG_HEADER);
        //Проверка кнопок навигации (вперед и назад): работают корректно (меняют слайды) - не работают
    }

    @Test(description = "Проверка наличия меню навигации после скролла")
    @Epic("Главная страница сайта")
    @Feature("Навигационное меню")
    @Story("Проверка отображения блока навигации после прокрутки")
    @Severity(SeverityLevel.MINOR)
    public void checkNavigationBlockAfterScrollingTest() {
        homePage.closePopUp().scrollToBottom();
        Assert.assertTrue(homePage.checkDisplayBlockNavigation(), "Header not found");
        Assert.assertEquals(homePage.getBlockNavigationText(), OutputData.EXPECTED_BLOG_HEADER);
    }

    @Test(description = "Проверка наличия контактной информации нижнего меню")
    @Epic("Главная страница сайта")
    @Feature("Контактная информация")
    @Story("Проверка отображения контактных данных в нижней части страницы")
    @Severity(SeverityLevel.NORMAL)
    public void displayAndCheckContactInfoFooterTest() {
        homePage.closePopUp().scrollToBottom();
        Assert.assertTrue(homePage.checkDisplayFooter());
        Assert.assertEquals(homePage.getFooterContactInfo(), OutputData.EXPECTED_CONTACTS_FOOTER);
    }

    @Test(description = "Проверка перехода по меню навигации на другие страницы")
    @Epic("Главная страница сайта")
    @Feature("Навигация")
    @Story("Переход на страницу 'Membership'")
    @Severity(SeverityLevel.CRITICAL)
    public void openMembershipPage() {
        homePage.closePopUp();
        MembershipPage membershipPage = homePage.getMembershipPage();
        Assert.assertEquals(membershipPage.getTitlePage(), OutputData.membershipTitle);
        Assert.assertEquals(driver.getCurrentUrl(), EndPoint.MEMBERSHIP.getUrl());
    }

    @Test(description = "Проверка ожидания неправильного заголовка страницы")
    @Epic("Главная страница сайта")
    @Feature("Навигация")
    @Story("Заголовок страницы 'Membership'")
    @Severity(SeverityLevel.MINOR)
    public void checkTitleMembershipPage() {
        homePage.closePopUp();
        MembershipPage membershipPage = homePage.getMembershipPage();
        softAssert.assertEquals(membershipPage.getTitlePage(), "Membership");
        softAssert.assertEquals(driver.getCurrentUrl(),  EndPoint.MEMBERSHIP.getUrl());
        softAssert.assertAll();
    }
}