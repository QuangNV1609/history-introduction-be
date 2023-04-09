package com.uet.quangnv.repository.impl;

import com.uet.quangnv.dto.RoleDto;
import com.uet.quangnv.entities.Role;
import com.uet.quangnv.repository.RoleRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RoleRepositoryImpl implements RoleRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<RoleDto> getAlllRole() {
        Query query = entityManager.createNativeQuery("Select id, role_name from Role", "Role");
        return query.getResultList();
    }
}
