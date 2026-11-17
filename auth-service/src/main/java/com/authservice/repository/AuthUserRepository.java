package com.authservice.repository;

import com.authservice.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser,Long> {

    public Optional<AuthUser> findByEmail(String email);
    public Optional<AuthUser> findByUserId(Long userId);
}
