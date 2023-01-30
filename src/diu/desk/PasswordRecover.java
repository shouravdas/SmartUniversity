/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diu.desk;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
 
public class PasswordRecover {
       String generatedCode;
       String receiverEmail;

   
    public void SendEmail(){
         String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder newCode = new StringBuilder();
        
        Random rnd = new Random();
        while (newCode.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            newCode.append(SALTCHARS.charAt(index));
        }
        generatedCode = newCode.toString();
        try{
            String host ="smtp.gmail.com" ;
            String user = "diusmartuniversity@gmail.com";
            String pass = "Dhaka@1205";
            String sendTo = receiverEmail;
            String from = "diusmartuniversity@gmail.com";
            String subject = "Please Don't Reply. Smart DIU Recovery code";
            String messageText = " Welcome to Smart Diu system. <br>"+" Your password recovery verification code is:<br> <b><b><strong><strong>"
                    + " "+generatedCode+"    </strong></strong></b></b> <br>"+"\n"
                    + "To reset your password Submit your veirfication code.\n"+
                    "<br><br> Note: Please Do not Share your Code to anyother.<br>";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(receiverEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);
            msg.setContent(messageText, "text/html; charset=utf-8");

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           
        }catch(Exception ex)
        {
            System.out.println(ex+"error");
        }
    }
        
}
