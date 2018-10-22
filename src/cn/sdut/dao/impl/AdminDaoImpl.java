package cn.sdut.dao.impl;

import cn.sdut.dao.AdminDao;
import cn.sdut.dao.UsersDao;
import cn.sdut.dao.entity.Users;
import cn.sdut.utils.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by liuzhichao on 2018/9/4.
 */
public class AdminDaoImpl implements AdminDao {

    /**
     * 添加普通用户信息
     * @param users 用户信息
     */
    @Override
    public void addUsers(Users users) {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "insert into users(uuid,uname,upwd,ubirth,uemail,upower,deptno) " +
                    "values(users_seq.nextval,?,?,?,?,2,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,users.getUname());
            pstm.setString(2,users.getUpwd());
            pstm.setString(3,users.getUbirth());
            pstm.setString(4,users.getUemail());
            pstm.setInt(5,users.getDeptno());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeAll(null,pstm,conn);
        }
    }

    /**
     * 通过ID查询某一个用户的信息
     * @param id
     * @return
     */
    @Override
    public Users getUserById(int id) {
        Users users = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            String sql = "select uuid,uname,upwd,ubirth,uemail,upower,deptno from users where uuid=?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,id);
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
     * 根据ID模糊查询用户
     * @param id 用户ID
     * @return
     */
    @Override
    public ArrayList<Users> getUsersById(int id) {
        ArrayList<Users> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            //必须是完整的一句,不能字符串拼接
            String sql = "select uuid,uname,upwd,ubirth,uemail,upower,deptno from users where uuid like ?";
            pstm = conn.prepareStatement(sql);
            String str = String.valueOf(id);
            pstm.setString(1,"%"+str+"%");
            rs = pstm.executeQuery();
            while(rs.next()) {
                Users users = new Users(rs.getInt("uuid"),
                        rs.getString("uname"),rs.getString("upwd"),
                        rs.getString("ubirth"),rs.getString("uemail"),
                        rs.getInt("upower"),rs.getInt("deptno"));
                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据名字模糊查询用户
     * @param name
     * @return
     */
    @Override
    public ArrayList<Users> getUsersByName(String name) {
        ArrayList<Users> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "select uuid,uname,upwd,ubirth,uemail,upower,deptno from users where uname like ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,"%"+name+"%");
            rs = pstm.executeQuery();
            while(rs.next()) {
                Users users = new Users(rs.getInt("uuid"),
                        rs.getString("uname"),rs.getString("upwd"),
                        rs.getString("ubirth"),rs.getString("uemail"),
                        rs.getInt("upower"),rs.getInt("deptno"));
                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public ArrayList<Users> getAllUsers() {
        ArrayList<Users> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "select uuid,uname,upwd,ubirth,uemail,upower,deptno from users";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()) {
                Users users = new Users(rs.getInt("uuid"),
                        rs.getString("uname"),rs.getString("upwd"),
                        rs.getString("ubirth"),rs.getString("uemail"),
                        rs.getInt("upower"),rs.getInt("deptno"));
                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 通过ID删除用户
     * @param id
     */
    @Override
    public void delUserById(int id) {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "delete from users where uuid="+id;
            pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeAll(null,pstm,conn);
        }
    }

    /**
     * 通过ID对用户信息进行修改
     * @param users
     */
    @Override
    public void updateUserById(Users users) {
        UsersDao usersDao = new UsersDaoImpl();
        usersDao.updateUsers(users);
    }


}
