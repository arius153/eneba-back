package com.eneba.enebaback.dto;

import com.eneba.enebaback.entities.BorrowLog;
import com.eneba.enebaback.entities.Image;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ToolRegisterDTO {

    private Long ownerUserId;

    private Long toolCategoryId;

    private String comment;

    private String geoCordX;

    private String geoCordY;

    private List<byte []> images;

}
