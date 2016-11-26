/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.book.repositories;

import br.com.fabiose.book.models.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fabioestrela
 */
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer>{
    
    @Override
    UserRole save(UserRole user);
    
}
