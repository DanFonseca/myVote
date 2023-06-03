package com.br.myvote.myvote.business.excpetion;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.ZonedDateTime;


@Getter
@Setter
@AllArgsConstructor
public class ApiException  {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;
}
