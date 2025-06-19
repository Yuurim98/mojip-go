package io.github.Yuurim98.mojip_go.meeting.service;

import io.github.Yuurim98.mojip_go.common.exception.CustomException;
import io.github.Yuurim98.mojip_go.common.exception.ErrorCode;
import io.github.Yuurim98.mojip_go.meeting.domain.Meeting;
import io.github.Yuurim98.mojip_go.meeting.domain.MeetingRepository;
import io.github.Yuurim98.mojip_go.meeting.domain.MeetingType;
import io.github.Yuurim98.mojip_go.meeting.dto.CreateMeetingReqDto;
import io.github.Yuurim98.mojip_go.user.domain.User;
import io.github.Yuurim98.mojip_go.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final UserRepository userRepository;
    private final MeetingRepository meetingRepository;

    @Transactional
    public void createMeeting(final CreateMeetingReqDto reqDto, final Long userId) {

        if (!MeetingType.containsName(reqDto.getMeetingType())) {
            throw new CustomException(ErrorCode.MEETING_TYPE_NOT_FOUND);
        }

        User user = findUserByUserId(userId);

        Meeting meeting = createMeetingEntity(reqDto, user);

        meetingRepository.save(meeting);
    }

    private User findUserByUserId(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private Meeting createMeetingEntity(final CreateMeetingReqDto reqDto, final User user) {
        return Meeting.of(
            reqDto.getTitle(),
            reqDto.getDescription(),
            reqDto.getMeetingType(),
            reqDto.getMaxParticipants(),
            user);
    }
}
