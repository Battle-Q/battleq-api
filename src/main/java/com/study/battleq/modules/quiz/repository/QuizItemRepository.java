package com.study.battleq.modules.quiz.repository;

import com.study.battleq.modules.quiz.domain.entity.QuizItem.QuizItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizItemRepository extends JpaRepository<QuizItemEntity, Long> {
    Optional<QuizItemEntity> findByIdAndDeletedAtIsNull(Long id);
}
