package com.uet.quangnv.service.impl;

import com.uet.quangnv.repository.UserScoreRepository;
import com.uet.quangnv.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserScoreServiceImpl implements UserScoreService {
    @Autowired
    private UserScoreRepository userScoreRepository;
}
