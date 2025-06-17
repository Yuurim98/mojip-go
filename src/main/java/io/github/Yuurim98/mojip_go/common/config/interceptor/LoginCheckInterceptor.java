package io.github.Yuurim98.mojip_go.common.config.interceptor;

import io.github.Yuurim98.mojip_go.common.exception.CustomException;
import io.github.Yuurim98.mojip_go.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    private static final String USER_SESSION_KEY = "authenticatedUser";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(USER_SESSION_KEY) == null) {
            throw new CustomException(ErrorCode.LOGIN_UNAUTHORIZED);
        }

        return true;
    }
}
