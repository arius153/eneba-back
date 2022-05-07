package com.eneba.enebaback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Builder
public class ToolFilterModel {
    private Integer minPrice;
    private Integer maxPrice;
    private Integer maxRange;
    private List<Integer> category;

    public boolean isEmpty() {
        return minPrice == null && maxPrice == null && maxRange == null && (category == null || category.isEmpty());
    }
}
