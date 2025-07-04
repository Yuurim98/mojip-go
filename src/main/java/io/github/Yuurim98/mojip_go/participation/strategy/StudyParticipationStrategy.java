package io.github.Yuurim98.mojip_go.participation.strategy;

import io.github.Yuurim98.mojip_go.common.exception.CustomException;
import io.github.Yuurim98.mojip_go.common.exception.ErrorCode;
import io.github.Yuurim98.mojip_go.meeting.domain.Meeting;
import io.github.Yuurim98.mojip_go.meeting.domain.MeetingType;
import io.github.Yuurim98.mojip_go.participation.domain.Participation;
import io.github.Yuurim98.mojip_go.participation.dto.CreateParticipationReqDto;
import io.github.Yuurim98.mojip_go.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class StudyParticipationStrategy implements MeetingParticipationStrategy{

    @Override
    public void validate(CreateParticipationReqDto reqDto, Meeting meeting) {
        if (reqDto.getRequestMessage() == null || reqDto.getRequestMessage().trim().isEmpty()) {
            throw new CustomException(ErrorCode.STUDY_PARTICIPATION_MESSAGE_REQUIRED);
        }
        meeting.validateForParticipation();
    }

    @Override
    public Participation createParticipation(User user, Meeting meeting,
        CreateParticipationReqDto reqDto) {
        return Participation.of(user, meeting, reqDto.getRequestMessage());
    }

    @Override
    public MeetingType getSupportedType() {
        return MeetingType.STUDY;
    }
}
