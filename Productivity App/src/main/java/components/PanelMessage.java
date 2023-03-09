package components;

import javax.swing.*;
import java.awt.*;

public class PanelMessage extends JPanel {

    private JLabel message;
    private MessageType messageType = MessageType.SUCCESS;
    private boolean show = false;

    public PanelMessage() {

        setPreferredSize(new Dimension(300,30));
        message = new JLabel("AAAAAAAAAAAAAAAA");
        add(message);
        setOpaque(false);
        setVisible(false);

    }

    public void showMessage(MessageType messageType, String message){
        this.messageType = messageType;
        this.message.setText(message);

        if(messageType == MessageType.SUCCESS){
            this.message.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\Productivity App\\src\\main\\resources\\icons\\success.png"));
        }else{
            this.message.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\Productivity App\\src\\main\\resources\\icons\\error.png"));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        if(messageType == MessageType.SUCCESS){
            graphics2D.setColor(new Color(61, 121, 94));
        }else{
            graphics2D.setColor(new Color(217, 6, 72, 145));
        }
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.9f));
        graphics2D.fillRoundRect(0,0,getWidth(),getHeight(),15,15);
        graphics2D.setComposite(AlphaComposite.SrcOver);
        graphics2D.setColor(new Color(245,245,245));
        graphics2D.drawRoundRect(0,0,getWidth() - 1, getHeight() - 1 ,15,15);
        super.paintComponent(g);
    }

    public static enum MessageType{
        SUCCESS, ERROR
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

}
