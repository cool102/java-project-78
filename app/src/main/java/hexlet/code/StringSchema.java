package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema {

    private String value;
    private List<Predicate<String>> tests = new ArrayList<>();

    public final boolean isValid(String input) {
        this.value = input;
        if (!tests.isEmpty()) {
            for (Predicate<String> currentCheck : tests) {
                boolean passed = currentCheck.test(input);
                if (!passed) {
                    return false;
                }
            }
        }
        return true;

    }

    public final StringSchema required() {
        Predicate<String> predicate = (v -> value != null && v.length() > 0);
        tests.add(predicate);
        return this;
    }

    public final StringSchema contains(String sample) {
        Predicate<String> predicate = (v -> this.value.contains(sample));
        tests.add(predicate);
        return this;
    }

    public final StringSchema minLength(int minLength) {
        Predicate<String> predicate = (v -> this.value.length() >= minLength);
        tests.add(predicate);
        return this;
    }


}
