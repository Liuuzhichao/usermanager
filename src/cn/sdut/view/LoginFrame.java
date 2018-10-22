package cn.sdut.view;

import cn.sdut.dao.entity.Users;
import cn.sdut.service.UserService;
import cn.sdut.service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by liuzhichao on 2018/9/4.
 */
public class LoginFrame extends JFrame implements ActionListener{

    private JTextField uname;
    private JPasswordField upwd;
    private JButton log;
    private JButton reg;

    public LoginFrame() {
        init();
        setBounds(240,45,1000,700);
        setTitle("用户管理系统");
        setVisible(true);
        setResizable(false); //窗口大小不可调整
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init() {

        setLayout(null);

        JLabel title = new JLabel("欢迎来到用户管理系统");
        title.setBounds(320,100,330,50);
        title.setFont(new Font("楷体",Font.BOLD,30));
        add(title);

        /**
         * 用户名和用户密码标签设置
         */
        JLabel userName = new JLabel("用户名");
        userName.setBounds(380,200,100,40);
        userName.setFont(new Font("楷体",Font.BOLD,20));
        add(userName);

        JLabel userPw = new JLabel("用户密码");
        userPw.setBounds(380,300,100,40);
        userPw.setFont(new Font("楷体",Font.BOLD,20));
        add(userPw);

        /**
         * 用户名和用户密码的输入框设置
         */
        uname = new JTextField(12);
        uname.setBounds(480,200,100,40);
        uname.addActionListener(this);
        add(uname);

        upwd = new JPasswordField();
        upwd.setBounds(480,300,100,40);
        upwd.addActionListener(this);
        add(upwd);

        /**
         * 登录按钮设置
         */
        log = new JButton("登录");
        log.setBounds(370,450,100,60);
        log.setFont(new Font("楷体",Font.BOLD,20));
        log.addActionListener(this);
        add(log);

        /**
         * 注册按钮
         */
        reg = new JButton("注册");
        reg.setBounds(520,450,100,60);
        reg.setFont(new Font("楷体",Font.BOLD,20));
        reg.addActionListener(this);
        add(reg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource()==log ) {
            String name = uname.getText().trim();
            if ( name!=null && !name.equals("") ) {
                UserService us = new UserServiceImpl();
                Users users = us.getUsersByName(name);
                if( users!=null ) {
                    String pwd = new String(upwd.getPassword());
                    if( pwd.equals(users.getUpwd()) ) {
                        JOptionPane.showMessageDialog(this,"登录成功",
                                "正确",JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        if ( users.getUpower()==1 || users.getUpower()==0 ) {
                            AdminMainFrame adminMainFrame = new AdminMainFrame(users);
                        } else {
                            UsersMainFrame usersMainFrame = new UsersMainFrame(users);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this,"密码错误",
                                "错误",JOptionPane.INFORMATION_MESSAGE);
                        //LoginFrame loginFrame = new LoginFrame();
                        //this.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(this,"用户不存在",
                            "错误",JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,"用户名不能为空",
                        "错误",JOptionPane.INFORMATION_MESSAGE);
            }
        } else if( e.getSource()==reg ) {
            RegFrame regFrame = new RegFrame(null,1);
            this.dispose();
        }
    }
}
