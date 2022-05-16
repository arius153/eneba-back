package com.eneba.enebaback.dto;

import lombok.*;

@Getter
@Setter
public class SimplifiedUserDTO {

    private Long userId;
    private String fullName;
    private double reviewAverage;
    private Long reviewCount;

    public SimplifiedUserDTO(int reviewAverage, int reviewCount) {
        this.reviewAverage = (double) reviewAverage;
        this.reviewCount = (long) reviewCount;
    }

    public SimplifiedUserDTO(double reviewAverage, Long reviewCount) {
        this.reviewAverage = reviewAverage;
        this.reviewCount = reviewCount;
    }
}
