/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.utils;

/**
 *
 * @author Danil
 */
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
 
public class WebUtils {
 
    public static String toString(User user) {
        StringBuilder sb = new StringBuilder();
 
        sb.append("UserName:").append(user.getUsername());
 
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }
    
    // Ищем присутствие роли админа (стоит изменить)
    public static String toStringRole(User user) {
        String provAdmin = new String();
        
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    first = false;
                    if ("ROLE_ADMIN".equals(a.getAuthority())){
                        provAdmin=a.getAuthority();
                    }
                }
            }
        }
        return provAdmin;
    }
     
}