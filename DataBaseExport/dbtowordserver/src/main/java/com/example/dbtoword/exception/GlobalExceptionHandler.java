package com.example.dbtoword.exception;


import com.example.dbtoword.response.CommonFun;
import com.example.dbtoword.response.CommonResult;
import com.example.dbtoword.response.CommonResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * controller层异常处理器，可捕获controller层抛出的运行时和非运行异常，也可在controller层自行try catch处理
 *
 * @author hmj
 * @since 2021/9/16
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public Object runtimeExceptionHandler(HttpServletRequest request, RuntimeException e) {
        CommonFun.outputException(e, logger);
        return new CommonResult(CommonResultEnum.FAILED, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(HttpServletRequest request, Exception e) {
        CommonFun.outputException(e, logger);
        return new CommonResult(CommonResultEnum.FAILED, e.getMessage());
    }


    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = CustomException.class)
    public CommonResult CustomExceptionHandler(HttpServletRequest req, CustomException e){
        CommonFun.outputException(e, logger);
        return new CommonResult(e.getCommonResultEnum(),e.getErrorMsg());
    }
}
