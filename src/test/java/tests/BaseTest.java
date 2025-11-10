package tests;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

@Getter
public class BaseTest {
    private WebDriver driver;
    protected boolean useIncognito = false;

    @BeforeClass(description = "Настройка браузера перед запуском тестов")
    public void setUp(){
        driver = createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    protected WebDriver createDriver() {
        return new ChromeDriver();
    }

    @AfterClass(description = "Закрываем браузер")
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}