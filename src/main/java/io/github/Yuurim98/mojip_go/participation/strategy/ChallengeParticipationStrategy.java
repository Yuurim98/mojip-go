package io.github.Yuurim98.mojip_go.participation.strategy;

import io.github.Yuurim98.mojip_go.meeting.domain.Meeting;
import io.github.Yuurim98.mojip_go.meeting.domain.MeetingType;
import io.github.Yuurim98.mojip_go.participation.domain.Participation;
import io.github.Yuurim98.mojip_go.participation.dto.CreateParticipationReqDto;
import io.github.Yuurim98.mojip_go.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class ChallengeParticipationStrategy implements MeetingParticipationStrategy {

    @Override
    public void validate(CreateParticipationReqDto reqDto, Meeting meeting) {
        meeting.validateForParticipation();
    }

    @Override
    public Participation createParticipation(User user, Meeting meeting,
        CreateParticipationReqDto reqDto) {
        meeting.addParticipant();
        return Participation.of(user, meeting);
    }

    @Override
    public MeetingType getSupportedType() {
        return MeetingType.CHALLENGE;
    }
}
