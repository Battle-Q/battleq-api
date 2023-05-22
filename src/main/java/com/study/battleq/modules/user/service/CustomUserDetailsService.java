package com.study.battleq.modules.user.service;

import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
        return new User(String.valueOf(userEntity.getId()), null, Collections.singleton(getGrantedAuthority(userEntity)));
    }

    private SimpleGrantedAuthority getGrantedAuthority(UserEntity userEntity) {
        return new SimpleGrantedAuthority(userEntity.getAuthority().toString());
    }
}
