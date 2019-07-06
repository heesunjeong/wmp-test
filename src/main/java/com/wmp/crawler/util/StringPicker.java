package com.wmp.crawler.util;

import java.util.Optional;

public class StringPicker {
    public static String numberPicker(String input) {
        return Optional.ofNullable(input)
                .orElse("")
                .replaceAll("[^0-9]", "");
    }

    public static String EnglishPicker(String input) {
        return Optional.ofNullable(input)
                .orElse("")
                .replaceAll("[^a-zA-Z]", "");
    }
}
