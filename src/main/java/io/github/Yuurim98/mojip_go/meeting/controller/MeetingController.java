package io.github.Yuurim98.mojip_go.meeting.controller;

import io.github.Yuurim98.mojip_go.auth.dto.SessionDto;
import io.github.Yuurim98.mojip_go.common.annotation.LoginUser;
import io.github.Yuurim98.mojip_go.common.response.ApiResponse;
import io.github.Yuurim98.mojip_go.meeting.dto.CreateMeetingReqDto;
import io.github.Yuurim98.mojip_go.meeting.service.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping()
    public ApiResponse<String> createMeeting(@Valid @RequestBody final CreateMeetingReqDto reqDto, @LoginUser final SessionDto sessionDto) {
        meetingService.createMeeting(reqDto, sessionDto.getUserId());
        return ApiResponse.success();
    }

}
