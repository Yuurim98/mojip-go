package io.github.Yuurim98.mojip_go.meeting.service;

import io.github.Yuurim98.mojip_go.common.exception.CustomException;
import io.github.Yuurim98.mojip_go.common.exception.ErrorCode;
import io.github.Yuurim98.mojip_go.meeting.domain.Meeting;
import io.github.Yuurim98.mojip_go.meeting.domain.MeetingRepository;
import io.github.Yuurim98.mojip_go.meeting.domain.MeetingType;
import io.github.Yuurim98.mojip_go.meeting.dto.CreateMeetingReqDto;
import io.github.Yuurim98.mojip_go.meeting.dto.MeetingListResDto;
import io.github.Yuurim98.mojip_go.user.domain.User;
import io.github.Yuurim98.mojip_go.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private static final String SORT_BY = "createdAt";

    private final MeetingRepository meetingRepository;
    private final UserService userService;

    @Transactional
    public void createMeeting(final CreateMeetingReqDto reqDto, final Long userId) {

        //TODO validateMeetingTypeStr 메서드 호출로 수정 필요
        if (!MeetingType.containsName(reqDto.getMeetingType())) {
            throw new CustomException(ErrorCode.MEETING_TYPE_NOT_FOUND);
        }

        User user = userService.findUserByUserId(userId);

        Meeting meeting = Meeting.of(
            reqDto.getTitle(),
            reqDto.getDescription(),
            reqDto.getMeetingType(),
            reqDto.getMaxParticipants(),
            user);

        meetingRepository.save(meeting);
    }

    public Page<MeetingListResDto> getMeetingList(int page, int size, String meetingTypeStr) {

        Sort sort = Sort.by(Direction.DESC, SORT_BY);
        Pageable pageable = PageRequest.of(page-1, size, sort);

        Page<Meeting> meetingList = null;

        if (meetingTypeStr != null) {
            validateMeetingTypeStr(meetingTypeStr);

            meetingList = meetingRepository.findByMeetingType(
                MeetingType.valueOf(meetingTypeStr.toUpperCase()), pageable);
        } else {
            meetingList = meetingRepository.findAll(pageable);
        }

        return meetingList.map(MeetingListResDto::from);
    }

    private void validateMeetingTypeStr(String meetingTypeStr) {
        if (!MeetingType.containsName(meetingTypeStr)) {
            throw new CustomException(ErrorCode.MEETING_TYPE_NOT_FOUND);
        }
    }
}
