package com.eneba.enebaback.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eneba.enebaback.entities.BorrowLog;
import com.eneba.enebaback.entities.User;

@Repository
public interface BorrowLogRepository extends JpaRepository<BorrowLog, Long> {
    List<BorrowLog> findByUser(User user);
}
