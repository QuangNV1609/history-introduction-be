package com.uet.quangnv.repository;

import com.uet.quangnv.dto.UserDto;

public interface UserRepositoryCustom {
    UserDto getUserInfo(String username);
}
