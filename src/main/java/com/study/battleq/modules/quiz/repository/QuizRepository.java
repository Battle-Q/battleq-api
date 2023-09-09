package com.study.battleq.modules.quiz.repository;

import com.study.battleq.modules.quiz.domain.entity.QuizItem.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findByIdAndDeletedAtIsNull(Long id);
}
