package com.example.myinvest.repository;

import com.example.myinvest.entity.UserEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> , JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findAllByBirthdayContains(int b);

    List<UserEntity> findAllByGender(String gender);

    List<UserEntity> findAllByBirthdayContainsAndGender(int b,String gender);

    @Override
    Optional<UserEntity> findById(Long userId);
}
