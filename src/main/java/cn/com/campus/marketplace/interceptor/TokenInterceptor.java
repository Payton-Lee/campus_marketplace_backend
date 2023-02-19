package cn.com.campus.marketplace.interceptor;

import cn.com.campus.marketplace.entity.enums.ReturnCode;
import cn.com.campus.marketplace.entity.result.ResultData;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("OPTIONS")) return true;
        String token = request.getHeader("Authorization");
        try {
            if (!token.isBlank() && JWTUtil.verify(token, "peytonlee".getBytes())) {
                return true;
            }
        }catch (Exception ignored) {

        }
        ResultData<?> resultData = ResultData.fail(ReturnCode.USER_NO_LOGIN.code, ReturnCode.USER_NO_LOGIN.message);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(JSONUtil.toJsonStr(resultData));
        return false;
    }
}
