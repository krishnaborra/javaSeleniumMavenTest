#!/bin/bash
echo " --------------------- STARTED  - TAI acceptance test suite with CHROME Browser proxying through Zap in Local -------------------------"
export DISPLAY=${DISPLAY=":99"}
mvn -Dbrowser=chrome -DzapEnabled=true -Dtest=RunSuite
echo " -------------------- ENDED - TAI acceptance test suite -----------------------------"
echo " --------------------- STARTED - ZAP SCAN -------------------------"
mvn -Dlogback.configurationFile=logback.xml -Dtest=ZapRunner
echo " -------------------- ENDED - ZAP SCAN-----------------------------"