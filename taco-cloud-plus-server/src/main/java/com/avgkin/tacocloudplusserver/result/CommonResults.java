package com.avgkin.tacocloudplusserver.result;

public class CommonResults {
    public static CommonResult<Void> success(){
        return new CommonResult<Void>().setCode(BaseCode.SUCCESS.message());
    }
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<>(BaseCode.SUCCESS.message(),null,data);
    }
    public static <T> CommonResult<T> success(T data,String message){
        return new CommonResult<>(BaseCode.SUCCESS.message(),message,data);
    }
    public static CommonResult<Void> fail(){
        return new CommonResult<>(BaseCode.ERROR.message());
    }
    public static <T> CommonResult<T> fail(T data){
        return new CommonResult<>(BaseCode.ERROR.message(),null,data);
    }
    public static CommonResult<Void> fail(String msg){
        return new CommonResult<>(BaseCode.ERROR.message(),msg);
    }
}
