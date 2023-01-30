 
package diu.desk;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import static java.awt.Component.TOP_ALIGNMENT;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.HorizontalDirection;
import javax.swing.JOptionPane;

 
public class RoutineExport extends GenRoutine {
  
     Connection con = null;
     Statement statement = null;
     PreparedStatement pStatement = null;
     ResultSet rs = null;
     private static final DateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
     private static final DateFormat onlyDate = new SimpleDateFormat("dd-MM-yyyy");
     Date date = new Date();
     String Location=System.getProperty("user.home")+"\\Desktop\\PDF\\";
     
     Document doc = new Document(PageSize.A4,40,40,30,30);
     Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
     Font small = FontFactory.getFont(FontFactory.defaultEncoding, 11, Font.NORMAL, BaseColor.BLACK);
     Font title = FontFactory.getFont(FontFactory.defaultEncoding, 14, Font.NORMAL, BaseColor.DARK_GRAY);
     Font underlined = FontFactory.getFont(FontFactory.defaultEncoding, 12, Font.UNDERLINE, BaseColor.BLACK);
      
     Font head = FontFactory.getFont(FontFactory.TIMES_BOLD, 20, BaseColor.BLACK);
     Font foot = FontFactory.getFont(FontFactory.defaultEncoding, 11, Font.NORMAL, BaseColor.DARK_GRAY);

    
     
     PdfPTable table;
     PdfPTable rTable;
     String batchName;
     ArrayList batchList=new ArrayList();
     String batchGroupNo;
     ArrayList batchGroupList=new ArrayList();
     String semesterNo;
     ArrayList semesterList =new ArrayList(); 
     String subjectName;
     ArrayList subjectList =new ArrayList();
     String subjectCode;
     ArrayList subjectCodeList=new ArrayList();
     String teachersName;
     ArrayList teachersList=new ArrayList();
     String tName;
     ArrayList<String> coordinator=new ArrayList<String>();
    
     
         public RoutineExport() {
       
        con = ConnectionFactory.getMysqlConnection();
        
        //  Get data from batch info to retrive data for use for Teachers load 
        try {
           
           pStatement=con.prepareStatement("SELECT * FROM batchinfo");
           rs=pStatement.executeQuery();
           while(rs.next()){
              batchName=rs.getString("batch");
                batchList.add(batchName);
                batchGroupNo=rs.getString("s_group");
                batchGroupList.add(batchGroupNo);
                semesterNo=rs.getString("semester");
                semesterList.add(semesterNo);
                coordinator.add(rs.getString("course_coordinator"));
               
           }
       } catch (Exception exx) {
           JOptionPane.showMessageDialog(null, "");
       }
        //  Get data from course info to retrive Course code sort by batch semester data for use for Teachers load 
        
       
            try{
            pStatement=con.prepareStatement("SELECT * FROM courseinfo");
             rs=pStatement.executeQuery();
              while(rs.next()){
                  subjectCode=rs.getString("course_code");
                  subjectCodeList.add(subjectCode);
                
              }
               
            }
            catch(Exception Scode){
                
            }
            //Get Teachers name for genearte Teachers load
            
             try{
            pStatement=con.prepareStatement("SELECT * FROM teachersinfo");
             rs=pStatement.executeQuery();
              while(rs.next()){
                  tName=rs.getString("tname");
                  teachersList.add(tName);
                
              }
               
            }
            catch(Exception Scode){
                
            }
                      
    }
     
  
         
    
    public static void main(String args[]) throws IOException {
    
    
    RoutineExport r= new RoutineExport();
    r.routineLogic();
    r.RoutinePDF();
    
    
    
    }
     
