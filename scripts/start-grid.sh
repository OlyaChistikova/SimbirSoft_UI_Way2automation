#!/bin/bash
java -jar scripts/selenium-server-4.38.0.jar hub &

sleep 5

java -jar scripts/selenium-server-4.38.0.jar node --hub http://localhost:4444/grid/register &
java -jar scripts/selenium-server-4.38.0.jar node --hub http://localhost:4444/grid/register &