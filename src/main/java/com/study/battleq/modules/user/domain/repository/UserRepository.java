package com.study.battleq.modules.user.domain.repository;

import com.study.battleq.modules.user.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmailAndDeletedAtIsNull(String email);
}
