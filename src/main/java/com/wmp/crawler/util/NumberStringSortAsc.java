package com.wmp.crawler.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NumberStringSortAsc implements StringSortAsc {
    @Override
    public List<String> sort(String input) {
        if (Strings.isEmpty(input)) {
            return Collections.emptyList();
        }

        return Arrays.stream(input.split(""))
                .sorted()
                .collect(Collectors.toList());
    }
}
