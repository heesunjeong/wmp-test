package com.wmp.crawler.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnglishStringSortAsc implements StringSortAsc {
    @Override
    public List<String> sort(String input) {
        if (Strings.isEmpty(input)) {
            return Collections.emptyList();
        }

        return Arrays
                .stream(input.split(""))
                .sorted((o1, o2) -> {
            if (o1.equalsIgnoreCase(o2)) {
                return o1.compareTo(o2);
            }
            return o1.compareToIgnoreCase(o2);
        }).collect(Collectors.toList());
    }
}
