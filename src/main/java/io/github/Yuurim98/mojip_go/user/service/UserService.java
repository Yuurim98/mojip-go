package io.github.Yuurim98.mojip_go.user.service;

import io.github.Yuurim98.mojip_go.common.exception.CustomException;
import io.github.Yuurim98.mojip_go.common.exception.ErrorCode;
import io.github.Yuurim98.mojip_go.common.util.PasswordUtil;
import io.github.Yuurim98.mojip_go.user.domain.User;
import io.github.Yuurim98.mojip_go.user.domain.UserRepository;
import io.github.Yuurim98.mojip_go.user.dto.UserRegisterReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordUtil passwordUtil;

    @Transactional
    public void registerUser(final UserRegisterReqDto registerReqDto) {

        validateDuplicateUser(registerReqDto.getNickname(), registerReqDto.getEmail());

        User user = User.of(registerReqDto.getNickname(), registerReqDto.getEmail(),
            passwordUtil.encodePassword(registerReqDto.getPassword()));

        userRepository.save(user);
    }

    private void validateDuplicateUser(String nickname, String email) {

        if (userRepository.existsByNickname(nickname)) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
        }

        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

}
