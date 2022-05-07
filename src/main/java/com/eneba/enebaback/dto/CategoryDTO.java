package com.eneba.enebaback.dto;

import com.eneba.enebaback.entities.ToolCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private final Long id;
    private final String name;

    public CategoryDTO(ToolCategory toolCategory) {
        this.id = toolCategory.getId();
        this.name = toolCategory.getCategoryName();
    }

}
