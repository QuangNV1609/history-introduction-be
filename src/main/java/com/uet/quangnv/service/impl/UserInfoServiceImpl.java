package com.uet.quangnv.service.impl;

import com.uet.quangnv.entities.User;
import com.uet.quangnv.entities.UserInfo;
import com.uet.quangnv.repository.UserInfoRepository;
import com.uet.quangnv.repository.UserRepository;
import com.uet.quangnv.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfo save(UserInfo userInfo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> optional = userRepository.findById(username);
        if (optional.isPresent()) {
            userInfo.setUser(optional.get());
            userInfo = userInfoRepository.save(userInfo);
            userInfo.setUser(null);
        }
        return userInfo;
    }
}
