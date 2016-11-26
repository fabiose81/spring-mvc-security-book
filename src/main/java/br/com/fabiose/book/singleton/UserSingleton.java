/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.book.singleton;

import br.com.fabiose.book.models.User;

/**
 *
 * @author fabioestrela
 */
public class UserSingleton {

    private static UserSingleton userSingleton = null;

    private static User user;

    private UserSingleton() {
        setUser(new User());
    }

    public static UserSingleton getInstance() {
        if (userSingleton == null) {
            userSingleton = new UserSingleton();
        }
        return userSingleton;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
