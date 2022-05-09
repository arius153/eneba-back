package com.eneba.enebaback.services;

import com.eneba.enebaback.dto.RateUserRequestDTO;
import com.eneba.enebaback.dto.UserReviewAnswerRequestDTO;
import com.eneba.enebaback.dto.UserReviewAnswerResponseDTO;
import com.eneba.enebaback.dto.UserReviewDTO;
import com.eneba.enebaback.entities.UserReview;
import com.eneba.enebaback.entities.UserReviewAnswer;
import com.eneba.enebaback.repositories.UserReviewAnswerRepository;
import com.eneba.enebaback.repositories.UserReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    UserServiceImpl userService;

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

    public Long rateUser(RateUserRequestDTO rateUserRequestDTO) {
        UserReview userReview = UserReview.builder()
                .comments(rateUserRequestDTO.getComment())
                .rating(rateUserRequestDTO.getRating())
                .reviewedAt(LocalDateTime.now())
                .reviewedByUser(userService.getLoggedUserEntity())
                .reviewedUser(userService.getUserById(rateUserRequestDTO.getUserToRateId()))
                .build();
        return userReviewRepository.save(userReview).getId();
    }

    @Transactional
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
