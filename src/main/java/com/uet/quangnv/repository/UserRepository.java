package com.uet.quangnv.repository;

import com.uet.quangnv.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {
    @Query(value = "Update User Set is_active = 0 where username = ?1", nativeQuery = true)
    @Modifying
    void blockAccountByUsername(String id);

    @Query(value = "select u.username, u.password, u.isActive from User u join u.roles r where r.roleName = 'ROLE_ADMIN_2'")
    Object[] getAdmin2();
}
