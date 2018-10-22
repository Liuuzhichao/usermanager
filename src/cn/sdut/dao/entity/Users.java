package cn.sdut.dao.entity;

/**
 * Created by liuzhichao on 2018/9/3.
 */
public class Users {

    private int uuid;//编号
    private String uname;//姓名
    private String upwd;//密码
    private String ubirth;//生日
    private String uemail;//邮箱
    private int upower;//权限 0.超级管理员 1.管理员 2.普通用户
    private int deptno;//部门编号

    public Users() {
    }

    public Users(int uuid, String uname, String upwd, String ubirth, String uemail, int upower, int deptno) {
        this.uuid = uuid;
        this.uname = uname;
        this.upwd = upwd;
        this.ubirth = ubirth;
        this.uemail = uemail;
        this.upower = upower;
        this.deptno = deptno;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUbirth() {
        return ubirth;
    }

    public void setUbirth(String ubirth) {
        this.ubirth = ubirth;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public int getUpower() {
        return upower;
    }

    public void setUpower(int upower) {
        this.upower = upower;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uuid=" + uuid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", ubirth='" + ubirth + '\'' +
                ", uemail='" + uemail + '\'' +
                ", upower=" + upower +
                ", deptno=" + deptno +
                '}';
    }
}
