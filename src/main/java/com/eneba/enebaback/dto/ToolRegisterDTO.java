package com.eneba.enebaback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ToolRegisterDTO {

    private String name;
    private Long category;
    private Float price;
    private String assistedTransportation;
    private String description;
    private String address;
    private Double lat;
    private Double lng;
    private List<Integer> daysAvailable;
    private String pickUpTimeWeekend;
    private String pickUpTimeWorkDay;

}
