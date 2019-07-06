package com.wmp.crawler.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UnknownHostException.class)
    @ResponseBody
    public ResponseEntity unknownHostHandler(HttpServletRequest req, Exception e) {
        log.error("httpMethod:{}, URL : {}, {}", req.getMethod(), req.getRequestURI(), e);
        return new ResponseEntity("invalid url", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity handleException(HttpServletRequest req, Exception e) {
        log.error("httpMethod:{}, URL : {}, e", req.getMethod(), req.getRequestURI(), e);
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
