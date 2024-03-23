package com.avgkin.tacocloudplusserver.exception;

import com.avgkin.tacocloudplusserver.result.CommonResult;
import com.avgkin.tacocloudplusserver.result.CommonResults;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public CommonResult<Void> handleCommonException(Exception e){
        return CommonResults.fail(e.getMessage());
    }
}
