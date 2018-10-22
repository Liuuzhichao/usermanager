package cn.sdut.dao;

import cn.sdut.dao.entity.Users;

import java.util.ArrayList;

/**
 * Created by liuzhichao on 2018/9/4.
 */
public interface AdminDao {

    /**
     * 添加用户
     * @param users 用户信息
     */
    public void addUsers(Users users);

    /**
     * 通过ID查询某一个用户的信息
     * @param id
     * @return
     */
    public Users getUserById(int id);

    /**
     * 通过id进行模糊查询
     * @param id 用户ID
     * @return
     */
    public ArrayList<Users> getUsersById(int id);

    /**
     * 通过姓名进行模糊查询
     * @param name
     * @return
     */
    public ArrayList<Users> getUsersByName(String name);

    /**
     * 查询全部用户信息
     * @return
     */
    public ArrayList<Users> getAllUsers();

    /**
     * 通过ID删除用户
     * @param id
     */
    public void delUserById(int id);

    /**
     * 通过ID修改用户信息
     * @param users
     */
    public void updateUserById(Users users);

}
