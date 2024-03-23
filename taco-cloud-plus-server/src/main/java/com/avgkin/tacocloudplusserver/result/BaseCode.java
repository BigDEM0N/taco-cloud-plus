package com.avgkin.tacocloudplusserver.result;

public enum BaseCode {
    SUCCESS("成功","200"),
    ERROR("服务器执行错误","500");

    private String message;
    private String code;
    BaseCode(String message, String code) {
        this.code = code;
        this.message = message;
    }
    public String message(){
        return this.message;
    }
}
