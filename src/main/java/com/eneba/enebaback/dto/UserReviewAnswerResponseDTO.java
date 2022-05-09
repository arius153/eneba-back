package com.eneba.enebaback.dto;

import com.eneba.enebaback.entities.UserReviewAnswer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserReviewAnswerResponseDTO {

    private Long id;
    private String answer;
    private String answeredBy;
    private LocalDateTime answeredAt;

    public UserReviewAnswerResponseDTO(UserReviewAnswer userReviewAnswer) {
        this.id = userReviewAnswer.getId();
        this.answer = userReviewAnswer.getAnswer();
        this.answeredAt = userReviewAnswer.getAnsweredAt();
        this.answeredBy = userReviewAnswer.getAnsweredBy().getName() + " " + userReviewAnswer.getAnsweredBy().getSurname();
    }
}
