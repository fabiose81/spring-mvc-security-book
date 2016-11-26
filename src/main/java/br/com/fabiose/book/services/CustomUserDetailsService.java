/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.book.services;

import br.com.fabiose.book.models.User;
import br.com.fabiose.book.models.UserRole;
import br.com.fabiose.book.repositories.UserRepository;
import br.com.fabiose.book.singleton.UserSingleton;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author fabioestrela
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
 
    private UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user == null) {
			return null;
		}
		List<GrantedAuthority> auth = AuthorityUtils
				.commaSeparatedStringToAuthorityList("USER");
                         
                for(UserRole userRole : user.getUserRoles()){
                    if(userRole.getRole().equalsIgnoreCase("ADMIN")){
                        auth = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ADMIN");
                        
                        break;
                    }
                }
                UserSingleton.getInstance().setUser(user);
                return new org.springframework.security.core.userdetails.User(username, user.getPassword(),auth);
	}
    
}
