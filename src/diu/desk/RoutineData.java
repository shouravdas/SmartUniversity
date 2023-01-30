/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diu.desk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

 
public class RoutineData {
    
    
    short[][] room = new short[40][8];             // [room1-40]     [day1-7]                 //to avoid room confliction
    short[][] labroom = new short[20][8];             // [room1-20]     [day1-7]               //to avoid lab room confliction
    short[][][] teacher = new short[40][8][6];     // [teacher1-40]  [day1-7] [period 1-5]    //to avoid teacher confliction
    String[][][] period = new String[40][8][6];    // [batch1-40]    [day1-7] [period 1-5]    
    short[][] classDay = new short[40][8];         // [batch1-40]    [day1-7]                  //to avoid classDay confliction. 
    String[][] secondRoom = new String[40][8];     // [batch1-40]    [day1-7]                  //if a classday fall in another room    
   //use temp instead rewrite prev value .
    
     Connection con = null;
     Statement statement = null;
     PreparedStatement pStatement = null;
     ResultSet rs = null;
     public RoutineData(){
          con = ConnectionFactory.getMysqlConnection();
     }
     //MEHEDI//MEHEDI//MEHEDI//MEHEDI//MEHEDI//MEHEDI//MEHEDI//MEHEDI//MEHEDI//MEHEDI//MEHEDI//MEHEDI//MEHEDI//MEHEDI//MEHEDI
    ArrayList<String> roomofthisBatch=new ArrayList();
    ArrayList<String> roomList=new ArrayList();
    ArrayList<String> labroomList=new ArrayList();
    ArrayList<String> batchList=new ArrayList();
    ArrayList<String> batchGroupList=new ArrayList();
    ArrayList<String> semesterList=new ArrayList();
    ArrayList<String> courseCodeList=new ArrayList();  
    ArrayList<String> labCodeList=new ArrayList(); 

    
    
        public void getRoomList(){
         try {
           
           pStatement=con.prepareStatement("SELECT roomNo FROM room WHERE roomType='Theory'");
           rs=pStatement.executeQuery();
           while(rs.next()){
                String rm=rs.getString("roomNo");
                roomList.add(rm);               
           }
           
           pStatement=con.prepareStatement("SELECT roomNo FROM room WHERE roomType='Lab'");
           rs=pStatement.executeQuery();
           while(rs.next()){
                String lrm=rs.getString("roomNo");
                labroomList.add(lrm);               
           }
           
           
           
       } catch (Exception exx) {
           JOptionPane.showMessageDialog(null, exx);
       }
    }
     
        
                 public int getRoomIndex(String batch){
         try {
           pStatement=con.prepareStatement("SELECT roomID FROM room,batchinfo WHERE batchinfo.room_no=room.roomNo and batchinfo.batch=?");
           pStatement.setString(1, batch); 
           rs=pStatement.executeQuery();
           while(rs.next()){
                int rm=rs.getInt("roomID");
                System.out.println("Room id: "+rm);
                return rm;
                
           }
       } catch (SQLException exx) {
           JOptionPane.showMessageDialog(null, exx);
       }
        return 0;
    }
     
        
        public void getBatchRelatedInfo(){
         try {
           
           pStatement=con.prepareStatement("SELECT * FROM batchinfo"); // add order by
           rs=pStatement.executeQuery();
           while(rs.next()){
                 //  String batchno=rs.getString("batch");   //this method needs an extra variable, why need it?? bogus memory waste!
                batchList.add(rs.getString("batch"));
                batchGroupList.add(rs.getString("s_group"));
                semesterList.add(rs.getString("semester"));
                roomofthisBatch.add(rs.getString("room_no"));
           }
       } catch (Exception exx) {
           JOptionPane.showMessageDialog(null, exx);
       }
    }
     
        
        
           public void getCourseCodeList(String semester){
        try{
            
            courseCodeList.clear();
            labCodeList.clear();
            
            pStatement=con.prepareStatement("SELECT course_code FROM courseinfo WHERE semester=? and credit=3");
             pStatement.setString(1,semester);
             rs=pStatement.executeQuery();
              while(rs.next()){
                 String  courseCode=rs.getString("course_code");
                  courseCodeList.add(courseCode);
              }
              
              
              pStatement=con.prepareStatement("SELECT course_code FROM courseinfo WHERE semester=? and credit=1");
             pStatement.setString(1,semester);
             rs=pStatement.executeQuery();
              while(rs.next()){
                 String  labCode=rs.getString("course_code");
                  labCodeList.add(labCode);
              }
              
              
            }
            catch(Exception Scode){
                JOptionPane.showMessageDialog(null, Scode);
            } 
    }
     
     //SHOURAV//SHOURAV//SHOURAV//SHOURAV//SHOURAV//SHOURAV//SHOURAV//SHOURAV//SHOURAV//SHOURAV//SHOURAV//SHOURAV//SHOURAV
    
    ArrayList<String> teachersList=new ArrayList();

    ArrayList<String> BatchteachersList=new ArrayList();
    ArrayList<String> engagedteachersList=new ArrayList();
    
    
    
    
    
 
  
 
    
    public void getTeachersData(){
         try{
            pStatement=con.prepareStatement("SELECT * FROM teachersinfo");
             rs=pStatement.executeQuery();
              while(rs.next()){
                String  teacher=rs.getString("tname");
                  teachersList.add(teacher);
                
              }
               
            }
            catch(Exception td){
                JOptionPane.showMessageDialog(null, td);
            }
    }
    
    
    public void getBatchTeachersInfo(){
        for (int i=0;i<batchGroupList.size();i++){
            String bG=batchGroupList.get(i); // bs Stands For Batch Group where Batch Group Contains of Assigend Course Teacher List in Course Info Table 
            String sem=(String)semesterList.get(i); 
            for(int j=0;j<courseCodeList.size();j++){
            String sCode=(String)courseCodeList.get(i); 
            try{
          pStatement=con.prepareStatement("SELECT * FROM courseinfo WHERE '"+bG+"'=?"+" and course_code=? and semester=?");
          pStatement.setString(1,bG);          
          pStatement.setString(2,sCode);
          pStatement.setString(3,sem);
           rs=pStatement.executeQuery();
           while(rs.next()){
               
                 String batchTeacherName=rs.getString(bG);
                 BatchteachersList.add(batchTeacherName);
                 
              }
               
            }
        
            catch(Exception btl){
                JOptionPane.showMessageDialog(null, btl);
            }
            }
        }
    }
    
 
    
}
