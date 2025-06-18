package io.github.Yuurim98.mojip_go.auth.controller;

import io.github.Yuurim98.mojip_go.auth.dto.LoginReqDto;
import io.github.Yuurim98.mojip_go.auth.dto.SessionDto;
import io.github.Yuurim98.mojip_go.auth.service.AuthService;
import io.github.Yuurim98.mojip_go.common.constants.SessionConstants;
import io.github.Yuurim98.mojip_go.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<String> login(@Valid @RequestBody final LoginReqDto loginReqDto,
        HttpServletRequest request) {

        HttpSession existingSession = request.getSession(false);

        if (existingSession != null) {
            existingSession.invalidate();
        }

        SessionDto sessionDto = authService.authenticate(loginReqDto.getEmail(),
            loginReqDto.getPassword());

        HttpSession newSession = request.getSession(true);

        newSession.setAttribute(SessionConstants.USER_SESSION_KEY, sessionDto);

        return ApiResponse.success();

    }
}
