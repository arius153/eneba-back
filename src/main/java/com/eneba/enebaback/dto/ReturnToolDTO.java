package com.eneba.enebaback.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturnToolDTO {

    private Long borrowLogId;
    
    private LocalDateTime returnedAt;

}
