package com.eneba.enebaback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private final Long Id;
    private final String name;

}
