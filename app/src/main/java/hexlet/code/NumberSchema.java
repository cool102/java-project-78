package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    private Object value;
    private List<Predicate<Object>> tests = new ArrayList<>();

    public final boolean isValid(Object candidate) {
        if (!tests.isEmpty()) {
            value = candidate;
            for (Predicate<Object> currentTest : tests) {
                boolean passed = currentTest.test(candidate);
                if (!passed) {
                    return false;
                }
            }
        }

        return true;
    }

    public final NumberSchema required() {
        Predicate<Object> predicate = o -> o != null && o instanceof Integer;
        tests.add(predicate);
        return this;
    }

    public final NumberSchema positive() {
        Predicate<Object> predicate = o -> o != null && o instanceof Integer && (int) o > 0;
        tests.add(predicate);
        return this;
    }

    public final NumberSchema range(int start, int end) {
        Predicate<Object> predicate = o -> ((int) o >= start) && ((int) o <= end);
        tests.add(predicate);
        return this;
    }

}
