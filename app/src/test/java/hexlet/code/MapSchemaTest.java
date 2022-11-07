package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapSchemaTest {
    private Validator v;
    private MapSchema schema;
    private Map<String, String> data = new HashMap<>();


    @BeforeEach
    public final void setUp() {
        v = new Validator();
        schema = v.map();
        data.put("key1", "value1");
    }

    @Test
    public final void notAnyValidators() {
        boolean actual = schema.isValid(null);
        Assertions.assertTrue(actual);
    }

    @Test
    public final void requiredTestNeg() {
        schema.required();
        boolean actual = schema.isValid(null);
        Assertions.assertFalse(actual);
    }

    @Test
    public final void requiredPositive() {
        schema.required();
        boolean actual = schema.isValid(new HashMap());
        Assertions.assertTrue(actual);
    }

    @Test
    public final void sizeofTest() {
        final int size = 2;
        schema.sizeof(size);
        boolean actual = schema.isValid(Map.of("key1", "value1", "key2", "value2"));
        Assertions.assertTrue(actual);
    }

    @Test
    public final void sizeofTest2() {
        final int size = 2;
        schema.sizeof(size);
        boolean actual = schema.isValid(data);
        Assertions.assertFalse(actual);

        data.put("key2", " + value2");
        boolean actual2 = schema.isValid(data);
        Assertions.assertTrue(actual2);
    }

}
