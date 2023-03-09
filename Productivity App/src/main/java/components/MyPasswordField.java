package components;

import java.awt.*;
import javax.swing.*;

public class MyPasswordField extends JPasswordField {

    private Icon prefixIcon;

    public MyPasswordField(Icon icon){
        prefixIcon = icon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintIcon(g);

    }

    private void paintIcon(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            g2.drawImage(prefix, 1, (getHeight() - prefixIcon.getIconHeight()) / 2, this);

        }
    }
}