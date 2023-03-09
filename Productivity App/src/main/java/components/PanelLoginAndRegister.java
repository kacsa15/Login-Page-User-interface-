package components;

import model.UserModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelLoginAndRegister extends JPanel {

    private UserModel user;

    public JPanel panel1;
    private JPanel loginPanel;
    private JPanel registerPanel;
    private CardLayout cardLayout;

   // private final Color lightBackground = new Color(224,231,233);
    private final Color backgroundColor = new Color(224,231,233);
    private final Color lightBackground = new Color(206	,211	,216);
    private final Color textColor = new Color(25, 25, 31);
    private final Color buttonColor = new Color(105,152,171);

    private final Font textFieldFont = new Font("Franklin Gothic Book",4,15);
    private final Font titleFont = new Font("Franklin Gothic Demi",1,30);

    public PanelLoginAndRegister(ActionListener registrationEvent){

        initRegister(registrationEvent);
        initLogin();
        panel1.removeAll();
        panel1.add(registerPanel);
        panel1.add(loginPanel);
        registerPanel.setVisible(false);
        loginPanel.setVisible(true);
//        panel1.repaint();
//        panel1.revalidate();

     //   initLogin();


    }

    private void initRegister(ActionListener registrationEvent){

        registerPanel.setLayout(new MigLayout("wrap", "push[center]push","push[]35[]10[]10[]25[]push"));

        JLabel label = new JLabel("Create Account");
        label.setForeground(buttonColor);
        label.setFont(titleFont);
        registerPanel.add(label);

        JTextField userTextField = new MyTextField(new ImageIcon("C:\\Users\\ASUS\\Desktop\\Productivity App\\src\\main\\resources\\icons\\user.png"));
        userTextField.setDocument(new JTextFieldLimit(20));
        userTextField.setText("Username");
        userTextField.setHorizontalAlignment(JTextField.CENTER);
        userTextField.setFont(textFieldFont);
        userTextField.setForeground(textColor);
        userTextField.setBackground(lightBackground);
        userTextField.setBorder(new EmptyBorder(1,1,1,1));
        registerPanel.add(userTextField, " w 45%, h 5%");

        JTextField emailTextField = new MyTextField(new ImageIcon("C:\\Users\\ASUS\\Desktop\\Productivity App\\src\\main\\resources\\icons\\email.png"));
        emailTextField.setText("Email");
        emailTextField.setHorizontalAlignment(JTextField.CENTER);
        emailTextField.setFont(textFieldFont);
        emailTextField.setForeground(textColor);
        emailTextField.setBackground(lightBackground);
        emailTextField.setBorder(new EmptyBorder(1,1,1,1));
        registerPanel.add(emailTextField, "w 45%, h 5%");

        JTextField passwordField = new MyPasswordField(new ImageIcon("C:\\Users\\ASUS\\Desktop\\Productivity App\\src\\main\\resources\\icons\\password.png"));
        passwordField.setDocument(new JTextFieldLimit(16));
        passwordField.setText("Password");
        passwordField.setHorizontalAlignment(JPasswordField.CENTER);
        passwordField.setFont(textFieldFont);
        passwordField.setForeground(textColor);
        passwordField.setBackground(lightBackground);
        passwordField.setBorder(new EmptyBorder(1,1,1,1));
        registerPanel.add(passwordField, " w 45%, h 5%");

        MyButton signUpButton = new MyButton("Sign Up", backgroundColor, buttonColor, buttonColor, 30);
        signUpButton.setPreferredSize(new Dimension(150, 27));
        signUpButton.setMinimumSize(new Dimension(150, 27));
        signUpButton.addActionListener(registrationEvent);
        signUpButton.setFocusPainted(false);
        signUpButton.setFont(textFieldFont);
        signUpButton.setBorder(new EmptyBorder(5,5,5,5));
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userTextField.getText().trim();
                String password = passwordField.getText().trim();
                String email = emailTextField.getText().trim();

                user = new UserModel(0, userName, email, password);
            }
        });
        registerPanel.add(signUpButton, " w 22%, h 5%");

        panel1.repaint();
        panel1.revalidate();

    }

    private void initLogin(){
        loginPanel.setLayout(new MigLayout("wrap", "push[center]push","push[]35[]10[]15[]25[]push"));

        JLabel label = new JLabel("Sign In");
        label.setForeground(buttonColor);
        label.setFont(titleFont);
        loginPanel.add(label);

        JTextField userTextField = new MyTextField(new ImageIcon("C:\\Users\\ASUS\\Desktop\\Productivity App\\src\\main\\resources\\icons\\user.png"));
        userTextField.setDocument(new JTextFieldLimit(20));
        userTextField.setText("Username");
        userTextField.setHorizontalAlignment(JTextField.CENTER);
        userTextField.setFont(textFieldFont);
        userTextField.setForeground(textColor);
        userTextField.setBackground(lightBackground);
        userTextField.setBorder(new EmptyBorder(1,1,1,1));
        loginPanel.add(userTextField, " w 45%, h 5%");

        JTextField passwordField = new MyPasswordField(new ImageIcon("C:\\Users\\ASUS\\Desktop\\Productivity App\\src\\main\\resources\\icons\\password.png"));
        passwordField.setDocument(new JTextFieldLimit(16));
        passwordField.setText("Password");
        passwordField.setHorizontalAlignment(JPasswordField.CENTER);
        passwordField.setFont(textFieldFont);
        passwordField.setForeground(textColor);
        passwordField.setBackground(lightBackground);
        passwordField.setBorder(new EmptyBorder(1,1,1,1));
        loginPanel.add(passwordField, " w 45%, h 5%");

        JButton forgotPasswordButton = new JButton("Forgot your password?");
        forgotPasswordButton.setFont(textFieldFont);
        forgotPasswordButton.setBackground(lightBackground);
        forgotPasswordButton.setForeground(buttonColor);
        forgotPasswordButton.setContentAreaFilled(false);
        forgotPasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordButton.setBorder(new EmptyBorder(0,0,0,0));
        loginPanel.add(forgotPasswordButton);

        MyButton loginButton = new MyButton("Sign Up", backgroundColor, buttonColor, buttonColor, 30);
        loginButton.setPreferredSize(new Dimension(150, 27));
        loginButton.setFocusPainted(false);
        loginButton.setFont(textFieldFont);
        loginButton.setBorder(new EmptyBorder(5,5,5,5));
        loginPanel.add(loginButton);

        panel1.repaint();
        panel1.revalidate();

    }

    public void showLogin(boolean show){
        if(show){
            registerPanel.setVisible(false);
            loginPanel.setVisible(true);
        }else{
            registerPanel.setVisible(true);
            loginPanel.setVisible(false);
        }
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
