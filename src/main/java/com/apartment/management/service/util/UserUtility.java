package com.apartment.management.service.util;

import com.apartment.management.entity.User;
import com.apartment.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * A UserUtility
 */
@Component
public class UserUtility {

    private static UserService userService;

    @Autowired
    public UserUtility(UserService userService) {
        UserUtility.userService = userService;
    }

    /**
     * Get Current Login User Username
     *
     * @return username
     */
    public static String getCurrentL0ginUserUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                return userDetails.getUsername();
            }
        }
        return null;
    }

    /**
     * Get Current Login User
     *
     * @return current login User
     */
    public static User getCurrentLoginUser() {
        return userService.findByUsername(getCurrentL0ginUserUsername());
    }
}
