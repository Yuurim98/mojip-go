package io.github.Yuurim98.mojip_go.meeting.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeetingType {
    STUDY("스터디"),
    CHALLENGE("챌린지");

    private final String description;

}
