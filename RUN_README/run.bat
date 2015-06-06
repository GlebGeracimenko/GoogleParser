cd.. &&
mvn clean &&
mvn package &&
cd target/ &&
java -jar googleParser-1.0-SNAPSHOT-jar-with-dependencies.jar &&
cmd /k