package com.uet.quangnv.service.impl;

import com.uet.quangnv.entities.Role;
import com.uet.quangnv.entities.User;
import com.uet.quangnv.repository.UserRepository;
import com.uet.quangnv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return null;
    }

    private boolean checkRoleAdmin2(List<Role> roles) {
        return roles
                .stream()
                .anyMatch(val -> val.getRoleName().equals("Admin2"));
    }

    @Override
    public List<User> findAllSecondaryAdmins() {
        return userRepository
                .findAll()
                .stream()
                .filter(admin -> checkRoleAdmin2(admin.getRoles()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findById(username);

        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(username + " not found!");
        } else if (!optional.get().getActive()) {
            throw new UsernameNotFoundException(username + " is not active");
        }

        User user = optional.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
