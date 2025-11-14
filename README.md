# SimbirSoft_UI_Way2automation

## Описание 
Этот проект направлен на автоматизацию тестирования веб-приложения "Way2Automation Banking App", сайта https://www.way2automation.com/ и формы регистрации https://www.way2automation.com/angularjs-protractor/registeration/#/login с использованием Java, Selenium WebDriver и TestNG.

## Технологии и версии

- **Java**: '17'
- **Selenium WebDriver**: '4.38.0'
- **TestNG**: '7.11.0'
- **ChromeDriver**: '142.0.7444.60'
- **Maven**: '4.0.0'
- **Allure**: '2.24.0'

## Запуск тестов 

- **Way2AutomationUI_Tests** - mvn clean test -P tests_default
- **Way2AutomationUI_Authorization_DataProvider** - mvn clean test -P tests_authorisation
- **Way2AutomationUI_Error_Screen** - mvn clean test -P tests_error_screen

## Формирование отчета

- allure serve