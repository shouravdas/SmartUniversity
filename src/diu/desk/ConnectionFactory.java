package diu.desk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static diu.desk.ConnectionFactory.connection;


public class ConnectionFactory {
    static Connection connection = null;
    
    
    public static Connection getMysqlConnection() {
        try{
           // Class.forName("com.mysql.jdbc.Driver");
  
           Class.forName("com.mysql.cj.jdbc.Driver");
//           
//             String url = "jdbc:mysql://localhost/demo_stp";
//          String username = "root";
//          String password = "";

          String url = "jdbc:mysql://localhost/smart_university";
          String username = "root";
          String password = "";


          //  String url = "jdbc:mysql://getintopc.online:2083/getinto1_demo_stp";
          //  String username = "getinto1_demo";
          //  String password = "Dhaka1205#";


           //  String url = "jdbc:mysql://db4free.net:3306/demo_stp";
           //  String username = "demo_project5";
           //  String password = "Dhaka1205";

         
           // String url = "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12279908";
           // String username = "sql12279908";
           // String password = "7tQtK3jgRr";

/* Server: sql12.freemysqlhosting.net
Name: sql12279908
Username: sql12279908
Password: 7tQtK3jgRr
Port number: 3306
phpMyAdmin: www.phpmyadmin.co 
 */        

        connection =(Connection) DriverManager.getConnection(url,username, password);
        
        return connection;
        
    }catch(Exception e){
                     JOptionPane.showMessageDialog(null, "Test internet connection");
                     System.out.println(e);
                     return null; 
}
                             
    }
    
    
}
