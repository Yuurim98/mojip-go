package io.github.Yuurim98.mojip_go.meeting.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    Page<Meeting> findByMeetingType(MeetingType meetingType, Pageable pageable);
}
