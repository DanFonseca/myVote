package com.br.myvote.myvote.business.excpetion;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super();
    }
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
