package com.example.dbtoword.response;

/**
 * 返回结果常量枚举类
 * @author liuzhaobo
 */
public enum CommonResultEnum {
    /**
     * 失败
     */
    FAILED(0, "failed"),

    SUCCESS(1, "success"),

    DB_CONNECT_ERROR(5000, "数据库连接异常"),

    DB_CLOSE_ERROR(5001, "数据库连接关闭异常"),

    DB_FIND_ERROR(5002, "数据库查询异常"),

    DB_EXPORT_ERROR(5003, "数据库查询异常");
    public int code;

    public String message;

    CommonResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
