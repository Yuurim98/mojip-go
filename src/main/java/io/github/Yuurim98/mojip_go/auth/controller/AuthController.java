package io.github.Yuurim98.mojip_go.auth.controller;

import io.github.Yuurim98.mojip_go.auth.dto.LoginReqDto;
import io.github.Yuurim98.mojip_go.auth.service.AuthService;
import io.github.Yuurim98.mojip_go.common.response.ApiResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody final LoginReqDto loginReqDto, HttpSession session) {
        Long userId = authService.authenticate(loginReqDto.getEmail(), loginReqDto.getPassword());

        session.setAttribute("userId", userId);

        return ResponseEntity.ok(ApiResponse.success());

    }
}
