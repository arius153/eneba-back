package com.eneba.enebaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eneba.enebaback.entities.UserReview;

import java.util.List;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {

    @Query(value = "SELECT * FROM USER_REVIEW AS UR WHERE UR.REVIEWED_USER_ID = ?1", nativeQuery = true)
    public List<UserReview> findUserReviewsByUserId(Long id);

}
