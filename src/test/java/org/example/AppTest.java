package org.example;

import org.junit.jupiter.api.*;

public class AppTest {
    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll executed");
    }

    @BeforeEach
    void setupThis() {
        System.out.println("@BeforeEach executed");
    }

    @Tag("DEV")
    @Test
    void testCalcOne() {
        System.out.println("======TEST ONE EXECUTED=======");
        Assertions.assertEquals(4, 4);
    }

    @Tag("PROD")
//    @Disabled
    @Test
    void testCalcTwo() {
        System.out.println("======TEST TWO EXECUTED=======");
        Assertions.assertEquals(6, 6);
    }

    @AfterEach
    void tearThis() {
        System.out.println("@AfterEach executed");
    }

    @AfterAll
    static void tear() {
        System.out.println("@AfterAll executed");
    }
}
