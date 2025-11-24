package pages;

import data.EndPoint;
import helpers.ActionPageHelper;
import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragAndDropPage {
    private WebDriver driver;
    private static String URL = EndPoint.DRAG_AND_DROP.getUrl();

    // Ифрейм, содержащий перетаскиваемые элементы
    @FindBy(xpath = "//iframe[@src='droppable/default.html']")
    private WebElement iFrame;

    // Перетаскиваемый элемент, который нужно перетащить
    @FindBy(xpath = "//div[@id='draggable']")
    private WebElement draggableElement;

    // Область, куда перетаскивается элемент
    @FindBy(xpath = "//div[@id='droppable']")
    private WebElement droppableElement;

    // Текст внутри области droppable
    @FindBy(xpath = "//div[@id='droppable']/p")
    private WebElement droppableText;

    /**
     * Объект страницы Droppable
     */
    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get(URL);
    }

    /**
     * Переключение в iframe, содержащий перетаскиваемые элементы
     */
    @Step("Переключиться в iframe с перетаскиваемыми элементами")
    public void switchToFrame() {
        driver.switchTo().frame(this.iFrame);
    }

    /**
     * Выполнение операции drag-and-drop для перетаскивания элемента
     */
    @Step("Выполнить перетаскивание элемента в область")
    public void dragAndDrop() {
        Waiters.waitTimeForVisibilityOfElement(driver, droppableElement);
        Waiters.waitTimeForVisibilityOfElement(driver, draggableElement);
        ActionPageHelper.dragAndDrop(driver, draggableElement, droppableElement);
    }

    /**
     * Получение текста внутри области droppable
     * @return текст внутри droppable элемента
     */
    @Step("Получить текст внутри droppable элемента")
    public String textDroppableElement() {
        return droppableText.getText();
    }
}
