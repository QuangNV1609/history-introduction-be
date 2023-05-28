package com.uet.quangnv.api;

import com.uet.quangnv.entities.UserInfo;
import com.uet.quangnv.entities.UserScore;
import com.uet.quangnv.service.UserInfoService;
import com.uet.quangnv.service.UserScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user-info")
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
public class UserInfoApi {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserScoreService userScoreService;

    @PostMapping(value = "/save")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserInfo> save(@RequestBody UserInfo userInfo) {
        userInfo = userInfoService.save(userInfo);
        userInfo.setUser(null);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @GetMapping(name = "/history-user-score")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<UserScore>> getHistoryUserScore(
            @RequestParam(name = "historicalPeriod", required = false) Integer historicalPeriod,
            @RequestParam(name = "numOfQuestion", required = false) Integer numOfQuestion
    ) {
        List<UserScore> userScores = userScoreService.getHistoryUserScore(historicalPeriod, numOfQuestion);
        return new ResponseEntity<>(userScores, HttpStatus.OK);
    }
}
