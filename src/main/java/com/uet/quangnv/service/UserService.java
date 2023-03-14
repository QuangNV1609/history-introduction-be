package com.uet.quangnv.service;

import com.uet.quangnv.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(User user);

    List<User> findAllSecondaryAdmins();
}
