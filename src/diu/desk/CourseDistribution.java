/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diu.desk;


import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javax.swing.DefaultButtonModel;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

 
public class CourseDistribution extends javax.swing.JFrame {


// <editor-fold defaultstate="collapsed" desc="Variable">
    Connection con = null;
     Statement statement = null;
     PreparedStatement pStatement = null;
     ResultSet rs = null;
     String batchNumber;
     String selectedBatchNo;
     String semesterGet;
     String SubJect;
     String teacherName;
     int Total=0;
     String teachercode4;
     String courseTitle;
     String courseCoordinator;
     String batchGroup;
     int selectedBatch;
     ArrayList FlagType=new ArrayList();

     int i=0;
     int subNo=0;
     int teachersload=0;
     String Role="";
    //array list for auto complite teacher list Part 02
     ArrayList teacherlist = new ArrayList();
     ArrayList courseList=new ArrayList();
     ArrayList teacherListSubmit=new ArrayList();
     ArrayList subjectCode = new ArrayList();
     ArrayList bAtch = new ArrayList();
     JButton buttons[]=new JButton[150];
     // </editor-fold>
      // <editor-fold defaultstate="collapsed" desc="Constructor">
    public CourseDistribution(String role) {
        initComponents();
         this.Role=role;
        // This is the connection for call connection Factory. part 01
        con = ConnectionFactory.getMysqlConnection();
        //quary for check semester for every batch. part 3
            SemiCheck();
        //Create button field For show Batch. This Buttion is Create by for loop and Dynamically. part 5
            for(i=0; i < Total; i++) {
            for(int j=0;j<bAtch.size();j++){
              buttons[i] = new JButton(""+bAtch.get(i));
            }
            }
            //color buttons of batches
                SetbuttonColor();


            GridLayout gl =new GridLayout(10,4);
            BatchinfoPanel.setLayout(gl);
        //Add window controls to the panel pl.
        // Action Listener for Auto Create Button's
            for( i=0; i < Total; i++) {
                BatchinfoPanel.add(buttons[i]);
                buttons[i].addActionListener(new java.awt.event.ActionListener() {
                                  public void actionPerformed(java.awt.event.ActionEvent e) {
                                     ClearAll();
                                     subNo=0;
                                    JButton myButton = (JButton)e.getSource();
                                    selectedBatchNo=myButton.getText();
                                    //Show Data into batch Lebel
                                    // SelectedBatchLbl.setText("Batch:");
                                     BatchLbl.setText(selectedBatchNo);

                                    //quary for get batch info
                                        BatchInformation();
                                    //add room in room select combobox
                                      RoomSelector();
                                    //quary for get Semester & course info
                                      SemesterInformation();
                                    // show teacher in combo by subcet code;
                                    if(subjectCode.size()==5){
                                        SubjectCodeCompare5();
                                        TecherComboData5();
                                     }
                                     else if(subjectCode.size()==6){
                                         SubjectCodeCompare6();
                                         TecherComboData6();
                                     }

                                    else if(subjectCode.size()==3){
                                        SubjectCodeCompare3();
                                        TecherComboData3();
                                    }
                                   subjectCode.clear();
                                   courseList.clear();
                                   teacherlist.clear();

                     }
            });
            }

            this.setContentPane(SemesterNumberPanel);
        //set the size of the window to be big enough to accomodate all controls.
            this.pack();
        //Finnaly, display the window
            this.setVisible(true);

    }
 // </editor-fold>

