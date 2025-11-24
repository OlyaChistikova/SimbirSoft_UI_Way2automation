package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DragAndDropPage;

public class DragAndDropTest extends BaseTest{
    private WebDriver driver;
    private DragAndDropPage dragAndDropPage;

    @BeforeMethod(description = "Открываем страницу Droppable")
    @Epic("Drag and Drop функциональность")
    @Feature("Перетаскивание элементов")
    @Story("Перетаскивание элемента в область droppable")
    public void setUrl() {
        driver = getDriver();
        dragAndDropPage = new DragAndDropPage(driver);
    }

    @Test(description = "Проверка перетаскивания элемента и текста после")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Drag and Drop функциональность")
    @Feature("Перетаскивание элементов")
    @Story("Выполнение drag-and-drop и проверка результата")
    public void dragAndDropTest() {
        dragAndDropPage.switchToFrame();
        dragAndDropPage.dragAndDrop();
        Assert.assertEquals(dragAndDropPage.textDroppableElement(), "Dropped!", "Текст после перетаскивания не совпадает");
    }
}