package com.example.marketplace.repos;

import com.example.marketplace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserCrudRepo extends JpaRepository<User, Integer> {
}
