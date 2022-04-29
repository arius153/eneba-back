package com.eneba.enebaback.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowToolDTO {

    private Long toolId;

    private Long borrowedByUserId;

    private LocalDateTime borrowedAt;

}
