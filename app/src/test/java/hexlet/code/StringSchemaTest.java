package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringSchemaTest {
    private Validator v;
    private StringSchema schema;

    @BeforeEach
    public final void init() {
        v = new Validator();
        schema = v.string();
    }

    @Test
    public final void noTestAreSwitched() {
        final String emptyLine = "";
        boolean actual = schema.isValid(emptyLine);
        Assertions.assertTrue(actual);

    }

    @Test
    public final void requiredValidationTest() {
        final String emptyLine = "";
        schema.required();
        boolean actual = schema.isValid(emptyLine);
        Assertions.assertFalse(actual);
    }

    @Test
    public final void minLengthValidationTest1() {
        final String line = "milk";
        final int minLength = 5;
        schema.minLength(minLength);
        boolean actual = schema.isValid(line);
        Assertions.assertFalse(actual);
    }

    @Test
    public final void minLengthValidationTest2() {
        final String line = "milk";
        final int minLength = 4;
        schema.minLength(minLength);
        boolean actual = schema.isValid(line);
        Assertions.assertTrue(actual);
    }

    @Test
    public final void minLengthValidationTest3() {
        final String line = "milk";
        final int minLength = 3;
        schema.minLength(minLength);
        boolean actual = schema.isValid(line);
        Assertions.assertTrue(actual);
    }

    @Test
    public final void containsValidationPositiveTest1() {
        final String inputLine = "Hello world";
        final String sample = "Hel";
        schema.contains(sample);
        boolean actual = schema.isValid(inputLine);
        Assertions.assertTrue(actual);
    }

    @Test
    public final void containsValidationPositiveTest2() {
        final String inputLine = "Hello world";
        final String sample = "Hallo";
        schema.contains(sample);
        boolean actual = schema.isValid(inputLine);
        Assertions.assertFalse(actual);
    }

    @Test
    public final void allValidatorsInOneTest1() {
        final String inputLine = "Hello, World!";
        final String sample = "Hello";
        final int minLength = 5;

        schema.required().contains(sample).minLength(minLength);
        boolean actual = schema.isValid(inputLine);
        Assertions.assertTrue(actual);
    }

    @Test
    public final void allValidatorsInOneTest2() {
        final String inputLine = "Hello, World!";
        final String sample = "Hello";
        final int minLength = 50;

        schema.required().contains(sample).minLength(minLength);
        boolean actual = schema.isValid(inputLine);
        Assertions.assertFalse(actual);
    }

    @Test
    public final void allValidatorsInOneTest3() {
        final String inputLine = "Hello, World!";
        final String sample = "Heeello";
        final int minLength = 5;

        schema.required().contains(sample).minLength(minLength);
        boolean actual = schema.isValid(inputLine);
        Assertions.assertFalse(actual);
    }
}
