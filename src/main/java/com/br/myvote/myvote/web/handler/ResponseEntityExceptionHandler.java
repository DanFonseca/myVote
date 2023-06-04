package com.br.myvote.myvote.web.handler;

import com.br.myvote.myvote.business.excpetion.ApiException;
import com.br.myvote.myvote.business.excpetion.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ResponseEntityExceptionHandler  {

    @ExceptionHandler(value
            = { NotFoundException.class })
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ApiException NotFoundException(
            RuntimeException ex, WebRequest request) {
       return  new ApiException(
               ex.getMessage(),
               HttpStatus.NOT_FOUND,
               ZonedDateTime.now()
       );
    }

    @ExceptionHandler(value
            = { IllegalArgumentException.class })
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ApiException BadRequestException(
            RuntimeException ex, WebRequest request) {
        return  new ApiException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
    }
}





