package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionPageHelper {
    private static Actions actions;

    /**
     * Функция перемещения элемента c помощью DragAndDrop
     * @param draggable - перемещаемый элемент
     * @param droppable - элемент в область которого будет перемещён draggable элемент
     * */
    @Step("Перемещение элемента с одного места в другое")
    public static void dragAndDrop(WebDriver driver, WebElement draggable, WebElement droppable){
        actions = new Actions(driver);
        actions.dragAndDrop(draggable, droppable).perform();
    }
}
