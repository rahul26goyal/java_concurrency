package com.rahulg.lombok;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @Builder will create a `public static class UniversityBuilder` class
 * `public static University.UniversityBuilder builder()` -> method to return UniversityBuilder object
 * adds an all args constructor
 */
@Builder
@Data
public class University {

    private final @NonNull String name;
    private final @NonNull String affiliation;
    private final String type; // pub, pri, trust
}

class UniversityAnnotationTests {


    @Test
    public void test_create_University() {
        University.UniversityBuilder builder = University.builder();
        University amrita = builder
            .name("Amrita University")
            .affiliation("A++ by NAAC")
            .type("Trust")
            .build();
        Assertions.assertNotNull(amrita);
        Assertions.assertEquals("Amrita University", amrita.getName());
    }

    @Test
    public void test_create_University_with_nulls() {
        University.UniversityBuilder builder = University.builder();
        University amrita = builder
            .name("Amrita University")
            .affiliation("A++ by NAAC")
            .build();
        Assertions.assertNotNull(amrita);
        Assertions.assertEquals("Amrita University", amrita.getName());
        Assertions.assertNull(amrita.getType());
    }

    @Test
    public void test_create_University_with_Nonnulls() {
        University.UniversityBuilder builder = University.builder();
        Assertions.assertThrows(NullPointerException.class, () -> {
            University amrita = builder
                .name("Amrita University")
                .affiliation(null)
                .build();
        });
    }
}
