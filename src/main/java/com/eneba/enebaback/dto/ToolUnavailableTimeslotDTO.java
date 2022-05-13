package com.eneba.enebaback.dto;

import com.eneba.enebaback.entities.BorrowLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolUnavailableTimeslotDTO {

    private LocalDateTime unavailableFrom;

    private LocalDateTime unavailableTill;

    public ToolUnavailableTimeslotDTO(BorrowLog borrowLog) {
        this.unavailableFrom = borrowLog.getBorrowedAt();
        this.unavailableTill = borrowLog.getReturnedAt();
    }

}
