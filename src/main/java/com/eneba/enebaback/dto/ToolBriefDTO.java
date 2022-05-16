package com.eneba.enebaback.dto;

import java.util.List;

import com.eneba.enebaback.entities.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ToolBriefDTO {

    private Long id;

    private String name;

    private String category;

    private Float price;

    private Integer pricePeriod;

    private Integer status;

    public ToolBriefDTO(Tool tool) {
        this.id = tool.getId();
        this.name = tool.getName();
        this.category = tool.getToolCategory().getCategoryName();
        this.price = tool.getPrice();
        this.pricePeriod = 1;
        this.status = tool.isAvailable() ? 1 : 2;
    }
}
