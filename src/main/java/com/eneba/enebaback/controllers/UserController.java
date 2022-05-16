package com.eneba.enebaback.controllers;

import com.eneba.enebaback.dto.UserDTO;
import com.eneba.enebaback.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public UserDTO getLoggedUserData() {
        return new UserDTO(userService.getLoggedUserEntity());
    }

    @GetMapping("/{userId}")
    public UserDTO getUserDataById(@PathVariable Long userId) {
        return new UserDTO(userService.getUserById(userId));
    }

    @PatchMapping("/password")
    public void updatePassword(@RequestBody String newPassword) {
        userService.updatePassword(newPassword);
    }

    @PatchMapping("/phone-number")
    public void updatePhoneNumber(@RequestBody String newPhoneNumber) {
        userService.updatePhoneNumber(newPhoneNumber);
    }
}
