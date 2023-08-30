package com.study.battleq.modules.board.domain.repository;

import com.study.battleq.modules.board.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @EntityGraph(attributePaths = {"user"})
    Optional<BoardEntity> findByIdAndDeletedAtIsNull(Long id);

    @EntityGraph(attributePaths = {"user"})
    List<BoardEntity> findAllByDeletedAtIsNull();

    @EntityGraph(attributePaths = {"user"})
    List<BoardEntity> findByTitleAndDeletedAtIsNull(String title);

}
