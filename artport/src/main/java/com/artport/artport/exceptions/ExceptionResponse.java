package com.artport.artport.exceptions;

import java.util.Date;

public record ExceptionResponse (
        Date timestamp,
        int status,
        String error,
        String path
){}
