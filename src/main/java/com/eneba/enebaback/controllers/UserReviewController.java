package com.eneba.enebaback.controllers;

import com.eneba.enebaback.dto.RateUserRequestDTO;
import com.eneba.enebaback.dto.UserReviewAnswerRequestDTO;
import com.eneba.enebaback.dto.UserReviewAnswerResponseDTO;
import com.eneba.enebaback.dto.UserReviewDTO;
import com.eneba.enebaback.services.UserReviewServiceImpl;
import com.eneba.enebaback.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-reviews")
public class UserReviewController {

    @Autowired
    private UserReviewServiceImpl userReviewService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public List<UserReviewDTO> getUserReviewsById() {
        return userReviewService.getUserReviewsByUserId(userService.getLoggedUserId());
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
