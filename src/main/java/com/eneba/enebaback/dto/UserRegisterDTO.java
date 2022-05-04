package com.eneba.enebaback.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterDTO {

    private String email;

    private String password;

    private String name;

    private String surname;

}
