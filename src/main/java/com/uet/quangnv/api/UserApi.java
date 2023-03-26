package com.uet.quangnv.api;

import com.uet.quangnv.entities.User;
import com.uet.quangnv.exception.domain.DuplicateIDException;
import com.uet.quangnv.service.MailService;
import com.uet.quangnv.service.UserService;
import com.uet.quangnv.token.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
public class UserApi {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MailService mailService;

    @GetMapping(value = "/test")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("QuangNV Test", HttpStatus.OK);
    }

    @GetMapping(value = "/get/code")
    public ResponseEntity<String> getCode(@RequestParam("email") String email) {
        log.info("Request to send code authentication to mail: " + email);
        Integer code = mailService.sendCode(email);
        return new ResponseEntity<String>(code.toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        log.info("Request to log in to user: " + user.getUsername());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(user);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @PostMapping(value = "/signIn")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> addUserByAdmin(@RequestBody User user) throws DuplicateIDException {
        log.info("Request to sign in: " + user.toString());
        userService.addUserByAdmin(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(value = "/sign-in-user")
    public ResponseEntity<User> addUser(@RequestBody User user) throws DuplicateIDException {
        log.info("Request to sign in: " + user.toString());
        userService.addUserByAdmin(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(value = "/add-admin")
    public ResponseEntity<User> addAdmin(@RequestBody User user) throws DuplicateIDException {
        log.info("Request to add user: " + user.toString());
        userService.addUserByAdmin(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/block-account")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void blockAccount(@RequestParam(name = "username") String username) {
        log.info("Request to block acccount: " + username);
        userService.blockAccount(username);
    }

    @DeleteMapping(value = "/delete-account")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAccount(@RequestParam(name = "username") String username) {
        log.info("Request to delete acccount: " + username);
        userService.deleteAccount(username);
    }
}
