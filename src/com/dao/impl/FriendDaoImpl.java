package com.dao.impl;


import com.dao.FriendDao;
import com.delta.core.porter.Porter;
import com.delta.core.util.JDBCUtil;
import com.entity.Friend;

import java.util.List;

public class FriendDaoImpl implements FriendDao {
    @Override
    public Friend get(Friend friend) throws Exception {
        List<Friend> friendList = Porter.loadBeans(Friend.class, "t_user_id = ? and name = ?" +
                        " and sex = ? and age = ? and qq = ? and tel = ? and email = ?" +
                        " and address = ?",
                friend.getT_user_id(),
                friend.getName(),
                friend.getSex(),
                friend.getAge(),
                friend.getQq(),
                friend.getTel(),
                friend.getEmail(),
                friend.getAddress());
        return friendList.size() > 0 ? friendList.get(0) : null;
    }

    @Override
    public List<Friend> list(int userId) throws Exception {
        return Porter.loadBeans(Friend.class, "t_user_id = ?", userId);
    }

    @Override
    public int add(Friend friend) throws Exception {
        return Porter.saveBean(friend) ? 1 : 0;
    }

    @Override
    public int update(Friend friend) throws Exception {
//        "id = " + friend.getId() + " and t_user_id = " + friend.getT_user_id()
//        return Porter.updateBeans(friend, "id=0 and t_user_id = 1");
        return JDBCUtil.executeUpdate("update t_friend set name = ?, sex = ?, age = ?, qq = ?, tel = ?, email = ?, address = ? where id = ? and t_user_id = ?",
                friend.getName(),
                friend.getSex(),
                friend.getAge(),
                friend.getQq(),
                friend.getTel(),
                friend.getEmail(),
                friend.getAddress(),
                friend.getId(),
                friend.getT_user_id());
    }

    @Override
    public int delete(Friend friend) throws Exception {
        return Porter.removeBeans(Friend.class, "id = ? and t_user_id = ?",
                friend.getId(), friend.getT_user_id());
    }

}
