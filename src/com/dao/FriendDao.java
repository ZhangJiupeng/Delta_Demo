package com.dao;

import com.entity.Friend;

import java.util.List;

public interface FriendDao {
    Friend get(Friend friend) throws Exception;

    List<Friend> list(int userId) throws Exception;

    int add(Friend friend) throws Exception;

    int update(Friend friend) throws Exception;

    int delete(Friend friend) throws Exception;
}
