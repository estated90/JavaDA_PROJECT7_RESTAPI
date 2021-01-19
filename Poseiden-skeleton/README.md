# Poseidon
## Technical:

1. Framework: Spring Boot v2.0.4
2. Java 8
3. Thymeleaf
4. Bootstrap v.4.3.1
5. Maven 3.6.3
6. MySql

## Installing

1. Install Java: https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
2. Install Maven: https://maven.apache.org/install.html
3. Install MySql: https://dev.mysql.com/downloads/installer/


## Setup with Intellij IDE
1. Create project from Initializr: File > New > project > Spring Initializr
2. Add lib repository into pom.xml
3. Add folders
    - Source root: src/main/java
    - View: src/main/resources
    - Static: src/main/resource/static
4. Create database with name "demo" as configuration in application.properties
5. Create database with name "test" as configuration in application-test.properties
6. Run sql script to create table doc/data.sql

## Setup with Eclipse

1. Create project from Initializr: File > Import> Maven> Existing Project
2. Create database with name "demo" as configuration in application.properties
3. Create database with name "test" as configuration in application-test.properties
4. Run sql script to create table doc/data.sql

## Running App

1. With a command prompt of your choice move to the root of project
2. Type: cd Poseiden-skeleton\
3. Use command: mvn spring-boot:run

## Testing

1. With a command prompt of your choice move to the root of project
2. Type: cd Poseiden-skeleton\
3. Use command: mvn test