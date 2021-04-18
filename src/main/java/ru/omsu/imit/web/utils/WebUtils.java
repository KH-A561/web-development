package ru.omsu.imit.web.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class WebUtils {
    public static String getUserInfo(User user) {
        String userInfo = format("UserName: %s ", user.getUsername());
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            return userInfo.concat(
                    format("( %s )",
                    authorities.stream().map(Object::toString).collect(Collectors.joining(", ")))
            );
        } else {
            return userInfo.concat("( )");
        }
    }
}
