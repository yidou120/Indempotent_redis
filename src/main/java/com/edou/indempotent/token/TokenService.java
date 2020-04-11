package com.edou.indempotent.token;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    //生成token
    String createToken();
    //校验token
    boolean checkToken(HttpServletRequest request) throws Exception;
}
