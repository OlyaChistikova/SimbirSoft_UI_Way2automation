package pages;

import data.EndPoint;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class BasicAuthPage {
    private WebDriver driver;
    private static String URL = EndPoint.BASIC_AUTH.getUrl();

    /**
     * Локатор элемента кнопки Display Image
     */
    @FindBy(xpath = "//input[@class='button2 buttonspace']")
    public WebElement displayImageButton;

    /**
     * Локатор элемента картинки успешной авторизации
     */
    @FindBy(xpath = "//img[@id='downloadImg']")
    public WebElement authenticatedImage;

    /**
     * Объект страницы Basic Authentication
     */
    public BasicAuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get(URL);
    }

    /**
     * Клик по кнопке "Display Image"
     */
    @Step("Клик по кнопке 'Display Image'")
    public void clickDisplayImage(){
        displayImageButton.click();

    }

    /**
     * Выполнение автоматизированной авторизации через внешнюю программу
     */
    @Step("Выполнить автоматическую авторизацию через скрипт")
    public void getAuthorise(){
        try {
            Runtime.getRuntime().exec("scripts/authenticationScriptAutoIt.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получение атрибута src изображения для проверки успешной авторизации
     * @return src изображения
     */
    @Step("Получить src изображения для проверки авторизации")
    public String checkImgSrc(){
        return authenticatedImage.getAttribute("src");
    }
}
