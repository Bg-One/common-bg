package com.example.dbtoword.response;




/**
 *  http 统一响应格式
 * @author liuzhaobo
 */
public class CommonResult extends BaseResult {

    public CommonResult(CommonResultEnum commonResultConstant, Object data) {
        super(commonResultConstant.getCode(), commonResultConstant.getMessage(), data);
    }
}
