package com.eneba.enebaback.dto;

import com.eneba.enebaback.entities.Tool;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToolBriefDTO {

    private Long id;

    private String name;

    private String category;

    private Float price;

    private Integer pricePeriod;

    private Integer status;

    public ToolBriefDTO(Long id, String name, String category, Float price, boolean available) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.pricePeriod = 1;
        this.status = available ? 1 : 2;
    }

    public ToolBriefDTO(Tool tool) {
        this.id = tool.getId();
        this.name = tool.getName();
        this.category = tool.getToolCategory().getCategoryName();
        this.price = tool.getPrice();
        this.pricePeriod = 1;
        this.status = tool.isAvailable() ? 1 : 2;

    }

}
