package com.avgkin.tacocloudplusserver.result;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResult<T> {
    private String code;
    private String message;
    private T data;
    public CommonResult<T> setCode(String code){
        this.code = code;
        return this;
    }
    public CommonResult(String code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public CommonResult(String code){
        this.code = code;
    }
    public CommonResult(String code,String msg){
        this.code = code;
        this.message = msg;
    }
}
