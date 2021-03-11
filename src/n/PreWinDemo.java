package n;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PreWinDemo {
    //声明主界面
    int WIDTH=500;
    int HEIGHT=300;
    JFrame main_frame=new JFrame("主界面");

    public void init() throws  Exception{
        //设置窗口属性
        System.out.println(ScreenUtils.getScreenWidth()+"\n"+ScreenUtils.getScreenHeight());
        main_frame.setBounds(ScreenUtils.getScreenWidth()/2-WIDTH/2,ScreenUtils.getScreenHeight()/2-HEIGHT/2,WIDTH,HEIGHT);
        main_frame.setResizable(false);


        //设置初始界面图片
        JPanel bgPanel=new JPanel();

        //实现按钮打开剪辑界面
        JButton btn=new JButton("Start");
        Box verticalBox = Box.createVerticalBox();
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new windowDemo().init();
                main_frame.dispose();
            }
        });

        //组装视图
        verticalBox.add(btn);
        bgPanel.add(verticalBox);
        main_frame.add(bgPanel);
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setVisible(true);
    }
    public static void main(String[] args) {
        try {
            new PreWinDemo().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}