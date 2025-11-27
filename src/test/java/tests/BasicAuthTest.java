package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasicAuthPage;

public class BasicAuthTest extends BaseTest{
    private WebDriver driver;
    BasicAuthPage basicAuthPage;

    @BeforeMethod(description = "Открываем страницу HTTP Authentication")
    public void setUrl() {
        driver = getDriver();
        basicAuthPage = new BasicAuthPage(driver);
    }

    @Test(description = "Проверка авторизации и отображения изображения")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Авторизация через HTTP Authentication")
    @Feature("Проверка доступа к защищенному ресурсу")
    @Story("Проверка успешной авторизации и отображения изображения")
    public void checkBasicAuthTest() {
        basicAuthPage.clickDisplayImage();
        basicAuthPage.getAuthorise();

        Assert.assertNotNull(basicAuthPage.checkImgSrc(), "Авторизация прошла не успешно");
    }
}