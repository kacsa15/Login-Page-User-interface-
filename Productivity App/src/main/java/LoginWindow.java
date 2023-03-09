import components.*;
import connection.DatabaseConnection;
import model.MessageModel;
import model.UserModel;
import net.miginfocom.swt.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import service.ServiceMail;
import service.ServiceUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class LoginWindow extends JFrame{

    private JPanel panel1;
    private JPanel contentPane;


    private MigLayout migLayout;
    private PanelCover panelCover;
    private PanelLoginAndRegister panelLoginAndRegister;
    private PanelLoading panelLoading;
    private PanelVerificationCode panelVerificationCode;
    private PanelMessage panelMessage;

    private ServiceUser serviceUser;

    private final int ADDSIZE = 10;
    private final int COVERSIZE = 40;
    private final int LOGINSIZE = 60;

    private boolean isLogin;
    private final DecimalFormat decimalFormat = new DecimalFormat("##0.###");

    public LoginWindow(){
        //contentPane = new JPanel(new GridLayout(1,2));
        //migLayout = ;
        contentPane = new JPanel(new net.miginfocom.swing.MigLayout("fill, debug"));
        setContentPane(contentPane);
        init();
    }

    private void init(){

        serviceUser = new ServiceUser();

        panelCover = new PanelCover();
        ActionListener actionListenerRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        };
        panelLoginAndRegister = new PanelLoginAndRegister(actionListenerRegister);
        panelLoading = new PanelLoading();
        panelVerificationCode = new PanelVerificationCode();

        TimingTarget timingTarget = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;

                double size = COVERSIZE;

                if(fraction <= 0.5f){
                    size += ADDSIZE + fraction;
                }else{
                    size += ADDSIZE - ADDSIZE * fraction;
                }

                if(isLogin){
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;

                    if(fraction >= 0.5f){
                        panelCover.rightRegister(fractionCover * 200);
                    }else{
                        panelCover.rightLogin(fractionLogin * 200);
                    }

                }else{
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if(fraction <= 0.5f){
                        panelCover.leftRegister(fraction * 200);

                    }else{
                        panelCover.leftLogin((1f - fraction) * 200);
                    }
                }

                if(fraction >= 0.5f){
                    panelLoginAndRegister.showLogin(isLogin);
                }

                fractionCover = Double.parseDouble(decimalFormat.format(fractionCover));
                fractionLogin =  Double.parseDouble(decimalFormat.format(fractionLogin));
                contentPane.add(panelCover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                contentPane.add(panelLoginAndRegister.panel1, "width " + LOGINSIZE + "%, pos " + fractionLogin + "al 0 n 100%");
                contentPane.revalidate();
            }

            public void end() {
                isLogin = !isLogin;
            }
        };

        Animator animator = new Animator(750,timingTarget);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);

        contentPane.add(panelLoading, "pos 0 0 100% 100%");
        contentPane.add(panelVerificationCode, "pos 0 0 100% 100%");
        contentPane.add(panelCover, "width " + COVERSIZE + "%, pos 0al 0 n 100%");
        contentPane.add(panelLoginAndRegister.panel1, "width " + LOGINSIZE + "%, pos 1al 0 n 100%");
        panelCover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!animator.isRunning()){
                    animator.start();
                }
            }
        });
        panelVerificationCode.addEventOkButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    UserModel user = panelLoginAndRegister.getUser();
                    if(serviceUser.verifyCodeWithUser(user.getUserID(), panelVerificationCode.getInputCode())){
                        serviceUser.doneVerifying(user.getUserID());
                        JOptionPane.showMessageDialog(contentPane, "SUCCESSSSSS!",
                                "Swing Tester", JOptionPane.ERROR_MESSAGE);
                        panelVerificationCode.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(contentPane, "incorrect code!",
                                "Swing Tester", JOptionPane.ERROR_MESSAGE);
                    }

                }catch (Exception exception){
                    JOptionPane.showMessageDialog(contentPane, "verify code:((",
                            "Swing Tester", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(panel1);
    }

    public void register(){
        //panelLoading.setVisible(true);
        //panelVerificationCode.setVisible(true);
        UserModel user = panelLoginAndRegister.getUser();
        try {
            if(serviceUser.checkDuplicateUserName(user.getUserName())){
                JOptionPane.showMessageDialog(contentPane, "username!",
                        "Swing Tester", JOptionPane.ERROR_MESSAGE);
            }else if(serviceUser.checkDuplicateEmail(user.getEmail())){
                JOptionPane.showMessageDialog(contentPane, "email!",
                        "Swing Tester", JOptionPane.ERROR_MESSAGE);
            }else{
                serviceUser.insertUser(user);
                sendMain(user);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(contentPane, "iiiidk",
                    "Swing Tester", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    private void sendMain(UserModel user){
        new Thread(new Runnable() {
            @Override
            public void run() {
                panelLoading.setVisible(true);
                MessageModel messageModel = new ServiceMail().sendMain(user.getEmail(), user.getVerifyCode());

                if(messageModel.isSuccess()){
                    panelLoading.setVisible(false);
                    panelVerificationCode.setVisible(true);

                }else{
                    panelLoading.setVisible(false);
                    JOptionPane.showMessageDialog(contentPane, "did not send message",
                            "Swing Tester", JOptionPane.ERROR_MESSAGE);
                }
            }
        }).start();
    }

    private void showMessage(PanelMessage.MessageType messageType, String message){
        panelMessage = new PanelMessage();
        panelMessage.showMessage(messageType, message);
        TimingTarget timingTarget = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if(!panelMessage.isShow()){
                    contentPane.add(panelMessage, "pos 0.5al -30",0);
                    panelMessage.setVisible(true);
                    contentPane.repaint();
                    //contentPane.revalidate();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if(panelMessage.isShow()){
                    f = 40 * (1f - fraction);
                }else{
                    f = 40 * fraction;
                }
                contentPane.add(panelMessage, "pos 0.5al " + (int) (f - 30));
                contentPane.repaint();
                contentPane.revalidate();
            }

            @Override
            public void end() {
                if(panelMessage.isShow()){
                    contentPane.remove(panelMessage);
                    contentPane.repaint();
                    contentPane.revalidate();
                }else{
                    panelMessage.setShow(true);
                }
            }
        };

        Animator animator = new Animator(300, timingTarget);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                    animator.start();
                }catch(InterruptedException e){
                    System.err.println(e);

                }
            }
        }).start();

    }


    public static void main (String[] args){

        try{
            DatabaseConnection.getInstance().connectToDatabase();
        }catch(SQLException e){
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Start");
                frame.setContentPane(new LoginWindow().contentPane);
                frame.setVisible(true);
                frame.setSize(750,500);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
    }
}
