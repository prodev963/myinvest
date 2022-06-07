package com.example.myinvest.service;

import com.example.myinvest.entity.UserEntity;
import com.example.myinvest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("USER_NOT_FOUND"));
    }

    public List<UserEntity> getUsers(Specification<UserEntity> specs){
        return userRepository.findAll(Specification.where(specs));
    }

}
