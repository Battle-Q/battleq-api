package com.study.battleq.modules.quiz.repository;

import com.study.battleq.modules.quiz.domain.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<QuizEntity, String> {
}
