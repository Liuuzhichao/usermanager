package cn.sdut.utils;

import cn.sdut.dao.entity.Users;
import cn.sdut.service.AdminService;
import cn.sdut.service.impl.AdminServiceImpl;

import java.util.ArrayList;

/**
 * Created by liuzhichao on 2018/9/5.
 */
public class UnameIsUnique {

    public static boolean unameIsUnique(String uname) {
        AdminService adminService = new AdminServiceImpl();
        ArrayList<Users> list = adminService.getAllUsers();
        for ( int i = 0; i < list.size(); i++ ) {
            if( list.get(i).getUname().equals(uname) ) {
                return false;
            }
        }
        return true;
    }

}
