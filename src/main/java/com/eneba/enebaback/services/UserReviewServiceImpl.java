package com.eneba.enebaback.services;

import com.eneba.enebaback.dto.*;
import com.eneba.enebaback.entities.UserReview;
import com.eneba.enebaback.entities.UserReviewAnswer;
import com.eneba.enebaback.logging.Logging;
import com.eneba.enebaback.repositories.ToolRepository;
import com.eneba.enebaback.repositories.UserReviewAnswerRepository;
import com.eneba.enebaback.repositories.UserReviewRepository;
import com.eneba.enebaback.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserReviewServiceImpl {

    @Autowired
    UserReviewRepository userReviewRepository;
    @Autowired
    UserReviewAnswerRepository userReviewAnswerRepository;
    @Autowired
    ToolRepository toolRepository;

    @Autowired
    UserServiceImpl userService;

    @Logging("Access user reviews")
    public List<UserReviewDTO> getUserReviewsByUserId(Long userId) {
        List<UserReview> userReviews = userReviewRepository.findUserReviewsByUserId(userId);
        List<UserReviewDTO> userReviewDTOS = new ArrayList<>();
        if (userReviews.isEmpty()) {
            return userReviewDTOS;
        }
        for (UserReview userReview : userReviews) {
            userReviewDTOS.add(new UserReviewDTO(userReview));
        }
        return userReviewDTOS;
    }

    @Logging("Rated a user")
    public Long rateUser(RateUserRequestDTO rateUserRequestDTO) {
        UserReview userReview = UserReview.builder()
                .comments(rateUserRequestDTO.getComment())
                .rating(rateUserRequestDTO.getRating())
                .toolName(toolRepository.getById(rateUserRequestDTO.getToolToRateId()).getName())
                .reviewedAt(LocalDateTime.now())
                .reviewedByUser(userService.getLoggedUserEntity())
                .reviewedUser(userService.getUserById(rateUserRequestDTO.getUserToRateId()))
                .build();
        return userReviewRepository.save(userReview).getId();
    }

    public SimplifiedUserDTO getUserAverage(Long userId) {
        SimplifiedUserDTO userReviewAverageAndCount = userReviewRepository.getSimplifiedUserReviewsUsingId(userId);
        userReviewAverageAndCount.setReviewAverage(MathUtils.round(userReviewAverageAndCount.getReviewAverage(), 2));
        userReviewAverageAndCount.setUserId(userId);
        return userReviewAverageAndCount;
    }

    @Transactional
    @Logging("Answered to user review")
    public UserReviewAnswerResponseDTO answerToReview(UserReviewAnswerRequestDTO userReviewAnswerRequestDto) {
        var userReview = userReviewRepository.findById(userReviewAnswerRequestDto.getUserReviewId()).orElse(null);
        if (userReview == null) {
            return null;
        }
        var userReviewAnswer = UserReviewAnswer.builder()
                .answer(userReviewAnswerRequestDto.getAnswer())
                .answeredBy(userService.getLoggedUserEntity())
                .answeredAt(LocalDateTime.now())
                .userReview(userReview)
                .build();

        userReviewAnswer = userReviewAnswerRepository.saveAndFlush(userReviewAnswer);
        return new UserReviewAnswerResponseDTO(userReviewAnswer);
    }
}
