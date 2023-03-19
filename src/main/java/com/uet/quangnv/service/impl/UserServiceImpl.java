package com.uet.quangnv.service.impl;

import com.uet.quangnv.entities.Role;
import com.uet.quangnv.entities.User;
import com.uet.quangnv.exception.domain.DuplicateIDException;
import com.uet.quangnv.repository.RoleRepository;
import com.uet.quangnv.repository.UserRepository;
import com.uet.quangnv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) throws DuplicateIDException {
        Optional<User> optional = userRepository.findById(user.getUsername());
        if (optional.isPresent()) {
            throw new DuplicateIDException("Mail này đã được sử dụng trước đó!");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user = userRepository.save(user);

        }
        return user;
    }

    @Override
    public void blockAccount(String username) {
        userRepository.blockAccountById(username);
    }

    @Override
    public void deleteAccount(String username) {
        userRepository.deleteById(username);
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
