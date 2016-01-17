package com.service.impl;

import com.dao.UserDao;
import com.delta.core.assembler.annotation.Detachable;
import com.entity.User;
import com.except.ServerLogicConflictException;
import com.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserDao userDao;

    @Detachable
    public static void setUserDao(UserDao userDao) {
        UserServiceImpl.userDao = userDao;
    }

    public boolean add(User user) throws Exception {
        if (userDao.get(user) != null) {
            throw new ServerLogicConflictException("User '" + user.getUsername() + "' has already exists.");
        } else {
            return userDao.add(user) > 0;
        }
    }

    public User get(User user) throws Exception {
        return userDao.get(user);
    }
}
