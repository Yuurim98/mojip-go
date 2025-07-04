package io.github.Yuurim98.mojip_go.participation.strategy;

import io.github.Yuurim98.mojip_go.meeting.domain.MeetingType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MeetingParticipationStrategyFactory {

    private final Map<MeetingType, MeetingParticipationStrategy> strategies;

    public MeetingParticipationStrategyFactory(List<MeetingParticipationStrategy> strategyList) {
        this.strategies = strategyList.stream()
            .collect(Collectors.toMap(MeetingParticipationStrategy::getSupportedType,
                strategy -> strategy));
    }

    public MeetingParticipationStrategy getStrategy(MeetingType meetingType) {
        return strategies.get(meetingType);
    }
}
