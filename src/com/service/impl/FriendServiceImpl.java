package com.service.impl;


import com.dao.FriendDao;
import com.delta.core.assembler.annotation.Detachable;
import com.entity.Friend;
import com.entity.User;
import com.except.ServerLogicConflictException;
import com.service.FriendService;

import java.util.List;

public class FriendServiceImpl implements FriendService {
    private static FriendDao friendDao;

    @Detachable
    public static void setFriendDao(FriendDao friendDao) {
        FriendServiceImpl.friendDao = friendDao;
    }

    public int add(Friend friend) throws Exception {
        if (friendDao.get(friend) != null) {
            throw new ServerLogicConflictException("Friend '" + friend.getName() + "@" + friend.getId() + "' has already exists.");
        } else {
            friendDao.add(friend);
            friend = friendDao.get(friend);
            return friend.getId();
        }
    }

    public boolean delete(Friend friend) throws Exception {
        if (friendDao.delete(friend) > 0) {
            return true;
        } else {
            throw new ServerLogicConflictException("Friend '" + friend.getName() + "@" + friend.getId() + "' not exists.");
        }
    }

    public boolean update(Friend friend) throws Exception {
        if (friendDao.update(friend) > 0) {
            return true;
        } else {
            throw new ServerLogicConflictException("Friend '" + friend.getName() + "@" + friend.getId() + "' not exists.");
        }
    }

    public Friend get(Friend friend) throws Exception {
        return friendDao.get(friend);
    }

    public List<Friend> list(User user) throws Exception {
        return friendDao.list(user.getId());
    }
}
