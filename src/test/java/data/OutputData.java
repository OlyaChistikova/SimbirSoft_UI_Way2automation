package data;

import helpers.PropertyProvider;

import java.util.Arrays;
import java.util.List;

/**
 * Класс, содержащий ожидаемые данные для сравнения в тестах.
 * Включает списки контактов, соцсетей, блогов и сообщения.
 */
public class OutputData {

    /**
     * Ожидаемые номера телефонов и контакты для раздела "Контакты"
     */
    public static final List<String> EXPECTED_CONTACTS_HEADER = Arrays.asList(
            "+919711-111-558",
            "+919711-191-558",
            "+1 646-480-0603",
            "seleniumcoaching",
            "trainer@way2automation.com"
    );

    /**
     * Ожидаемые ссылки на мессенджеры или соцсети
     */
    public static final List<String> EXPECTED_MESSENGER_HEADER = Arrays.asList(
            "https://www.facebook.com/way2automation",
            "https://in.linkedin.com/in/rahul-arora-0490b751",
            "https://plus.google.com/u/0/+RamanAhujatheseleniumguru",
            "https://www.youtube.com/c/seleniumappiumtutorialtraining"
    );

    /**
     * Ожидаемые пункты меню или разделы блога
     */
    public static final List<String> EXPECTED_BLOG_HEADER = Arrays.asList(
            "Home",
            "All Courses",
            "Video Tutorial",
            "Resources",
            "Careers",
            "Lifetime Membership",
            "Blog",
            "Forum",
            "Member Login"
    );

    /**
     * Ожидаемые данные в футере раздела "Контакты"
     */
    public static final List<String> EXPECTED_CONTACTS_FOOTER = Arrays.asList(
            "CDR Complex, 3rd Floor, Naya Bans Market, Sector 15, Noida, Near sec-16 Metro Station",
            "+91 97111-11-558",
            "+91 97111-91-558",
            "trainer@way2automation.com",
            "seleniumcoaching@gmail.com"
    );

    /**
     * Ожидаемое название страницы или раздела "Lifetime Мembership"
     */
    public static String membershipTitle = PropertyProvider.getProperty("lifetime-membership.title");

    /**
     * Ожидаемый заголовок страницы успешной авторизации
     */
    public static String successAuthTitle = PropertyProvider.getProperty("success-auth-title");

    /**
     * Сообщение при неуспешной авторизации
     */
    public static String unsuccessAuthMessage = PropertyProvider.getProperty("unsuccess-auth-message");

    /**
     * Сообщение при успешной регистрации
     */
    public static String successRegistrMessage = PropertyProvider.getProperty("success-registr-message");

    /**
     * Сообщение об алерте при добавлении клиента
     */
    public static String addCutAllertMessage = PropertyProvider.getProperty("addCustAllertMessage");

    /**
     * Сообщение при открытии алерта клиента
     */
    public static String openCustAllertMessage = PropertyProvider.getProperty("openCustAllertMessage");
}
