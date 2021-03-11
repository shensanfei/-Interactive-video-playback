package n;


import javax.swing.*;
import java.awt.*;

public class BackGroundPanel extends JPanel {
    //声明图片
    private Image BackIcon;
    public BackGroundPanel(Image BackIcon){
        this.BackIcon=BackIcon;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //绘制背景
        g.drawImage(BackIcon,0,0,this.getWidth(),this.getHeight(),null);
    }
}
