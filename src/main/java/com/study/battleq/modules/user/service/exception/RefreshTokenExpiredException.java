package com.study.battleq.modules.user.service.exception;

import com.study.battleq.infrastructure.common.exception.BattleQException;
import org.springframework.http.HttpStatus;

public class RefreshTokenExpiredException extends BattleQException {

    private static final RefreshTokenExpiredException INSTANCE = new RefreshTokenExpiredException("토큰의 유효시간이 만료되었습니다.", HttpStatus.UNAUTHORIZED);

    public RefreshTokenExpiredException(String message, HttpStatus status) {
        super(message, status);
    }

    public static RefreshTokenExpiredException thrown() {
        return INSTANCE;
    }
}
