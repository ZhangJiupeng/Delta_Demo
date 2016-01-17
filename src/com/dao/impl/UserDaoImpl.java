package com.dao.impl;


import com.dao.UserDao;
import com.delta.core.porter.Porter;
import com.delta.core.porter.devtools.PorterUtil;
import com.delta.depend.util.MD5Encryptor;
import com.entity.User;
import com.sun.xml.internal.ws.wsdl.writer.document.Port;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public int add(User user) throws Exception {
        user.setPassword(MD5Encryptor.encrypt(user.getPassword(), "" + user.getPassword().length()));
        return Porter.saveBean(user) ? 1 : 0;
    }

    @Override
    public User get(User user) throws Exception {
        List<User> userList = Porter.loadBeans(User.class, "username = ? and password = ?",
                user.getUsername(),
                MD5Encryptor.encrypt(user.getPassword(), "" + user.getPassword().length()));
        Porter.loadBeans(User.class);
        return userList.size() > 0 ? userList.get(0) : null;
    }

    @Test
    public void test() throws IOException {

    }

}
