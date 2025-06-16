package io.github.Yuurim98.mojip_go.auth.dto;

import lombok.Getter;

@Getter
public class SessionDto {

    private Long userId;

    private String email;

    private String nickname;

    private SessionDto(Long userId, String email, String nickname) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
    }

    public static SessionDto of(Long userId, String email, String nickname) {
        return new SessionDto(userId, email, nickname);
    }
}
