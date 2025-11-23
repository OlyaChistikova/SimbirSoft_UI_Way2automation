package tests;

import data.InputData;
import helpers.DriverFactory;
import helpers.JavaScriptExecutorHelper;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;

@Getter
public class BaseTest {
    private WebDriver driver;
    protected boolean useIncognito = false;
    protected SoftAssert softAssert;

    @BeforeMethod(description = "Настройка браузера перед запуском тестов", enabled = true)
    public void setUp(){
        driver = DriverFactory.createDriver(InputData.getBrowser());
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        softAssert = new SoftAssert();
    }

    @BeforeMethod(description = "СетАп для параллельного тестирования", enabled = false)
    public void parallelSetUp() {
        try{
            driver = DriverFactory.createGridDriver(InputData.getBrowser());
        } catch (IOException ex){
            ex.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Step("Делаем скриншот в случае провала теста")
    public void takeScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            JavaScriptExecutorHelper.takeScreenshot(driver);
        }
    }

    @AfterMethod(description = "Закрываем браузер")
    public void tearDown(ITestResult result){
        takeScreenshotOnFailure(result);
        if (driver != null){
            driver.quit();
        }
    }
}