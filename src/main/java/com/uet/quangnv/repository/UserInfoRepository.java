package com.uet.quangnv.repository;

import com.uet.quangnv.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Modifying
    @Query(value = "Update user_info Set imageid = ?1 where id = ?2", nativeQuery = true)
    void updateImageID(Long imageId, Long userInfoId);

}
