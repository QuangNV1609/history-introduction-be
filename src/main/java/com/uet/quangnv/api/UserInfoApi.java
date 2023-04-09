package com.uet.quangnv.api;

import com.uet.quangnv.entities.UserInfo;
import com.uet.quangnv.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user-info")
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
public class UserInfoApi {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping(value = "/save")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserInfo> save(@RequestBody UserInfo userInfo) {
        userInfo = userInfoService.save(userInfo);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }
}
