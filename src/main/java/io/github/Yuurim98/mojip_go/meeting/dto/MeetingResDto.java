package io.github.Yuurim98.mojip_go.meeting.dto;

import io.github.Yuurim98.mojip_go.meeting.domain.Meeting;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MeetingResDto {

    private String title;

    private String description;

    private String meetingStatus;

    private String meetingType;

    private Long userId;

    private int maxParticipants;

    private int currentParticipants;

    public static MeetingResDto from(Meeting meeting) {
        return MeetingResDto.builder()
            .title(meeting.getTitle())
            .description(meeting.getDescription())
            .meetingStatus(meeting.getMeetingStatus().getDescription())
            .meetingType(meeting.getMeetingType().getDescription())
            .userId(meeting.getUser().getId())
            .maxParticipants(meeting.getMaxParticipants())
            .currentParticipants(meeting.getCurrentParticipants())
            .build();
    }
}
