package IOTest;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MarkExample {

    public static void main(String[] args) {
        try {
            // 初始化一个字节数组，内有10个字节的数据
            byte[] bytes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
            // 用一个ByteArrayInputStream来读取这个字节数组
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            // 将ByteArrayInputStream包含在一个BufferedInputStream
            BufferedInputStream bis = new BufferedInputStream(in);
            // 读取字节1,2
            System.out.print(bis.read() + ",");
            System.out.print(bis.read() + ",");
            // 在此处做标记，同时设置readlimit参数为3
            System.out.println("mark");
            bis.mark(3);

            // 继续输出3，4，5，6值
            System.out.print(bis.read() + ",");
            System.out.print(bis.read() + ",");
            System.out.print(bis.read() + ",");
            System.out.print(bis.read() + ",");
            // 调用reset()方法
            System.out.println("reset");
            bis.reset();
            // 输出剩下的值
            System.out.println("");
            int c;
            while ( (c = bis.read()) != -1 ) {
                System.out.print(c + ",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



