package com.study.battleq.modules.user.service;

import com.study.battleq.modules.user.domain.entity.BattleQUser;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserQueryRepository;
import com.study.battleq.modules.user.domain.repository.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserQueryRepository userQueryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = findByEmail(username);
        return new BattleQUser(userEntity.getEmail(), userEntity.getPassword(), Collections.singleton(getGrantedAuthority(userEntity)), userEntity.getId(), userEntity.getNickname());
    }

    private UserEntity findByEmail(String username) {
        try {
            return userQueryRepository.findByEmail(username);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("해당 회원이 존재하지 않습니다.");
        }
    }

    private SimpleGrantedAuthority getGrantedAuthority(UserEntity userEntity) {
        return new SimpleGrantedAuthority(userEntity.getAuthority().toString());
    }
}
