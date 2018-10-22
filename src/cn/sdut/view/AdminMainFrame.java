package cn.sdut.view;

import cn.sdut.dao.entity.Users;
import cn.sdut.service.AdminService;
import cn.sdut.service.impl.AdminServiceImpl;
import cn.sdut.utils.SetTable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by liuzhichao on 2018/9/4.
 */
public class AdminMainFrame extends JFrame implements ActionListener {

    private Users users;

    private JMenuItem queryByName, queryById;
    private JMenuItem addUsers;
    private JMenuItem delById;
    private JMenuItem updateById;
    private JMenuItem backUp;
    private JMenuItem exit;

    public AdminMainFrame() {
        init();
        setBounds(240,45,1000,700);
        setTitle("用户管理系统");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public AdminMainFrame(Users users) {
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

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu add = new JMenu("添加");
        menuBar.add(add);
        JMenu query = new JMenu("查询");
        menuBar.add(query);
        JMenu update = new JMenu("修改");
        menuBar.add(update);
        JMenu del = new JMenu("删除");
        menuBar.add(del);
        JMenu rest = new JMenu("其他");
        menuBar.add(rest);

        addUsers = new JMenuItem("添加用户");
        addUsers.addActionListener(this);
        add.add(addUsers);
        queryById = new JMenuItem("根据id查询用户");
        queryById.addActionListener(this);
        query.add(queryById);
        queryByName = new JMenuItem("根据姓名查询用户");
        queryByName.addActionListener(this);
        query.add(queryByName);
        updateById = new JMenuItem("根据id修改用户信息");
        updateById.addActionListener(this);
        update.add(updateById);
        delById = new JMenuItem("根据id删除用户");
        delById.addActionListener(this);
        del.add(delById);
        exit = new JMenuItem("主页");
        exit.addActionListener(this);
        rest.add(exit);
        backUp = new JMenuItem("备份");
        backUp.addActionListener(this);
        rest.add(backUp);

        /**
         * 标题的设置
         */
        JLabel jLabel = new JLabel("欢迎来到用户管理系统",JLabel.CENTER);
        jLabel.setBounds(90,10,800,80);
        jLabel.setFont(new Font("楷体",Font.BOLD,30));
        add(jLabel);

        String[] head = {"编号","姓名","生日","邮箱","部门"};
        String[][] data = SetTable.setTable("0");
        JTable jTable = new JTable(data,head);
        //设置表头字体
        jTable.getTableHeader().setFont(new Font("宋体",Font.BOLD,20));
        //设置表头高度
        jTable.getTableHeader().setPreferredSize(new Dimension(1,35));
        //设置表格内字体
        jTable.setFont(new Font("楷体",Font.BOLD,17));
        jTable.setRowHeight(30);//设置表格的行高
        SetTable.setTableColumnCenter(jTable);//设置字体居中
        JScrollPane jScrollPane = new JScrollPane(jTable);//滚动控件
        jScrollPane.setLocation(90,100);
        jScrollPane.setSize(800,500);
        add(jScrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource()==addUsers ) {
            RegFrame regFrame = new RegFrame(users,2);
            this.dispose();
        } else if( e.getSource()==queryById ){
            String str = JOptionPane.showInputDialog("请输入id关键字进行模糊查询:");
            if(str.length()==0 || str.equals("") || str==null || str.matches("[^0-9]")) {
                JOptionPane.showMessageDialog(this,"输入无效",
                        "错误",JOptionPane.INFORMATION_MESSAGE);
            } else {
                SelectUsersByIdFrame selectUsersByIdFrame = new SelectUsersByIdFrame(users,str);
                this.dispose();
            }
        } else if( e.getSource()==queryByName ){
            String str = JOptionPane.showInputDialog("请输入姓名关键字进行模糊查询:");
            if(str.length()==0 || str.equals("") || str==null) {
                JOptionPane.showMessageDialog(this,"输入无效",
                        "错误",JOptionPane.INFORMATION_MESSAGE);
            } else {
                SelectUsersByNameFrame selectUsersByNameFrame = new SelectUsersByNameFrame(users,str);
                this.dispose();
            }
        } else if( e.getSource()==updateById ){
            String str = JOptionPane.showInputDialog("请输入要修改的用户ID:");
            if (str.length()==0 || str.equals("") || str==null || str.matches("[^0-9]")) {
                JOptionPane.showMessageDialog(this,"输入无效",
                        "错误",JOptionPane.INFORMATION_MESSAGE);
            } else {
                AdminService adminService = new AdminServiceImpl();
                Users user = adminService.getUserById(Integer.parseInt(str));
                if ( user!=null ) {
                    int flag = 1;
                    if (user.getUpower()==0) {
                        flag = 0;//超级管理员
                    }
                    UpdateUserByIdFrame updateUserByIdFrame = new UpdateUserByIdFrame(users,user,flag);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this,"该用户不存在",
                            "错误",JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } else if( e.getSource()==delById ){
            String str = JOptionPane.showInputDialog("请输入要删除的用户ID:");
            if (str.length()==0 || str.equals("") || str==null || str.matches("[^0-9]")) {
                JOptionPane.showMessageDialog(this,"输入无效",
                        "错误",JOptionPane.INFORMATION_MESSAGE);
            } else {
                if( users.getUuid()==Integer.parseInt(str) ) {
                    JOptionPane.showMessageDialog(this,"当前用户不能删除",
                            "错误",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    AdminService adminService = new AdminServiceImpl();
                    Users user = adminService.getUserById(Integer.parseInt(str));
                    if ( user!=null ) {
                        if (users.getUpower()<=user.getUpower()) {
                            adminService.delUserById(Integer.parseInt(str));
                            JOptionPane.showMessageDialog(this,"删除成功",
                                    "正确",JOptionPane.INFORMATION_MESSAGE);
                            AdminMainFrame adminMainFrame = new AdminMainFrame(users);
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(this,"权限不足",
                                    "错误",JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this,"该用户不存在",
                                "错误",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } else if( e.getSource()==exit ){
            LoginFrame loginFrame = new LoginFrame();
            this.dispose();

        } else if( e.getSource()==backUp ){

        }
    }
}
