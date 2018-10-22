package cn.sdut.utils;

import cn.sdut.dao.entity.Users;
import cn.sdut.service.AdminService;
import cn.sdut.service.impl.AdminServiceImpl;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.ArrayList;

/**
 * Created by liuzhichao on 2018/9/4.
 */
public class SetTable {

    /**
     * 为表格填充数据
     * @param num 可变长参数,第一个参数表示查询类型:0.查询全部 1.根据ID查询 2.根据name查询
     *            第二个参数表示id或者name
     * @return
     */
    public static String[][] setTable(String... num) {
        AdminService adminService = new AdminServiceImpl();
        ArrayList<Users> list = null;
        if( num[0].equals("0") ) {
            list = adminService.getAllUsers();
        } else if( num[0].equals("1") ) {
            int id = Integer.parseInt(num[1]);
            list = adminService.getUsersById(id);
        } else if( num[0].equals("2") ) {
            list = adminService.getUsersByName(num[1]);
        }
        String[][] data = new String[list.size()][5];
        for ( int i = 0; i < list.size(); i++ ) {
            data[i][0] = String.valueOf(list.get(i).getUuid());
            data[i][1] = list.get(i).getUname();
            data[i][2] = list.get(i).getUbirth();
            data[i][3] = list.get(i).getUemail();
            data[i][4] = String.valueOf(list.get(i).getDeptno());
        }
        return data;
    }

    /**
     * 表格居中显示方法
     * @param jTable
     */
    public static void setTableColumnCenter(JTable jTable) {
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class,defaultTableCellRenderer);
    }

}
