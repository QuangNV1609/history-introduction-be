package com.uet.quangnv.dto;

import java.util.List;

public class UserDto {
    private String username;
    private List<String> roleName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoleName() {
        return roleName;
    }

    public void setRoleName(List<String> roleName) {
        this.roleName = roleName;
    }
}
