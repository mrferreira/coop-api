package com.mferreira.coopapi.configuration;

import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    private ErrorMessage errorMessage;

    public GlobalRestExceptionHandler(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<Object> handleAccessConflict(RuntimeException ex, WebRequest req) {
        return handleExceptionInternal(ex, errorMessage.json(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
    }
}