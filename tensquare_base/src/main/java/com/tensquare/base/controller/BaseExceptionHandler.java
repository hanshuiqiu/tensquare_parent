package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hanshuiqiu
 * @create 2020-09-24 14:20
 *
 * 统一异常处理类:
 *
 * 为了使我们的代码更容易维护，我们创建一个类集中处理异常
 */

@ControllerAdvice
public class BaseExceptionHandler {

//    如果不增加BaseExceptionHandler处理公共异常，若发生异常，则返回的数据格式如下：
//    {
//        "timestamp": "2020-09-24T13:00:41.207+0000",
//        "status": 500,
//        "error": "Internal Server Error",
//        "message": "/ by zero",
//        "path": "/label"
//    }
//
//     但是为了统一返回给前端的数据格式，使用BaseExceptionHandler后，若发生异常，则返回的数据格式如下：
//    {
//        "flag": false,
//        "code": 20001,
//        "message": "/ by zero",
//        "data": null
//    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exception(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }

}