    private CourseDistribution() {
      //To change body of generated methods, choose Tools | Templates.
    }

// <editor-fold defaultstate="collapsed" desc="Clear ComboBox Item Method">
    public void ClearAll(){
      CordinatorButtonGroup.clearSelection();
      teacherCombo1.removeAllItems();
      teacherCombo2.removeAllItems();
      teacherCombo3.removeAllItems();
      teacherCombo4.removeAllItems();
      teacherCombo5.removeAllItems();
      teacherCombo6.removeAllItems();
      roomSelect.removeAllItems();
      selectedBatchNo=null;semesterGet=null;batchGroup=null;
      SetNotification.setText("");
}
          // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Batch Information Method">
    public void BatchInformation(){
        try{
         pStatement=con.prepareStatement("SELECT * FROM batchinfo WHERE batch=?");
                        pStatement.setString(1,BatchLbl.getText());
                        rs=pStatement.executeQuery();
                              if(rs.next()){
                            semesterGet=rs.getString("semester");
                            //show Data into semester lbl
                            setSemesterNumber.setText(semesterGet);
                            batchGroup=rs.getString("s_group");
                            String roomNo=rs.getString("room_no");
                            roomSelect.addItem(roomNo);

                          }
        }catch(Exception exc){
            System.out.println("Error"+exc);
        }

    }
     // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Semester Information Method">
    public void SemesterInformation(){
        try{
        pStatement=con.prepareStatement("SELECT * FROM courseinfo WHERE semester=?");
                             pStatement.setString(1,semesterGet);
                             rs=pStatement.executeQuery();
                                while(rs.next()){
                                    SubJect=rs.getString("course_code");
                                    subjectCode.add(SubJect);
                                   // This is for save data into courseinfo for teacher entry for get suggession
                                    courseTitle=rs.getString("course_title");
                                    courseList.add(courseTitle);
                                    //Show current Assigned Teacher In Combobox  // Part 25
                                    teacherName=rs.getString(batchGroup);
                                    teacherlist.add(teacherName);
                                    subNo=subNo+1;

                                }
    }catch(Exception excp){
            System.out.println("Error" +excp);
                }
    }
// </editor-fold>
 // <editor-fold defaultstate="collapsed" desc="Subject Compare Method1">
     public void SubjectCodeCompare5(){
         teacherCombo1.addItem((String)teacherlist.get(0));
         teacherCombo2.addItem((String)teacherlist.get(1));
         teacherCombo3.addItem((String)teacherlist.get(2));
         teacherCombo4.addItem((String)teacherlist.get(3));
         teacherCombo5.addItem((String)teacherlist.get(4));
         teacherCombo1.setSelectedIndex(0);
         teacherCombo2.setSelectedIndex(0);
         teacherCombo3.setSelectedIndex(0);
         teacherCombo4.setSelectedIndex(0);
         teacherCombo5.setSelectedIndex(0);

        SubLabel1.setText((String)courseList.get(0));
        SubLabel2.setText((String)courseList.get(1));
        SubLabel3.setText((String)courseList.get(2));
        SubLabel4.setText((String)courseList.get(3));
        SubLabel5.setText((String)courseList.get(4));

        codeLabel1.setText((String) subjectCode.get(0));
        codeLabel2.setText((String) subjectCode.get(1));
        codeLabel3.setText((String) subjectCode.get(2));
        codeLabel4.setText((String) subjectCode.get(3));
        codeLabel5.setText((String) subjectCode.get(4));
        codeLabel3.setVisible(true);
        codeLabel4.setVisible(true);
        codeLabel5.setVisible(true);
        SubLabel5.setVisible(true);
        codeLabel3.setVisible(true);
        SubLabel3.setVisible(true);
        SubLabel4.setVisible(true);
        codeLabel6.setVisible(false);
        SubLabel6.setVisible(false);
        teacherCombo3.setVisible(true);
        teacherCombo4.setVisible(true);
        teacherCombo5.setVisible(true);
        teacherCombo6.setVisible(false);
        Cordinator3.setVisible(true);
        Cordinator4.setVisible(true);
        Cordinator5.setVisible(true);
        Cordinator6.setVisible(false);
        TL3.setVisible(true);
        TL4.setVisible(true);
        TL5.setVisible(true);
        TL6.setVisible(false);

     }
        // </editor-fold>
     // <editor-fold defaultstate="collapsed" desc="Subject Compare Method2">
      public void SubjectCodeCompare6(){
         teacherCombo1.addItem((String)teacherlist.get(0));
         teacherCombo2.addItem((String)teacherlist.get(1));
         teacherCombo3.addItem((String)teacherlist.get(2));
         teacherCombo4.addItem((String)teacherlist.get(3));
         teacherCombo5.addItem((String)teacherlist.get(4));
         teacherCombo6.addItem((String)teacherlist.get(5));
         teacherCombo1.setSelectedIndex(0);
         teacherCombo2.setSelectedIndex(0);
         teacherCombo3.setSelectedIndex(0);
         teacherCombo4.setSelectedIndex(0);
         teacherCombo5.setSelectedIndex(0);
         teacherCombo6.setSelectedIndex(0);

        SubLabel1.setText((String)courseList.get(0));
        SubLabel2.setText((String)courseList.get(1));
        SubLabel3.setText((String)courseList.get(2));
        SubLabel4.setText((String)courseList.get(3));
        SubLabel5.setText((String)courseList.get(4));
        SubLabel6.setText((String)courseList.get(5));

        codeLabel1.setText((String) subjectCode.get(0));
        codeLabel2.setText((String) subjectCode.get(1));
        codeLabel3.setText((String) subjectCode.get(2));
        codeLabel4.setText((String) subjectCode.get(3));
        codeLabel5.setText((String) subjectCode.get(4));
        codeLabel6.setText((String) subjectCode.get(5));
        codeLabel4.setVisible(true);
        codeLabel5.setVisible(true);
        SubLabel5.setVisible(true);
        SubLabel4.setVisible(true);
        codeLabel6.setVisible(true);
        SubLabel6.setVisible(true);
        teacherCombo4.setVisible(true);
        teacherCombo5.setVisible(true);
        teacherCombo6.setVisible(true);
        Cordinator3.setVisible(true);
        Cordinator4.setVisible(true);
        Cordinator5.setVisible(true);
        Cordinator6.setVisible(true);
        codeLabel3.setVisible(true);
        SubLabel3.setVisible(true);
        teacherCombo3.setVisible(true);
        TL3.setVisible(true);
        TL4.setVisible(true);
        TL5.setVisible(true);
        TL6.setVisible(true);
      }
      // </editor-fold>
      // <editor-fold defaultstate="collapsed" desc="Subject Compare Method3">
      public void SubjectCodeCompare3(){

     teacherCombo1.addItem((String)teacherlist.get(0));
     teacherCombo2.addItem((String)teacherlist.get(1));

     teacherCombo1.setSelectedIndex(0);
     teacherCombo2.setSelectedIndex(0);

    SubLabel1.setText((String)courseList.get(0));
    SubLabel2.setText((String)courseList.get(1));

    codeLabel1.setText((String) subjectCode.get(0));
    codeLabel2.setText((String) subjectCode.get(1));
        codeLabel3.setVisible(false);
        codeLabel4.setVisible(false);
        codeLabel5.setVisible(false);
        SubLabel3.setVisible(false);
        SubLabel5.setVisible(false);
        SubLabel4.setVisible(false);
        codeLabel6.setVisible(false);
        SubLabel6.setVisible(false);
        teacherCombo4.setVisible(false);
        teacherCombo5.setVisible(false);
        teacherCombo6.setVisible(false);
        teacherCombo3.setVisible(false);
        Cordinator3.setVisible(false);
        Cordinator4.setVisible(false);
        Cordinator5.setVisible(false);
        Cordinator6.setVisible(false);
        TL3.setVisible(false);
        TL4.setVisible(false);
        TL5.setVisible(false);
        TL6.setVisible(false);
      }
      // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Teacher Information 6">
public void TecherComboData6(){
    try{   String Ssql="SELECT tname from teachersinfo";
        pStatement=con.prepareStatement(Ssql);
        rs=pStatement.executeQuery(Ssql);
          while(rs.next()){

            String teachercode11=rs.getString("tname");
            teacherCombo1.addItem(teachercode11);
            teacherCombo2.addItem(teachercode11);
            teacherCombo3.addItem(teachercode11);
            teacherCombo4.addItem(teachercode11);
            teacherCombo5.addItem(teachercode11);
            teacherCombo6.addItem(teachercode11);

            AutoCompleteDecorator.decorate(teacherCombo1);
            AutoCompleteDecorator.decorate(teacherCombo2);
            AutoCompleteDecorator.decorate(teacherCombo3);
            AutoCompleteDecorator.decorate(teacherCombo4);
            AutoCompleteDecorator.decorate(teacherCombo5);
            AutoCompleteDecorator.decorate(teacherCombo6);
             }
        }
        catch(Exception excep){
                JOptionPane.showMessageDialog(null, "Error: "+excep);
        }
}
 // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Teacher Information 5 ">
public void TecherComboData5(){
    try{   String Ssql="SELECT tname from teachersinfo";
        pStatement=con.prepareStatement(Ssql);
        rs=pStatement.executeQuery(Ssql);
          while(rs.next()){

            String teachercode11=rs.getString("tname");
            teacherCombo1.addItem(teachercode11);
            teacherCombo2.addItem(teachercode11);
            teacherCombo3.addItem(teachercode11);
            teacherCombo4.addItem(teachercode11);
            teacherCombo5.addItem(teachercode11);

            AutoCompleteDecorator.decorate(teacherCombo1);
            AutoCompleteDecorator.decorate(teacherCombo2);
            AutoCompleteDecorator.decorate(teacherCombo3);
            AutoCompleteDecorator.decorate(teacherCombo4);
            AutoCompleteDecorator.decorate(teacherCombo5);
             }
        }
        catch(Exception excep){
                JOptionPane.showMessageDialog(null, "Error: "+excep);
        }
}
 // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Teacher Information Method">
public void TecherComboData3(){
    try{   String Ssql="SELECT tname from teachersinfo";
        pStatement=con.prepareStatement(Ssql);
        rs=pStatement.executeQuery(Ssql);
          while(rs.next()){

            String teachercode11=rs.getString("tname");
            teacherCombo1.addItem(teachercode11);
            teacherCombo2.addItem(teachercode11);
            AutoCompleteDecorator.decorate(teacherCombo1);
            AutoCompleteDecorator.decorate(teacherCombo2);
            }
        }
        catch(Exception excep){
                JOptionPane.showMessageDialog(null, "Error: "+excep);
        }
}
 // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Subject Information Method">
public void SemiCheck(){
     try {
           pStatement=con.prepareStatement("SELECT * FROM batchinfo WHERE semester <=12");
           rs=pStatement.executeQuery();
           while(rs.next()){
               batchNumber=rs.getString("batch");
              Total=Total+1;  // Here calculate the total Batch found on Database
              bAtch.add(batchNumber);
             String batchFlag=rs.getString("flag");
             FlagType.add(batchFlag);

           }
       } catch (Exception exx) {
           JOptionPane.showMessageDialog(null, "Error "+exx);
       }
}
// </editor-fold>



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CordinatorButtonGroup = new javax.swing.ButtonGroup();
        SemesterNumberPanel = new javax.swing.JPanel();
        CourseDmenu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BatchinfoPanel = new javax.swing.JPanel();
        BatchLbl = new javax.swing.JLabel();
        codeLabel1 = new javax.swing.JLabel();
        codeLabel2 = new javax.swing.JLabel();
        codeLabel3 = new javax.swing.JLabel();
        codeLabel4 = new javax.swing.JLabel();
        codeLabel5 = new javax.swing.JLabel();
        codeLabel6 = new javax.swing.JLabel();
        teacherCombo1 = new javax.swing.JComboBox<>();
        teacherCombo2 = new javax.swing.JComboBox<>();
        teacherCombo3 = new javax.swing.JComboBox<>();
        teacherCombo4 = new javax.swing.JComboBox<>();
        teacherCombo5 = new javax.swing.JComboBox<>();
        teacherCombo6 = new javax.swing.JComboBox<>();
        SubLabel1 = new javax.swing.JLabel();
        SubLabel3 = new javax.swing.JLabel();
        SubLabel4 = new javax.swing.JLabel();
        SubLabel5 = new javax.swing.JLabel();
        SubLabel6 = new javax.swing.JLabel();
        SaveFinalButton = new javax.swing.JButton();
        setSemesterNumber = new javax.swing.JLabel();
        roomSelect = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        Cordinator2 = new javax.swing.JCheckBox();
        Cordinator1 = new javax.swing.JCheckBox();
        Cordinator4 = new javax.swing.JCheckBox();
        Cordinator3 = new javax.swing.JCheckBox();
        Cordinator6 = new javax.swing.JCheckBox();
        Cordinator5 = new javax.swing.JCheckBox();
        SelectedBatchLbl = new javax.swing.JLabel();
        CheckBoxPanel = new javax.swing.JPanel();
        SetNotification = new javax.swing.JLabel();
        SaveAsDraftBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        SelectedBatchLbl1 = new javax.swing.JLabel();
        loadLbl = new javax.swing.JLabel();
        code = new javax.swing.JLabel();
        coursename = new javax.swing.JLabel();
        courseteacher = new javax.swing.JLabel();
        code1 = new javax.swing.JLabel();
        code2 = new javax.swing.JLabel();
        TL1 = new javax.swing.JLabel();
        TL3 = new javax.swing.JLabel();
        TL2 = new javax.swing.JLabel();
        TL4 = new javax.swing.JLabel();
        TL5 = new javax.swing.JLabel();
        TL6 = new javax.swing.JLabel();
        SubLabel2 = new javax.swing.JLabel();
        code3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Course Distribution");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SemesterNumberPanel.setBackground(new java.awt.Color(0, 69, 79));
        SemesterNumberPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CourseDmenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cd_120px.png"))); // NOI18N
        SemesterNumberPanel.add(CourseDmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 130, 140));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Course Distribution");
        SemesterNumberPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 340, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Dhaka International University");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SemesterNumberPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 710, 50));

        BatchinfoPanel.setOpaque(false);
        SemesterNumberPanel.add(BatchinfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 340, 390));

        BatchLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BatchLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BatchLbl.setToolTipText("Selected Batch");
        BatchLbl.setOpaque(true);
        SemesterNumberPanel.add(BatchLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 60, 40));

        codeLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codeLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        codeLabel1.setOpaque(true);
        SemesterNumberPanel.add(codeLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 250, 90, 40));

        codeLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codeLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        codeLabel2.setOpaque(true);
        SemesterNumberPanel.add(codeLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, 90, 40));

        codeLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codeLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        codeLabel3.setOpaque(true);
        SemesterNumberPanel.add(codeLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, 90, 40));

        codeLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codeLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        codeLabel4.setOpaque(true);
        SemesterNumberPanel.add(codeLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, 90, 40));

        codeLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codeLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        codeLabel5.setOpaque(true);
        SemesterNumberPanel.add(codeLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, 90, 40));

        codeLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codeLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        codeLabel6.setOpaque(true);
        SemesterNumberPanel.add(codeLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 500, 90, 40));

        teacherCombo1.setEditable(true);
        teacherCombo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherCombo1ActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(teacherCombo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 250, 300, 40));

        teacherCombo2.setEditable(true);
        teacherCombo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherCombo2ActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(teacherCombo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 300, 300, 40));

        teacherCombo3.setEditable(true);
        teacherCombo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherCombo3ActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(teacherCombo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 350, 300, 40));

        teacherCombo4.setEditable(true);
        teacherCombo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherCombo4ActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(teacherCombo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 400, 300, 40));

        teacherCombo5.setEditable(true);
        teacherCombo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherCombo5ActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(teacherCombo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 450, 300, 40));

        teacherCombo6.setEditable(true);
        teacherCombo6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherCombo6ActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(teacherCombo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 500, 300, 40));

        SubLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SubLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        SubLabel1.setOpaque(true);
        SemesterNumberPanel.add(SubLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, 260, 40));

        SubLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SubLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        SubLabel3.setOpaque(true);
        SemesterNumberPanel.add(SubLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 350, 260, 40));

        SubLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SubLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        SubLabel4.setOpaque(true);
        SemesterNumberPanel.add(SubLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 400, 260, 40));

        SubLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SubLabel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        SubLabel5.setOpaque(true);
        SemesterNumberPanel.add(SubLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 450, 260, 40));

        SubLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SubLabel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        SubLabel6.setOpaque(true);
        SemesterNumberPanel.add(SubLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 500, 260, 40));

        SaveFinalButton.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        SaveFinalButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save_close_48px.png"))); // NOI18N
        SaveFinalButton.setText("Save as Final");
        SaveFinalButton.setToolTipText("After clicking this, you can change values until create new routine.");
        SaveFinalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveFinalButtonActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(SaveFinalButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 560, 210, 50));

        setSemesterNumber.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        setSemesterNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setSemesterNumber.setToolTipText("Current Semester of the Selected Batch");
        setSemesterNumber.setOpaque(true);
        SemesterNumberPanel.add(setSemesterNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 140, 60, 40));

        roomSelect.setEditable(true);
        roomSelect.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        SemesterNumberPanel.add(roomSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 140, 130, 40));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Select Room:");
        SemesterNumberPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 130, 40));

        CordinatorButtonGroup.add(Cordinator2);
        Cordinator2.setToolTipText("Tick here to set this teacher as coordinator of the selected batch.");
        Cordinator2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cordinator2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cordinator2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cordinator2ActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(Cordinator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 310, 25, 25));

        CordinatorButtonGroup.add(Cordinator1);
        Cordinator1.setToolTipText("Tick here to set this teacher as coordinator of the selected batch.");
        Cordinator1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cordinator1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cordinator1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cordinator1ActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(Cordinator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 260, 25, 25));

        CordinatorButtonGroup.add(Cordinator4);
        Cordinator4.setToolTipText("Tick here to set this teacher as coordinator of the selected batch.");
        Cordinator4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cordinator4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cordinator4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cordinator4ActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(Cordinator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 410, 25, 25));

        CordinatorButtonGroup.add(Cordinator3);
        Cordinator3.setToolTipText("Tick here to set this teacher as coordinator of the selected batch.");
        Cordinator3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cordinator3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cordinator3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cordinator3ActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(Cordinator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 360, 25, 25));

        CordinatorButtonGroup.add(Cordinator6);
        Cordinator6.setToolTipText("Tick here to set this teacher as coordinator of the selected batch.");
        Cordinator6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cordinator6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cordinator6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cordinator6ActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(Cordinator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 510, 25, 25));

        CordinatorButtonGroup.add(Cordinator5);
        Cordinator5.setToolTipText("Tick here to set this teacher as coordinator of the selected batch.");
        Cordinator5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cordinator5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cordinator5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cordinator5ActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(Cordinator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 460, 25, 25));

        SelectedBatchLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        SelectedBatchLbl.setForeground(new java.awt.Color(255, 255, 255));
        SelectedBatchLbl.setText("Batch No.:");
        SemesterNumberPanel.add(SelectedBatchLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 90, 40));

        CheckBoxPanel.setBackground(new java.awt.Color(51, 51, 255));
        CheckBoxPanel.setToolTipText("Select Course Coordinator");
        CheckBoxPanel.setOpaque(false);
        SemesterNumberPanel.add(CheckBoxPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 220, -1, 290));

        SetNotification.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        SetNotification.setForeground(new java.awt.Color(255, 0, 0));
        SetNotification.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SemesterNumberPanel.add(SetNotification, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 340, 30));

        SaveAsDraftBtn.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        SaveAsDraftBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save_as_final_48px.png"))); // NOI18N
        SaveAsDraftBtn.setText("Save as Draft");
        SaveAsDraftBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAsDraftBtnActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(SaveAsDraftBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 560, 210, 50));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        jButton1.setToolTipText("Go to Home");
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        SemesterNumberPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 40, 40));

        SelectedBatchLbl1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        SelectedBatchLbl1.setForeground(new java.awt.Color(255, 255, 255));
        SelectedBatchLbl1.setText("Semester No.:");
        SemesterNumberPanel.add(SelectedBatchLbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, 120, 40));

        loadLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loadLbl.setForeground(new java.awt.Color(255, 255, 255));
        loadLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loadLbl.setText("Load");
        loadLbl.setToolTipText("Current Class Load");
        SemesterNumberPanel.add(loadLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 210, 90, 40));

        code.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        code.setForeground(new java.awt.Color(255, 255, 255));
        code.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        code.setText("Code");
        SemesterNumberPanel.add(code, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 90, 40));

        coursename.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        coursename.setForeground(new java.awt.Color(255, 255, 255));
        coursename.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coursename.setText("Course Name");
        SemesterNumberPanel.add(coursename, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 260, 40));

        courseteacher.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        courseteacher.setForeground(new java.awt.Color(255, 255, 255));
        courseteacher.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        courseteacher.setText("Assign Teacher(Tick for Coordinator)");
        SemesterNumberPanel.add(courseteacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 210, 300, 40));

        code1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        code1.setForeground(new java.awt.Color(255, 255, 255));
        code1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SemesterNumberPanel.add(code1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 1340, 0));

        code2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        code2.setForeground(new java.awt.Color(255, 255, 255));
        code2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        code2.setText("Automatic Routine Generation");
        SemesterNumberPanel.add(code2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 340, 20));

        TL1.setForeground(new java.awt.Color(255, 255, 255));
        SemesterNumberPanel.add(TL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 250, 40, 40));

        TL3.setForeground(new java.awt.Color(255, 255, 255));
        SemesterNumberPanel.add(TL3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 350, 40, 40));

        TL2.setForeground(new java.awt.Color(255, 255, 255));
        SemesterNumberPanel.add(TL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 300, 40, 40));

        TL4.setForeground(new java.awt.Color(255, 255, 255));
        SemesterNumberPanel.add(TL4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 400, 40, 40));

        TL5.setForeground(new java.awt.Color(255, 255, 255));
        SemesterNumberPanel.add(TL5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 450, 40, 40));

        TL6.setForeground(new java.awt.Color(255, 255, 255));
        SemesterNumberPanel.add(TL6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 500, 40, 40));

        SubLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SubLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        SubLabel2.setOpaque(true);
        SemesterNumberPanel.add(SubLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 260, 40));

        code3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        code3.setForeground(new java.awt.Color(255, 255, 255));
        code3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        code3.setText("Department of CSE | Faculty of Science & Engineering");
        SemesterNumberPanel.add(code3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 410, 30));

        getContentPane().add(SemesterNumberPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 660));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


