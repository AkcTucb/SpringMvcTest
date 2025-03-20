package ru.Akctucb.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Akctucb.springmvc.model.User;
import ru.Akctucb.springmvc.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "userlist";
    }


    @GetMapping("/user/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "userform";
    }


    @PostMapping("/user/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }


    @GetMapping("/user/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "userform";
    }


    @PostMapping("/user/edit")
    public String updateUser(@ModelAttribute("user") User user) {

        userService.updateUser(user);
        return "redirect:/users";
    }


    @GetMapping("/user/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}

