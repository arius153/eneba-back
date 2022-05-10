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
public class UserDTO {

    private Long id;

    private String email;

    private String fullName;

    private String natIdNumber;

    private String phoneNumber;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.fullName = user.getName() + " " + user.getSurname();
        this.natIdNumber = "59707271234";
        this.phoneNumber = "+37061234579";
    }
}
