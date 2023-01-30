/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diu.desk;

import com.sbix.jnotify.NPosition;
import com.sbix.jnotify.NoticeWindow;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

 
public class MailBox extends javax.swing.JFrame {
     Connection con = null;
     Statement statement = null;
     PreparedStatement pStatement = null;
     ResultSet rs = null;
     String recieverName;
     String user_name;
      
    public MailBox() {
        initComponents();
         con = ConnectionFactory.getMysqlConnection();
         ReadUserName();
         showInboxMail(); 
         showSendMail();
         toComboItem();
         }

    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EmailPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        showMessageTF = new javax.swing.JTextPane();
        sendBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        inboxTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        messageSubjectTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        SenderOrRecieverL = new javax.swing.JLabel();
        fromTf = new javax.swing.JTextField();
        userNameL = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sendToCombo = new javax.swing.JComboBox<>();
        ReplyBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        SendboxTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admins' Communication");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        EmailPanel.setBackground(new java.awt.Color(0, 69, 79));
        EmailPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EmailPanelMouseClicked(evt);
            }
        });
        EmailPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        showMessageTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showMessageTFMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(showMessageTF);

        EmailPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, 300, 230));

        sendBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sendBtn.setText("Send");
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendBtnActionPerformed(evt);
            }
        });
        EmailPanel.add(sendBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, 130, 40));

        closeBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        closeBtn.setText("Close");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        EmailPanel.add(closeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 490, 120, 40));

        inboxTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "From", "Subject", "Date", "Time"
            }
        ));
        inboxTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inboxTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(inboxTable);

        EmailPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 320, 210));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Outbox");
        EmailPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 320, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Subject");
        EmailPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 90, 40));

        messageSubjectTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messageSubjectTFMouseClicked(evt);
            }
        });
        EmailPanel.add(messageSubjectTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 300, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Message");
        EmailPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 90, 30));

        SenderOrRecieverL.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        SenderOrRecieverL.setForeground(new java.awt.Color(255, 255, 255));
        SenderOrRecieverL.setText("From");
        EmailPanel.add(SenderOrRecieverL, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 90, 30));

        fromTf.setEditable(false);
        fromTf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fromTfMouseClicked(evt);
            }
        });
        EmailPanel.add(fromTf, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 300, 30));
        EmailPanel.add(userNameL, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 4, 110, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("To");
        EmailPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 90, 30));

        EmailPanel.add(sendToCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 300, 30));

        ReplyBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ReplyBtn.setText("Reply");
        ReplyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReplyBtnActionPerformed(evt);
            }
        });
        EmailPanel.add(ReplyBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 490, 110, 40));

        SendboxTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Send To", "Subject", "Time", "Date"
            }
        ));
        SendboxTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SendboxTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(SendboxTable);

        EmailPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 320, 200));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Inbox");
        EmailPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 39, 320, 30));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sms_26px.png"))); // NOI18N
        jLabel5.setText("Admins' Communication");
        EmailPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 50));

        getContentPane().add(EmailPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inboxTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inboxTableMouseClicked
        
        int index = inboxTable.getSelectedRow();        
        TableModel model = inboxTable.getModel();       
       String messageSubject = model.getValueAt(index,1).toString();  
       String sendTime=model.getValueAt(index,3).toString();
       SenderOrRecieverL.setText("From");
      
       try{
           
       pStatement=con.prepareStatement("SELECT * FROM mail_inbox WHERE time=?");
        pStatement.setString(1,sendTime);
           rs=pStatement.executeQuery();
           if(rs.next()){
                 String messageDetails=rs.getString("message_details");
                 showMessageTF.setText(messageDetails);
                 String messageReceiver=rs.getString("sender");                                
                 fromTf.setText(messageReceiver);
                 messageSubjectTF.setText(messageSubject);
           }
       }
       catch(Exception ex){
           System.out.println("Error"+ex);
       }
    }//GEN-LAST:event_inboxTableMouseClicked

    private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendBtnActionPerformed
       
      recieverName=sendToCombo.getSelectedItem().toString();
        String insartMessage="insert into mail_inbox(date,message,message_details,time,sender,receiver) values(?,?,?,?,?,?)";
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
        
            Calendar cal = Calendar.getInstance();
            Date messageTime=cal.getTime();
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
 
        try{
            pStatement=con.prepareStatement(insartMessage);
            pStatement.setString(1,dateFormat.format(date));
            pStatement.setString(2,messageSubjectTF.getText());
            pStatement.setString(3,showMessageTF.getText());
            pStatement.setString(4,timeFormat.format(messageTime));
            pStatement.setString(5,user_name);
            pStatement.setString(6,recieverName);
                       
        pStatement.execute();
                new NoticeWindow(Color.green, "Send Success", 002, NPosition.TOP_LEFT);
                clearFieldsText();
        }
        
        catch(Exception excp){
            
        }
        
        GetModel();
               
              
    }//GEN-LAST:event_sendBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
       this.dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void EmailPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmailPanelMouseClicked
       
    }//GEN-LAST:event_EmailPanelMouseClicked

    private void fromTfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fromTfMouseClicked
       
    }//GEN-LAST:event_fromTfMouseClicked

    private void messageSubjectTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messageSubjectTFMouseClicked
        
    }//GEN-LAST:event_messageSubjectTFMouseClicked

    private void showMessageTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMessageTFMouseClicked
       
    }//GEN-LAST:event_showMessageTFMouseClicked

    
    private void ReplyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReplyBtnActionPerformed
       String insartMessage="insert into mail_inbox(date,message,message_details,time,sender,receiver) values(?,?,?,?,?,?)";
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
        
            Calendar cal = Calendar.getInstance();
            Date messageTime=cal.getTime();
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
 
        try{
            pStatement=con.prepareStatement(insartMessage);
            pStatement.setString(1,dateFormat.format(date));
            pStatement.setString(2,messageSubjectTF.getText());
            pStatement.setString(3,showMessageTF.getText());
            pStatement.setString(4,timeFormat.format(messageTime));
            pStatement.setString(5,user_name);
            pStatement.setString(6,fromTf.getText());
                       
        pStatement.execute();
                new NoticeWindow(Color.green, "Send Success", 002, NPosition.TOP_LEFT);
                clearFieldsText();
        }
        
        catch(Exception excp){
            new NoticeWindow(Color.RED, "Error TO send", 002, NPosition.TOP_LEFT);
        }
        
         GetModel();
        
    }//GEN-LAST:event_ReplyBtnActionPerformed

    private void SendboxTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SendboxTableMouseClicked
         int index = SendboxTable.getSelectedRow();        
        TableModel sendmodel = SendboxTable.getModel();       
       String messageSubject = sendmodel.getValueAt(index,1).toString();  
       String sendTime=sendmodel.getValueAt(index,3).toString();
       SenderOrRecieverL.setText("Send To");
      
       try{
           
       pStatement=con.prepareStatement("SELECT * FROM mail_inbox WHERE time=?");
        pStatement.setString(1,sendTime);
           rs=pStatement.executeQuery();
           if(rs.next()){
                 String messageDetails=rs.getString("message_details");
                 showMessageTF.setText(messageDetails);
                 String messageReceiver=rs.getString("receiver");                                
                 fromTf.setText(messageReceiver);
                 messageSubjectTF.setText(messageSubject);
           }
       }
       catch(Exception ex){
         
       }
    }//GEN-LAST:event_SendboxTableMouseClicked

    //inbox get data start from here 
    
    public ArrayList<MailDataInbox> getMailDataInbox()
   {
       ArrayList<MailDataInbox> InboxMailList = new ArrayList<MailDataInbox>();
       
       try {
                      
              String mails="SELECT * FROM mail_inbox WHERE receiver=?";
             pStatement=con.prepareStatement(mails);
        pStatement.setString(1,user_name);
           rs=pStatement.executeQuery();
            MailDataInbox mailbox;

           while(rs.next())
           {
mailbox = new MailDataInbox(rs.getString("date"),rs.getString("message"),rs.getString("time"),rs.getString("sender"));
               InboxMailList.add(mailbox);
           }
           
          
    if((statement.executeUpdate(mails))== 1)
           {
               // refresh jtable data
               DefaultTableModel Imodel = (DefaultTableModel)inboxTable.getModel();
               Imodel.setRowCount(0);
               showInboxMail();
           }
           
       } 
      catch (Exception e) {
         
       }
      
       
       return InboxMailList;
   }
   
  
   public void showInboxMail(){
       ArrayList<MailDataInbox> list=getMailDataInbox();
       DefaultTableModel Inboxmodel=(DefaultTableModel)inboxTable.getModel();
       Object[] row=new Object[4];
       for (int i=0;i<list.size();i++){
       row[0]=list.get(i).getSender();
       row[1]=list.get(i).getMessage();
       row[2]=list.get(i).getDate();
       row[3]=list.get(i).getTime();
       
       
       Inboxmodel.addRow(row);
       
       }
   }
    
   public void toComboItem(){
        try{
           
       pStatement=con.prepareStatement("SELECT userName FROM adminlogin");
       rs=pStatement.executeQuery();
           while(rs.next()){
                 String userName=rs.getString("userName");
                 sendToCombo.addItem(userName);
              }
            sendToCombo.setSelectedIndex(-1);
       }
       catch(Exception ex){
           
       }
   }
   
   public void clearFieldsText(){
       fromTf.setText("");
       messageSubjectTF.setText("");
       sendToCombo.setSelectedIndex(-1);
       showMessageTF.setText("");
   }
   // Read user name from Text file 
        void ReadUserName(){
        File file=new File("user_name.txt");
        try{
            Scanner input=new Scanner(file);
            user_name=input.nextLine();
            System.out.println(user_name);
        }catch(Exception ec){
            
        }
    }  
        
    //send box is started from here 
         public ArrayList<MailDataSendBox> getMailDataSendBox()
   {
       ArrayList<MailDataSendBox> sendMailList = new ArrayList<MailDataSendBox>();
       
       try {
                      
              String sends="SELECT * FROM mail_inbox WHERE sender=?";
             pStatement=con.prepareStatement(sends);
                pStatement.setString(1,user_name);
             rs=pStatement.executeQuery();
            MailDataSendBox sendBox;

           while(rs.next())
           {
sendBox = new MailDataSendBox(rs.getString("receiver"),rs.getString("message"),rs.getString("date"),rs.getString("time"));
               sendMailList.add(sendBox);
           }
           
          
    if((statement.executeUpdate(sends))== 1)
           {
               // refresh jtable data
               DefaultTableModel Smodel = (DefaultTableModel)SendboxTable.getModel();
               Smodel.setRowCount(0);
               showInboxMail();
           }
           
       } 
      catch (Exception e) {
       }
      
       
       return sendMailList;
   }
   
  
   public void showSendMail(){
       ArrayList<MailDataSendBox> list=getMailDataSendBox();
       DefaultTableModel sendBoxModel=(DefaultTableModel)SendboxTable.getModel();
       Object[] row=new Object[4];
       for (int i=0;i<list.size();i++){
       row[0]=list.get(i).getSendto();
       row[1]=list.get(i).getSendsubject();
       row[2]=list.get(i).getSenddate();
       row[3]=list.get(i).getSendtime();
       
       
       sendBoxModel.addRow(row);
       
       }
   }
        
        //Get Table model start
   
  public void GetModel(){
         DefaultTableModel inboxmodel = (DefaultTableModel)inboxTable.getModel();
               inboxmodel.setRowCount(0);
               showInboxMail();
          DefaultTableModel sendboxmodel = (DefaultTableModel)SendboxTable.getModel();
               sendboxmodel.setRowCount(0);
               showSendMail();
   }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MailBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MailBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MailBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MailBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MailBox().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EmailPanel;
    private javax.swing.JButton ReplyBtn;
    private javax.swing.JTable SendboxTable;
    private javax.swing.JLabel SenderOrRecieverL;
    private javax.swing.JButton closeBtn;
    private javax.swing.JTextField fromTf;
    private javax.swing.JTable inboxTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField messageSubjectTF;
    private javax.swing.JButton sendBtn;
    private javax.swing.JComboBox<String> sendToCombo;
    private javax.swing.JTextPane showMessageTF;
    public javax.swing.JLabel userNameL;
    // End of variables declaration//GEN-END:variables
}
