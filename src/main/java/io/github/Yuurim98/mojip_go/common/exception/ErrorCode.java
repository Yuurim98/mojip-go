package io.github.Yuurim98.mojip_go.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "이미 사용 중인 닉네임입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    LOGIN_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),
    MEETING_TYPE_NOT_FOUND(HttpStatus.BAD_REQUEST, "유효하지 않은 타입입니다."),
    MEETING_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 모임입니다."),
    MEETING_CLOSED(HttpStatus.CONFLICT, "모집이 마감되었습니다."),
    MEETING_FULL(HttpStatus.CONFLICT, "정원이 가득 찼습니다."),
    STUDY_PARTICIPATION_MESSAGE_REQUIRED(HttpStatus.BAD_REQUEST, "스터디 참여 시 요청 메시지는 필수입니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
