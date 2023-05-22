package com.study.battleq.modules.user.service;

import com.study.battleq.modules.user.domain.entity.BattleQUser;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserQueryRepository;
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
        UserEntity userEntity = userQueryRepository.findByEmail(username);
        return new BattleQUser(userEntity.getEmail(), userEntity.getPassword(), Collections.singleton(getGrantedAuthority(userEntity)), userEntity.getId(), userEntity.getNickname());
    }

    private SimpleGrantedAuthority getGrantedAuthority(UserEntity userEntity) {
        return new SimpleGrantedAuthority(userEntity.getAuthority().toString());
    }
}
