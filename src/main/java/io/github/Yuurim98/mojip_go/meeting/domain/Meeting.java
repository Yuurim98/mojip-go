package io.github.Yuurim98.mojip_go.meeting.domain;

import io.github.Yuurim98.mojip_go.common.entity.BaseEntity;
import io.github.Yuurim98.mojip_go.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "meetings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meeting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MeetingStatus meetingStatus;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MeetingType meetingType;

    @Column(nullable = false)
    private int maxParticipants;

    @Column(nullable = false)
    private int currentParticipants;

    private Meeting(String title, String description, MeetingStatus meetingStatus,
        MeetingType meetingType, int maxParticipants, int currentParticipants, User user) {
        this.title = title;
        this.description = description;
        this.meetingStatus = meetingStatus;
        this.meetingType = meetingType;
        this.maxParticipants = maxParticipants;
        this.currentParticipants = currentParticipants;
        this.user = user;
    }

    public static Meeting of(String title, String description, String meetingType,
        int maxParticipants, User user) {
        return new Meeting(title, description, MeetingStatus.RECRUITING,
            MeetingType.valueOf(meetingType), maxParticipants, 1, user);
    }
}
