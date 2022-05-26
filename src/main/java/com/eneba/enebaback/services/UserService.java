package com.eneba.enebaback.services;

import com.eneba.enebaback.dto.CustomUser;
import com.eneba.enebaback.dto.UserRegisterDTO;
import com.eneba.enebaback.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException;

    public User RegisterUser(UserRegisterDTO userRegisterDTO);

    public Long getLoggedUserId();

    public User getLoggedUserEntity();

    public User getUserById(Long id);

    public String updatePassword(String newPassword);

    public String updatePhoneNumber(String newPhoneNumber);

    public String getUserFullName(Long userId);
}
