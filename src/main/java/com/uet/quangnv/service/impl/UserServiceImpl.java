package com.uet.quangnv.service.impl;

import com.uet.quangnv.dto.UserDto;
import com.uet.quangnv.entities.Role;
import com.uet.quangnv.entities.User;
import com.uet.quangnv.exception.domain.DataFormatWrong;
import com.uet.quangnv.exception.domain.DuplicateIDException;
import com.uet.quangnv.repository.RoleRepository;
import com.uet.quangnv.repository.UserRepository;
import com.uet.quangnv.service.UserService;
import com.uet.quangnv.ultis.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUserByAdmin(User user) throws DuplicateIDException {
        Optional<User> optional = userRepository.findById(user.getUsername());
        if (optional.isPresent()) {
            throw new DuplicateIDException("Mail này đã được sử dụng trước đó!");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user = userRepository.save(user);

        }
        return user;
    }

    @Override
    public User addUser(User user) throws DuplicateIDException {
        Optional<User> optional = userRepository.findById(user.getUsername());
        if (optional.isPresent()) {
            throw new DuplicateIDException("Mail này đã được sử dụng trước đó!");
        } else {
            Optional<Role> optionalRole = roleRepository.findByRoleName("ROLE_USER");
            if (optionalRole.isPresent()) {
                user.setRoles(List.of(optionalRole.get()));
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user = userRepository.save(user);

        }
        return user;
    }

    @Override
    public void blockAccount(String username) {
        Optional<User> optional = userRepository.findById(username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(username + " not found!");
        } else if (!optional.get().getActive()) {
            userRepository.openAccountByUsername(username);
        } else {
            userRepository.blockAccountByUsername(username);
        }
    }

    @Override
    public void deleteAccount(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findById(username);

        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(username + " not found!");
        } else if (!optional.get().getActive()) {
            throw new UsernameNotFoundException(username + " is not active");
        }

        User user = optional.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public Object[] getAdmin2() {
        return userRepository.getAdmin2();
    }

    @Override
    public UserDto getUserInfo() {
        UserDto userDto = Utils.getCurrentUserLogin();
        UserDto userDtoSaved = userRepository.getUserInfo(userDto.getUsername());
        userDtoSaved.setRoleName(userDto.getRoleName());
        return userDtoSaved;
    }

    @Override
    public Boolean checkUserExits(String username) {
        Optional<User> optional = userRepository.findById(username);
        if (optional.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteMultiAccount(List<String> username) {
        userRepository.deleteAllById(username);
    }

    @Override
    public void changePassword(UserDto userDto) throws DataFormatWrong {
        if (userDto.getNewPassword().equals(userDto.getPassword())) {
            throw new DataFormatWrong(" Mật khẩu mới và mật khẩu cũ đang trùng nhau!");
        } else {
            userDto.setUsername(Utils.getCurrentUserLogin().getUsername());
            Optional<User> optional = userRepository.findById(userDto.getUsername());
            if (!optional.isPresent()) {
                throw new UsernameNotFoundException(userDto.getUsername() + " not found!");
            } else if (!optional.get().getActive()) {
                throw new UsernameNotFoundException(userDto.getUsername() + " is not active");
            } else if (!passwordEncoder.matches(userDto.getPassword(), optional.get().getPassword())) {
                throw new DataFormatWrong("Mật khẩu cũ không đúng!");
            } else {
                userRepository.updatePassword(passwordEncoder.encode(userDto.getNewPassword()), userDto.getUsername());
            }
        }

    }
}
