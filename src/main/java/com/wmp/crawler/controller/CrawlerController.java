package com.wmp.crawler.controller;

import com.wmp.crawler.model.RequestDto;
import com.wmp.crawler.model.ResultDto;
import com.wmp.crawler.service.CrawlerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("api")
public class CrawlerController {

    private final CrawlerService crawlerService;

    @Autowired
    public CrawlerController(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @ApiOperation(value = "url 크롤링 후 정렬하여 나누는 api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "url", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "includeTags", value = "태그 포함 여부", required = true, dataType = "boolean", paramType = "query", defaultValue = "false"),
            @ApiImplicitParam(name = "divisor", value = "출력 묶음 단위", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("crawling")
    public ResponseEntity<ResultDto> crawling(@Valid RequestDto request, BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(crawlerService.crawling(request));
    }
}
