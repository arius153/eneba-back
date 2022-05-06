package com.eneba.enebaback.dto;

import com.eneba.enebaback.entities.UserReview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewDTO {

    private Long id;

    private int rating;

    private String comments;

    private String reviewedBy;

    private LocalDateTime reviewedAt;

    public UserReviewDTO(UserReview userReview) {
        this.id = userReview.getId();
        this.rating = userReview.getRating();
        this.comments = userReview.getComments();
        this.reviewedAt = userReview.getReviewedAt();
        this.reviewedBy = userReview.getReviewedByUser().getName() + " " + userReview.getReviewedByUser().getSurname();
    }

}
