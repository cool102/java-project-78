package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema extends BaseSchema {


    private List<Predicate<String>> tests = new ArrayList<>();


    public final boolean isValid(String input) {
        System.out.println();
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
        Predicate<String> predicate = (value -> value != null && value.length() > 0);
        tests.add(predicate);
        return this;
    }

    public final StringSchema contains(String sample) {
        Predicate<String> predicate = (v -> v.contains(sample));
        tests.add(predicate);
        return this;
    }

    public final StringSchema minLength(int minLength) {
        Predicate<String> predicate = (v -> v.length() >= minLength);
        tests.add(predicate);
        return this;
    }


}
