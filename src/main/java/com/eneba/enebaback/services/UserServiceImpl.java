package com.eneba.enebaback.services;

import com.eneba.enebaback.EnebaBackApplication;
import com.eneba.enebaback.dto.CustomUser;
import com.eneba.enebaback.dto.UserRegisterDTO;
import com.eneba.enebaback.entities.User;
import com.eneba.enebaback.logging.Logging;
import com.eneba.enebaback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return new CustomUser(user.getEmail(), user.getPassword(), new ArrayList<>(), user.getId()) {
        };
    }

    @Logging("Registered user")
    public User RegisterUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists!");
        }
        User user = new User();
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRegisterDTO.getPassword()));
        user.setName(userRegisterDTO.getName());
        user.setSurname(userRegisterDTO.getSurname());
        return userRepository.save(user);
    }

    public Long getLoggedUserId() {
        var securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            var details = securityContext.getAuthentication().getPrincipal();
            if (details instanceof CustomUser) {
                return ((CustomUser) details).getId();
            }
        }
        return null;
    }

    public User getLoggedUserEntity() {
        Long loggedUserID = getLoggedUserId();
        if (loggedUserID == null) {
            return null;
        }
        return userRepository.findById(loggedUserID).orElse(null);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Logging("Updated password")
    public String updatePassword(String newPassword) {
        User user = getLoggedUserEntity();
        if (user == null) {
            return "";
        }

        String hashedPassword = bCryptPasswordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);
        return userRepository.save(user).getPassword();
    }

    @Transactional
    @Logging("Updated phone number")
    public String updatePhoneNumber(String newPhoneNumber) {
        User user = getLoggedUserEntity();
        if (user == null) {
            return "";
        }

        //String hashedPassword = bCryptPasswordEncoder.encode(newPassword);
        //user.setPhoneNumber(newPhoneNumber);
        //return userRepository.save(user).getPhoneNumber();
        return newPhoneNumber;
    }

    public String getUserFullName(Long userId) {
        return userRepository.getFullNameUsingId(userId);
    }
}
