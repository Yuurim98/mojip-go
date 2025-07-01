package io.github.Yuurim98.mojip_go.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MeetingConstants {

    public static final String SORT_BY_CREATED_AT = "createdAt";

    public static final int DEFAULT_PAGE_NUMBER_FOR_CACHE = 0;
    public static final int DEFAULT_PAGE_SIZE_FOR_CACHE = 10;
    public static final Sort.Direction DEFAULT_SORT_DIRECTION_FOR_CACHE = Sort.Direction.DESC;
}
