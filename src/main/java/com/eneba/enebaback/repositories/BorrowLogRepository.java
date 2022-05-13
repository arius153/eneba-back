package com.eneba.enebaback.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eneba.enebaback.entities.BorrowLog;
import com.eneba.enebaback.entities.User;

@Repository
public interface BorrowLogRepository extends JpaRepository<BorrowLog, Long> {
    List<BorrowLog> findByUser(User user);

    @Query(value = "SELECT * FROM BORROW_LOG AS B WHERE B.TOOL_ID = ?1 AND B.RETURNED_AT <= CURRENT_DATE)", nativeQuery = true)
    public List<BorrowLog> findFutureBorrows(Long toolId);
}
