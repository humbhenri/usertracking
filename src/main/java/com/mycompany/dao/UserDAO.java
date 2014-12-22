package com.mycompany.dao;

import java.util.List;

import com.mycompany.model.User;

public interface UserDAO {

    void save(User user);

    List<User> findAll();
}
