package com.study.battleq.modules.quiz.repository;

import com.study.battleq.modules.quiz.domain.entity.QuizItem.QuizEntity;
import com.study.battleq.modules.quiz.domain.entity.QuizItem.QuizItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
    Optional<QuizEntity> findByIdAndDeletedAtIsNull(Long id);
}
