package com.eneba.enebaback.repositories;

import java.util.List;

import com.eneba.enebaback.dto.ReservedToolDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eneba.enebaback.entities.BorrowLog;
import com.eneba.enebaback.entities.User;

@Repository
public interface BorrowLogRepository extends JpaRepository<BorrowLog, Long> {
    List<BorrowLog> findByUser(User user);

    @Query(value = "SELECT * FROM BORROW_LOG AS B WHERE B.TOOL_ID = ?1 AND CURRENT_DATE <= B.RETURNED_AT", nativeQuery = true)
    public List<BorrowLog> findFutureBorrows(Long toolId);

    @Query(value = "select new com.eneba.enebaback.dto.ReservedToolDTO(tool.id, tool.name, user.id, user.name, user.surname, borrowedAt, returnedAt, user.email)  from BorrowLog where tool.user.id = ?1")
    public List<ReservedToolDTO> findCurrentlyRentedByUserId(Long id);
}
