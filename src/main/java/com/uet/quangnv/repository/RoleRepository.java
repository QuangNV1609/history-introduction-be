package com.uet.quangnv.repository;

import com.uet.quangnv.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, RoleRepositoryCustom {
    Optional<Role> findByRoleName(String roleName);
}
