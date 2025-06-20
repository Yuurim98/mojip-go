package io.github.Yuurim98.mojip_go.common.argumentresolver;

import io.github.Yuurim98.mojip_go.auth.dto.SessionDto;
import io.github.Yuurim98.mojip_go.common.annotation.LoginUser;
import io.github.Yuurim98.mojip_go.common.constants.SessionConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginUserAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
        boolean isSessionDtoType = SessionDto.class.isAssignableFrom(parameter.getParameterType());

        return hasLoginUserAnnotation && isSessionDtoType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        return (SessionDto) session.getAttribute(SessionConstants.USER_SESSION_KEY);
    }
}
