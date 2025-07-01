package io.github.Yuurim98.mojip_go.common.util;

import io.github.Yuurim98.mojip_go.common.constants.MeetingConstants;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class MeetingCacheConditionEvaluator {

    public boolean isCacheableForLatestFirstPage(Pageable pageable, String meetingTypeStr) {
        return pageable.getPageNumber() == MeetingConstants.DEFAULT_PAGE_NUMBER_FOR_CACHE &&
            pageable.getPageSize() == MeetingConstants.DEFAULT_PAGE_SIZE_FOR_CACHE &&
            pageable.getSort().equals(Sort.by(MeetingConstants.DEFAULT_SORT_DIRECTION_FOR_CACHE,
                MeetingConstants.SORT_BY_CREATED_AT)) && meetingTypeStr == null;
    }
}
