/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diu.desk;

 
public class MailDataInbox {
     private String date,message,time,sender;    
   
    public String getSender() {
        return sender;
    }

       public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public MailDataInbox(String date, String message, String time,String sender) {
        this.date = date;
        this.message = message;
        this.time = time;
        this.sender=sender;
    }
    
}

