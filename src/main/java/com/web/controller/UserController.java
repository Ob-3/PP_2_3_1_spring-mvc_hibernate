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

    @Autowired
    private UserService userService;

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
    public String showAddUserForm() {
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam String email) {
        userService.saveUser(new User(name, email));
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
    public String updateUser(@RequestParam Long id, @RequestParam String name, @RequestParam String email) {
        User user = userService.getUserById(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            userService.updateUser(user);
        }
        return "redirect:/users/list";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        if (userService.getUserById(id) != null) {
            userService.deleteUser(id);
        }
        return "redirect:/users/list";
    }

}
