package cn.sdut.view;

import cn.sdut.dao.entity.Users;
import cn.sdut.service.AdminService;
import cn.sdut.service.impl.AdminServiceImpl;
import cn.sdut.utils.DateUtils;
import cn.sdut.utils.UnameIsUnique;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by liuzhichao on 2018/9/5.
 */
public class UpdateUserByIdFrame extends JFrame implements ActionListener {

    private Users user;
    private Users users;
    private int flag;//标记变量:1.管理员的修改信息界面  2.普通用户的修改信息界面
    private String pow = "";

    private JTextField uname, ubirth, uemail, upower, deptno;
    private JPasswordField upwd;
    private JButton enter;

    public UpdateUserByIdFrame() {

    }

    public UpdateUserByIdFrame(Users user, Users users, int flag) {
        this.user = user;
        this.users = users;
        this.flag = flag;
        if (users.getUpower()==1) {
            pow += "管理员";
        } else if (users.getUpower()==2){
            pow += "普通用户";
        } else {
            pow += "超级管理员";
        }

        init();
        setBounds(240,45,1000,700);
        setTitle("用户管理系统");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init() {
        setLayout(null);

        /**
         * 欢迎语标签
         */
        JLabel jLabel = new JLabel("请修改用户的相关信息",JLabel.CENTER);
        jLabel.setBounds(90,50,800,80);
        jLabel.setFont(new Font("楷体",Font.BOLD,30));
        add(jLabel);

        JLabel name = new JLabel("姓  名");
        name.setBounds(350,150,100,40);
        name.setFont(new Font("楷体",Font.BOLD,25));
        add(name);

        JLabel pwd = new JLabel("密  码");
        pwd.setBounds(350,210,100,40);
        pwd.setFont(new Font("楷体",Font.BOLD,25));
        add(pwd);

        JLabel birth = new JLabel("生  日");
        birth.setBounds(350,270,100,40);
        birth.setFont(new Font("楷体",Font.BOLD,25));
        add(birth);

        JLabel email = new JLabel("邮  箱");
        email.setBounds(350,330,100,40);
        email.setFont(new Font("楷体",Font.BOLD,25));
        add(email);

        JLabel power = new JLabel("权限");
        power.setBounds(350,390,100,40);
        power.setFont(new Font("楷体",Font.BOLD,25));
        add(power);

        JLabel dept = new JLabel("部  门");
        dept.setBounds(350,450,100,40);
        dept.setFont(new Font("楷体",Font.BOLD,25));
        add(dept);

        /**
         * 信息文本框
         */
        uname = new JTextField(12);
        uname.setText(users.getUname());
        uname.setBounds(460,150,160,40);
        //uname.setFont(new Font("楷体",Font.BOLD,25));
        uname.addActionListener(this);
        add(uname);

        upwd = new JPasswordField(12);
        upwd.setText(users.getUpwd());
        upwd.setBounds(460,210,160,40);
        upwd.addActionListener(this);
        add(upwd);

        ubirth = new JTextField(12);
        ubirth.setText(users.getUbirth());
        ubirth.setBounds(460,270,160,40);
        //ubirth.setFont(new Font("楷体",Font.BOLD,25));
        ubirth.addActionListener(this);
        add(ubirth);

        uemail = new JTextField(12);
        uemail.setText(users.getUemail());
        uemail.setBounds(460,330,160,40);
        //uemail.setFont(new Font("楷体",Font.BOLD,25));
        uemail.addActionListener(this);
        add(uemail);

        upower = new JTextField(12);
        upower.setText(pow);
        upower.setBounds(460,390,160,40);
        upower.addActionListener(this);
        add(upower);
        if (flag==2) {
            upower.setEnabled(false);
        }

        deptno = new JTextField(12);
        deptno.setText(String.valueOf(users.getDeptno()));
        deptno.setBounds(460,450,160,40);
        //deptno.setFont(new Font("楷体",Font.BOLD,25));
        deptno.addActionListener(this);
        add(deptno);

        /**
         * 确认按钮
         */
        enter = new JButton("确定");
        enter.setBounds(410,510,150,50);
        enter.setFont(new Font("楷体",Font.BOLD,20));
        enter.addActionListener(this);
        add(enter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource()==enter ) {
            //不对用户权限进行修改,则进行数据类型的转换
            int pow = 2;//默认普通用户权限
            boolean tPow = true;
            if( (upower.getText().equals("普通用户") || upower.getText().equals("2"))
                    && (flag==0 || flag==1 || flag==2) ) {
                pow = 2;
                tPow = true;
            } else if ( (upower.getText().equals("管理员") || upower.getText().equals("1"))
                    && (flag==0 || flag==1) ) {
                pow = 1;
                tPow = true;
            } else if ( (upower.getText().equals("超级管理员") || upower.getText().equals("0"))
                    && flag==0 ){
                pow = 0;
                tPow = true;
            } else{
                JOptionPane.showMessageDialog(this,"权限不足",
                        "错误",JOptionPane.INFORMATION_MESSAGE);
                tPow = false;
            }

            if ( tPow ) {
                String name = uname.getText().trim();
                String pwd = new String(upwd.getPassword()).trim();
                String birth = ubirth.getText();
                String email = uemail.getText().trim();
                String regMail = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
                if( name.length()==0 || name.length()>10 ) {
                    JOptionPane.showMessageDialog(this,"用户名长度为1~10位",
                            "错误",JOptionPane.INFORMATION_MESSAGE);
                    uname.setText("");
                } else if ( !UnameIsUnique.unameIsUnique(name) && !name.equals(users.getUname().trim()) ) {
                    JOptionPane.showMessageDialog(this,"用户名已存在",
                            "错误",JOptionPane.INFORMATION_MESSAGE);
                } else if( pwd.length()==0 || pwd.length()>10 ) {
                    JOptionPane.showMessageDialog(this,"密码长度为1~10位",
                            "错误",JOptionPane.INFORMATION_MESSAGE);
                    upwd.setText("");
                } else if ( !DateUtils.dateIsTrue(birth) ) {
                    JOptionPane.showMessageDialog(this,"日期不合法",
                            "错误",JOptionPane.INFORMATION_MESSAGE);
                } else if( !email.matches(regMail) ) {
                    JOptionPane.showMessageDialog(this,"邮箱格式不合法",
                            "错误",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Users user = new Users(users.getUuid(),name,pwd, ubirth.getText(), email,pow,
                            Integer.parseInt(deptno.getText()));
                    AdminService adminService = new AdminServiceImpl();
                    adminService.updateUserById(user);
                    JOptionPane.showMessageDialog(this,"修改成功",
                            "正确",JOptionPane.INFORMATION_MESSAGE);
                    if (flag==1 || flag==0) {
                        AdminMainFrame adminMainFrame = new AdminMainFrame(this.user);
                    } else if ( flag==2 ) {
                        UsersMainFrame usersMainFrame = new UsersMainFrame(user);
                    }
                    this.dispose();
                }

            }
        }
    }
}
