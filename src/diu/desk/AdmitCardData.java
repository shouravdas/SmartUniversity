/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diu.desk;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


 
public class AdmitCardData {
  
    String name;
    String fathersName;
    String mothersName;
    String Roll;
    String Batch;
    String Session;
    String Semester;
    String Department;
    String StudentId;
    
    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String Semester) {
        this.Semester = Semester;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getRoll() {
        return Roll;
    }

    public void setRoll(String Roll) {
        this.Roll = Roll;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String Batch) {
        this.Batch = Batch;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String Session) {
        this.Session = Session;
    }
  
      
}
 