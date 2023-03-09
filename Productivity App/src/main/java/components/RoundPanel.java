package components;

import javax.swing.*;
import java.awt.*;

public class RoundPanel extends JPanel {

    private final Color lightBackground = new Color(134, 174, 175, 84);//196, 196, 195

    public RoundPanel(){
        setOpaque(false);
    }

    protected void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(lightBackground);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.fillRoundRect(0, 0, getWidth(),getHeight(),20,20);


        super.paintComponent(graphics);
    }
}
