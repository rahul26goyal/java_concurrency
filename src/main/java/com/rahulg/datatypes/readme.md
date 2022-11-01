## [Get Started with Java](https://www.baeldung.com/get-started-with-java-series)

# Java Data Types
1. Primitive Data Types
2. Object / Reference Data Types (String, Array, other class based objects.)

# Java Classes 
1. `Abstract Class`:An abstract class is a class that has unimplemented methods, though it can actually have both: 
2. In contrast, `Concrete classes` don't have any unimplemented methods. Whether the implementations are inherited or not, so long as each method has an implementation, the class is concrete.
3. that code reuse is a very compelling reason to use abstract classes, as long as the “is-a” relationship within the class hierarchy is preserved.
4. 

# Java Access Modifiers for classes.
[Reference](https://www.baeldung.com/java-access-modifiers)
Simply put, there are four access modifiers: `public, private, protected and default (no keyword)`.

Before we begin let's note that 
- a top-level class can use `public or default` access modifiers only. The default scopes the class to the  
- At the member level, we can use all four.
- The `default` access modifier is also called **package-private**, which means that all members are visible within the same package but aren't accessible from other packages:
- If we add the `public` keyword to a **class, method or property** then we're making it available to the whole world, i.e. all other classes in all packages will be able to use it. This is the least restrictive access modifier:
- Any method, property or constructor with the private keyword is accessible from the same class only. This is the most restrictive access modifier and is core to the concept of encapsulation. All data will be hidden from the outside world:
- If we declare a method, property or constructor with the protected keyword, we can access the member from the same package (as with package-private access level) and in addition from all subclasses of its class, even if they lie in other packages:

# Java Variable Scope
- Java object Instance and class variables don't require us to initialize them. As soon as we declare these variables, they are given a default value (0, null, etc).
- Local variables (declared within functions scope) must be initialized before use, as they don't have a default value and the compiler won't let us use an uninitialized value.
- The `final` keyword applied to a field means that the field's value can no longer be changed after initialization. In this way, we can define constants in Java.
- The `final` keyword when used with static variables become immutable. When used with instacne variable, we can assign value during the initialization of the object constructor.
- 

# Java Initialiers
- In Java, an initializer is a block of code that has no associated name or data type and is placed outside of any method, constructor, or another block of code.
- There are 2 types of initializer block code
    - Static Block
    - Instance Block
- In Java, the order for initialization statements is as follows:
    - static variables and static initializers in order
    - instance variables and instance initializers in order
    - constructors    

# Object Lify cycle
- All objects in Java are stored in our program's heap memory. In fact, the heap represents a large pool of unused memory, allocated for our Java application.
-  the garbage collector is a Java program that takes care of automatic memory management by deleting objects that are no longer reachable.

# Java Interfaces
-  an interface is an abstract type that contains a collection of methods and constant variables. It is one of the core concepts in Java and is used to achieve abstraction, polymorphism and multiple inheritances.


# Java Enums [Link](https://www.baeldung.com/a-guide-to-java-enums)
-  It denotes a special type of class that always extends the java.lang.Enum class.
- ENUMS are fixed list of constant with type associated. We can not create new enums at runtime.
- ENUMS can have  variables and methods associated which helps to add property to enum objects. 
- ENUMS can be used to define  singletone classes. 
- ENUMS are used to create Stragery  pattern classes as well.
- the enum type provides a valueOf() method that takes a String as an argument, and returns the corresponding enum object:



# Java `final` keyword
- Classes marked as final can’t be extended. If we look at the code of Java core libraries, we’ll find many final classes there. One example is the String class.
- Methods marked as final cannot be overridden. When we design a class and feel that a method shouldn’t be overridden, we can make this method final. We can also find many final methods in Java core libraries.
- Variables marked as final can't be reassigned. Once a final variable is initialized, it can’t be altered.
- If we have a final reference variable, we can’t reassign it either. But this doesn’t mean that the object it refers to is immutable. We can change the properties of this object freely.
- A final argument can’t be changed inside a method:
- Final fields can be either constants or write-once fields. Note that any final field must be initialized before the constructor completes.
- 



