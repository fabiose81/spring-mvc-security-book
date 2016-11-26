/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.book.services;

/**
 *
 * @author fabioestrela
 */
import br.com.fabiose.book.models.User;
import br.com.fabiose.book.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) throws Exception {
        return userRepository.save(user);
    }

    public void delete(Integer id) throws Exception {
        userRepository.delete(id);
    }

    public List<User> listAll() throws Exception {
        return userRepository.findAll();
    }

    public User findByUserName(String userName) throws Exception {
        return userRepository.findByUserName(userName);
    }
}
