
# Java ArrayList
- ArrayList resides within Java Core Libraries `java.util.arrayList`
- ArrayList is one of the List implementations built atop an array, which is able to dynamically grow and shrink as you add/remove elements. 
- good practice to use generic interface List as a variable declaration type, because it decouples it from a particular implementation.
- 

# Java LinkedLists
- LinkedList is a doubly-linked list implementation of the List and Deque interfaces.
- It is not synchronized (Not thread safe)
- Every element is a node, which keeps a reference to the next and previous ones
- It maintains insertion order
```java
LinkedList<Object> linkedList = new LinkedList<>();
```
# Java Comparable vs  Comparator
- The Comparable interface is a good choice to use for defining the default ordering, or in other words, if it's the main way of comparing objects.

So why use a Comparator if we already have Comparable?
- Sometimes we can't modify the source code of the class whose objects we want to sort, thus making the use of Comparable impossible
- Using Comparators allows us to avoid adding additional code to our domain classes
- We can define multiple different comparison strategies, which isn't possible when using Comparable

# Java Generics

Generic methods:
- Generic methods have a type parameter (the diamond operator enclosing the type) before the return type of the method declaration.
- Type parameters can be bounded
- Generic methods can have different type parameters separated by commas in the method signature.
- Method body for a generic method is just like a normal method.
```java
public <T> List<T> fromArrayToList(T[] a) {   
    return Arrays.stream(a).collect(Collectors.toList());
}
```
```java
public static <T, G> List<G> fromArrayToList(T[] a, Function<T, G> mapperFunction) {
    return Arrays.stream(a)
      .map(mapperFunction)
      .collect(Collectors.toList());
}
```

# Deep copy vs shallow copy 
- https://stackoverflow.com/questions/184710/what-is-the-difference-between-a-deep-copy-and-a-shallow-copy

# Java HashSets
- It stores unique elements and permits nulls
- backed by HshMap
- no order maintained
- not threadsafe
