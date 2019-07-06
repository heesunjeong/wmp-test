package com.wmp.crawler.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

@Getter
@Setter
public class RequestDto {
    @NotEmpty
    @URL(message = "URL 형식으로 입력해주세요.")
    private String url;

    private Boolean includeTags;

    @Min(0)
    Integer divisor;
}
