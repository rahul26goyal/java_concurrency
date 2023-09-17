package com.rahulg.gson;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.google.gson.Gson;
public class SimpleExample {

    class User {
        private final String name;
        private final Integer age;
        private final String Address;

        User(String name, Integer age, String address) {
            this.name = name;
            this.age = age;
            Address = address;
        }

        @Override
        public String toString() {
            return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", Address='" + Address + '\'' +
                '}';
        }
    }

    @Test
    public void test_simple(){

        User u1 = new User("Rahul", 12, "Ladugaon");
        System.out.println("u1 = " + u1);

        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
        String u1Json = gson.toJson(u1);
        System.out.println("u1Json = " + u1Json);
        Assertions.assertTrue(Boolean.TRUE);
        boolean bool = true;
        Boolean b = new Boolean(true);
        Assertions.assertEquals(bool, b);


    }
}
