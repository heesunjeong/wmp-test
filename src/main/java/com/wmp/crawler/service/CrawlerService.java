package com.wmp.crawler.service;

import com.wmp.crawler.model.RequestDto;
import com.wmp.crawler.model.ResultDto;
import com.wmp.crawler.util.*;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

/*
테스트 코드 실행을 위해 메소드의 접근제어자를 default로 하였습니다.
 */

@Service
public class CrawlerService {

    private final StringSortAsc numberStringSortAsc;
    private final StringSortAsc englishStringSortAsc;

    @Autowired
    public CrawlerService(NumberStringSortAsc stringSortAsc, EnglishStringSortAsc englishStringSortAsc) {
        this.numberStringSortAsc = stringSortAsc;
        this.englishStringSortAsc = englishStringSortAsc;
    }

    public ResultDto crawling(RequestDto request) throws IOException {
        String parsingData = parseHtml(request.getUrl(), request.getIncludeTags());
        String formattedData = sortingData(parsingData);

        return divideDataByDivisor(formattedData, request.getDivisor());
    }

    String parseHtml(String url, Boolean isIncludeTags) throws IOException {
        Document doc = Jsoup.connect(url).get();
        if (isIncludeTags) {
            return String.valueOf(doc);
        }

        return String.valueOf(doc.text());
    }

    boolean urlValidate(String url) {
        if (Strings.isEmpty(url)) {
            return false;
        }
        return url.matches("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)");
    }

    String sortingData(String data) {
        String eng = StringPicker.EnglishPicker(data);
        String num = StringPicker.numberPicker(data);

        return crossMerge(englishStringSortAsc.sort(eng), numberStringSortAsc.sort(num));
    }

    String crossMerge(List<String> englishList, List<String> numberList) {
        StringBuilder sb = new StringBuilder();
        int maxSize = Math.max(englishList.size(), numberList.size());

        for (int i = 0; i < maxSize; i++) {
            if (i < englishList.size()) {
                sb.append(englishList.get(i));
            }

            if (i < numberList.size()) {
                sb.append(numberList.get(i));
            }
        }

        return String.valueOf(sb);
    }

    ResultDto divideDataByDivisor(String formattedData, int divisor) {
        if (divisor == 0 || formattedData.length() < divisor) {
            return ResultDto.builder().quotient("").rest(formattedData).build();
        }

        int quotientSize = formattedData.length() - (formattedData.length() % divisor);

        return ResultDto.builder()
                .quotient(separateQuotient(formattedData.substring(0, quotientSize), divisor))
                .rest(formattedData.substring(quotientSize)).build();
    }

    String separateQuotient(String data, int divisor) {
        StringJoiner sj = new StringJoiner(", ");
        for (int i = 0; i < data.length(); i += divisor) {
            sj.add(data.substring(i, i + divisor));
        }
        return String.valueOf(sj);
    }
}
