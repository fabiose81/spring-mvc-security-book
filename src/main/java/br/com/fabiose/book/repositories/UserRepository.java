/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.book.repositories;

/**
 *
 * @author fabioestrela
 */

import br.com.fabiose.book.models.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    
    @Override
    User save(User user);
    
    @Override
    void delete(Integer id);

    User findByUserName(String userName);
    
    @Override
    List<User> findAll();
}
