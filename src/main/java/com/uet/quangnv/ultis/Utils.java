package com.uet.quangnv.ultis;

import com.uet.quangnv.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public static Date beforeSendDateToClient(Date date) {
        try {
            SimpleDateFormat myDate = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
            myDate.setTimeZone(TimeZone.getTimeZone("UTC"));
            return myDate.parse(date.toString());
        } catch (Exception e) {
            return null;
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
        String DDMMYYYY = "dd/MM/yyyy";
        String YYYYMMDD = "yyyy-MM-dd";
    }
}
