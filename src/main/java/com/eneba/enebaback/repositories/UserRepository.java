package com.eneba.enebaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eneba.enebaback.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query(value = "select concat(name, ' ' ,surname) from User WHERE id = ?1")
    String getFullNameUsingId(Long userId);

    boolean existsByEmail(String email);
}
