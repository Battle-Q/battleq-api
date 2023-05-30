package com.study.battleq.board.repository;

import com.study.battleq.board.domain.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends CrudRepository<BoardEntity, Long> {
    public Optional<BoardEntity> findById(Long id);

    //TODO Page 페이징 처리
    //TODO 중복 메서드 개선?
    //TODO Soft Delete ex) findByTitleAndDeleteAtIsNull();
    public List<BoardEntity> findAll();
    public List<BoardEntity> findByTitle(String title);
    public List<BoardEntity> findByContent(String content);
    public List<BoardEntity> findByCategory(String category);
    public List<BoardEntity> findByWriter(String writer);
}
