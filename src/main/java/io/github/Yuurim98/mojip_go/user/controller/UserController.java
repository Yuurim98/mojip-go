package io.github.Yuurim98.mojip_go.user.controller;

import io.github.Yuurim98.mojip_go.common.response.ApiResponse;
import io.github.Yuurim98.mojip_go.user.dto.UserRegisterReqDto;
import io.github.Yuurim98.mojip_go.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(
        @Valid @RequestBody UserRegisterReqDto registerReqDto) {
        userService.registerUser(registerReqDto);
        return ResponseEntity.ok(ApiResponse.success());
    }

}



