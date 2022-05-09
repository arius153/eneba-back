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
public class ToolDTO {

    private Long id;

    private String toolCategory;

    private Long userId;

    private String description;

    private Double geoCordX;

    private Double geoCordY;

    private Float price;

    private Boolean available;

    private String name;

    private String assistedTransportation;

    private List<String> images;

    private String formattedAddress;

    private String pickUpTimeWorkDay;

    private String pickUpTimeWeekend;

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
