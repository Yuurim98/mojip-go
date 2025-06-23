package io.github.Yuurim98.mojip_go.meeting.controller;

import io.github.Yuurim98.mojip_go.auth.dto.SessionDto;
import io.github.Yuurim98.mojip_go.common.annotation.LoginUser;
import io.github.Yuurim98.mojip_go.common.response.ApiResponse;
import io.github.Yuurim98.mojip_go.meeting.dto.CreateMeetingReqDto;
import io.github.Yuurim98.mojip_go.meeting.dto.MeetingListResDto;
import io.github.Yuurim98.mojip_go.meeting.service.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meetings")
public class MeetingController {

    private static final String SORT_BY_CREATED_AT = "createdAt";

    private final MeetingService meetingService;

    @PostMapping()
    public ApiResponse<String> createMeeting(@Valid @RequestBody final CreateMeetingReqDto reqDto,
        @LoginUser final SessionDto sessionDto) {
        meetingService.createMeeting(reqDto, sessionDto.getUserId());
        return ApiResponse.success();
    }

    @GetMapping()
    public ApiResponse<Page<MeetingListResDto>> getMeetingList(
        @PageableDefault(sort = SORT_BY_CREATED_AT, direction = Direction.DESC) Pageable pageable,
        @RequestParam(required = false) String meetingType) {
        return ApiResponse.success(meetingService.getMeetingList(pageable, meetingType));
    }

}
