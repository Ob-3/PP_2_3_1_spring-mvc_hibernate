package web.controller;

import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("/users/add")
    public String showAddForm() {
        return "users/add";
    }

    @PostMapping("/users/add")
    public String addUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "users/edit";
    }

    @PostMapping("/users/edit")
    public String updateUser(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("email") String email) {
        User user = userService.findById(id);
        user.setName(name);
        user.setEmail(email);
        userService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