// <editor-fold defaultstate="collapsed" desc="Save Final Button Action">
    private void SaveFinalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveFinalButtonActionPerformed

     if(null==CordinatorButtonGroup.getSelection()){
       JOptionPane.showMessageDialog(this, "Please Select Coordinator");
     }


      else{
          if(subNo==3){
              UpdateRoom();
             Course1UPdate();
             Course2Update();
             FlagUpdate();            
       CourseCordinatorUpdate();
                    }
          else  if(subNo==6){
                    UpdateRoom();
                    Course1UPdate();
                    Course2Update();
                    Course3Update();
                    Course4Update();
                    Course5Update();
                    Course6Update();
                    FlagUpdate();
                    CourseCordinatorUpdate();
                    }

          else  if(subNo==5){
                    UpdateRoom();
                     Course1UPdate();
                    Course2Update();
                    Course3Update();
                    Course4Update();
                    Course5Update();
                    FlagUpdate();
                    CourseCordinatorUpdate();
                  }

     }
       GetFlagStatus();
        SetbuttonColor();
        subNo=0;

    }//GEN-LAST:event_SaveFinalButtonActionPerformed
// </editor-fold>
    private void Cordinator1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cordinator1ActionPerformed
       courseCoordinator=teacherCombo1.getSelectedItem().toString();
    }//GEN-LAST:event_Cordinator1ActionPerformed

    private void Cordinator2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cordinator2ActionPerformed
         courseCoordinator=teacherCombo2.getSelectedItem().toString();

    }//GEN-LAST:event_Cordinator2ActionPerformed

    private void Cordinator3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cordinator3ActionPerformed
         courseCoordinator=teacherCombo3.getSelectedItem().toString();

    }//GEN-LAST:event_Cordinator3ActionPerformed

    private void Cordinator4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cordinator4ActionPerformed
         courseCoordinator=teacherCombo4.getSelectedItem().toString();

    }//GEN-LAST:event_Cordinator4ActionPerformed

    private void Cordinator5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cordinator5ActionPerformed
        courseCoordinator=teacherCombo5.getSelectedItem().toString();

    }//GEN-LAST:event_Cordinator5ActionPerformed

    private void Cordinator6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cordinator6ActionPerformed
         courseCoordinator=teacherCombo6.getSelectedItem().toString();

    }//GEN-LAST:event_Cordinator6ActionPerformed

    private void SaveAsDraftBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAsDraftBtnActionPerformed
          System.out.println(subNo);

          if(subNo==3){
              UpdateRoom();
                        Course1UPdate();
                        Course2Update();
                        FlagDraftUpdate();

                    }
          else  if(subNo==6){

                        UpdateRoom();
                         Course1UPdate();
                         Course2Update();
                         Course3Update();
                         Course4Update();
                         Course5Update();
                         Course6Update();
                        FlagDraftUpdate();

                    }

          else  if(subNo==5){
                UpdateRoom();
                Course1UPdate();
                Course2Update();
                Course3Update();
                Course4Update();
                Course5Update();
                FlagDraftUpdate();

                  }
        GetFlagStatus();
        SetbuttonColor();

        subNo=0;
    }//GEN-LAST:event_SaveAsDraftBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Home hm=new Home(Role);
        hm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void teacherCombo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherCombo1ActionPerformed
       Object tc1=evt.getSource();

       if(tc1==teacherCombo1){
        try{
             int t1=0;
         for(int i=1;i<=5;i++){
              pStatement=con.prepareStatement("SELECT * FROM courseinfo WHERE batch_group"+i+"=?");
               pStatement.setString(1,(String)teacherCombo1.getSelectedItem());
                        rs=pStatement.executeQuery();
                        if(rs.next()){
                        t1+=rs.getInt("credit");

                        }
         }
              TL1.setText(String.valueOf(t1));

        }catch(Exception exc){
            System.out.println("Error"+exc);
        }

       }

    }//GEN-LAST:event_teacherCombo1ActionPerformed

    private void teacherCombo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherCombo2ActionPerformed
        Object tc2=evt.getSource();

       if(tc2==teacherCombo2){
        try{
             int t2=0;
         for(int i=1;i<=5;i++){
              pStatement=con.prepareStatement("SELECT * FROM courseinfo WHERE batch_group"+i+"=?");
               pStatement.setString(1,(String)teacherCombo2.getSelectedItem());
                        rs=pStatement.executeQuery();
                        if(rs.next()){
                        t2+=rs.getInt("credit");

                        }
         }
              TL2.setText(String.valueOf(t2));

        }catch(Exception exc){
            System.out.println("Error"+exc);
        }

       }
    }//GEN-LAST:event_teacherCombo2ActionPerformed

    private void teacherCombo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherCombo3ActionPerformed
         Object tc3=evt.getSource();

       if(tc3==teacherCombo3){
        try{
             int t3=0;
         for(int i=1;i<=5;i++){
              pStatement=con.prepareStatement("SELECT * FROM courseinfo WHERE batch_group"+i+"=?");
               pStatement.setString(1,(String)teacherCombo3.getSelectedItem());
                        rs=pStatement.executeQuery();
                        if(rs.next()){
                        t3+=rs.getInt("credit");

                        }
         }
              TL3.setText(String.valueOf(t3));

        }catch(Exception exc){
            System.out.println("Error"+exc);
        }

       }
    }//GEN-LAST:event_teacherCombo3ActionPerformed

    private void teacherCombo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherCombo4ActionPerformed
          Object tc4=evt.getSource();

       if(tc4==teacherCombo4){
        try{
             int t4=0;
         for(int i=1;i<=5;i++){
              pStatement=con.prepareStatement("SELECT * FROM courseinfo WHERE batch_group"+i+"=?");
               pStatement.setString(1,(String)teacherCombo4.getSelectedItem());
                        rs=pStatement.executeQuery();
                        if(rs.next()){
                        t4+=rs.getInt("credit");

                        }
         }
              TL4.setText(String.valueOf(t4));

        }catch(Exception exc){
            System.out.println("Error"+exc);
        }

       }
    }//GEN-LAST:event_teacherCombo4ActionPerformed

    private void teacherCombo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherCombo5ActionPerformed
        Object tc5=evt.getSource();

       if(tc5==teacherCombo5){
        try{
             int t5=0;
         for(int i=1;i<=5;i++){
              pStatement=con.prepareStatement("SELECT * FROM courseinfo WHERE batch_group"+i+"=?");
               pStatement.setString(1,(String)teacherCombo5.getSelectedItem());
                        rs=pStatement.executeQuery();
                        if(rs.next()){
                        t5+=rs.getInt("credit");

                        }
         }
              TL5.setText(String.valueOf(t5));

        }catch(Exception exc){
            System.out.println("Error"+exc);
        }

       }
    }//GEN-LAST:event_teacherCombo5ActionPerformed

    private void teacherCombo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherCombo6ActionPerformed
        Object tc6=evt.getSource();

       if(tc6==teacherCombo6){
        try{
             int t6=0;
         for(int i=1;i<=5;i++){
              pStatement=con.prepareStatement("SELECT * FROM courseinfo WHERE batch_group"+i+"=?");
               pStatement.setString(1,(String)teacherCombo6.getSelectedItem());
                        rs=pStatement.executeQuery();
                        if(rs.next()){
                        t6+=rs.getInt("credit");

                        }
         }
              TL6.setText(String.valueOf(t6));

        }catch(Exception exc){
            System.out.println("Error"+exc);
        }

       }
    }//GEN-LAST:event_teacherCombo6ActionPerformed



    public void RoomSelector(){
         try {
         pStatement=con.prepareStatement("SELECT room_no FROM `batchinfo`GROUP BY room_no HAVING COUNT(*)<3 ");
           rs=pStatement.executeQuery();
           while(rs.next()){
             String roomNO=rs.getString("room_no");
             roomSelect.addItem(roomNO);
           }

    }catch(Exception rs){
        System.out.println("Error"+rs);
       }
    }

  public void CourseCordinatorUpdate(){
   
      try{
                          String update=("UPDATE batchinfo SET course_coordinator=? WHERE batch=?");
                        pStatement=con.prepareStatement(update);
                        pStatement.setString(1,courseCoordinator);
                        pStatement.setString(2, BatchLbl.getText());
                        pStatement.executeUpdate();

                    }

                        catch(Exception excp){
                            SetNotification.setText("Not Saved!"+excp);

                        }
  
    }
    public void UpdateRoom(){
     try{
                          String update=("UPDATE batchinfo SET room_no=? WHERE batch=?");
                        pStatement=con.prepareStatement(update);
                        pStatement.setString(1,roomSelect.getSelectedItem().toString());
                        pStatement.setString(2,BatchLbl.getText());
                        pStatement.executeUpdate();

                    }

                        catch(Exception excp){
                            SetNotification.setText("Not Saved!"+excp);

                        }
         }

    public void GetFlagStatus(){
         try {
              FlagType.clear();
           pStatement=con.prepareStatement("SELECT * FROM batchinfo WHERE semester <=12");
           rs=pStatement.executeQuery();
           while(rs.next()){
             String batchFlag=rs.getString("flag");
             FlagType.add(batchFlag);

           }
       } catch (Exception exx) {
           JOptionPane.showMessageDialog(null, "Error "+exx);
       }
    }

    //button color set method
