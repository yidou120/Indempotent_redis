package com.edou.indempotent.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ResultVo
 * @Description 返回值对象
 * @Author 中森明菜
 * @Date 2020/4/11 13:58
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo {
    private int code;
    private String message;
}
