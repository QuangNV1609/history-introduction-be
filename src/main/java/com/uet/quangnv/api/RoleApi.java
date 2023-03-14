package com.uet.quangnv.api;

import com.uet.quangnv.entities.Role;
import com.uet.quangnv.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/role")
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
public class RoleApi {
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<Role>> getAllRole() {
        log.info("Request to get all roles: ");
        List<Role> roles = roleService.getAllRole();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


}
