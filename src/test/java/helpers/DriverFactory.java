package helpers;

import data.InputData;
import io.qameta.allure.Step;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    static WebDriver driver;

    @Step("Создать локальный драйвер для браузера: {browser}")
    public static WebDriver createDriver(String browser){
        String versionBrowser = InputData.getVersionBrowser();
        switch (browser.toLowerCase()){
            case ("chrome"):
                ChromeOptions cdOptions = new ChromeOptions();
//                    cdOptions.addArguments("--incognito");
                cdOptions.setBrowserVersion(versionBrowser);
                driver = new ChromeDriver();
                break;
            case ("firefox"):
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.setBrowserVersion(versionBrowser);
                driver = new FirefoxDriver(ffOptions);
                break;
            case ("edge"):
                EdgeOptions meOptions = new EdgeOptions();
                meOptions.setBrowserVersion(versionBrowser);
                driver = new EdgeDriver();
                break;
            case ("ie"):
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability("ignoreProtectedModeSettings", true);
                ieOptions.ignoreZoomSettings();
                ieOptions.introduceFlakinessByIgnoringSecurityDomains();
                driver = new InternetExplorerDriver(ieOptions);
                break;
            default:
                throw new IllegalArgumentException("Некорректный браузер: " + browser);
        }
        return driver;
    }

    @Step("Создать удаленный драйвер для браузера: {browser}")
    public static WebDriver createGridDriver(String browser) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.WIN11);
            switch (browser) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                case "ie":
                    capabilities.setBrowserName("internet explorer");
                    capabilities.setCapability("ignoreProtectedModeSettings", true);
                    break;
            }
        return new RemoteWebDriver(new URL(InputData.getGridHubURL()), capabilities);
    }
}