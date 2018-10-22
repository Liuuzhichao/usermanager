package cn.sdut.service;

import cn.sdut.dao.entity.Users;

/**
 * Created by liuzhichao on 2018/9/4.
 */
public interface UserService {

    /**
     * 普通用户进行的操作
     */

    /**
     * 通过姓名查询用户信息
     * @param name
     * @return
     */
    public Users getUsersByName(String name);

    /**
     * 修改当前用户信息
     * @param users 用户信息
     */
    public void updateUsers(Users users);

}
