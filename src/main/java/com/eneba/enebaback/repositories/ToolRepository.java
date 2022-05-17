package com.eneba.enebaback.repositories;

import com.eneba.enebaback.dto.ToolBriefDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eneba.enebaback.entities.Tool;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long>, JpaSpecificationExecutor<Tool> {

    @Query(value = "SELECT * FROM TOOL AS T WHERE T.ID NOT IN (SELECT B.TOOL_ID FROM BORROW_LOG AS B WHERE B.RETURNED_AT IS NULL)", nativeQuery=true)
    List<Tool> findAllAvailableTools();

    @Query(value = "SELECT * FROM TOOL AS T WHERE T.ID NOT IN (SELECT B.TOOL_ID FROM BORROW_LOG AS B WHERE B.RETURNED_AT IS NULL) AND T.TOOL_CATEGORY_ID = ?1", nativeQuery = true)
    List<Tool> findAllAvailableToolsByCategory(Long categoryId);

    @Query(value = "SELECT * FROM TOOL AS T WHERE T.ID = ?1 AND T.ID NOT IN (SELECT B.TOOL_ID FROM BORROW_LOG AS B WHERE B.RETURNED_AT IS NULL)", nativeQuery = true)
    Tool findAvailableToolById(Long id);

    @Query(value = "select new com.eneba.enebaback.dto.ToolBriefDTO(id, name, toolCategory.categoryName, price, available) from Tool where user.id = ?1")
    List<ToolBriefDTO> findAllToolsByUserId(Long userId);
}
