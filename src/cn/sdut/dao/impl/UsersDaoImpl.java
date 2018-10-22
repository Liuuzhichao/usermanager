package cn.sdut.dao.impl;

import cn.sdut.dao.UsersDao;
import cn.sdut.dao.entity.Users;
import cn.sdut.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by liuzhichao on 2018/9/4.
 */
public class UsersDaoImpl implements UsersDao {
    /**
     * 普通用户进行的操作
     */


    /**
     * 根据名字查询用户
     * @param name
     * @return
     */
    @Override
    public Users getUsersByName(String name) {
        Users users = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            String sql = "select uuid,uname,upwd,ubirth,uemail,upower,deptno from users " +
                    "where uname=?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,name);
            rs = pstm.executeQuery();
            while( rs.next() ) {
                users = new Users(rs.getInt("uuid"),
                        rs.getString("uname"),rs.getString("upwd"),
                        rs.getString("ubirth"),rs.getString("uemail"),
                        rs.getInt("upower"),rs.getInt("deptno"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeAll(rs,pstm,conn);
        }
        return users;
    }

    /**
     * 修改用户信息
     * @param users 用户信息
     */
    @Override
    public void updateUsers(Users users) {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "update users set uname=?,upwd=?,ubirth=?,uemail=?,upower=?,deptno=? where uuid=?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, users.getUname());
            pstm.setString(2,users.getUpwd());
            pstm.setString(3,users.getUbirth());
            pstm.setString(4,users.getUemail());
            pstm.setInt(5, users.getUpower());
            pstm.setInt(6,users.getDeptno());
            pstm.setInt(7,users.getUuid());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeAll(null,pstm,conn);
        }
    }


}
