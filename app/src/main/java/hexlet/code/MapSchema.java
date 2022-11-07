package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    private List<Predicate<Map<Object, Object>>> tests = new ArrayList<Predicate<Map<Object, Object>>>();


    final boolean isValid(Object value) {
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
