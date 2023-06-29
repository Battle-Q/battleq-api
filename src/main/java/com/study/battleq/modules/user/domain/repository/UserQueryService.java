package com.study.battleq.modules.user.domain.repository;

import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserRepository userRepository;

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmailAndDeletedAtIsNull(email).orElseThrow(UserNotFoundException::thrown);
    }

    public boolean hasDuplicateEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean hasDuplicateNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public UserEntity findById(Long id) {
        return userRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(UserNotFoundException::thrown);
    }
}
