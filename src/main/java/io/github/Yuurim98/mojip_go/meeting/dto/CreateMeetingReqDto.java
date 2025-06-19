package io.github.Yuurim98.mojip_go.meeting.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateMeetingReqDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String meetingType;

    @Min(value = 2, message = "모집 인원은 최소 2명 이상이어야 합니다.")
    private int maxParticipants;
}
