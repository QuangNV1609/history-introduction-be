package com.uet.quangnv.service;

import com.uet.quangnv.entities.User;
import com.uet.quangnv.exception.domain.DuplicateIDException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User addUserByAdmin(User user) throws DuplicateIDException;

    User addUser(User user) throws DuplicateIDException;

    void blockAccount(String username);

    void deleteAccount(String username);

    Object[] getAdmin2();
}
