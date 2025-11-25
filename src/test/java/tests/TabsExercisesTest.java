package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.TabsExercisesPage;

import java.util.Iterator;
import java.util.Set;

public class TabsExercisesTest extends BaseTest {
    private WebDriver driver;
    TabsExercisesPage tabsExercisesPage;

    @BeforeMethod(description = "Открываем страницу Frames And Windows")
    public void setUrl() {
        driver = getDriver();
        tabsExercisesPage = new TabsExercisesPage(driver);
    }

    @Test(description = "Проверка переключения фокуса между вкладками")
    @Epic("Работа с вкладками и окнами браузера")
    @Feature("Переключение и управление вкладками")
    @Story("Переключение фокуса между вкладками")
    @Severity(SeverityLevel.CRITICAL)
    public void switchFocusBetweenTabsTest() {
        tabsExercisesPage.switchToFrame();
        tabsExercisesPage.switchToTab();
        tabsExercisesPage.switchToTab();

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();

        driver.switchTo().window(tabsExercisesPage.getBrowserTabByNumber(iterator, driver.getWindowHandles().size()));

        softAssert.assertEquals(tabsExercisesPage.getTabIndexByHandle(handles), 3);
        softAssert.assertEquals(driver.getCurrentUrl(), "https://way2automation.com/way2auto_jquery/frames-windows/defult1.html#");
        softAssert.assertAll();
    }
}
