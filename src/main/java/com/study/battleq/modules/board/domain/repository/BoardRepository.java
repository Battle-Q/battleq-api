package com.study.battleq.modules.board.domain.repository;

import com.study.battleq.modules.board.domain.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends CrudRepository<BoardEntity, Long> {
    Optional<BoardEntity> findByIdAndDeletedAtIsNull(Long id);
    List<BoardEntity> findAllByDeletedAtIsNull();
    List<BoardEntity> findByTitleAndDeletedAtIsNull(String title);
}
