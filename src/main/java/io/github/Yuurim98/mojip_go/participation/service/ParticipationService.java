package io.github.Yuurim98.mojip_go.participation.service;

import io.github.Yuurim98.mojip_go.common.exception.CustomException;
import io.github.Yuurim98.mojip_go.common.exception.ErrorCode;
import io.github.Yuurim98.mojip_go.meeting.domain.Meeting;
import io.github.Yuurim98.mojip_go.meeting.domain.MeetingType;
import io.github.Yuurim98.mojip_go.meeting.service.MeetingService;
import io.github.Yuurim98.mojip_go.participation.domain.Participation;
import io.github.Yuurim98.mojip_go.participation.domain.ParticipationRepository;
import io.github.Yuurim98.mojip_go.participation.dto.CreateParticipationReqDto;
import io.github.Yuurim98.mojip_go.user.domain.User;
import io.github.Yuurim98.mojip_go.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final MeetingService meetingService;
    private final UserService userService;
    private final ParticipationRepository participationRepository;

    @Transactional
    public void createParticipation(CreateParticipationReqDto reqDto, Long meetingId, Long userId) {
        User user = userService.findUserByUserId(userId);
        Meeting meeting = meetingService.getMeetingById(meetingId);

        Participation participation;

        meeting.participation();

        if (meeting.getMeetingType() == MeetingType.STUDY) {
            validateRequestMessage(reqDto.getRequestMessage());
            participation = Participation.of(user, meeting, reqDto.getRequestMessage());

        } else {
            participation = Participation.of(user, meeting);
        }

        participationRepository.save(participation);
    }

    private void validateRequestMessage(String requestMessage) {
        if (requestMessage == null || requestMessage.trim().isEmpty()) {
            throw new CustomException(ErrorCode.STUDY_PARTICIPATION_MESSAGE_REQUIRED);
        }
    }

}
