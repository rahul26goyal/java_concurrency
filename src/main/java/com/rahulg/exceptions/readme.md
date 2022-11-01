# Java Exceptions
[Reference](https://www.baeldung.com/java-exceptions)
An exception is an event that disturbs the normal flow of the program's execution.

- Java exceptions fall into two main categories: checked exceptions and unchecked exceptions.
- checked exceptions represent errors outside the control of the program. 
- Java verifies checked exceptions at compile-time.
- The Exception class is the superclass of checked exceptions, so we can create a custom checked exception by extending Exception:
- functions throwing checked exception much declare in the signature
```java
public class IncorrectFileNameException extends Exception {
    public IncorrectFileNameException(String errorMessage) {
        super(errorMessage);
    }
}
```
- If a program throws an unchecked exception, it reflects some error inside the program logic.
-  if we divide a number by 0, Java will throw ArithmeticException:
- Java does not verify unchecked exceptions at compile-time.
-  we don't have to declare unchecked exceptions in a method with the throws keyword.
- The RuntimeException class is the superclass of all unchecked exceptions, so we can create a custom unchecked exception by extending RuntimeException:

```java
public class NullOrEmptyException extends RuntimeException {
    public NullOrEmptyException(String errorMessage) {
        super(errorMessage);
    }
}
```

Note: 
**“If a client can reasonably be expected to recover from an exception, make it a checked exception.**

**If a client cannot do anything to recover from the exception, make it an unchecked exception.”**

## Chained Exceptions
- It is usefull to  easily compare shown logs and conclude that the chained exceptions lead to cleaner logs.
- chined exception is used when we create a create wrapper exceptions..
- propagating the exception stace is always helpful..