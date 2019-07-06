package com.wmp.crawler.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResultDto {
    String quotient;
    String rest;
}
