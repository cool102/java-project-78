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
    private Map<String, BaseSchema> schemas = new HashMap<>();


    @BeforeEach
    public final void setUp() {
        v = new Validator();
        schema = v.map();
        data.put("key1", "value1");
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
    }

    @Test
    public void shapeTestOK() {
        Map<String, Object> human1 = new HashMap<>();
        final int age = 100;
        human1.put("name", "Kolya");
        human1.put("age", age);
        schema.shape(schemas);
        boolean actual = schema.isValid(human1);
        Assertions.assertTrue(actual);

    }

    @Test
    public void shapeTestNOK() {
        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        schema.shape(schemas);
        boolean actual = schema.isValid(human3);
        Assertions.assertFalse(actual);

    }

    @Test
    public void shapeTest4() {
        Map<String, Object> human4 = new HashMap<>();
        final int age = -5;
        human4.put("name", "Valya");
        human4.put("age", age);
        schema.shape(schemas);
        schema.isValid(human4);
        boolean actual = schema.isValid(human4);
        Assertions.assertFalse(actual);

    }

    @Test
    public void shapeTest3() {
        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        schema.shape(schemas);
        schema.isValid(human2);
        boolean actual = schema.isValid(human2);
        Assertions.assertTrue(actual);

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
