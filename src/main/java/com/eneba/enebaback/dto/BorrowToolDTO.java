package com.eneba.enebaback.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowToolDTO {

    private Long toolId;

    private Long borrowedByUserId;

    private String ownerName;

    private String ownerLastName;

    private LocalDateTime borrowedAt;

    private LocalDateTime returnedAt;

    private Float toolPrice;

    private String ownerAddress;

    private Double ownerGeoCordX;

    private Double ownerGeoCordY;
}
