package com.eneba.enebaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eneba.enebaback.entities.BorrowLog;

@Repository
public interface BorrowLogRepository extends JpaRepository<BorrowLog, Long> {
}
