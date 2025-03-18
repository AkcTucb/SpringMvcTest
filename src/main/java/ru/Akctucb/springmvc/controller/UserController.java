package ru.Akctucb.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.Akctucb.springmvc.model.User;
import ru.Akctucb.springmvc.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Отображение списка пользователей
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "userList"; // страница userList.html
    }

    // Показ формы добавления нового пользователя
    @GetMapping("/user/add")
    public String showAddForm() {
        return "userForm"; // страница userForm.html
    }

    // Добавление пользователя )
    @PostMapping("/user/add")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("email") String email) {
        User user = new User(name, email);
        userService.addUser(user);
        return "redirect:/users";
    }

    // Показ формы редактирования пользователя
    @GetMapping("/user/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "userForm"; // та же форма, но с заполненными полями
    }

    // Обработка обновления пользователя
    @PostMapping("/user/edit")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("email") String email) {
        User user = userService.getUser(id);
        if(user != null) {
            user.setName(name);
            user.setEmail(email);
            userService.updateUser(user);
        }
        return "redirect:/users";
    }

    // Удаление пользователя
    @GetMapping("/user/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
