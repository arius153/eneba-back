package com.eneba.enebaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eneba.enebaback.entities.ToolCategory;

@Repository
public interface ToolCategoryRepository extends JpaRepository<ToolCategory, Long> {
}
