package com.uet.quangnv.api;

import com.uet.quangnv.entities.UserScore;
import com.uet.quangnv.service.UserScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user-score")
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
public class UserScoreApi {
    @Autowired
    private UserScoreService userScoreService;

    @GetMapping(name = "/top-user-score")
    public ResponseEntity<List<UserScore>> getTopUserScore(
            @RequestParam("historicalPeriod") Integer historicalPeriod,
            @RequestParam("numOfQuestion") Integer numOfQuestion) {
        List<UserScore> userScores = userScoreService.getTopUserScore(historicalPeriod, numOfQuestion);
        return new ResponseEntity<>(userScores, HttpStatus.OK);
    }

    @PostMapping(name = "/save")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserScore> saveUserScore(@RequestBody UserScore userScore) {
        userScore = userScoreService.save(userScore);
        return new ResponseEntity<>(userScore, HttpStatus.OK);
    }
}
