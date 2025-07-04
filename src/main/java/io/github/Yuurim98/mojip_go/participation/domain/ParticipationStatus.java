package io.github.Yuurim98.mojip_go.participation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ParticipationStatus {
    REQUESTED("요청"),
    ACCEPTED("수락"),
    REJECTED("거절"),
    JOINED("참여");

    private final String description;
}
