package com.eneba.enebaback.dto;

import com.eneba.enebaback.entities.Image;
import com.eneba.enebaback.entities.Tool;
import lombok.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ToolRegisterDTO {

    private Long id;
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
    private List<byte[]> files;
    private Long version;
    private Boolean override;
}
