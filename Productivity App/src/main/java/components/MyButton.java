package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JButton {

    private Color color;
    private Color colorBorder;
    private int radius;


    public MyButton(String text, Color color, Color colorClicked, Color colorBorder, int radius) {
        super(text);
        setBackground(color);
        this.color = color;
        this.colorBorder = colorBorder;
        this.radius = radius;
        setContentAreaFilled(false);
        setFocusPainted(false);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                setBackground(colorClicked);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setBackground(color);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setBackground(color.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setBackground(color);
            }
        });
    }


    protected void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        // Paint border
        graphics2D.setColor(colorBorder);
        graphics2D.fillRoundRect(0,0, getWidth(), getHeight(), radius, radius);
        graphics2D.setColor(getBackground());
        //Set border --> 2pix
        graphics2D.fillRoundRect(2,2,getWidth() - 4 , getHeight() - 4, radius, radius);

        super.paintComponent(graphics);
    }
}
