package com.eneba.enebaback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter @Setter @Builder
public class ToolSortModel {
    private String sortBy;

    private Boolean reversed;

    public Sort.Direction getSortDirection() {
        if (reversed == null) {
            return null;
        }

        return reversed ? Sort.Direction.DESC : Sort.Direction.ASC;
    }

    public boolean isEmpty() {
        return sortBy == null && reversed == null;
    }
}
