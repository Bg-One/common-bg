package com.example.dbtoword.exception;


import com.example.dbtoword.response.CommonResultEnum;

/**
 * @Author bo
 * @Date 2023 09 26 09 00
 **/
public class CustomException extends RuntimeException{
    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;

    private CommonResultEnum commonResultEnum;

    public CustomException(CommonResultEnum commonResultEnum, String errorMsg) {
        this.commonResultEnum = commonResultEnum;
        this.errorMsg = errorMsg;
    }

    public CustomException() {
        super();
    }

    public CustomException(CommonResultEnum commonResultEnum) {
        this.errorCode = commonResultEnum.getCode();
        this.errorMsg = commonResultEnum.getMessage();
        this.commonResultEnum = commonResultEnum;
    }

    public CustomException(CommonResultEnum commonResultEnum, Throwable cause) {
        this.errorCode = commonResultEnum.getCode();
        this.errorMsg = commonResultEnum.getMessage();
    }

    public CustomException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public CustomException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CustomException(int errorCode, String errorMsg, Throwable cause) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public CommonResultEnum getCommonResultEnum() {
        return commonResultEnum;
    }

    public void setCommonResultEnum(CommonResultEnum commonResultEnum) {
        this.commonResultEnum = commonResultEnum;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }


}
