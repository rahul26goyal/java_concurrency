# java_concurrency
Learning About Concurrency in Java with Examples.


### To Learn Junit testing, 

* added junit-5.1.0 as dependency.
* Basic testing code in com.rahulg.testing package.
* To run all unit tests
```$xslt
mvn test
```

* To compile code while skipping unit test:
```$xslt
mvn -DskipTests=true package

or
mvn -Dmaven.test.skip=true package

or using a skiptestProfile 
mvn  package -PskiptestProfile
```
