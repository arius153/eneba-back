package com.eneba.enebaback.dto;

import com.eneba.enebaback.entities.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ToolDTO {

    private Long id;

    private String toolCategory;

    private Long userId;

    private String description;

    private String geoCordX;

    private String geoCordY;

    private Double price;

    private Boolean available;

    private String name;

    public ToolDTO(Tool tool) {
        this.id = tool.getId();
        this.toolCategory = tool.getToolCategory().getCategoryName();
        this.userId = tool.getUser().getId();
        this.description = tool.getDescription();
        this.geoCordX = tool.getGeoCordX();
        this.geoCordY = tool.getGeoCordY();
        this.price = tool.getPrice();
        this.available = tool.isAvailable();
        this.name = tool.getName();
    }
}
