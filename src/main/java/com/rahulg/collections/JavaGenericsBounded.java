package com.rahulg.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JavaGenericsBounded {

    public static <T extends Employee> DesignationGrade getGrade(T employee) {
        Integer salary = employee.getSalary();
        for(DesignationGrade dg: DesignationGrade.values()){
            if (salary > dg.getSalaryMinLimit()) { // this logic is not correct but ignore for ow.
                return dg;
            }
        }
        return null;
    }

    @Test
    public void test_UpperBoundGenerics(){

        Employee e1 = new Employee("Rahul", 15000);
        Employee e2 = new Employee("Ramesh", 64000);
        DesignationGrade dg1 = JavaGenericsBounded.getGrade(e1);
        DesignationGrade dg2 = JavaGenericsBounded.getGrade(e2);
        System.out.println("dg2 = " + dg1);
        System.out.println("dg2 = " + dg2);

        Person p1 = new Person("Rahul");
        //DesignationGrade dg3 = JavaBoundedGenerics.getGrade(p1); // this will not be acceptable
        Consultant c1 = new Consultant("Rahul", 15000, "XYZ");
        DesignationGrade dg4 = getGrade(c1);
        System.out.println("dg4 = " + dg4);
        if (dg4.equals(DesignationGrade.valueOf("INTERN"))) {
            System.out.println("Equality matched = " + dg4);
        }
        if(dg1.equals(DesignationGrade.INTERN)) {
            System.out.println("Equality = " + dg1);
        }

    }

//    public List<String> getNames(List<Employee> employees) {
//        List<String> names = new ArrayList<>();
//        employees.stream()
//            .forEach(e -> names.add(e.getName()));
//        System.out.println("names = " + names);
//        return names;
//    }

    public List<String> getNames(List<? extends Employee> employees) {
        List<String> names = new ArrayList<>();
        employees.stream()
            .forEach(e -> names.add(e.getName()));
        System.out.println("names = " + names);
        return names;
    }

    public void printSalary(List<Employee> employees) {
        employees//.stream()
            .forEach(Employee::print);
    }

    @Test
    public void test_simple(){
        Employee e1 = new Employee("Rahul", 15000);
        Employee e2 = new Employee("Ramesh", 64000);
        List<String> eNames = getNames(List.of(e1, e2));
        System.out.println("eNames = " + eNames);
        printSalary(List.of(e1, e2));

        Consultant c1 = new Consultant("Rahul", 15000, "XYZ");
        Consultant c2 = new Consultant("Rahul", 15000, "XYZ");
        List<String> cNames = getNames(List.of(c1, c2));
        System.out.println("cNames = " + cNames);
        printSalary(List.of(c1, c2));
    }
}

class Person {

    protected final String name;

    Person(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
}

class Employee extends Person {

    private final Integer salary;

    Employee(String name, Integer salary) {
        super(name);
        this.salary = salary;
    }

    public Integer getSalary() {
        return this.salary;
    }
    
    public static void print(Employee e){
        System.out.println("salary = " + e.getSalary());
    }

}

class Consultant extends Employee {

    private final String agency;

    Consultant(String name, Integer salary, String agency) {
        super(name, salary);
        this.agency = agency;
    }
}

enum DesignationGrade {
    INTERN(10000),
    JUNIOR(20000),
    SENIOR(50000),
    MANAGER(100000);

    private Integer salaryMinLimit; // lower limit

    DesignationGrade(int i) {
        salaryMinLimit = i;
    }

    public Integer getSalaryMinLimit(){
        return salaryMinLimit;
    }
}