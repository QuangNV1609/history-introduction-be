package com.uet.quangnv.api;

import com.uet.quangnv.entities.User;
import com.uet.quangnv.exception.domain.DuplicateIDException;
import com.uet.quangnv.service.MailService;
import com.uet.quangnv.service.UserService;
import com.uet.quangnv.token.JwtTokenProvider;
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
    private ResponseEntity<String> test() {
        return new ResponseEntity<>("QuangNV Test", HttpStatus.OK);
    }

    @GetMapping(value = "/get/code")
    public ResponseEntity<String> getCode(@RequestParam("email") String email) {
        Integer code = mailService.sendCode(email);
        return new ResponseEntity<String>(code.toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    private ResponseEntity<String> login(@RequestBody User user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(user);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @PostMapping(value = "/signIn")
    private ResponseEntity<User> save(@RequestBody User user) throws DuplicateIDException {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/block-account")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    private void blockAccount(@RequestParam(name = "username") String username) {
        userService.blockAccount(username);
    }

    @DeleteMapping(value = "/delete-account")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    private void deleteAccount(@RequestParam(name = "username") String username) {
        userService.deleteAccount(username);
    }
}
