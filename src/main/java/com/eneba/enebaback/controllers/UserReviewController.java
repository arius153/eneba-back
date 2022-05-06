package com.eneba.enebaback.controllers;

import com.eneba.enebaback.dto.UserReviewDTO;
import com.eneba.enebaback.services.UserReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-reviews")
public class UserReviewController {

    @Autowired
    private UserReviewServiceImpl userReviewService;

    @GetMapping
    public List<UserReviewDTO> getUserReviewsById(@RequestParam Long id) {
        return userReviewService.getUserReviewsByUserId(id);
    }

}
