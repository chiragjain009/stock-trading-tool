# stock-trading-tool
Spring Boot Application Stock Trading Tool
# Spring Boot Application Super Simple Stock Market

## Built With

* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system 
* 	[Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.

## External Tools Used

* [Postman](https://www.getpostman.com/) - API Development Environment (Testing Documentation)

## To-Do

- [x] Logger (Console, File, Mail)
- [x] RESTful Web Service (CRUD)
- [x] Swagger Documentation
- [x] Junit Tests
- [x] Integration Tests

## Running the application locally

There are several ways to run `Super Simple Stock Market` on your local machine. One way is to execute the `main` method in the `com.gbce.stock.market.StockMarketApplication` class from your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```


## Documentation

* [Swagger](http://localhost:8080/swagger-ui.html) - Documentation & Testing
