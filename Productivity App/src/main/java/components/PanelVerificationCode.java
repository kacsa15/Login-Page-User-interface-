package components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class PanelVerificationCode extends JPanel {

    private MigLayout migLayout;
    private RoundPanel roundPanel;
    private JLabel titleLabel;
    private JLabel informativeLabel;
    private JTextField codeTextField;
    private MyButton okButton;
    private MyButton cancelButton;

    private final Color lightBackground = new Color(206	,211	,216);
    private final Color textColor = new Color(25, 25, 31);
    private final Color buttonColor = new Color(105,152,171);
    private final Color verificationBackground = new Color(134, 174, 175);

    private final Font textFieldFont = new Font("Franklin Gothic Book",4,15);
    private final Font titleFont = new Font("Franklin Gothic Book",1,30);

    public PanelVerificationCode(){

        migLayout = new MigLayout("wrap","push[center]push", "push [] push");
        setLayout(migLayout);
        roundPanel = new RoundPanel();
        roundPanel.setLayout(new GridBagLayout());
        roundPanel.setMinimumSize(new Dimension(300,150));
        init();
        setOpaque(false);
        setFocusCycleRoot(true);
        super.setVisible(false);
        setFocusable(true);
        addMouseListener(new MouseAdapter() {
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

    }

    public void setVisible(boolean bool){
        super.setVisible(bool);
        if(bool){
            codeTextField.grabFocus();
            codeTextField.setText("");
        }
    }

    private void init(){

        roundPanel.setLayout(new MigLayout("fill", "[center]", "push[]10push"));

        titleLabel = new JLabel("Verify code");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        roundPanel.add(titleLabel," cell 0 0 2 0,center");

        informativeLabel = new JLabel("  Check you email to get your code  ");
        informativeLabel.setForeground(textColor);
        informativeLabel.setFont(textFieldFont);

        roundPanel.add(informativeLabel," cell 0 1 2 0,center");

        codeTextField = new JTextField("Enter your code here");
        codeTextField.setHorizontalAlignment(JTextField.CENTER);
        codeTextField.setFont(textFieldFont);
        codeTextField.setForeground(textColor);
        codeTextField.setBackground(lightBackground);
        codeTextField.setBorder(new EmptyBorder(1,1,1,1));
        roundPanel.add(codeTextField," cell 0 2 2 0,center,  w 60%, h 15%");


        okButton = new MyButton("Ok",verificationBackground, new Color(61, 121, 94), new Color(61, 121, 68), 40);
        okButton.setPreferredSize(new Dimension(65, 27));
        okButton.setMinimumSize(new Dimension(55, 27));
        okButton.setBorder(new EmptyBorder(5,5,5,5));
        okButton.setFont(textFieldFont);
        okButton.setForeground(textColor);
        roundPanel.add(okButton, " cell 0 3 2 0,center");

        cancelButton = new MyButton("Cancel",verificationBackground, new Color(217, 6, 72, 145), new Color(217, 6, 72, 145), 40);
        cancelButton.setPreferredSize(new Dimension(65, 27));
        cancelButton.setMinimumSize(new Dimension(55, 27));
        cancelButton.setFont(textFieldFont);
        cancelButton.setForeground(textColor);
        cancelButton.setBorder(new EmptyBorder(5,5,5,5));
        roundPanel.add(cancelButton, "cell 0 3 3 0,center");

        add(roundPanel);

    }

    protected void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(new Color(255,255,255));
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        graphics2D.fillRect(0,0,getWidth(),getHeight());
        graphics2D.setComposite(AlphaComposite.Src);
        super.paintComponent(graphics);
    }

    public String getInputCode(){
        return codeTextField.getText().trim();
    }

    public void addEventOkButton(ActionListener event){
        okButton.addActionListener(event);
    }
}
