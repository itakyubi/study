package test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * test.Test3
 * 2020-03-26 10:30
 *
 * @author wuao <wuao@baidu.com>
 */
public class Test3 {

    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        System.out.println(LocalDate.now());
    }
}
