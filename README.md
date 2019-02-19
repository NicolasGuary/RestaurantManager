# RestaurantManager

Java architecture and JavaFX project.

## How to compile and execute RestaurantManager?
###### Please note: you might get warnings regarding JavaFX runtime, they can be ignored, otherwise you will need JavFX v10.0.1
###### As this projet use a JDBC connector, it should be included into the classpath.
###### You will require a Java version (like 1.8) that has JavaFX, otherwise you should import it
### First Method: Use the provided .sh scripts

If rights are not granted to execute the .sh scripts use: `chmod +x RestaurantManager.sh`
Then you can launch the project using `./RestaurantManager.sh` that you will find at root of the project.
After execution, scripts will clean the project from the .class files.

### Second Method: Compile and Launch in Terminal

From the RestaurantManager folder, that you can access with `cd RestaurantManager` from the directory containing our project, execute the Unix command line: `javac -cp src/lib/mysql-connector-java-5.1.47.jar $(find . -name "*.java")` in order to compile the whole project.
Now move to the src folder with `cd src`
Then you can launch the app by doing `java -cp .:lib/mysql-connector-java-5.1.47.jar ui.GUI`
OPTIONNAL : If you want to clean the projet from the .class file, you can do: `rm $(find . -name "*.class")`
