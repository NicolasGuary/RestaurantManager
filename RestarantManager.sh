#!/bin/bash
javac -cp src/lib/mysql-connector-java-5.1.47.jar $(find . -name "*.java")
cd src
java -cp .:lib/mysql-connector-java-5.1.47.jar ui.GUI

rm $(find . -name "*.class")
