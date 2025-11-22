package tests;

import data.InputData;
import helpers.JavaScriptExecutorHelper;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

@Getter
public class BaseTest {
    private WebDriver driver;
    protected boolean useIncognito = false;
    protected SoftAssert softAssert;

    @BeforeMethod(description = "Настройка браузера перед запуском тестов", enabled = false)
    public void setUp(){
        driver = createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        softAssert = new SoftAssert();
    }

    @BeforeMethod(description = "СетАп для параллельного тестирования", enabled = true)
    public void parallelSetUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.WIN11);
        capabilities.setBrowserName("chrome");
        try{
            driver = new RemoteWebDriver(new URL(InputData.getGridHubURL()), capabilities);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    protected WebDriver createDriver() {
        return new ChromeDriver();
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