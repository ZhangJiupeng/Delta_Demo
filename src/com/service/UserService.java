package com.service;

import com.entity.User;

public interface UserService {
    boolean add(User user) throws Exception;
    User get(User user) throws Exception;
}
