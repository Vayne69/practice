package com.example.gateway.controller;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Yang Jian
 * @date : 2019/9/19 15:24
 */
@RestController
public class FallbackController {
    @GetMapping("/fallback")
    @ResponseStatus
    public Mono<Map<String, Object>> fallback(ServerWebExchange exchange, Throwable throwable) {
        Map<String, Object> result = new HashMap<>(8);
        ServerHttpRequest request = exchange.getRequest();
        result.put("path", request.getPath().pathWithinApplication().value());
        result.put("method", request.getMethod());
        if (null != throwable.getCause()) {
            result.put("message", throwable.getCause().getMessage());
        } else {
            result.put("message", throwable.getMessage());
        }
        return Mono.just(result);
    }

    @GetMapping("/fallback/test")
    @ResponseStatus
    public Mono<Map<String, Object>> fallbacka(ServerWebExchange exchange, Throwable throwable) {
        Map<String, Object> result = new HashMap<>(8);
        ServerHttpRequest request = exchange.getRequest();
        result.put("path", request.getPath().pathWithinApplication().value());
        result.put("method", request.getMethod());
        if (null != throwable.getCause()) {
            result.put("message", throwable.getCause().getMessage());
        } else {
            result.put("message", throwable.getMessage());
        }
        result.put("asd", "asdddddd");
        return Mono.just(result);
    }
}
