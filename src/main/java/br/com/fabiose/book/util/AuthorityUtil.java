/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.book.util;

import java.util.Collection;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author fabioestrela
 */
public class AuthorityUtil {

    public static boolean checkAdminAuthority() {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean adminAuthority = false;
        for (SimpleGrantedAuthority sga : authorities) {
            if (sga.getAuthority().equalsIgnoreCase("ADMIN")) {
                adminAuthority = true;
                break;
            }
        }
        return adminAuthority;
    }
}
