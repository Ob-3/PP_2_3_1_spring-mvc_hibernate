package com.web.controller;

import com.web.model.User;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String homePage() {
        return "index";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "list";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }


    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        System.out.println("Controller addUser - addUser: " + user);
        userService.saveUser(user);
        return "redirect:/users/list";
    }

    @GetMapping("/edit")
    public String showEditUserForm(@RequestParam Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/users/list";
        }
        model.addAttribute("user", user);
        return "edit";
    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/users/list";
    }


    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/users/list";
    }

}
