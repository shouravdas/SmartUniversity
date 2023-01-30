/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diu.desk;

import java.util.concurrent.TimeUnit;


public class Loading {
public static void main(String[] args) {
        String command = "C:\\xampp\\xampp_start.exe";
        try{
            Process pro = Runtime.getRuntime().exec(command);
                   ProcessBar pb=new ProcessBar();
                     pb.setVisible(true);
                  // TimeUnit.SECONDS.sleep(12);               
              for(int i=0;i<=100;i++){
                  Thread.sleep(110);
                  pb.jProgressBar1.setValue(i);
                  
              } 
              pb.dispose();
            Login h=new Login();
            h.setVisible(true);
              
              
        }
        catch(Exception ex){
            
        }
       
       
    }
       
}
