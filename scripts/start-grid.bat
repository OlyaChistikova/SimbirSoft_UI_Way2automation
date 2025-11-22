@echo off
START /B java -jar scripts/selenium-server-4.38.0.jar hub
timeout /t 5 /nobreak
START /B java -jar scripts/selenium-server-4.38.0.jar node --hub http://localhost:4444/grid/register
START /B java -jar scripts/selenium-server-4.38.0.jar node --hub http://localhost:4444/grid/
echo "Selenium Grid запущен"
pause