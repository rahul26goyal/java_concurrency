## Introduction 
Project Lombok is a java library that automatically plugs into your editor and build tools and helps reduce 
the boiler plate code. Let’s see how to setup Lombok project first.

## How does lombok work?
- Lombok has various annotations which can be used within our code that is be processed during 
the compile time and appropriate code expansion would take place based on the annotation used.

- Lombok only does the code reduction in view time, after the compiling the byte code is injected with all the boiler plate. 
  This helps keeping our codebase small, clean and easy to read and maintain.
  
## How to add lombok dependency to java project 
```java
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
	<version>1.18.25</version>
</dependency>
```