public void SetbuttonColor(){
      for( i=0; i <Total; i++) {

                          if ("done".equals(FlagType.get(i))){
                            buttons[i].setBackground(new java.awt.Color(154,205,50));  //green
                         }


                         if ("draft".equals(FlagType.get(i))){
                              buttons[i].setBackground(new java.awt.Color(189,183,107));  //DarkKhaki
                         }

                           if(FlagType.get(i)==null){
                            buttons[i].setBackground(new java.awt.Color(153,25,0)); //red
                           }
                       }
}

    /**
     * @param args the command line arguments
     */

 public void Course1UPdate(){
         try{

                        String updateMessage2= "UPDATE courseinfo SET "+batchGroup+"=? WHERE course_code=?";
                        pStatement=con.prepareStatement(updateMessage2);
                        pStatement.setString(1,teacherCombo1.getSelectedItem().toString());
                        pStatement.setString(2,codeLabel1.getText());
                        pStatement.executeUpdate();

                    }

                        catch(Exception excp){
                            SetNotification.setText("Not Saved!"+excp);

                        }
    }
    public void Course2Update(){
         try{

                        String updateMessage2= "UPDATE courseinfo SET "+batchGroup+"=? WHERE course_code=?";
                        pStatement=con.prepareStatement(updateMessage2);
                        pStatement.setString(1,teacherCombo2.getSelectedItem().toString());
                        pStatement.setString(2,codeLabel2.getText());
                        pStatement.executeUpdate();
                       SetNotification.setText("Saved");
                    }

                        catch(Exception excp){
                            SetNotification.setText("Not Saved!"+excp);

                        }
    }

    public void Course3Update(){

                      try{

                        String updateMessage2= "UPDATE courseinfo SET "+batchGroup+"=? WHERE course_code=?";
                        pStatement=con.prepareStatement(updateMessage2);
                        pStatement.setString(1,teacherCombo3.getSelectedItem().toString());
                        pStatement.setString(2,codeLabel3.getText());
                        pStatement.executeUpdate();

                    }

                        catch(Exception excp){
                            SetNotification.setText("Not Saved!"+excp);

                        }
    }
    public void Course4Update(){
        try{

                        String updateMessage2= "UPDATE courseinfo SET "+batchGroup+"=? WHERE course_code=?";
                        pStatement=con.prepareStatement(updateMessage2);
                        pStatement.setString(1,teacherCombo4.getSelectedItem().toString());
                        pStatement.setString(2,codeLabel4.getText());
                        pStatement.executeUpdate();

                    }

                        catch(Exception excp){
                            SetNotification.setText("Not Saved!"+excp);

                        }
    }
    public void Course5Update(){
        try{

                        String updateMessage2= "UPDATE courseinfo SET "+batchGroup+"=? WHERE course_code=?";
                        pStatement=con.prepareStatement(updateMessage2);
                        pStatement.setString(1,teacherCombo5.getSelectedItem().toString());
                        pStatement.setString(2,codeLabel5.getText());
                        pStatement.executeUpdate();

                    }

                        catch(Exception excp){
                            SetNotification.setText("Not Saved!"+excp);

                        }
    }
    public void Course6Update(){
        try{

                        String updateMessage2= "UPDATE courseinfo SET "+batchGroup+"=? WHERE course_code=?";
                        pStatement=con.prepareStatement(updateMessage2);
                        pStatement.setString(1,teacherCombo6.getSelectedItem().toString());
                        pStatement.setString(2,codeLabel6.getText());
                        pStatement.executeUpdate();

                    }

                        catch(Exception excp){
                            SetNotification.setText("Not Saved!"+excp);

                        }
    }
    public void FlagDraftUpdate(){
        try{

                        String updateMessage2= "UPDATE batchinfo SET flag=? WHERE batch=?";
                        pStatement=con.prepareStatement(updateMessage2);
                        pStatement.setString(1,"draft");
                        pStatement.setString(2,selectedBatchNo);
                        pStatement.executeUpdate();
                       SetNotification.setText("Saved");
                    }

                        catch(Exception excp){
                            SetNotification.setText("Not Saved!"+excp);

                        }
    }
    public void FlagUpdate(){
          try{

                        String updateMessage2= "UPDATE batchinfo SET flag=? WHERE batch=?";
                        pStatement=con.prepareStatement(updateMessage2);
                        pStatement.setString(1,"done");
                        pStatement.setString(2,selectedBatchNo);
                        pStatement.executeUpdate();
                       SetNotification.setText("Saved");
                    }

                        catch(Exception excp){
                            SetNotification.setText("Not Saved!"+excp);

                        }
    }

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
            java.util.logging.Logger.getLogger(CourseDistribution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CourseDistribution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CourseDistribution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CourseDistribution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CourseDistribution().setVisible(true);

            }
        });



    }
