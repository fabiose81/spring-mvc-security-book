/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.book.controllers;

/**
 *
 * @author fabioestrela
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.fabiose.book.services.UserService;
import br.com.fabiose.book.models.User;
import br.com.fabiose.book.models.UserRole;
import br.com.fabiose.book.services.UserRoleService;
import br.com.fabiose.book.util.AuthorityUtil;
import br.com.fabiose.book.util.MessageUtil;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String save(@ModelAttribute User user, Model model) {
        MessageUtil messageUtil = new MessageUtil();
        try {
            user = userService.save(user);
            
            UserRole userRole = new UserRole();
            userRole.setRole("USER");
            userRole.setUser(user);
            
            userRoleService.save(userRole);
            
            messageUtil.setType("alert alert-success");
            messageUtil.setMessage("User " + user.getUserName() + " inserted!");
        } catch (DataIntegrityViolationException de) {
            messageUtil.setType("alert alert-danger");
            messageUtil.setMessage("Already user with this name : " + user.getUserName());
        } catch (Exception e) {
            messageUtil.setType("alert alert-danger");
            messageUtil.setMessage(e.getMessage());
        } finally {
            try {
                model.addAttribute("users", userService.listAll());
            } catch (Exception e) {
            }
            model.addAttribute("user", new User());
            model.addAttribute("msg", messageUtil);
            model.addAttribute("adminAuthority", AuthorityUtil.checkAdminAuthority());
        }
        return "user";
    }

    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        MessageUtil messageUtil = new MessageUtil();
        try {
            userService.delete(id);
            messageUtil.setType("alert alert-success");
            messageUtil.setMessage("User deleted!");
        } catch (Exception e) {
            messageUtil.setType("alert alert-danger");
            messageUtil.setMessage(e.getMessage());
        } finally {
            try {
                model.addAttribute("users", userService.listAll());
            } catch (Exception e) {
            }
            model.addAttribute("user", new User());
            model.addAttribute("msg", messageUtil);
            model.addAttribute("adminAuthority", AuthorityUtil.checkAdminAuthority());
        }
        return "user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String list(Model model) {
        try {
            model.addAttribute("users", userService.listAll());
            model.addAttribute("user", new User());
            model.addAttribute("adminAuthority", AuthorityUtil.checkAdminAuthority());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user";
    }

}
