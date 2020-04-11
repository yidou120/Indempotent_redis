package com.edou.indempotent.controller;

import cn.hutool.json.JSONUtil;
import com.edou.indempotent.annotation.AutoIdempotent;
import com.edou.indempotent.token.TokenService;
import com.edou.indempotent.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MainController
 * @Description 测试接口幂等性控制层
 * @Author 中森明菜
 * @Date 2020/4/11 14:40
 * @Version 1.0
 */
@RestController
@RequestMapping("/token")
public class MainController {
    @Autowired
    TokenService tokenService;
    
    //生成token
    @GetMapping()
    public String get() {
        String token = tokenService.createToken();
        ResultVo resultVo = new ResultVo(200,token);
        return JSONUtil.toJsonStr(resultVo);
    }

    //测试接口幂等性
    @AutoIdempotent
    @GetMapping("testIdempotent")
    public String test(){
        ResultVo resultVo = new ResultVo(200,"请求成功");
        return JSONUtil.toJsonStr(resultVo);
    }
}