// <editor-fold defaultstate="collapsed" desc="Varibale Declaration">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BatchLbl;
    private javax.swing.JPanel BatchinfoPanel;
    private javax.swing.JPanel CheckBoxPanel;
    private javax.swing.JCheckBox Cordinator1;
    private javax.swing.JCheckBox Cordinator2;
    private javax.swing.JCheckBox Cordinator3;
    private javax.swing.JCheckBox Cordinator4;
    private javax.swing.JCheckBox Cordinator5;
    private javax.swing.JCheckBox Cordinator6;
    private javax.swing.ButtonGroup CordinatorButtonGroup;
    private javax.swing.JLabel CourseDmenu;
    private javax.swing.JButton SaveAsDraftBtn;
    private javax.swing.JButton SaveFinalButton;
    private javax.swing.JLabel SelectedBatchLbl;
    private javax.swing.JLabel SelectedBatchLbl1;
    private javax.swing.JPanel SemesterNumberPanel;
    private javax.swing.JLabel SetNotification;
    private javax.swing.JLabel SubLabel1;
    private javax.swing.JLabel SubLabel2;
    private javax.swing.JLabel SubLabel3;
    private javax.swing.JLabel SubLabel4;
    private javax.swing.JLabel SubLabel5;
    private javax.swing.JLabel SubLabel6;
    private javax.swing.JLabel TL1;
    private javax.swing.JLabel TL2;
    private javax.swing.JLabel TL3;
    private javax.swing.JLabel TL4;
    private javax.swing.JLabel TL5;
    private javax.swing.JLabel TL6;
    private javax.swing.JLabel code;
    private javax.swing.JLabel code1;
    private javax.swing.JLabel code2;
    private javax.swing.JLabel code3;
    private javax.swing.JLabel codeLabel1;
    private javax.swing.JLabel codeLabel2;
    private javax.swing.JLabel codeLabel3;
    private javax.swing.JLabel codeLabel4;
    private javax.swing.JLabel codeLabel5;
    private javax.swing.JLabel codeLabel6;
    private javax.swing.JLabel coursename;
    private javax.swing.JLabel courseteacher;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel loadLbl;
    private javax.swing.JComboBox<String> roomSelect;
    private javax.swing.JLabel setSemesterNumber;
    private javax.swing.JComboBox<String> teacherCombo1;
    private javax.swing.JComboBox<String> teacherCombo2;
    private javax.swing.JComboBox<String> teacherCombo3;
    private javax.swing.JComboBox<String> teacherCombo4;
    private javax.swing.JComboBox<String> teacherCombo5;
    private javax.swing.JComboBox<String> teacherCombo6;
    // End of variables declaration//GEN-END:variables
 // </editor-fold>
}
