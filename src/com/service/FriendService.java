package com.service;

import com.entity.Friend;
import com.entity.User;

import java.util.List;

public interface FriendService {
    int add(Friend friend) throws Exception;

    boolean delete(Friend friend) throws Exception;

    boolean update(Friend friend) throws Exception;

    Friend get(Friend friend) throws Exception;

    List<Friend> list(User user) throws Exception;
}
