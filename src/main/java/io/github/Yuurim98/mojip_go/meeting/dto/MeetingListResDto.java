package io.github.Yuurim98.mojip_go.meeting.dto;

import io.github.Yuurim98.mojip_go.meeting.domain.Meeting;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class MeetingListResDto {

    private String title;

    private String meetingStatus;

    private String meetingType;

    private int maxParticipants;

    private int currentParticipants;

    public static MeetingListResDto from(Meeting meeting) {
        return MeetingListResDto.builder()
            .title(meeting.getTitle())
            .meetingStatus(meeting.getMeetingStatus().getDescription())
            .meetingType(meeting.getMeetingType().getDescription())
            .maxParticipants(meeting.getMaxParticipants())
            .currentParticipants(meeting.getCurrentParticipants())
            .build();
    }

}

