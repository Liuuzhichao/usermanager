package cn.sdut.service.impl;

import cn.sdut.dao.AdminDao;
import cn.sdut.dao.UsersDao;
import cn.sdut.dao.entity.Users;
import cn.sdut.dao.impl.AdminDaoImpl;
import cn.sdut.dao.impl.UsersDaoImpl;
import cn.sdut.service.UserService;

/**
 * Created by liuzhichao on 2018/9/4.
 */
public class UserServiceImpl implements UserService {


    @Override
    public Users getUsersByName(String name) {
        UsersDao usersDao = new UsersDaoImpl();
        Users users = usersDao.getUsersByName(name);
        return users;
    }

    @Override
    public void updateUsers(Users users) {

    }

}
