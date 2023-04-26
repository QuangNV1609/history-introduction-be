package com.uet.quangnv.dto;

import java.util.Date;
import java.util.List;

public class UserDto {
    private String username;
    private List<String> roleName;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private Long imageId;

    public UserDto() {
    }

    public UserDto(String username, String firstName, String lastName, Date birthDay, Long imageId) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.imageId = imageId;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
