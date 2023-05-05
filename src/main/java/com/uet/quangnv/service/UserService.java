package com.uet.quangnv.service;

import com.uet.quangnv.dto.UserDto;
import com.uet.quangnv.entities.User;
import com.uet.quangnv.exception.domain.DuplicateIDException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface UserService extends UserDetailsService {
    User addUserByAdmin(User user) throws DuplicateIDException;

    User addUser(User user) throws DuplicateIDException;

    void blockAccount(String username);

    void deleteAccount(String username);

    void deleteMultiAccount(List<String> username);

    Object[] getAdmin2();

    UserDto getUserInfo();
}
