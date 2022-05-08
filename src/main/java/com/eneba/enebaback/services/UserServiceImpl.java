package com.eneba.enebaback.services;

import com.eneba.enebaback.dto.CustomUser;
import com.eneba.enebaback.dto.UserRegisterDTO;
import com.eneba.enebaback.entities.User;
import com.eneba.enebaback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return new CustomUser(user.getEmail(), user.getPassword(), new ArrayList<>(), user.getId()) {
        };
    }

    public User RegisterUser(UserRegisterDTO userRegisterDTO) {
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

    public User getUserById() {
        Long loggedUserID = getLoggedUserId();
        if (loggedUserID == null) {
            return null;
        }
        return userRepository.findById(loggedUserID).orElse(null);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
