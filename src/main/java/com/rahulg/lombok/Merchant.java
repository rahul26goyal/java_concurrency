package com.rahulg.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * @Data is a shortcut annotation that combines the features of @ToString, @EqualsAndHashCode, @Getter @Setter, and @RequiredArgsConstructor together.
 * So, @Data generates all the boilerplate involved in POJOs (Plain Old Java Objects).
 */
@AllArgsConstructor
@Data
public class Merchant {

    private final int id;
    private final String name;
    private String category;
}

class MerchantAnnotationTests {

    private Merchant merchant;

    @BeforeEach
    public void setup() {
        merchant = new Merchant(12345, "Rmesh Kuar", "Plumbing");
    }

    @Test
    public void test_Getters() {
        Assertions.assertEquals(12345, merchant.getId());
        Assertions.assertEquals("Rmesh Kuar", merchant.getName());
        Assertions.assertEquals("Plumbing", merchant.getCategory());
    }

    @Test
    public void test_Setters() {
        // only non-final fields can be set
        merchant.setCategory("Electricals");
        Assertions.assertEquals(12345, merchant.getId());
        Assertions.assertEquals("Rmesh Kuar", merchant.getName());
        Assertions.assertEquals("Electricals", merchant.getCategory());
    }

    @Test
    public void test_Equals() {
        Merchant mmerchant = new Merchant(12345, "Rmesh Kuar", "Plumbing");
        Assertions.assertEquals(mmerchant, merchant);
        merchant.hashCode();
    }


}
