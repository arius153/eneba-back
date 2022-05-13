package com.eneba.enebaback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowingDTO {

    private Long toolId;

    private LocalDateTime borrowedAt;

    private LocalDateTime returnedAt;

}
