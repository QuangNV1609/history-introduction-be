package com.uet.quangnv.ultis;

import com.uet.quangnv.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public final class Utils {
    public static Date convertStringToDate(String dateString, String format) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        try {
            return new SimpleDateFormat(format).parse(dateString);
        } catch (Exception e) {
            throw new RuntimeException("Ngày tháng sai định dạng!");
        }
    }

    public static void setParamQuery(Query query, Map<String, Object> paramMap) {
        paramMap.forEach((key, value) -> {
            query.setParameter(key, value);
        });
    }

    public static UserDto getCurrentUserLogin() {
        UserDto userDto = new UserDto();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userDto.setUsername(authentication.getName());
        List<String> listRoleName = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            listRoleName.add(grantedAuthority.getAuthority());
        }
        userDto.setRoleName(listRoleName);
        return userDto;
    }

    public interface DateFormat {
        String DDMMYYYY = "dd/mm/yyyy";
        String YYYYMMDD = "yyyy-mm-dd";
    }
}
