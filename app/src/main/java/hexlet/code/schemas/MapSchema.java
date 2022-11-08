package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    private List<Predicate<Map<Object, Object>>> tests = new ArrayList<Predicate<Map<Object, Object>>>();


    public final boolean isValid(Object value) {
        if (!tests.isEmpty() || value != null) {
            for (Predicate<Map<Object, Object>> currentCheck : tests) {
                boolean passed = currentCheck.test((Map<Object, Object>) value);
                if (!passed) {
                    return false;
                }
            }
        }
        return true;
    }

    public final MapSchema shape(Map schemaMap) {
        Predicate<Map<Object, Object>> predicate = dataMap -> {
            for (Map.Entry<Object, Object> entry : dataMap.entrySet()) {
                Object keyData = entry.getKey();
                Object valueData = entry.getValue();
                BaseSchema valueSchema = (BaseSchema) schemaMap.get(keyData);
                if (valueSchema instanceof StringSchema) {
                    StringSchema stringValueSchema = (StringSchema) valueSchema;
                    String value = (String) valueData;
                    boolean passed = stringValueSchema.isValid(value);
                    if (!passed) {
                        return false;
                    }
                }
                if (valueSchema instanceof NumberSchema) {
                    NumberSchema numberValueSchema = (NumberSchema) valueSchema;

                    boolean passed = numberValueSchema.isValid(valueData);
                    if (!passed) {
                        return false;
                    }
                }


            }
            return true;

        };
        tests.add(predicate);
        return this;
    }

    public final MapSchema required() {
        Predicate<Map<Object, Object>> predicate = m -> m != null && m instanceof Map;
        tests.add(predicate);
        return this;
    }

    public final MapSchema sizeof(int size) {
        Predicate<Map<Object, Object>> predicate = m -> m.size() == size;
        tests.add(predicate);
        return this;
    }

}
