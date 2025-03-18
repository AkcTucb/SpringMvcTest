package ru.Akctucb.springmvc.service;

import ru.Akctucb.springmvc.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(Long id);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
