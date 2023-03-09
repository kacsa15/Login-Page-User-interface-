package components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class PanelCover extends JPanel {

    //private JButton testButton;
    private JPanel panel;
    private ActionListener event;
    private MigLayout migLayout;
    private JLabel title;
    private JLabel firstDescript;
    private JLabel secondDescript;
    private MyButton changeButton;
    private boolean isLogin;

    private final DecimalFormat decimalFormat = new DecimalFormat("##0.##");

    private final Color lightBackground = new Color(206	,211	,216);
    private final Color backgroundColor = new Color(239, 239, 232);
    private final Color textColor = new Color(25, 25, 31);
    private final Color buttonColor = new Color(105,152,171);
    private final Color buttonClickedColor = new Color(64, 104, 130);

    private final Font textFieldFont = new Font("Franklin Gothic Book",4,15);
    private final Font titleFont = new Font("Franklin Gothic Demi",1,30);

    public PanelCover(){

        migLayout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]15[]push");
        setLayout(migLayout);
        setOpaque(false);
        init();
        changeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                event.actionPerformed(e);

            }
        });
    }

    private void init(){

        title = new JLabel("<html> Welcome back &#10084 </html>");
        title.setFont(titleFont);
        title.setForeground(lightBackground);
        add(title);

        firstDescript = new JLabel("If you don't have an account");
        firstDescript.setForeground(lightBackground);
        firstDescript.setFont(textFieldFont);
        add(firstDescript);

        secondDescript = new JLabel("   create one here  ");
        secondDescript.setForeground(lightBackground);
        secondDescript.setFont(textFieldFont);
        add(secondDescript);

        changeButton = new MyButton("Sign Up", buttonColor, buttonClickedColor, lightBackground, 30);
        changeButton.setPreferredSize(new Dimension(90, 27));
        changeButton.setBorder(new EmptyBorder(5,5,5,5));
        changeButton.setFont(textFieldFont);
        changeButton.setForeground(lightBackground);
        add(changeButton);
    }

    protected void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        GradientPaint gradientPaint = new GradientPaint(new Point(0,0), new Color(15,62,99), new Point(0, getHeight()),new Color(120,182,190));
        graphics2D.setPaint(gradientPaint);
        graphics2D.fillRect(0,0,getWidth(),getHeight());
        super.paintComponent(graphics);
    }

    public void rightLogin(double v){

        v = Double.valueOf(decimalFormat.format(v));
        login(true);
        migLayout.setComponentConstraints(title,"pad 0 " + v + "% 0 " + v + "%");
        migLayout.setComponentConstraints(firstDescript,"pad 0 " + v + "% 0 " + v + "%");
        migLayout.setComponentConstraints(secondDescript,"pad 0 " + v + "% 0 " + v + "%");

    }
    public void leftLogin(double v){

        v = Double.valueOf(decimalFormat.format(v));
        login(true);
        migLayout.setComponentConstraints(title,"pad 0 " + v + "% 0 " + v + "%");
        migLayout.setComponentConstraints(firstDescript,"pad 0 " + v + "% 0 " + v + "%");
        migLayout.setComponentConstraints(secondDescript,"pad 0 " + v + "% 0 " + v + "%");

    }
    public void rightRegister(double v){

        v = Double.valueOf(decimalFormat.format(v));
        login(false);
        migLayout.setComponentConstraints(title,"pad 0 -" + v + "% 0 0");
        migLayout.setComponentConstraints(firstDescript,"pad 0 -" + v + "% 0 0");
        migLayout.setComponentConstraints(secondDescript,"pad 0 -" + v + "% 0 0");

    }
    public void leftRegister(double v){

        v = Double.valueOf(decimalFormat.format(v));
        login(false);
        migLayout.setComponentConstraints(title,"pad 0 -" + v + "% 0 0");
        migLayout.setComponentConstraints(firstDescript,"pad 0 -" + v + "% 0 0");
        migLayout.setComponentConstraints(secondDescript,"pad 0 -" + v + "% 0 0");

    }

    private void login(boolean login){

        if(isLogin == login){
            title.setText("<html> Welcome back &#10084 </html>");
            firstDescript.setText("If you don't have an account");
            secondDescript.setText("   create one here  ");
            changeButton.setText("Sign Up");
        }else{
                title.setText("<html> Hi, friend &#10024 </html>");
            title.setFont(titleFont);
            firstDescript.setText("You already have an account?");
            secondDescript.setText("Log in now!");
            changeButton.setText("Sign In");
        }

    }

    public void addEvent(ActionListener event){

        this.event = event;
    }

}
