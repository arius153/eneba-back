package com.eneba.enebaback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReviewAnswerRequestDTO {
    private Long userReviewId;
    private String answer;
}
