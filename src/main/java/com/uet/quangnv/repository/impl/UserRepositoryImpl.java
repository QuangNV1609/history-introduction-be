package com.uet.quangnv.repository.impl;

import com.uet.quangnv.dto.ArticleDto;
import com.uet.quangnv.dto.UserDto;
import com.uet.quangnv.repository.UserRepositoryCustom;
import com.uet.quangnv.ultis.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    public UserDto getUserInfo(String username) {
        StringBuilder sql = new StringBuilder("SELECT \n" +
                "  user.username," +
                "  user_info.first_name, \n" +
                "  user_info.last_name, \n" +
                "  user_info.birth_day, \n" +
                "  user_info.imageid \n" +
                "FROM \n" +
                "  user \n" +
                "  LEFT JOIN user_info ON user.username = user_info.user_id \n" +
                "WHERE \n" +
                "  user.username = :username");
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        Query query = entityManager.createNativeQuery(sql.toString(), "UserDto");
        Utils.setParamQuery(query, params);
        return (UserDto) query.getSingleResult();
    }
}
