package ru.Akctucb.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Akctucb.springmvc.dao.UserDao;
import ru.Akctucb.springmvc.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void addUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void updateUser(User userFromForm) {
        User existingUser = userDao.getUserById(userFromForm.getId());
        if (existingUser != null) {
            existingUser.setName(userFromForm.getName());
            existingUser.setEmail(userFromForm.getEmail());
            userDao.updateUser(existingUser);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}

