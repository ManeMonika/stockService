# Stock Data Service

Service for basic crud oprations of stock data

## Getting Started

### Dependencies

* JDK 11
* Spring Boot 2.7.5
* Apache Maven 3.8.6 
* MongoDb
* Windows 10

### Installing

* Data Imported from Collection [Dow Jones Index from 2011](http://archive.ics.uci.edu/ml/datasets/Dow+Jones+Index#)
* This data file(.csv) can be added to classpath in order to bulk upload it into database on server startup.
* Update application.properties for specific values ex. database details

### Executing program

* mvn clean install
* mvn spring-boot:run
* API are accessible using Potsman or http://localhost:8080/swagger-ui/index.html

## Version History

* 0.1
    * Initial Release to add and retrieve the stock data

## License


## Acknowledgments
* [Dow Jones Index from 2011](http://archive.ics.uci.edu/ml/datasets/Dow+Jones+Index#)



