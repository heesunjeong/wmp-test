package com.wmp.crawler.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StringSortAsc {
    List<String> sort(String input);
}
