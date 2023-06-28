package com.study.battleq.modules.user.domain.repository;

import com.study.battleq.modules.user.domain.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmailAndDeletedAtIsNull(String email);

  Optional<UserEntity> findByNickname(String nickname);

  Optional<UserEntity> findByIdAndDeletedAtIsNull(Long id);

  boolean existsByEmail(String email);

  boolean existsByNickname(String nickname);
}
