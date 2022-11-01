# java_concurrency
Learning About Concurrency in Java with Examples.


### To Learn Junit testing, 

* added junit-5.11.0 as dependency.
* Basic testing code in com.rahulg.testing package.
* To run all unit tests
```$xslt
mvn test
```
* we have also used Juint5 in the core classes for ease of testing the code.

* To compile code while skipping unit test:
```$xslt
mvn -DskipTests=true package

or
mvn -Dmaven.test.skip=true package

or using a skiptestProfile 
mvn  package -PskiptestProfile
```
