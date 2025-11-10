package com.es.wesays;

import javax.swing.*;
import java.awt.*;

public class Cardioid extends JFrame {
    //定义窗口大小
    private static final int WIDTH = 480;
    private static final int HEIGHT = 600;
    //获取屏幕大小
    private static final int WINDOW_WIDTH = Toolkit.getDefaultToolkit()
            .getScreenSize()
            .width;
    private static final int WINDOW_HEIGHT = Toolkit.getDefaultToolkit()
            .getScreenSize()
            .height;

    //构造函数
    public Cardioid() {
        //设置窗口标题
        super("♥爱心");
        //设置背景色
        this.setBackground(Color.BLACK);
        //设置窗口位置
        this.setLocation((WINDOW_WIDTH - WIDTH) / 2, (WINDOW_HEIGHT - HEIGHT) / 2);
        //设置窗口大小
        this.setSize(WIDTH, HEIGHT);
        //设置窗口布局
        this.setLayout(getLayout());
        //设置窗口可见
        this.setVisible(true);
        //设置窗口的默认关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //图形函数
    public void paint(Graphics g) {
        //横纵坐标及半径
        double x, y, r;
        Image image = this.createImage(WIDTH, HEIGHT);
        Graphics pic = image.getGraphics();
        //绘制图形
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                r = Math.PI / 45 + Math.PI / 45 * i *
                        (1 - Math.sin(Math.PI / 45 * j)) * 18;
                x = r * Math.cos(Math.PI / 45 * j) *
                        Math.sin(Math.PI / 45 * i) + WIDTH / 2;
                y = -r * Math.sin(Math.PI / 45 * j) + HEIGHT / 2;
                pic.setColor(Color.MAGENTA);
                pic.setFont(new Font("楷体", Font.BOLD, 40)); //设置字体
                pic.drawString("我爱你", 180, 200); //绘制字符串
                pic.drawString("一万年", 220, 240); //绘制字符串
                pic.setColor(Color.MAGENTA);
                pic.fillOval((int) x, (int) y, 2, 2);
            }
            //生成图片
            g.drawImage(image, 0, 0, this);
        }
    }
}
