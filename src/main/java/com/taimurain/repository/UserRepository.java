package com.taimurain.repository;

import com.taimurain.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByMobileNo(String mobileNo);
}
