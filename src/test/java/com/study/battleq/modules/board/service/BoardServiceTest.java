package com.study.battleq.modules.board.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardServiceTest {
    /**
     * 트랜잭션을 관리하는 것이 주요 책임 (CRUD에 대한 책임은 Repository에 역임)
     * Repository의 동작은 Repository Test에서 확인했으므로 ㄱRepository의 메소드가 제대로 호출되는지만 확인하면 되므로 실제 Repository가 아닌 Mock ㄲ데ㅐ냐새교fmf tkdydgksek.
     * Mockito를 사용하므로 실제 저장/조회가 발생하지 않는다.
     */
    @Test
    void save() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByTitle() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}