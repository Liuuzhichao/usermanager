package cn.sdut.service.impl;

import cn.sdut.dao.AdminDao;
import cn.sdut.dao.entity.Users;
import cn.sdut.dao.impl.AdminDaoImpl;
import cn.sdut.service.AdminService;

import java.util.ArrayList;

/**
 * Created by liuzhichao on 2018/9/4.
 */
public class AdminServiceImpl implements AdminService {
    @Override
    public void addUsers(Users users) {
        AdminDao adminDao = new AdminDaoImpl();
        adminDao.addUsers(users);
    }

    @Override
    public Users getUserById(int id) {
        AdminDao adminDao = new AdminDaoImpl();
        Users users = adminDao.getUserById(id);
        return users;
    }

    @Override
    public ArrayList<Users> getUsersById(int id) {
        AdminDao adminDao = new AdminDaoImpl();
        ArrayList<Users> list = new ArrayList<>();
        list = adminDao.getUsersById(id);
        return list;
    }

    @Override
    public ArrayList<Users> getUsersByName(String name) {
        AdminDao adminDao = new AdminDaoImpl();
        ArrayList<Users> list = new ArrayList<>();
        list = adminDao.getUsersByName(name);
        return list;
    }

    @Override
    public ArrayList<Users> getAllUsers() {
        AdminDao adminDao = new AdminDaoImpl();
        ArrayList<Users> list = new ArrayList<>();
        list = adminDao.getAllUsers();
        return list;
    }

    @Override
    public void delUserById(int id) {
        AdminDao adminDao = new AdminDaoImpl();
        adminDao.delUserById(id);
    }

    @Override
    public void updateUserById(Users users) {
        AdminDao adminDao = new AdminDaoImpl();
        adminDao.updateUserById(users);
    }
}
