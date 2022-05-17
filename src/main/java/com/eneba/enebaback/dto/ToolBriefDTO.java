package com.eneba.enebaback.dto;

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

}
