package org.codefx.demo.junit5.basics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.*;

class AssumeTest {

    @Test
    void exitIfFalseIsTrue() {
        assumeTrue(false);
        System.exit(1);
    }

    @Test
    void exitIfTrueIsFalse() {
        assumeFalse(this::truism);
        System.exit(1);
    }

    private boolean truism() {
        return true;
    }

    @Test
    void exitIfNullEqualsString() {
        assumingThat(
                "null".equals(null),
                () -> System.exit(1)
        );
    }

}
