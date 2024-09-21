## Java Streams (Java 8)

- Java streams APIs were added in Java version 8 
- Streams are powerful and expressive function programming feature..
- These are basically used to process collections and maniputate data.
- to process sequences of elements, such as collections or arrays, in a declarative manner. 

### Generating Streams

- From a collection: Collection.stream()
-  From an array: Arrays.stream(array)
 - Using a Stream builder: Stream.builder().add(element1).add(element2).build()
 - From an I/O channel: Files.lines(Path)
  - Infinite Stream: Stream.iterate(seed, function) or Stream.generate(supplier)

## Stream APIs
- Java Streams offer a rich set of operations, including intermediate and terminal operations. 
- intermediate operations are transformation, are lazy and return a new Stream, 
- terminal operations are actions, trigger the processing of the data and return a final result or a side-effect.

### Intermediate operations:

- filter(Predicate): Filters elements based on a given predicate.
- map(Function): Transforms elements using a given function.
- flatMap(Function): Transforms elements into Streams and flattens the resulting Streams.
- distinct(): Returns a Stream with unique elements.
- sorted(Comparator): Sorts the elements based on a given comparator.
- peek(Consumer): Performs an action on each element without modifying the Stream, useful for debugging.

### Terminal operations:

- forEach(Consumer): Performs an action on each element.
- toArray(): Collects elements into an array.
- reduce(BinaryOperator): Reduces the elements to a single value.
- collect(Collector): Collects elements into a mutable container.
- min(Comparator), max(Comparator): Finds the minimum or maximum element.
- count(): Counts the elements.
- anyMatch(Predicate), allMatch(Predicate), noneMatch(Predicate): Tests whether the predicates match the elements.
- findFirst(), findAny(): Retrieves the first or any element that satisfies a given condition.

