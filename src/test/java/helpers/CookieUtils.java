package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class CookieUtils {

    /**
     * Функция сохранения cookies со страницы в файл json
     * @param driver - объект вебдрайвера
     * @param filePath - путь сохранения файла
     * */
    @Step("Сохранение cookie в файл json")
    public static void saveCookiesToFile(WebDriver driver, String filePath) {
        Set<Cookie> cookies = driver.manage().getCookies();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(cookies);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Функция чтения cookies из файла json
     * @param driver - объект вебдрайвера
     * @param filePath - путь к сохранённому файлу
     * */
    @Step("Чтение cookie из файла json")
    public static void loadCookiesFromFile(WebDriver driver, String filePath) {
        Type type = new TypeToken<HashSet<Cookie>>() {}.getType();
        try{
            Set<Cookie> cookiesFromFile = new Gson().fromJson(new FileReader(filePath), type);
            for (Cookie cookie : cookiesFromFile) {
                driver.manage().addCookie(cookie);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
