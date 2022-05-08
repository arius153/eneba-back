package com.eneba.enebaback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RateUserRequestDTO {

    private String comment;
    private Long userToRateId;
    private Integer rating;

}
