package org.example.service;

import org.example.model.User;
import org.example.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getUserByCredientials(String email, String password) {
        return userDao.getUserByCredientials(email, password);
    }

    public User getUserById(long id) {
        return userDao.getUserById(id);
    }
}
