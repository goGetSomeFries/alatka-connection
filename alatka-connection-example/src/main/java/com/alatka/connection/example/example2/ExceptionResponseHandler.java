package com.alatka.connection.example.example2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResponseHandler {

    private final Logger logger = LoggerFactory.getLogger(ExceptionResponseHandler.class);

    @ExceptionHandler({Exception.class})
    public RespMess edaException(Exception e) {
        logger.error(e.getMessage(), e);
        return new RespMess("error", e.getMessage());
    }

}
