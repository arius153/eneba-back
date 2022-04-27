package com.eneba.enebaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eneba.enebaback.entities.Tool;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
}