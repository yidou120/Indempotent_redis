package com.edou.indempotent.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ServiceException
 * @Description 自定义异常类
 * @Author 中森明菜
 * @Date 2020/4/11 14:29
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends RuntimeException{
    private int code;
    private String msg;
}
