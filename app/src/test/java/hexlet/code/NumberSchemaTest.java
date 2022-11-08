package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NumberSchemaTest {
    private Validator v;
    private NumberSchema schema;

    @BeforeEach
    public final void setUp() {
        v = new Validator();
        schema = v.number();
    }

    @Test
    public final void notAnyValidators() {
        boolean actual = schema.isValid(null);
        Assertions.assertTrue(actual);
    }

    @Test
    public final void requiredTestPositive() {
        schema.required();
        final int candidate = 5;
        boolean actual = schema.isValid(candidate);
        Assertions.assertTrue(actual);
    }

    @Test
    public final void requiredTestNegative1() {
        schema.required();
        boolean actual = schema.isValid(null);
        Assertions.assertFalse(actual);
    }

    @Test
    public final void requiredValidatorTestNegative2() {
        schema.required();
        final String candidate = "5";
        boolean actual = schema.isValid(candidate);
        Assertions.assertFalse(actual);
    }

    @Test
    public final void requiredValidatorTestNegative3() {
        schema.required();
        final int candidate = -5;
        boolean actual = schema.isValid(candidate);
        Assertions.assertTrue(actual);
    }

    @Test
    public final void requiredValidatorTestNegative4() {
        schema.required();
        final int candidate = 0;
        boolean actual = schema.isValid(candidate);
        Assertions.assertTrue(actual);
    }

    @Test
    public final void positiveValidatorTest() {
        schema.positive();
        final int candidate = 5;
        boolean actual = schema.isValid(candidate);
        Assertions.assertTrue(actual);
    }

    @Test
    public final void positiveValidatorTest2() {
        schema.positive();
        final int candidate = -5;
        boolean actual = schema.isValid(candidate);
        Assertions.assertFalse(actual);
    }

    @Test
    public final void rangeValidatorTest() {
        final int rangeStart = 5;
        final int rangeEnd = 5;
        schema.range(rangeStart, rangeEnd);
        boolean actual = schema.isValid(rangeStart);
        Assertions.assertTrue(actual);

        boolean actual1 = schema.isValid(rangeEnd);
        Assertions.assertTrue(actual1);

        final int middleRange = 4;
        boolean actual2 = schema.isValid(middleRange);
        Assertions.assertFalse(actual2);

        final int outOfRange = 4;
        boolean actual3 = schema.isValid(outOfRange);
        Assertions.assertFalse(actual3);

    }
}
