package com.rahulg.lombok;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Reference: https://auth0.com/blog/a-complete-guide-to-lombok/

// @NoArgsConstructor // can not add this with final variables class.
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
// @Setter for all attributes
public class Author {

    private final @NonNull  String id;

    @Setter
    private @NonNull String  name;

    private String lastName;
}


class AuthorTests {

    @BeforeEach
    public void setup(){
        System.out.println("Running Test..");
    }
    
    @Test
    public void createObject() {

        // Author author = new Author(); // this is going to throw error.
        Author author2 = new Author("Id1", "Rahul", "Goyal");
        System.out.println("author = " + author2);
    }

    @Test
    public void canGetAuthorDetails_Getter() {
        Author author = new Author("Id1", "Rahul", "Goyal");
        System.out.println("author = " + author);
        String name = author.getName();
        System.out.println("name = " + name);
        System.out.println("Id:" + author.getId());
    }

    @Test
    public void canSetAuthorSurname_Setter() {
        Author author = new Author("Id1", "Rahul", "Goyal");
        System.out.println("author = " + author);
        author.setName("NewRahul");
        System.out.println("name = " + author.getName());
        Assertions.assertEquals("NewRahul", author.getName());
    }

    @Test
    public void test_ToString_annotation() {
        Author author = new Author("Id1", "Rahul", "Goyal");
        Assertions.assertEquals("Author(id=Id1, name=Rahul, lastName=Goyal)", author.toString());
    }

    @Test
    public void test_throwsExceptionForNonNull_params() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Author auth = new Author("Id1", null, "Goyal");
        });

        Assertions.assertThrows(NullPointerException.class, () -> {
            Author auth = new Author(null, "Rahul", "Goyal");
        });

        Assertions.assertDoesNotThrow(() -> {
            Author auth = new Author("", "Rahul", "Goyal");
        }, "Should not throw exception for empty string.");
    }
}

