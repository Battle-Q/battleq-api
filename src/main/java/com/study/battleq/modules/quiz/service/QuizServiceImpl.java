package com.study.battleq.modules.quiz.service;

import com.study.battleq.modules.quiz.domain.CreateQuizRequest;
import com.study.battleq.modules.quiz.domain.CreateQuizResponse;
import com.study.battleq.modules.quiz.domain.entity.QuizDto;
import com.study.battleq.modules.quiz.domain.entity.QuizEntity;
import com.study.battleq.modules.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{

    private final QuizRepository quizRepository;


    @Override
    public CreateQuizResponse createQuiz(CreateQuizRequest createQuizRequest) {
        QuizEntity quiz = QuizEntity.of(createQuizRequest.getQuizType(), createQuizRequest.getQuizData());

        QuizEntity save = quizRepository.save(quiz);

        return CreateQuizResponse.of(save.getQuizType(), save.getQuizData());
    }


    @Override
    public QuizDto getQuiz(Long quizId) {
        // 삭제된거 고려
        // 트랜잭션 분리
        QuizEntity quizEntity = quizRepository.findById(quizId).orElseThrow();

        return new QuizDto(quizEntity.getId(), quizEntity.getQuizType(), quizEntity.getQuizData());

    }


}
