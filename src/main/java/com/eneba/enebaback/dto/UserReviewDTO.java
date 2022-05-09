package com.eneba.enebaback.dto;

import com.eneba.enebaback.entities.UserReview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewDTO {

    private Long id;

    private int rating;

    private String comments;

    private String reviewedBy;

    private LocalDateTime reviewedAt;

    private List<UserReviewAnswerResponseDTO> answers;

    public UserReviewDTO(UserReview userReview) {
        this.id = userReview.getId();
        this.rating = userReview.getRating();
        this.comments = userReview.getComments();
        this.reviewedAt = userReview.getReviewedAt();
        this.reviewedBy = userReview.getReviewedByUser().getName() + " " + userReview.getReviewedByUser().getSurname();
        this.answers = userReview.getAnswers().stream().map(UserReviewAnswerResponseDTO::new).collect(Collectors.toList());
    }

}
