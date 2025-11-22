@echo off

:: Проверяем существование файла с упавшими тестами
if exist "target\surefire-reports\testng-failed.xml" (
    call mvn clean test -P target/surefire-reports/testng-failed.xml
)