package io.github.Yuurim98.mojip_go.meeting.domain;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeetingType {
    STUDY("스터디"),
    CHALLENGE("챌린지");

    private final String description;

    public static boolean containsName(String typeName) {
        return Arrays.stream(MeetingType.values())
            .anyMatch(type -> type.name().equals(typeName));
    }
}
