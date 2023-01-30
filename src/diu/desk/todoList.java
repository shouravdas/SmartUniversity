/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diu.desk;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

 
public class todoList extends javax.swing.JFrame {
 Connection con = null;
     Statement statement = null;
     PreparedStatement pStatement = null;
     ResultSet rs = null;
     String userName;
     
     ArrayList<String> TaskList=new ArrayList<String>();
     ArrayList<String> DateList=new ArrayList<String>();
             
    /**
     * Creates new form todoList
     */
    public todoList() {
       con = ConnectionFactory.getMysqlConnection();
        initComponents();
        ReadDate();
        CheckTODO();
        TODOSet();
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ADDBTN = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        date1 = new javax.swing.JTextField();
        DatePicker = new com.toedter.calendar.JDateChooser();
        TaskName = new javax.swing.JTextField();
        task1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        task2 = new javax.swing.JTextField();
        date2 = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        task3 = new javax.swing.JTextField();
        date3 = new javax.swing.JTextField();
        jCheckBox3 = new javax.swing.JCheckBox();
        task4 = new javax.swing.JTextField();
        date4 = new javax.swing.JTextField();
        jCheckBox4 = new javax.swing.JCheckBox();
        task5 = new javax.swing.JTextField();
        date5 = new javax.swing.JTextField();
        jCheckBox5 = new javax.swing.JCheckBox();
        date6 = new javax.swing.JTextField();
        task6 = new javax.swing.JTextField();
        jCheckBox6 = new javax.swing.JCheckBox();
        task7 = new javax.swing.JTextField();
        date7 = new javax.swing.JTextField();
        jCheckBox7 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("To Do List Manager");

        jPanel1.setBackground(new java.awt.Color(0, 69, 79));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ADDBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        ADDBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ADDBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ADDBTNMouseClicked(evt);
            }
        });
        jPanel1.add(ADDBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 50, 60));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("To Do List");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 680, 50));

        date1.setToolTipText("");
        jPanel1.add(date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, 130, 30));

        DatePicker.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                DatePickerFocusGained(evt);
            }
        });
        jPanel1.add(DatePicker, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 130, 40));

        TaskName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TaskName.setForeground(new java.awt.Color(102, 102, 102));
        TaskName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TaskName.setText("Add Task");
        TaskName.setToolTipText("Enter your Task here");
        TaskName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TaskNameFocusGained(evt);
            }
        });
        TaskName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TaskNameMouseClicked(evt);
            }
        });
        jPanel1.add(TaskName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 420, 40));

        task1.setToolTipText("");
        jPanel1.add(task1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 420, 30));

        jCheckBox1.setToolTipText("Done?");
        jCheckBox1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 30, 30));

        task2.setToolTipText("");
        jPanel1.add(task2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 420, 30));

        date2.setToolTipText("");
        jPanel1.add(date2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, 130, 30));

        jCheckBox2.setToolTipText("Done?");
        jCheckBox2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jCheckBox2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, 30, 30));

        task3.setToolTipText("");
        jPanel1.add(task3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 420, 30));

        date3.setToolTipText("");
        jPanel1.add(date3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 130, 30));

        jCheckBox3.setToolTipText("Done?");
        jCheckBox3.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jCheckBox3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, 30, 30));

        task4.setToolTipText("");
        jPanel1.add(task4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 420, 30));

        date4.setToolTipText("");
        jPanel1.add(date4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, 130, 30));

        jCheckBox4.setToolTipText("Done?");
        jCheckBox4.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jCheckBox4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 290, 30, 30));

        task5.setToolTipText("");
        jPanel1.add(task5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 420, 30));

        date5.setToolTipText("");
        jPanel1.add(date5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 330, 130, 30));

        jCheckBox5.setToolTipText("Done?");
        jCheckBox5.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jCheckBox5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 330, 30, 30));

        date6.setToolTipText("");
        jPanel1.add(date6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 370, 130, 30));

        task6.setToolTipText("");
        jPanel1.add(task6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 420, 30));

        jCheckBox6.setToolTipText("Done?");
        jCheckBox6.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jCheckBox6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 370, 30, 30));

        task7.setToolTipText("");
        jPanel1.add(task7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 420, 30));

        date7.setToolTipText("");
        jPanel1.add(date7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 410, 130, 30));

        jCheckBox7.setToolTipText("Done?");
        jCheckBox7.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jCheckBox7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 410, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        int result= JOptionPane.showConfirmDialog(this, "Your Task will be Removed!", "Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
      if(result==0){
           try{
              pStatement=con.prepareStatement("DELETE FROM `todolist` WHERE `taskname`=?");                      
               pStatement.setString(1,task1.getText()); 
              pStatement.executeUpdate();
             CheckTODO();
             TODOSet();
        }
           catch(Exception exce){
             
           } }else{
          
      }
                 
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        int result= JOptionPane.showConfirmDialog(this, "Your Task will be Removed!", "Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
      if(result==0){
           try{
              pStatement=con.prepareStatement("DELETE FROM `todolist` WHERE `taskname`=?");                      
               pStatement.setString(1,task2.getText()); 
              pStatement.executeUpdate();
             CheckTODO();
             TODOSet();
        }
           catch(Exception exce){
             
           } }else{
          
      }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        int result= JOptionPane.showConfirmDialog(this, "Your Task will be Removed!", "Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
      if(result==0){
           try{
              pStatement=con.prepareStatement("DELETE FROM `todolist` WHERE `taskname`=?");                      
               pStatement.setString(1,task3.getText()); 
              pStatement.executeUpdate();
             CheckTODO();
             TODOSet();
        }
           catch(Exception exce){
             
           } }else{
          
      }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        int result= JOptionPane.showConfirmDialog(this, "Your Task will be Removed!", "Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
      if(result==0){
           try{
              pStatement=con.prepareStatement("DELETE FROM `todolist` WHERE `taskname`=?");                      
               pStatement.setString(1,task4.getText()); 
              pStatement.executeUpdate();
             CheckTODO();
             TODOSet();
        }
           catch(Exception exce){
             
           } }else{
          
      }
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        int result= JOptionPane.showConfirmDialog(this, "Your Task will be Removed!", "Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
      if(result==0){
           try{
              pStatement=con.prepareStatement("DELETE FROM `todolist` WHERE `taskname`=?");                      
               pStatement.setString(1,task5.getText()); 
              pStatement.executeUpdate();
             CheckTODO();
             TODOSet();
        }
           catch(Exception exce){
             
           } }else{
          
      }
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
       int result= JOptionPane.showConfirmDialog(this, "Your Task will be Removed!", "Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
      if(result==0){
           try{
              pStatement=con.prepareStatement("DELETE FROM `todolist` WHERE `taskname`=?");                      
               pStatement.setString(1,task6.getText()); 
              pStatement.executeUpdate();
             CheckTODO();
             TODOSet();
        }
           catch(Exception exce){
             
           } }else{
          
      }
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        int result= JOptionPane.showConfirmDialog(this, "Your Task will be Removed!", "Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
      if(result==0){
           try{
              pStatement=con.prepareStatement("DELETE FROM `todolist` WHERE `taskname`=?");                      
               pStatement.setString(1,task7.getText()); 
              pStatement.executeUpdate();
             CheckTODO();
             TODOSet();
        }
           catch(Exception exce){
             
           } }else{
          
      }
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void ADDBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ADDBTNMouseClicked
    
        AddToDoList();
        CheckTODO();
        TODOSet();
         
    }//GEN-LAST:event_ADDBTNMouseClicked

    private void TaskNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TaskNameFocusGained
       
    }//GEN-LAST:event_TaskNameFocusGained

    private void DatePickerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DatePickerFocusGained
       
    }//GEN-LAST:event_DatePickerFocusGained

    private void TaskNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaskNameMouseClicked
        TaskName.setText(""); 
    }//GEN-LAST:event_TaskNameMouseClicked
        void ReadDate(){
        File file=new File("user_name.txt");
        try{
            Scanner input=new Scanner(file);
            userName=input.nextLine();            
        }catch(Exception ec){
            System.out.printf("Error %s",ec);
        }
    }
    public void AddToDoList(){
       java.util.Date date = DatePicker.getDate();
        String taskdate = DateFormat.getDateInstance().format(date);
     try {
         pStatement=con.prepareStatement("INSERT INTO `todolist`(`username`, `taskname`, `date`) VALUES (?,?,?)");
         pStatement.setString(1, userName);
         pStatement.setString(2, TaskName.getText());
         pStatement.setString(3,taskdate);
          pStatement.execute();
          JOptionPane.showMessageDialog(null, "Task Added");
     } catch (SQLException ex) {
         Logger.getLogger(todoList.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
    public void CheckTODO(){
        try {
           pStatement=con.prepareStatement("SELECT * FROM `todolist` WHERE username=?");           
           pStatement.setString(1,userName);
             rs=pStatement.executeQuery();
            while(rs.next()){
               TaskList.add(rs.getString("taskname"));
               DateList.add(rs.getString("date"));
            }
             } catch (SQLException ex) {
           Logger.getLogger(NewRoutine.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void TODOSet(){
        if(TaskList.size()>=1){
             task1.setText(TaskList.get(0));
        }else{
             task1.setText("");
        }
         if(TaskList.size()>=2){
            task2.setText(TaskList.get(1)); 
        }else{
           task2.setText("");  
        }
          if(TaskList.size()>=3){
             task3.setText(TaskList.get(2));
        }else{
             task3.setText("");
        }
           if(TaskList.size()>=4){
             task4.setText(TaskList.get(3));
        }else{
             task4.setText("");
        }
            if(TaskList.size()>=5){
             task5.setText(TaskList.get(4));
        }else{
             task5.setText("");
        }
             if(TaskList.size()>=6){
              task6.setText(TaskList.get(5));
        }else{
           task6.setText(""); 
        }
              if(TaskList.size()>=7){
              task7.setText(TaskList.get(6));
        }else{
            task7.setText("");
        }
      
        
       //add date on text field
       if(DateList.size()>=1){
             date1.setText(DateList.get(0));
        }else{
             date1.setText("");
        }
         if(DateList.size()>=2){
            date2.setText(DateList.get(1)); 
        }else{
           date2.setText("");  
        }
          if(DateList.size()>=3){
             date3.setText(DateList.get(2));
        }else{
             date3.setText("");
        }
           if(DateList.size()>=4){
             date4.setText(DateList.get(3));
        }else{
             date4.setText("");
        }
            if(DateList.size()>=5){
             date5.setText(DateList.get(4));
        }else{
             date5.setText("");
        }
             if(DateList.size()>=6){
              date6.setText(DateList.get(5));
        }else{
           date6.setText(""); 
        }
              if(DateList.size()>=7){
              date7.setText(DateList.get(6));
        }else{
            date7.setText("");
        }
              DateList.clear();
              TaskList.clear();
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
            java.util.logging.Logger.getLogger(todoList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(todoList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(todoList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(todoList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new todoList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ADDBTN;
    private com.toedter.calendar.JDateChooser DatePicker;
    private javax.swing.JTextField TaskName;
    private javax.swing.JTextField date1;
    private javax.swing.JTextField date2;
    private javax.swing.JTextField date3;
    private javax.swing.JTextField date4;
    private javax.swing.JTextField date5;
    private javax.swing.JTextField date6;
    private javax.swing.JTextField date7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField task1;
    private javax.swing.JTextField task2;
    private javax.swing.JTextField task3;
    private javax.swing.JTextField task4;
    private javax.swing.JTextField task5;
    private javax.swing.JTextField task6;
    private javax.swing.JTextField task7;
    // End of variables declaration//GEN-END:variables
}