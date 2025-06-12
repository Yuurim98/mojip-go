package io.github.Yuurim98.mojip_go.meeting.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeetingStatus {

    RECRUITING("모집중"),
    COMPLETED("모집완료");

    private final String description;
}
