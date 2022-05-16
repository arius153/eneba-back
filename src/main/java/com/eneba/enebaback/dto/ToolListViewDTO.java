package com.eneba.enebaback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToolListViewDTO {
    private Long id;
    private String name;
    private String category;
    private Float price;
    private Integer pricePeriod = 1;
    private Integer status = 1;

    public ToolListViewDTO(Long id, String name, String category, Float price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
