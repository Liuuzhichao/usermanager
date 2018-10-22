package cn.sdut.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by liuzhichao on 2018/9/5.
 */
public class DateUtils {

    public static boolean dateIsTrue(String str) {

        boolean flag = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf.setLenient(false);
            sdf.parse(str);
        } catch (ParseException e) {
            flag = false;
        } finally {
            return flag;
        }
    }

}
