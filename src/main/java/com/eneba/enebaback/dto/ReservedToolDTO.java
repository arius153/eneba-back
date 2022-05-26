package com.eneba.enebaback.dto;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class ReservedToolDTO {

    private final Long toolId;
    private final String toolName;
    private final Long borrowerId;
    private final String borrowerFullName;
    private final String date;
    private final String borrowerEmail;

    public ReservedToolDTO(Long toolId, String toolName, Long borrowerId, String borrowerName, String borrowerSurname, LocalDateTime borrowedAt, LocalDateTime returnedAt, String borrowerEmail) {
        this.toolId = toolId;
        this.toolName = toolName;
        this.borrowerId = borrowerId;
        this.borrowerFullName = borrowerName + " " + borrowerSurname;
        this.borrowerEmail = borrowerEmail;
        this.date = borrowedAt.toLocalDate().toString() + " - " + returnedAt.toLocalDate().toString();
    }
}
