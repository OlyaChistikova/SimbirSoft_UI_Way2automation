package tests;

import helpers.EndPoint;
import helpers.OutputData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MembershipPage;

import static helpers.EndPoint.HOME;

public class HomePageTest extends BaseTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void setUrl() {
        driver = getDriver();
        driver.get(HOME.getUrl());
        homePage = new HomePage(driver);
    }

    @Test(description = "Проверка наличия всех основных элементов страницы", priority = 1)
    public void checkOpenPageTest() {
        Assert.assertTrue(homePage.checkDisplayHeader(), "Header not found");
        Assert.assertTrue(homePage.checkDisplayBlockNavigation(), "Block Navigation not found");
        Assert.assertTrue(homePage.checkDisplayRegistrationButton(), "Registration Button not found");
        Assert.assertTrue(homePage.checkDisplayCoursesList(), "Courses List not found");
        homePage.closePopUp().scrollToBottom();
        Assert.assertTrue(homePage.checkDisplayFooter(), "Footer not found");
    }

    @Test(description = "Проверка наличия контактной информации верхнего меню", priority = 2)
    public void displayAndCheckContactInfoHeaderTest() {
        Assert.assertTrue(homePage.checkDisplayHeader(), "Header not found");
        Assert.assertEquals(homePage.getContactInfoHeader(), OutputData.EXPECTED_CONTACTS_HEADER);
        Assert.assertEquals(homePage.getMessengerInfoHeader(), OutputData.EXPECTED_MESSENGER_HEADER);
    }

    @Test(description = "Проверка наличия верхнего меню (блока с навигацией)", priority = 3)
    public void checkNavigationBlock() {
        Assert.assertTrue(homePage.checkDisplayBlockNavigation());
        Assert.assertEquals(homePage.getBlockNavigationText(), OutputData.EXPECTED_BLOG_HEADER);
        //Проверка кнопок навигации (вперед и назад): работают корректно (меняют слайды) - не работают
    }

    @Test(description = "Проверка наличия меню навигации после скролла", priority = 4)
    public void checkNavigationBlockAfterScrollingTest() {
        homePage.scrollToBottom();
        Assert.assertTrue(homePage.checkDisplayBlockNavigation(), "Header not found");
        Assert.assertEquals(homePage.getBlockNavigationText(), OutputData.EXPECTED_BLOG_HEADER);
    }

    @Test(description = "Проверка наличия контактной информации нижнего меню", priority = 5)
    public void displayAndCheckContactInfoFooterTest() {
        homePage.scrollToBottom();
        Assert.assertTrue(homePage.checkDisplayFooter());
        Assert.assertEquals(homePage.getFooterContactInfo(), OutputData.EXPECTED_CONTACTS_FOOTER);
    }

    @Test(description = "Проверка перехода по меню навигации на другие страницы", priority = 6)
    public void openMembershipPage() {
        MembershipPage membershipPage = homePage.getMembershipPage();
        Assert.assertEquals(membershipPage.getTitlePage(), OutputData.membershipTitle);
        Assert.assertEquals(driver.getCurrentUrl(), EndPoint.MEMBERSHIP.getUrl());
    }

    @AfterMethod
    public void scrollToTop() {
        driver.manage().deleteAllCookies();
        homePage.scrollToTop();
    }
}