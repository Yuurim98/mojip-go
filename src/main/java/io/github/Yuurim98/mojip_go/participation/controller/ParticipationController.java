package io.github.Yuurim98.mojip_go.participation.controller;

import io.github.Yuurim98.mojip_go.auth.dto.SessionDto;
import io.github.Yuurim98.mojip_go.common.annotation.LoginUser;
import io.github.Yuurim98.mojip_go.common.response.ApiResponse;
import io.github.Yuurim98.mojip_go.participation.dto.CreateParticipationReqDto;
import io.github.Yuurim98.mojip_go.participation.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meetings")
public class ParticipationController {

    private final ParticipationService participationService;

    @PostMapping("/{meetingId}/participation")
    public ApiResponse<String> createParticipation(
        @RequestBody final CreateParticipationReqDto reqDto,
        @PathVariable Long meetingId,
        @LoginUser final SessionDto sessionDto) {
        participationService.createParticipation(reqDto, meetingId, sessionDto.getUserId());
        return ApiResponse.success();
    }

}
