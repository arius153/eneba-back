package com.eneba.enebaback.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowToolDTO {

    private String toolName;

    private Long toolId;

    private Long borrowedByUserId;

    private String ownerName;

    private String ownerLastName;

    private LocalDateTime borrowedAt;

    private LocalDateTime returnedAt;

    private LocalDate borrowedAtDate;

    private LocalDate returnedAtDate;

    private Float toolPrice;

    private String toolPlace;

    private Double ownerGeoCordX;

    private Double ownerGeoCordY;

    private Float pricePaid;
}
