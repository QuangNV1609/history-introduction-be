package com.uet.quangnv.service;

import com.uet.quangnv.entities.User;
import com.uet.quangnv.exception.domain.DuplicateIDException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(User user) throws DuplicateIDException;

    void blockAccount(String username);

    void deleteAccount(String username);

    List<User> findAllSecondaryAdmins();
}
