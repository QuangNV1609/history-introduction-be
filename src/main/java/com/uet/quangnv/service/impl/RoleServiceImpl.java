package com.uet.quangnv.service.impl;

import com.uet.quangnv.dto.RoleDto;
import com.uet.quangnv.repository.RoleRepository;
import com.uet.quangnv.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDto> getAllRole() {
        return roleRepository.getAlllRole();
    }
}
