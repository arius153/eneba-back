package com.eneba.enebaback.services;

import com.eneba.enebaback.dto.*;

import java.util.List;

public interface UserReviewService {

    public SimplifiedUserDTO getUserAverage(Long userId);

    public List<UserReviewDTO> getUserReviewsByUserId(Long userId);

    public Long rateUser(RateUserRequestDTO rateUserRequestDTO);

    public UserReviewAnswerResponseDTO answerToReview(UserReviewAnswerRequestDTO userReviewAnswerRequestDto);
}
