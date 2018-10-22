package cn.sdut.view;

import cn.sdut.dao.entity.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by liuzhichao on 2018/9/5.
 */
public class UsersMainFrame extends JFrame implements ActionListener {

    private Users users;
    private JButton updateBtn;

    public UsersMainFrame(){
    }

    public UsersMainFrame(Users users) {
        this.users = users;

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
        JLabel jLabel = new JLabel("您的信息如下:",JLabel.CENTER);
        jLabel.setBounds(90,40,800,80);
        jLabel.setFont(new Font("楷体",Font.BOLD,30));
        add(jLabel);

        JLabel id = new JLabel("编  号:");
        id.setBounds(350,140,100,40);
        id.setFont(new Font("楷体",Font.BOLD,25));
        add(id);

        JLabel name = new JLabel("姓  名:");
        name.setBounds(350,200,100,40);
        name.setFont(new Font("楷体",Font.BOLD,25));
        add(name);

        JLabel pwd = new JLabel("密  码:");
        pwd.setBounds(350,260,100,40);
        pwd.setFont(new Font("楷体",Font.BOLD,25));
        add(pwd);

        JLabel birth = new JLabel("生  日:");
        birth.setBounds(350,320,100,40);
        birth.setFont(new Font("楷体",Font.BOLD,25));
        add(birth);

        JLabel email = new JLabel("邮  箱:");
        email.setBounds(350,380,100,40);
        email.setFont(new Font("楷体",Font.BOLD,25));
        add(email);

        JLabel power = new JLabel("权  限:");
        power.setBounds(350,440,100,40);
        power.setFont(new Font("楷体",Font.BOLD,25));
        add(power);

        JLabel dept = new JLabel("部  门:");
        dept.setBounds(350,500,100,40);
        dept.setFont(new Font("楷体",Font.BOLD,25));
        add(dept);

        /**
         * 信息标签
         */
        JLabel uuid = new JLabel(String.valueOf(users.getUuid()));
        uuid.setBounds(460,140,160,40);
        uuid.setFont(new Font("楷体",0,20));
        add(uuid);

        JLabel uname = new JLabel(users.getUname());
        uname.setBounds(460,200,160,40);
        uname.setFont(new Font("楷体",0,20));
        add(uname);

        JLabel upwd = new JLabel(users.getUpwd());
        upwd.setBounds(460,260,160,40);
        upwd.setFont(new Font("楷体",0,20));
        add(upwd);

        JLabel ubirth = new JLabel(users.getUbirth());
        ubirth.setBounds(460,320,160,40);
        ubirth.setFont(new Font("楷体",0,20));
        add(ubirth);

        JLabel uemail = new JLabel(users.getUemail());
        uemail.setBounds(460,380,200,40);
        uemail.setFont(new Font("楷体",0,20));
        add(uemail);

        JLabel upower = new JLabel("普通用户");
        upower.setBounds(460,440,160,40);
        upower.setFont(new Font("楷体",0,20));
        add(upower);

        JLabel udept = new JLabel(String.valueOf(users.getDeptno()));
        udept.setBounds(460,500,160,40);
        udept.setFont(new Font("楷体",0,20));
        add(udept);

        /**
         * 修改按钮
         */
        updateBtn = new JButton("修改");
        updateBtn.setBounds(410,560,150,50);
        updateBtn.setFont(new Font("楷体",0,20));
        updateBtn.addActionListener(this);
        add(updateBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource()==updateBtn ) {
            UpdateUserByIdFrame updateUserByIdFrame = new UpdateUserByIdFrame(users,users,2);
            this.dispose();
        }
    }
}
