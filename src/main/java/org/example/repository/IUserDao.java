package org.example.repository;

import org.example.model.User;

public interface IUserDao {
    User getUserByCredientials(String email, String password);

    User getUserById(long id);
}
