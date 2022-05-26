package com.eneba.enebaback.controllers;

import com.eneba.enebaback.dto.RateUserRequestDTO;
import com.eneba.enebaback.dto.UserReviewAnswerRequestDTO;
import com.eneba.enebaback.dto.UserReviewAnswerResponseDTO;
import com.eneba.enebaback.dto.UserReviewDTO;
import com.eneba.enebaback.services.UserReviewService;
import com.eneba.enebaback.services.UserService;
import com.eneba.enebaback.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-reviews")
public class UserReviewController {

    @Autowired
    private UserReviewService userReviewService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserReviewDTO> getUserReviews() {
        return userReviewService.getUserReviewsByUserId(userService.getLoggedUserId());
    }

    @GetMapping("/{userId}")
    public List<UserReviewDTO> getUserReviewsById(@PathVariable Long userId) {
        return userReviewService.getUserReviewsByUserId(userId);
    }

    @PostMapping("/rate")
    private Long rateUser(@RequestBody RateUserRequestDTO rateUserRequestDTO) {
        return this.userReviewService.rateUser(rateUserRequestDTO);
    }

    @PostMapping("/answer")
    private UserReviewAnswerResponseDTO answerToReview(@RequestBody UserReviewAnswerRequestDTO userReviewAnswerRequestDto) {
        return this.userReviewService.answerToReview(userReviewAnswerRequestDto);
    }

}
