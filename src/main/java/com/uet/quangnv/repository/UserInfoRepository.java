package com.uet.quangnv.repository;

import com.uet.quangnv.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Modifying
    @Query(value = "Update user_info Set imageid = ?1 where id = ?2", nativeQuery = true)
    void updateImageID(Long imageId, Long userInfoId);

    @Modifying
    @Query(value = "Update user_info Set first_name = ?1, last_name = ?2 where id = ?3", nativeQuery = true)
    void updateFirstNameAndLastName(String firstName, String lastName, Long userID);

    @Query(value = "Select * from user_info where user_id = ?", nativeQuery = true)
    Optional<UserInfo> checkUserInfoCreatedByUsername(String username);

}
