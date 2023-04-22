package com.uet.quangnv.dto;

import com.uet.quangnv.entities.User;

import javax.persistence.*;
import java.util.Date;

public class UserInfoDto {
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String username;
}
