package com.dao;

import com.entity.User;

public interface UserDao {
    int add(User user) throws Exception;

    User get(User user) throws Exception;
}
