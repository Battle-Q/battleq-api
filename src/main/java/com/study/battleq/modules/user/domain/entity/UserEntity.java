package com.study.battleq.modules.user.domain.entity;

import com.study.battleq.infrastructure.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "index_users_created_at", columnList = "created_at"),
                @Index(name = "index_users_updated_at", columnList = "updated_at"),
                @Index(name = "index_users_deleted_at", columnList = "deleted_at"),
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_users_email", columnNames = "email"),
                @UniqueConstraint(name = "unique_users_nickname", columnNames = "nickname")
        }
)
public class UserEntity extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "email", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, length = 1024)
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    private String nickname;
    @Column(name = "authority", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Authority authority;

    private UserEntity(String email, String name, String password, String nickname, Authority authority) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
    }

    public static UserEntity of(String email, String name, String password, String nickname, Authority authority) {
        return new UserEntity(email, name, password, nickname, authority);
    }
}
