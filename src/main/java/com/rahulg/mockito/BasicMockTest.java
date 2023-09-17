package com.rahulg.mockito;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.ArgumentCaptor;

import java.util.Collection;
import java.util.Random;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.anyInt;

public class BasicMockTest {

    final User mockerUser = Mockito.mock(User.class);

    @Test
    public void do_nothing() {
        System.out.println("Hi");
        Mockito.when(mockerUser.getName()).thenReturn("Rahul", "SameName");
        String name = mockerUser.getName();
        System.out.println("name = " + name);
        Assertions.assertEquals(name, "Rahul");
        for(int i=0; i<5;i++) {
            String name2 = mockerUser.getName();
            Assertions.assertEquals(name2, "SameName");
        }
    }

    @Test
    public void test_differentRetune() {
        Mockito.when(mockerUser.getInt(1)).thenReturn(10);
        Mockito.when(mockerUser.getInt(2)).thenReturn(20);
        Mockito.when(mockerUser.getInt(anyInt())).thenReturn(30);
        Assertions.assertEquals(mockerUser.getInt(1), 10);
        Assertions.assertEquals(mockerUser.getInt(1), 10);
        Assertions.assertEquals(mockerUser.getInt(2), 20);
    }



    class User {
        private final String name;
        private final Integer age;
        private final String Address;

        User(String name, Integer age, String address) {
            this.name = name;
            this.age = age;
            Address = address;
        }

        public String getName() {
            return this.name;
        }

        public int getInt(int index) {
            return new Random().nextInt();
        }
    }


}
