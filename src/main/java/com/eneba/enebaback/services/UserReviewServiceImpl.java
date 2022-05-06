package com.eneba.enebaback.services;

import com.eneba.enebaback.dto.UserReviewDTO;
import com.eneba.enebaback.entities.UserReview;
import com.eneba.enebaback.repositories.UserReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserReviewServiceImpl {

    @Autowired
    UserReviewRepository userReviewRepository;

    public List<UserReviewDTO> getUserReviewsByUserId(Long userId) {
        List<UserReview> userReviews = userReviewRepository.findUserReviewsByUserId(userId);
        List<UserReviewDTO> userReviewDTOS = new ArrayList<UserReviewDTO>();
        if(userReviews.isEmpty()) {
            return userReviewDTOS;
        }
        for(UserReview userReview : userReviews) {
            userReviewDTOS.add(new UserReviewDTO(userReview));
        }
        return userReviewDTOS;
    }

}
