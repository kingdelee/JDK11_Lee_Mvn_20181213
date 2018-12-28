package org.codefx.demo.junit5.basics;

import org.codefx.demo.junit5.DisabledOnOs;
import org.codefx.demo.junit5.OS;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class LifecycleTest {

    @BeforeAll
    static void initializeExternalResources() {
        System.out.println("Initializing external resources...");
    }

    @BeforeEach
    void initializeMockObjects() {
        System.out.println("Initializing mock objects...");
    }

    @Test
    void someTest() {
        System.out.println("Running some test...");
        assertTrue(true);
    }

    @Test
    void otherTest() {
        assumeTrue(true);

        System.out.println("Running another test...");
        assertNotEquals(1, 42, "Why would these be the same?");
    }

    @Test
    @Disabled
    void disabledTest() {
        System.exit(1);
    }

    @Test
    @DisabledOnOs(OS.NIX)
    void disabledNixTest() {
        fail("Only runs on Unix/Linux");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tearing down...");
    }

    @AfterAll
    static void freeExternalResources() {
        System.out.println("Freeing external resources...");
    }

}
