package components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class PanelLoading extends JPanel {

    private JLabel loadingGif;
    private MigLayout migLayout;

    public PanelLoading(){

        loadingGif = new JLabel(new ImageIcon("C:\\Users\\ASUS\\Desktop\\Productivity App\\src\\main\\resources\\icons\\loading.gif"));
        migLayout = new MigLayout("wrap","push[center]push", "push [] push");
        setLayout(migLayout);
        add(loadingGif);
        setOpaque(false);
        setFocusCycleRoot(true);
        setVisible(false);
        setFocusable(true);
        addMouseListener(new MouseAdapter() {
        });
    }

    protected void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(new Color(255,255,255));
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        graphics2D.fillRect(0,0,getWidth(),getHeight());
        graphics2D.setComposite(AlphaComposite.SrcOver);
        super.paintComponent(graphics);
    }
}