      public void RoutinePDF() throws IOException
      {
          
       
          
          
            File file=new File(Location);
            if(!file.exists())
            {
                file.mkdir();
            }
            try{ 
               
          PdfWriter writer = PdfWriter.getInstance(doc,new FileOutputStream(Location+"Class Routine - "+currentDate.format(date)+".pdf"));
         // PdfWriter writer = PdfWriter.getInstance(doc,new FileOutputStream(Desktop+"Class Routine.pdf"));

            doc.open();
            //structure of  course info table
            table=new PdfPTable(4);
            float [] colwidth=new float[]{5f,18f,40f,35f};
            table.setWidths(colwidth);
            table.setWidthPercentage(100);
            table.setKeepTogether(true);
         
            // table.setTotalWidth(520);   //for custom table size 
            // table.setLockedWidth(true);
       

            rTable= new PdfPTable(5);
            float [] colwidthR=new float[]{25f,25f,25f,25f,25f};
            rTable.setWidths(colwidthR);
            rTable.setWidthPercentage(100);
            rTable.setKeepTogether(true);
            
            }
            catch(Exception ec){ 
                JOptionPane.showMessageDialog(null, "Error: "+ec);
            }
            
       
            
            
       
            
      // once for each batch loop
       for(int i=0;i<batchList.size();i++){
       // for(int i=0;i<2;i++){     //for 2 batch dev. test time
        String bGroup=(String)batchGroupList.get(i);
           String sem=(String)semesterList.get(i); 
            System.out.println(batchList.get(i));
           // System.out.println(data);
    
        try {
            Paragraph p0=new Paragraph("Dhaka International University\n",head);
            Paragraph p00= new Paragraph("Faculty of Science & Engineering\n" 
                            +"Department of CSE(Evening Shift)\n"+"Batch: "+batchList.get(i)
                            +" | Semester: "+sem+"\n");
                    Chunk chunk = new Chunk("CLASS ROUTINE" );
                    chunk.setUnderline(TOP_ALIGNMENT, -4);
                    chunk.setBackground(BaseColor.LIGHT_GRAY, 0f, 0f, 0f, 2f);
                    
                    p00.add(chunk);
                     
           p0.setAlignment(Paragraph.ALIGN_CENTER);
           p00.setAlignment(Paragraph.ALIGN_CENTER);

             doc.add(p0);
             doc.add(p00);
            Chunk glue = new Chunk(new VerticalPositionMark());
            Paragraph p = new Paragraph("\n\nCourse Coordinator: "+coordinator.get(i));
            p.add(new Chunk(glue));
            p.add("Room: "+rd.roomofthisBatch.get(i)+"\n\n");
            doc.add(p); 
        
        }  catch (DocumentException ex) {
          JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
      
         // <editor-fold defaultstate="collapsed" desc="Generating Routine for each batch"> 
         
           // System.out.println("Semester: "+rd.semesterList.get(3));
           
              // System.out.println(Arrays.deepToString(rd.room)); 
            //column heading  
            Paragraph rp1= new Paragraph("Class Day");
            Paragraph rp2= new Paragraph("Time & Course Code");

            //setting allignment 
            PdfPCell rcell1 = new PdfPCell(rp1);   rcell1.setHorizontalAlignment(1);  
            PdfPCell rcell2 = new PdfPCell(rp2);   rcell2.setHorizontalAlignment(1); 
            rcell2.setColspan(4);
            
            rTable.addCell(rcell1);    
            rTable.addCell(rcell2);
            //column heading ends
           
            // String[][][] period = new String[40][8][6];    // [batch1-40]    [day1-7] [period 1-5]
            //  short[][] classDay = new short[40][8];         // [batch1-40]    [day1-7]
           for(int d=1;d<=7;d++)
            {
                    if(rd.classDay[i][d]!=0)
                        {
                             Paragraph p1 = null;
                             String secondR=null;
                             if(rd.secondRoom[i][d]!=null)
                                 secondR=rd.secondRoom[i][d]; 
                             else
                                 secondR=""; 
                             
                        switch(d){
                             case 1:   p1= new Paragraph("Tuesday\n"+secondR);    break;
                             case 2:   p1= new Paragraph("Wednesday\n"+secondR);  break;
                             case 3:   p1= new Paragraph("Thursday\n"+secondR);   break;
                             case 4:   p1= new Paragraph("Friday\n"+secondR);     break;
                             case 5:   p1= new Paragraph("Saturday\n"+secondR);   break;
                             case 6:   p1= new Paragraph("Sunday\n"+secondR);     break;
                             case 7:   p1= new Paragraph("Monday\n"+secondR);     break;
                             default:                                             break;
                        }
                        

  
                        Paragraph p2 =new Paragraph(" ");  
                        Paragraph p3=p2, p4=p2, p5=p2;
                        
                      if(rd.period[i][d][1]!=null)
                          p2= new Paragraph("6:00-6:50\n------------\n"+rd.period[i][d][1]);
                      if(rd.period[i][d][2]!=null)
                          p3= new Paragraph("6:50-7:40\n-------------\n"+rd.period[i][d][2]);
                      if(rd.period[i][d][3]!=null)
                          p4= new Paragraph("7:40-8:30\n-------------\n"+rd.period[i][d][3]); 
                      if(rd.period[i][d][4]!=null)
                          p5= new Paragraph("8:30-9:20\n-------------\n"+rd.period[i][d][4]); 
                        
                        //setting allignment and bg-color
                        PdfPCell cell1 = new PdfPCell(p1);   cell1.setHorizontalAlignment(1); 
                        PdfPCell cell2 = new PdfPCell(p2);   cell2.setHorizontalAlignment(1);  
                        PdfPCell cell3 = new PdfPCell(p3);   cell3.setHorizontalAlignment(1); 
                        PdfPCell cell4 = new PdfPCell(p4);   cell4.setHorizontalAlignment(1);  
                        PdfPCell cell5 = new PdfPCell(p5);   cell5.setHorizontalAlignment(1);  


                        rTable.addCell(cell1);    
                        rTable.addCell(cell2);
                        rTable.addCell(cell3);
                        rTable.addCell(cell4);  
                        rTable.addCell(cell5); 
                        }            
            
            }
            
             
         
          // </editor-fold>
        
         // <editor-fold defaultstate="collapsed" desc="Generating course list for each batch">        
        
            //column heading
            Paragraph p3= new Paragraph("Sl.");
            Paragraph p4= new Paragraph("Course Code");
            Paragraph p5= new Paragraph("Course Name");
            Paragraph p6= new Paragraph("Name of the Teacher"); 
 
            //setting allignment and bg-color
            PdfPCell cell1 = new PdfPCell(p3);   cell1.setHorizontalAlignment(1); cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell2 = new PdfPCell(p4);   cell2.setHorizontalAlignment(1); cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell3 = new PdfPCell(p5);   cell3.setHorizontalAlignment(1); cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPCell cell4 = new PdfPCell(p6);   cell4.setHorizontalAlignment(1); cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                
            
            
            table.addCell(cell1);    
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);  
            //column heading ends
             
            
            
            int sl=1;
           for(int k=0;k<subjectCodeList.size();k++){
               String sCode=(String)subjectCodeList.get(k);
               
               try{
           pStatement=con.prepareStatement("SELECT * FROM courseinfo WHERE '"+bGroup+"'=?"+" and course_code=? and semester=?");
          
          pStatement.setString(1,bGroup);          
          pStatement.setString(2,sCode);
          pStatement.setString(3,sem);
           rs=pStatement.executeQuery();
           while(rs.next()){
             PdfPCell cell5 = new PdfPCell(new Paragraph(String.valueOf(sl)));  cell5.setHorizontalAlignment(1);
              
            table.addCell(cell5);  
              PdfPCell cell6 = new PdfPCell(new Paragraph((String)subjectCodeList.get(k)));  cell6.setHorizontalAlignment(1); //subject code
               
            table.addCell(cell6);  
            
            table.addCell(rs.getString("course_title"));
            table.addCell(rs.getString(bGroup)); //teacher's name
            
            sl++;
            
             
           }
      
           }
                catch(SQLException excep){
                    System.out.println("Error");
           }
               
           }
           
             // </editor-fold>
           
         //<editor-fold defaultstate="collapsed" desc="Generating Counselling Hour Table">
         
         
         
          //</editor-fold>
          
         //<editor-fold defaultstate="collapsed" desc="Generating Mark Distribution Box">
            PdfPTable marks = new PdfPTable(1); 
            marks.setHorizontalAlignment(0);    //allign right
            marks.setWidthPercentage(40f);
            
            try{
            pStatement=con.prepareStatement("SELECT * FROM infobank WHERE infoName='marksDistribution'");
            rs=pStatement.executeQuery();
 
         PdfPCell cell = new PdfPCell(new Phrase("Marks Distribution")); 
         cell.setHorizontalAlignment(1);
         marks.addCell(cell);
         
         Paragraph info = new Paragraph(); 
         info.setFont(small);
        
          while(rs.next()){
                  info.add(rs.getString("currentValue")+"\n");
      
              }
        cell= new PdfPCell(info); 
        cell.setHorizontalAlignment(1);
        marks.addCell(cell);
 
            }
            catch(SQLException Scode){
        
            }
         
         
          //</editor-fold>
             
         //<editor-fold defaultstate="collapsed" desc="Generating Academic Calender Info">
           
           
           
            PdfPTable calendar = new PdfPTable(2); 
            // calendar.setHorizontalAlignment(2);    //allign right
            calendar.setWidthPercentage(100f);
            calendar.setSpacingBefore(5);
            try{
           
 
         PdfPCell cell = new PdfPCell(); 
 
         
         
        Paragraph info1 = new Paragraph(""), info2=new Paragraph("");
          info1.setFont(small); info2.setFont(small);
          //info1.setSpacingAfter(5);
         // info1.setSpacingBefore(5);
          
          info1.setTabSettings(new TabSettings(32f));

        
        
        for(int cal=1;cal<11;cal++)
        {
         pStatement=con.prepareStatement("SELECT * FROM academiccalendar WHERE id=?");
         pStatement.setString(1, String.valueOf(cal));
            rs=pStatement.executeQuery();
        
           while(rs.next()){
               if(cal>=1 && cal<=5)
               {
                    info1.add(rs.getString("title"));
                    info1.add(Chunk.TABBING);
                    info1.add(": "+rs.getString("date")+"\n" );    
               }
 
               else 
               {
                    info2.add(rs.getString("title"));
                    info2.add(Chunk.TABBING);
                    info2.add(": "+rs.getString("date")+"\n" );    
               }
                
            }
               
               
               
               
//                     info1= new Paragraph(
//                        " Academic Calendar: \n"+
//                        " Class Starting Date    : "+rs.getString("csd")+"\n"+
//                        " Mid-Term Exam Date     : "+rs.getString("med")+"\n"+
//                        " Mid-Term Retake Date   : "+rs.getString("mrd")+"\n"+
//                        " Mid Result Publish Date: "+rs.getString("mrpd")+"\n"+
//                        " Tuition Fee Payment    : "+rs.getString("tfp")
//                     ); 
//
//                      info2= new Paragraph(
//                        " Class Closing Date        : "+rs.getString("ccd")+"\n"+
//                        " Semester Final Exam Date  : "+rs.getString("fed")+"\n"+
//                        " Semester Final Retake Date: "+rs.getString("frd")+"\n"+
//                        " Final Result Publish Date : "+rs.getString("frpd")+"\n"+
//                        " Next Semester Class Start : "+rs.getString("nscs") 
//
//                     ); 
 
                  
              }
      
        cell= new PdfPCell(info1);
        cell.setBorderWidth(0);
        cell.setBorderWidthLeft(1);
        calendar.addCell(cell);
        
 
        cell= new PdfPCell(info2);
        cell.setBorderWidth(0);
        cell.setBorderWidthLeft(1);
        calendar.addCell(cell);
       
 
            }
            catch(SQLException Scode){
        
            }

 
  
         //</editor-fold>
 
         //<editor-fold defaultstate="collapsed" desc="Generating Program officer info">
       
            // https://www.mikesdotnetting.com/article/89/itextsharp-page-layout-with-columns  may be needed later. 
            
            PdfPTable pOfficer = new PdfPTable(1); 
            pOfficer.setHorizontalAlignment(2);    //allign right
            pOfficer.setWidthPercentage(40f);
            
            try{
            pStatement=con.prepareStatement("SELECT * FROM adminlogin where userName='a.registrar'");
            
             rs=pStatement.executeQuery();
              
     
        
         PdfPCell cell = new PdfPCell(new Phrase("Program Officer's Info")); 
         cell.setHorizontalAlignment(1);
         pOfficer.addCell(cell);
         
         Paragraph info = new Paragraph(); 
       
           while(rs.next()){
                  info.add(rs.getString("name")+"\n");
                  info.add(rs.getString("designation")+"\n");
                  info.add("Cell: "+rs.getString("mobile")+"\n");
                  info.add("Email: "+rs.getString("email"));
                  
                  //another way
                 //  Paragraph info= new Paragraph(
                 //            rs.getString("name")+"\n"
                 //           +rs.getString("designation")+"\n"
                 //           +"Cell: "+rs.getString("mobile")+"\n"
                 //           +"Email: "+rs.getString("email")); 
                  
              }
        cell= new PdfPCell(info); 
        cell.setHorizontalAlignment(1);
        pOfficer.addCell(cell);
 
            }
            catch(SQLException Scode){
        
            }
         
         
         //</editor-fold>
         
         
         
         try {
                 
                    doc.add(rTable);
                    rTable.flushContent(); 
                    doc.add(new Paragraph("\n")); 
                    
                    doc.add(table);
                    table.flushContent();
                    doc.add(new Paragraph("\n")); 
                    
                    
                    
                    doc.add(new Paragraph("Academic Calendar: ", title));
                    doc.add(calendar);
                    calendar.flushContent();
                    doc.add(new Paragraph("\n"));
                    
                    PdfPTable table = new PdfPTable(2);
                    table.setWidthPercentage(100);
                    table.addCell(getCell(marks, PdfPCell.ALIGN_LEFT));
                    table.addCell(getCell(pOfficer, PdfPCell.ALIGN_RIGHT));
                    doc.add(table);

//                    doc.add(marks);                                        
//                    doc.add(pOfficer); 
                
     
                   Paragraph footer= new Paragraph("\n| CSE official fb group: goo.gl/zj33wf | website: diu.ac |  Routine generation date: "+onlyDate.format(date)+" |", foot);
                   footer.setAlignment(1);
                    
                    doc.add(footer); 
                    doc.newPage();  
                } catch (DocumentException ex) {
                   JOptionPane.showMessageDialog(null, "Error: "+ex);
                }
       }
        
        
         doc.close();
         JOptionPane.showMessageDialog(null, "PDF Created Successfully");
      Desktop.getDesktop().open(new java.io.File(Location+"Class Routine - "+currentDate.format(date)+".pdf"));
         
      
      }

public PdfPCell getCell(PdfPTable text, int alignment) {
    PdfPCell cell = new PdfPCell(text);
    cell.setPadding(10);
    cell.setHorizontalAlignment(alignment);
    cell.setBorder(PdfPCell.NO_BORDER);
    return cell;
}
        
}

 
 
      
      
      
      
    

