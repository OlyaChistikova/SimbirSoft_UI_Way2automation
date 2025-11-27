package data;

import helpers.PropertyProvider;
import lombok.Getter;

/**
 * Перечисление конечных точек (URL) приложения.
 * Каждая конечная точка соответствует определенному ресурсу в веб-приложении.
 */
@Getter
public enum EndPoint {
    HOME(PropertyProvider.getProperty("home.url")),
    MEMBERSHIP(PropertyProvider.getProperty("home.url").concat("lifetime-membership-club/")),
    AUTHORISATION(PropertyProvider.getProperty("home.url").concat("angularjs-protractor/registeration/#/login")),
    BANKING(PropertyProvider.getProperty("home.url").concat("angularjs-protractor/banking/#/login")),
    REGISTRATION(PropertyProvider.getProperty("home.url").concat("angularjs-protractor/banking/registrationform.html")),
    BANK_MANAGER(PropertyProvider.getProperty("home.url").concat("angularjs-protractor/banking/#/manager")),
    ADD_CUSTOMER(BANK_MANAGER.getUrl().concat("/addCust")),
    OPEN_ACCOUNT(BANK_MANAGER.getUrl().concat("/openAccount")),
    CUSTOMERS(BANK_MANAGER.getUrl().concat("/list")),
    CUSTOMER_LOGIN(PropertyProvider.getProperty("home.url").concat("angularjs-protractor/banking/#/customer")),
    ACCOUNT(PropertyProvider.getProperty("home.url").concat("angularjs-protractor/banking/#/account")),
    TRANSACTIONS(PropertyProvider.getProperty("home.url").concat("angularjs-protractor/banking/#/listTx")),
    SQL(PropertyProvider.getProperty("sql.url")),
    TABS(PropertyProvider.getProperty("tabs.url")),
    DRAG_AND_DROP(PropertyProvider.getProperty("drag.drop.url")),
    ALERTS(PropertyProvider.getProperty("alerts.url")),
    BASIC_AUTH(PropertyProvider.getProperty("basic_auth"));

    private final String url;

    /**
     * Конструктор перечисления, который инициализирует URL для каждой конечной точки.
     *
     * @param url строка, представляющая URL конечной точки.
     */
    EndPoint(String url) {this.url = url;}
}