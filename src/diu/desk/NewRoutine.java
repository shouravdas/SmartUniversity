/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diu.desk;

import com.sbix.jnotify.NPosition;
import com.sbix.jnotify.NoticeWindow;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;


public class NewRoutine extends javax.swing.JFrame {

   String Role="";
    Connection con = null;
     Statement statement = null;
     PreparedStatement pStatement = null;
     ResultSet rs = null;
      String dataSaveDate;
     String currentDate;
     ArrayList<String> BatchGroup=new ArrayList<String>();
     ArrayList<String> ComboItem=new ArrayList<String>();
    
    public NewRoutine(String role) {         
        con = ConnectionFactory.getMysqlConnection();
        initComponents();
        this.Role=role;
        AddBatchPanel.setVisible(false);
        
      ReadDate();  
      CheckDate();
    }

    NewRoutine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

          public void CheckBG(){
       try {
           pStatement=con.prepareStatement("SELECT s_group FROM `batchinfo` WHERE semester=?");           
           pStatement.setString(1,SemesterCombo.getSelectedItem().toString());
             rs=pStatement.executeQuery();
            while(rs.next()){
               BatchGroup.add(rs.getString("s_group"));
            }
       } catch (SQLException ex) {
           Logger.getLogger(NewRoutine.class.getName()).log(Level.SEVERE, null, ex);
       }
            
            
           }
    public void AddBatch(){
         
        int dialog=JOptionPane.YES_NO_OPTION;
        int result= JOptionPane.showConfirmDialog(this, "Are You Sure To Add This Batch", "ADD", dialog);
       //This is use for set data if accept condition 
        if(result==0){
        String insartData="insert into batchinfo(batch,semester,s_group) values(?,?,?)";
       //Insart new data into Batchinfo table 
        try{
            pStatement=con.prepareStatement(insartData);
            String BatchNo1=BatchNameTf.getText();
            if (BatchNo1.equals("")){
                new NoticeWindow(Color.red, "Fill Data into Batch Name", 002, NPosition.TOP_RIGHT);
            }
            else{
            pStatement.setString(1,BatchNameS.getSelectedItem().toString()+BatchNameTf.getText());
            pStatement.setString(2,SemesterCombo.getSelectedItem().toString());
            pStatement.setString(3,BatchGroupCombo.getSelectedItem().toString());                       
            pStatement.execute();
            AddBatchPanel.setVisible(false);
            new NoticeWindow(Color.green, "Batch "+BatchNameS.getSelectedItem().toString()+BatchNameTf.getText()+" is Added Successful", 002, NPosition.TOP_RIGHT);
            }
              /*     
                This is user for Add Batch panel Save button action event 
            */
        }
        
        catch(Exception excp){
            new NoticeWindow(Color.red, "Failed", 001, NPosition.TOP_RIGHT);
        }
        }
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
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        addBatch = new javax.swing.JButton();
        progressSemester = new javax.swing.JButton();
        AddBatchPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        SemesterCombo = new javax.swing.JComboBox<>();
        BatchNameS = new javax.swing.JComboBox<>();
        BatchNameTf = new javax.swing.JTextField();
        CancleBtn = new javax.swing.JButton();
        AddbatchSvaeBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        BatchGroupCombo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        courseDist = new javax.swing.JButton();
        calendarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generate New Class Routine");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 69, 79));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1_circle_26px.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 50, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4_circle_26px.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 50, 50));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3_circle_26px.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 50, 50));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2_circle_26px.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 50, 50));

        addBatch.setText("Add New Batches");
        addBatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBatchActionPerformed(evt);
            }
        });
        jPanel1.add(addBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 160, 40));

        progressSemester.setText("Update Batch Info");
        progressSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                progressSemesterActionPerformed(evt);
            }
        });
        jPanel1.add(progressSemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 160, 40));

        AddBatchPanel.setBackground(java.awt.SystemColor.activeCaption);
        AddBatchPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("   Batch Number");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AddBatchPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 150, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("    Semester");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AddBatchPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 150, 40));

        SemesterCombo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SemesterCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        SemesterCombo.setToolTipText("");
        SemesterCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SemesterComboActionPerformed(evt);
            }
        });
        AddBatchPanel.add(SemesterCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 260, 40));

        BatchNameS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BatchNameS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "E-", "D-" }));
        AddBatchPanel.add(BatchNameS, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 60, 40));

        BatchNameTf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AddBatchPanel.add(BatchNameTf, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 200, 40));

        CancleBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        CancleBtn.setText("CANCLE");
        CancleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancleBtnActionPerformed(evt);
            }
        });
        AddBatchPanel.add(CancleBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 140, 30));

        AddbatchSvaeBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        AddbatchSvaeBtn.setText("SAVE");
        AddbatchSvaeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddbatchSvaeBtnActionPerformed(evt);
            }
        });
        AddBatchPanel.add(AddbatchSvaeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 140, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("   Semester Group");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AddBatchPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 150, 40));

        BatchGroupCombo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BatchGroupCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "batch_group1", "batch_group2", "batch_group3", "batch_group4", "batch_group5" }));
        BatchGroupCombo.setToolTipText("");
        AddBatchPanel.add(BatchGroupCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 260, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add A New Batch");
        AddBatchPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 310, 50));

        jPanel1.add(AddBatchPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 500, 480));

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 120, 40));

        courseDist.setText("Course Distribution");
        courseDist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseDistActionPerformed(evt);
            }
        });
        jPanel1.add(courseDist, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 160, 40));

        calendarButton.setText("Set Academic Calendar");
        calendarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calendarButtonActionPerformed(evt);
            }
        });
        jPanel1.add(calendarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 160, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void progressSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_progressSemesterActionPerformed
       AddBatchPanel.setVisible(false);
        ConfirmationPassword cp=new ConfirmationPassword();
        int result= JOptionPane.showConfirmDialog(this, "Are You Confirm To Change!!", "Your database will certainly change!!",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
      if(result==0){
          cp.setVisible(true); 
      }
        else{
            cp.setVisible(false);
        }
      
    }//GEN-LAST:event_progressSemesterActionPerformed

    private void addBatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBatchActionPerformed
      AddBatchPanel.setVisible(true);
    }//GEN-LAST:event_addBatchActionPerformed

    private void CancleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancleBtnActionPerformed
        AddBatchPanel.setVisible(false);
    }//GEN-LAST:event_CancleBtnActionPerformed

    private void AddbatchSvaeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddbatchSvaeBtnActionPerformed
     AddBatch();

    }//GEN-LAST:event_AddbatchSvaeBtnActionPerformed

    private void courseDistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseDistActionPerformed
         AddBatchPanel.setVisible(false);
         CourseDistribution cd=new CourseDistribution(Role);
         cd.setVisible(true);
          this.dispose();
    }//GEN-LAST:event_courseDistActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        Home home = new Home(Role);
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void calendarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calendarButtonActionPerformed
         AddBatchPanel.setVisible(false);
         calendar cd=new calendar(Role);
         cd.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_calendarButtonActionPerformed

    private void SemesterComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SemesterComboActionPerformed
        Object tc1=evt.getSource();
       if(tc1==SemesterCombo){
           ComboItem.add("batch_group1");
           ComboItem.add("batch_group2");
           ComboItem.add("batch_group3");
           ComboItem.add("batch_group4");
           ComboItem.add("batch_group5");
          BatchGroupCombo.setModel(new DefaultComboBoxModel(ComboItem.toArray()));
           CheckBG();
          for(int i=0;i<BatchGroup.size();i++){
              BatchGroupCombo.removeItem(BatchGroup.get(i));  
          } 
           System.out.println(BatchGroup);
           BatchGroup.clear();
           ComboItem.clear();
                     
       }
    }//GEN-LAST:event_SemesterComboActionPerformed

    
    
    public void CheckDate(){
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
	Date date = new Date();
        currentDate=dateFormat.format(date);
        try {
    Date date1 = dateFormat.parse(currentDate);
    Date date2 = dateFormat.parse(dataSaveDate);
    long diff = date1.getTime() - date2.getTime();
    
    long Day=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            System.out.println(Day);
    if(Day>65){
        progressSemester.setEnabled(true);
    }else{
         progressSemester.setEnabled(false);
    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
    }
    void ReadDate(){
        File file=new File("save_date.txt");
        try{
            Scanner input=new Scanner(file);
            dataSaveDate=input.nextLine();            
        }catch(Exception ec){
            System.out.printf("Error %s",ec);
        }
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
            java.util.logging.Logger.getLogger(NewRoutine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewRoutine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewRoutine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewRoutine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewRoutine().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddBatchPanel;
    private javax.swing.JButton AddbatchSvaeBtn;
    private javax.swing.JComboBox<String> BatchGroupCombo;
    private javax.swing.JComboBox<String> BatchNameS;
    private javax.swing.JTextField BatchNameTf;
    private javax.swing.JButton CancleBtn;
    private javax.swing.JComboBox<String> SemesterCombo;
    private javax.swing.JButton addBatch;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton calendarButton;
    private javax.swing.JButton courseDist;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton progressSemester;
    // End of variables declaration//GEN-END:variables
}
