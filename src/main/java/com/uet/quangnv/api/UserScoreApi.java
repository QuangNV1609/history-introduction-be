package com.uet.quangnv.api;

import com.uet.quangnv.service.UserScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user-info")
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
public class UserScoreApi {
    @Autowired
    private UserScoreService userScoreService;
}
