package data;

import helpers.PropertyProvider;

public class InputData {

    // Имя пользователя для авторизации в системе
    public static final String validUsernameAuthorisation = PropertyProvider.getProperty("usernameAuthorisation");

    // Пароль для авторизации
    public static final String validPasswordAuthorisation = PropertyProvider.getProperty("passwordAuthorisation");

    // Имя для регистрации нового пользователя
    public static final String firstNameRegistration = PropertyProvider.getProperty("firstNameRegistration");

    // Фамилия для регистрации
    public static final String lastNameRegistration = PropertyProvider.getProperty("lastNameRegistration");

    // Электронная почта для регистрации
    public static final String emailRegistration = PropertyProvider.getProperty("emailRegistration");

    // Пароль для регистрации
    public static final String passwordRegistration = System.getenv("REG_PASSWORD") != null ? System.getenv("REG_PASSWORD") : "";

    // Ожидаемое хобби при регистрации (используется для проверки или выбора)
    public static final String hobbyExpectedRegistration = PropertyProvider.getProperty("hobbyExpectedRegistration");

    // Пол для регистрации (например, "Male", "Female")
    public static final String genderRegistration = PropertyProvider.getProperty("genderRegistration");

    // Имя клиента для тестов
    public static final String firstNameCustomer = PropertyProvider.getProperty("firstNameCustomer");

    // Фамилия клиента для тестов
    public static final String lastNameCustomer = PropertyProvider.getProperty("lastNameCustomer");

    // Почтовый индекс клиента
    public static final String postCodeCustomer = PropertyProvider.getProperty("postCodeCustomer");

    // Валюта, используемая у клиента (например, "Pound", "Dollar")
    public static final String currencyCustomer = PropertyProvider.getProperty("currencyCustomer");

    // Сумма депозита для тестирования
    public static final String amountDeposit = PropertyProvider.getProperty("amountDeposit");
}
