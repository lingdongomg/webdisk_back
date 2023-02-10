package com.hukaichao.webdisk.repository;

import com.hukaichao.webdisk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, String> {
}

