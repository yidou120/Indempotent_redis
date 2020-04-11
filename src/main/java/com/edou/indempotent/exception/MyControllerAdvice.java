package com.edou.indempotent.exception;

import com.edou.indempotent.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName MyControllerAdvice
 * @Description 异常捕获类
 * @Author 中森明菜
 * @Date 2020/4/11 14:33
 * @Version 1.0
 */
@ControllerAdvice
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ResultVo serviceExceptionHandler(ServiceException se){
        ResultVo resultVo = new ResultVo(se.getCode(),se.getMsg());
        return resultVo;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultVo exceptionHandler(Exception e){
        ResultVo resultVo = new ResultVo(400,e.getMessage());
        return resultVo;
    }
}
