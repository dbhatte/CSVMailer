# CSVMailer

- This program takes a filepath as a parameter and mails to the recipients
- It uses Executors.newWorkStealingPool as its an implementation of ForkJoinPool using all available processor core
  This will firstly allow us to scale according to the number of available cores
  Also, this allows the least amount of idling of threads as idle threads steal work from busy threads
- I have added comments in the source code for details.

## Requirements

- JDK 8
- Maven

## To compile
```
mvn compile
mvn package
```
## To run
```
java -jar target/csv-mailer-1.0-SNAPSHOT.jar <PATH_TO_FILE>/fileName.csv
```

