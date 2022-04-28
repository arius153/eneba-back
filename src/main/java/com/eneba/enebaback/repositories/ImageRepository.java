package com.eneba.enebaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eneba.enebaback.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
