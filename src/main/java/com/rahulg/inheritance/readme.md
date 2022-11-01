# JAVA Inheritance

- a class can inherit another class and multiple interfaces, while an interface can inherit other interfaces.
- in the absence of an extends keyword, a class implicitly inherits class java.lang.Object.
- A subclass class inherits the non-static protected and public members from the superclass class. In addition, the members with default (package-private) access are inherited if the two classes are in the same package.
- If subtypes fulfill the “is-a” condition and mainly provide additive functionality further down the classes hierarchy, then inheritance is the way to go.

- we can use the super keyword to access the parent class.
- we can use `this` keywork to access the object's member variables and functions.


  Read here about all scenarions with class and interface [java inheritance](https://www.baeldung.com/java-inheritance)


# Java Compositions

- In a nutshell, composition allows us to model objects that are made up of other objects, thus defining a “has-a” relationship between them.

- Furthermore, the composition is the strongest form of association, which means that the object(s) that compose or are contained by one object are destroyed too when that object is destroyed.

- It's easy to understand the motivations behind pushing composition over inheritance. In every scenario where it's possible to establish a semantically correct “has-a” relationship between a given class and others, the composition is the right choice to make.

- A computer has a processor, memory, disk, soundcard etc. So, define each object separatey and use composition to group it.

