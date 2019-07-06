package com.wmp.crawler.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private final ObjectMapper objectMapper;

    public LoggerInterceptor() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String params = objectMapper.writeValueAsString(request.getParameterMap());
        log.debug("LoggerInterceptor preHandle() - Request URI: {}, params: {}", request.getRequestURI(), params);
        return super.preHandle(request, response, handler);
    }
}