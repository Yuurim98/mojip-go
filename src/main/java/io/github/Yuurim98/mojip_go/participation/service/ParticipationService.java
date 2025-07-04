package io.github.Yuurim98.mojip_go.participation.service;

import io.github.Yuurim98.mojip_go.meeting.domain.Meeting;
import io.github.Yuurim98.mojip_go.meeting.service.MeetingService;
import io.github.Yuurim98.mojip_go.participation.domain.Participation;
import io.github.Yuurim98.mojip_go.participation.domain.ParticipationRepository;
import io.github.Yuurim98.mojip_go.participation.dto.CreateParticipationReqDto;
import io.github.Yuurim98.mojip_go.participation.strategy.MeetingParticipationStrategy;
import io.github.Yuurim98.mojip_go.participation.strategy.MeetingParticipationStrategyFactory;
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
    private final MeetingParticipationStrategyFactory strategyFactory;

    @Transactional
    public void createParticipation(CreateParticipationReqDto reqDto, Long meetingId, Long userId) {
        User user = userService.findUserByUserId(userId);
        Meeting meeting = meetingService.getMeetingById(meetingId);

        MeetingParticipationStrategy strategy = strategyFactory.getStrategy(meeting.getMeetingType());

        strategy.validate(reqDto, meeting);
        Participation participation = strategy.createParticipation(user, meeting, reqDto);

        participationRepository.save(participation);
    }

}
