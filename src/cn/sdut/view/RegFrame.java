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
 * Created by liuzhichao on 2018/9/4.
 */
public class RegFrame extends JFrame implements ActionListener {

    private Users users;
    private int flag;//标记是注册还是插入.注册1,插入2;
    private JTextField uname, ubirth, uemail, deptno;
    private JPasswordField upwd;
    private JButton enter;

    public RegFrame(Users users, int flag) {
        this.users = users;
        this.flag = flag;
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
        JLabel jLabel = new JLabel("请添加用户的相关信息",JLabel.CENTER);
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

        JLabel dept = new JLabel("部  门");
        dept.setBounds(350,390,100,40);
        dept.setFont(new Font("楷体",Font.BOLD,25));
        add(dept);

        /**
         * 信息文本框
         */
        uname = new JTextField(12);
        uname.setBounds(460,150,160,40);
        //uname.setFont(new Font("楷体",Font.BOLD,25));
        uname.addActionListener(this);
        add(uname);

        upwd = new JPasswordField(12);
        upwd.setBounds(460,210,160,40);
        upwd.addActionListener(this);
        add(upwd);

        ubirth = new JTextField(12);
        ubirth.setBounds(460,270,160,40);
        //ubirth.setFont(new Font("楷体",Font.BOLD,25));
        ubirth.addActionListener(this);
        add(ubirth);
        /*// 格式
        String defaultFormat = "yyyy-MM-dd HH:mm:ss";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.BOLD, 14);
        Dimension dimension = new Dimension(177, 24);
        DatePicker datepick = new DatePicker(date,defaultFormat,font,dimension);*/


        uemail = new JTextField(12);
        uemail.setBounds(460,330,160,40);
        //uemail.setFont(new Font("楷体",Font.BOLD,25));
        uemail.addActionListener(this);
        add(uemail);

        deptno = new JTextField(12);
        deptno.setBounds(460,390,160,40);
        //deptno.setFont(new Font("楷体",Font.BOLD,25));
        deptno.addActionListener(this);
        add(deptno);

        /**
         * 确认按钮
         */
        enter = new JButton("确定");
        enter.setBounds(410,450,150,50);
        enter.setFont(new Font("楷体",Font.BOLD,20));
        enter.addActionListener(this);
        add(enter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource()==enter ) {
            String name = uname.getText().trim();
            String pwd = new String(upwd.getPassword()).trim();
            String birth = ubirth.getText();
            String email = uemail.getText().trim();
            String regMail = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
            if( name.length()==0 || name.length()>10 ) {
                JOptionPane.showMessageDialog(this,"用户名长度为1~10位",
                        "错误",JOptionPane.INFORMATION_MESSAGE);
                uname.setText("");
            } else if ( !UnameIsUnique.unameIsUnique(name) ) {
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
                AdminService adminService = new AdminServiceImpl();
                Users users = new Users(0,name,pwd, ubirth.getText(), email,2,
                        Integer.parseInt(deptno.getText()));
                adminService.addUsers(users);
                JOptionPane.showMessageDialog(this,"插入成功",
                        "正确",JOptionPane.INFORMATION_MESSAGE);
                if (flag==1) {
                    LoginFrame loginFrame = new LoginFrame();
                } else {
                    AdminMainFrame adminMainFrame = new AdminMainFrame(users);
                    this.dispose();
                }
            }
        }
    }
}
