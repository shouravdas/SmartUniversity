package Library;

import com.sbix.jnotify.NPosition;
import com.sbix.jnotify.NoticeWindow;
import diu.desk.ConnectionFactory;
import java.awt.Color;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class StudentRege extends javax.swing.JFrame {

     
   Connection con = null;
     Statement statement = null;
     PreparedStatement pStatement = null;
     ResultSet rs = null;
     String regeNo;
     String libId;
     String [] sid;
     int lid;
     String Student_id;
    public StudentRege() {
        initComponents();
         con = ConnectionFactory.getMysqlConnection();
          ReadUserId();
         
          }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        studentId = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        dept = new javax.swing.JTextField();
        batch = new javax.swing.JTextField();
        SaveBtn = new javax.swing.JButton();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 69, 79));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Register A Student", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Department");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Student Name");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Batch");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Student  Reg. no");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 110, 30));

        studentId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                studentIdKeyReleased(evt);
            }
        });
        jPanel1.add(studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 190, 30));
        jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 190, 30));
        jPanel1.add(dept, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 190, 30));
        jPanel1.add(batch, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 190, 30));

        SaveBtn.setText("Save");
        SaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveBtnActionPerformed(evt);
            }
        });
        jPanel1.add(SaveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 120, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 310));
        jPanel1.getAccessibleContext().setAccessibleName("Student Reg");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveBtnActionPerformed
            
        if(batch.getText().equals("")){  
          JOptionPane.showMessageDialog(null, "Enter Correct Student Id");
      }
      else{
           SearchUser();
           if(Student_id==null){
            GenLibraryId();
            Student_id=null;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateGet = new Date(); 
        try {
           pStatement=con.prepareStatement("INSERT INTO library_member (student_id,library_id,date) VALUES (?,?,?)");
           pStatement.setString(1,regeNo);
           pStatement.setString(2,libId);           
          pStatement.setString(3,dateFormat.format(dateGet));           
          pStatement.execute();
           JOptionPane.showMessageDialog(null, "Member id ="+libId);
           ClearTF();
           lid=0;
           ReadUserId();
        }
       catch (SQLException ex) { 
              System.out.println("Error"+ex);
       }
           }else{
                JOptionPane.showMessageDialog(null, "User ID Alredy Exists");
                ClearTF();
                Student_id=null;
           }
        
      }
             
    }//GEN-LAST:event_SaveBtnActionPerformed

    private void studentIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentIdKeyReleased
               GetStudentInfo();
    }//GEN-LAST:event_studentIdKeyReleased
    
    public void SearchUser(){
       try {
           pStatement=con.prepareStatement("SELECT * FROM library_member WHERE student_id=?");
           pStatement.setString(1,studentId.getText());
           rs=pStatement.executeQuery();
           if(rs.next()){
               Student_id=rs.getString("student_id");               
           }
       } catch (SQLException ex) {
           Logger.getLogger(StudentRege.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }
    
    
    public void GetStudentInfo(){
       try {
           pStatement=con.prepareStatement("SELECT * FROM `studentinfo` WHERE student_id=?");
           pStatement.setString(1,studentId.getText());
           rs=pStatement.executeQuery();
           if(rs.next()){
              regeNo = rs.getString("student_id");
              name.setText(rs.getString("student_name"));
              dept.setText(rs.getString("department"));
              batch.setText(rs.getString("batch"));
              
           }
       } catch (SQLException ex) {
           Logger.getLogger(StudentRege.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void GenLibraryId(){
         DateFormat dFormat = new SimpleDateFormat("yyyy");
	Date dat = new Date(); 
        String d=dFormat.format(dat);
        PassUserId();
        int id=lid+1;
        sid=regeNo.split("-");
        libId=sid[0]+sid[1]+"-"+id+"/"+d;       
        
    }
    
    
     public void ClearTF(){
        studentId.setText("");
        name.setText("");
        batch.setText("");
        dept.setText("");       
    }
     
     public void PassUserId(){
        File file=new File("L_id.txt");
        try{
            PrintWriter out=new PrintWriter(file);
            out.println(lid+1);
            out.close();
        }catch(Exception ex){
            System.out.printf("Error %s\n", ex); 
        }
    }
     
     void ReadUserId(){
        File file=new File("L_id.txt");
        try{
            Scanner input=new Scanner(file);
            lid=input.nextInt();            
        }catch(Exception ec){
            
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
            java.util.logging.Logger.getLogger(StudentRege.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentRege.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentRege.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentRege.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentRege().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SaveBtn;
    private javax.swing.JTextField batch;
    private javax.swing.JTextField dept;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField name;
    private javax.swing.JTextField studentId;
    // End of variables declaration//GEN-END:variables
}
