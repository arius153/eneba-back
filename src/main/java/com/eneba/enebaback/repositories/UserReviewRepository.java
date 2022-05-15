package com.eneba.enebaback.repositories;

import com.eneba.enebaback.dto.SimplifiedUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eneba.enebaback.entities.UserReview;

import java.util.List;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {

    @Query(value = "SELECT * FROM USER_REVIEW AS UR WHERE UR.REVIEWED_USER_ID = ?1", nativeQuery = true)
    List<UserReview> findUserReviewsByUserId(Long id);

    @Query(value = "select new com.eneba.enebaback.dto.SimplifiedUserDTO(coalesce(avg(rating), 0), coalesce(count(rating), 0)) from UserReview where reviewedUser.id = ?1")
    SimplifiedUserDTO getSimplifiedUserReviewsUsingId(Long userId);
}
