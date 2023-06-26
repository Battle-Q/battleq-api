package com.study.battleq.modules.board.domain.repository;

import com.study.battleq.modules.board.domain.entity.BoardEntity;
import com.study.battleq.modules.board.domain.projection.BoardSummaryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    Optional<BoardEntity> findByIdAndDeletedAtIsNull(Long id);
    List<BoardEntity> findAllByDeletedAtIsNull();
    List<BoardSummaryList> findGenericProjectionsByTitle(String title, Class<BoardSummaryList> boardSummaryListClass);
}
