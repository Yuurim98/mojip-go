package io.github.Yuurim98.mojip_go.participation.strategy;

import io.github.Yuurim98.mojip_go.meeting.domain.Meeting;
import io.github.Yuurim98.mojip_go.meeting.domain.MeetingType;
import io.github.Yuurim98.mojip_go.participation.domain.Participation;
import io.github.Yuurim98.mojip_go.participation.dto.CreateParticipationReqDto;
import io.github.Yuurim98.mojip_go.user.domain.User;

public interface MeetingParticipationStrategy {

    void validate(CreateParticipationReqDto reqDto, Meeting meeting);

    Participation createParticipation(User user, Meeting meeting, CreateParticipationReqDto reqDto);

    MeetingType getSupportedType();
}
