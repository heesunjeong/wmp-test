package com.wmp.crawler.service;

import com.wmp.crawler.model.ResultDto;
import com.wmp.crawler.util.StringPicker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerServiceTest {

    @Autowired
    CrawlerService crawlerService;

    @Test
    public void url_유효성() {
        Assert.assertFalse(crawlerService.urlValidate(""));
        Assert.assertFalse(crawlerService.urlValidate(null));

        Assert.assertFalse(crawlerService.urlValidate("hello world"));
        Assert.assertTrue(crawlerService.urlValidate("http://google.com"));
        Assert.assertTrue(crawlerService.urlValidate("https://github.com/"));
        Assert.assertFalse(crawlerService.urlValidate("https://github,co"));
    }

    @Test(expected = UnknownHostException.class)
    public void 파싱() throws IOException {
        crawlerService.parseHtml("http://test1exampla.com", true);
    }

    @Test
    public void 영어만_가져오기() {
        Assert.assertEquals(StringPicker.EnglishPicker("a3안녕2ha"), "aha");
    }

    @Test
    public void 숫자만_가져오기() {
        Assert.assertEquals(StringPicker.numberPicker("a3안녕2ha"), "32");
    }

    @Test
    public void 영어_숫자_섞기() {
        Assert.assertEquals(crawlerService.crossMerge(Arrays.asList("a", "h", "a"), Arrays.asList("3", "2")), "a3h2a");
    }

    @Test
    public void 영어없이_포매팅() {
        Assert.assertEquals(crawlerService.sortingData("1234043"), "0123344");
    }

    @Test
    public void 출력_묶음_단위로_문자_나누기() {
        Assert.assertEquals(crawlerService.divideDataByDivisor("0123344", 2), ResultDto.builder().quotient("012334").rest("4").build());
    }

    @Test
    public void 길이보다_긴값_나누기() {
        Assert.assertEquals(crawlerService.divideDataByDivisor("0123344", 100), ResultDto.builder().quotient("").rest("0123344").build());
    }

    @Test
    public void 영으로_나누기() {
        Assert.assertEquals(crawlerService.divideDataByDivisor("0123344", 0), ResultDto.builder().quotient("").rest("0123344").build());

    }

    @Test
    public void 구분자_붙이기() {
        Assert.assertEquals(crawlerService.separateQuotient("안녕하세요", 1), "안,녕,하,세,요");
    }
}
