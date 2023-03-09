package service;

import model.MessageModel;

import java.util.Properties;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServiceMail {

    public MessageModel sendMain(String toEmail, String code) {
        MessageModel ms = new MessageModel(false, "");
        String from = "******@gmail.com"; //kinga.sasusimon
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        String username = "****@gmail.com";
        String password = "Kicsikacsa15";    //  Your email password here
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
//            message.setSubject("Verify Code");
//            message.setText(code);
//            Transport.send(message);
//            ms.setSuccess(true);
//        } catch (MessagingException e) {
//            if (e.getMessage().equals("Invalid Addresses")) {
//                ms.setMessage("Invalid email");
//            } else {
//                ms.setMessage("Error");
//            }
//        }
        return ms;
    }
}