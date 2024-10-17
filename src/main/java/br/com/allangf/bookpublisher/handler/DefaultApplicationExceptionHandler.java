package br.com.allangf.bookpublisher.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Integer.MIN_VALUE)
public class DefaultApplicationExceptionHandler {
}
