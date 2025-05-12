package com.workintech.s19challenge.service;
import com.workintech.s19challenge.entity.user.Role;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.repository.RoleRepository;
import com.workintech.s19challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

//RegisterService olarak da adlandırılır.

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    //password'ü encode eden dependency
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String userName, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setAuthorities(roles);

        return userRepository.save(user);
    }
}
