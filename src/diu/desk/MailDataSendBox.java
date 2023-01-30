/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diu.desk;

 
public class MailDataSendBox {
  private String sendto,sendtime,senddate,sendsubject;
    public String getSendto() {
        return sendto;
    }

   
    public String getSendtime() {
        return sendtime;
    }

   
    
    public String getSenddate() {
        return senddate;
    }

  
    public String getSendsubject() {
        return sendsubject;
    }
     public MailDataSendBox(String sendto, String sendsubject, String senddate,String sendtime) {
        this.sendto = sendto;
        this.sendsubject = sendsubject;        
        this.senddate = senddate;
        this.sendtime = sendtime;
        
    }


}  
