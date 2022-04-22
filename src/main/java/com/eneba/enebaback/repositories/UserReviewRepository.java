package com.eneba.enebaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eneba.enebaback.entities.UserReview;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}
