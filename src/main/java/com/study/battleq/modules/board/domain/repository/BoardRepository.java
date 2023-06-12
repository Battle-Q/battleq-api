package com.study.battleq.modules.board.domain.repository;

import com.study.battleq.modules.board.domain.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends CrudRepository<BoardEntity, Long> {
    Optional<BoardEntity> findById(Long id);

    //TODO Page 페이징 처리
    //TODO 중복 메서드 개선?
    //TODO 삭제 정책 : Soft Delete ex) findByTitleAndDeleteAtIsNull();
    List<BoardEntity> findAll();

    List<BoardEntity> findByTitle(String title);

    List<BoardEntity> findByContent(String content);

    List<BoardEntity> findByCategory(String category);

    List<BoardEntity> findByWriter(String writer);
}
