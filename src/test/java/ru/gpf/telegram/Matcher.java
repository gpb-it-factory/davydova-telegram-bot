package ru.gpf.telegram;

import static org.assertj.core.api.Assertions.assertThat;

public class Matcher {
    public static <T> void match(T actual, T expected, String... ignore) {
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields(ignore)
                .isEqualTo(expected);
    }
}
