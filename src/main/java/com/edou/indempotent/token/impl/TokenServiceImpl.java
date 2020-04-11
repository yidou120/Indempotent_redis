package com.edou.indempotent.token.impl;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.edou.indempotent.exception.ServiceException;
import com.edou.indempotent.redis.RedisService;
import com.edou.indempotent.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName TokenServiceImpl
 * @Description token操作的实现类
 * @Author 中森明菜
 * @Date 2020/4/11 13:07
 * @Version 1.0
 */
@Service
public class TokenServiceImpl implements TokenService {
    private final String TOKEN_NAME = "token";

    @Autowired
    RedisService redisService;

    @Override
    //生成token
    public String createToken() {
        String uuid = RandomUtil.randomUUID();
        StrBuilder token = StrBuilder.create();
        try {
            token.append(TOKEN_NAME).append(uuid);
            redisService.setExp(token.toString(),token.toString(),10000L);
            if(StrUtil.isNotEmpty(token.toString())){
                return token.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    //校验token
    public boolean checkToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        if(StrUtil.isBlank(token)){
            token = request.getParameter("token");
            if(StrUtil.isBlank(token)){
                //抛出异常
                throw new ServiceException(101,"token为空");
            }
        }
        if(!redisService.isExist(token)){
            throw new ServiceException(401,"重复提交");
        }
        if(!redisService.delete(token)){
            throw new ServiceException(402,"重复提交");
        }
        return true;
    }
}
