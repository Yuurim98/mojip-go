package io.github.Yuurim98.mojip_go.common.config;

import io.github.Yuurim98.mojip_go.common.config.interceptor.LoginCheckInterceptor;
import io.github.Yuurim98.mojip_go.common.constants.PathConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {


    private final LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
            .addPathPatterns("/api/**")
            .excludePathPatterns(PathConstants.LOGIN_PATH, PathConstants.REGISTER_PATH);
    }
}
