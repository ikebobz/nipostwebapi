/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nipostweb;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.*;
import java.util.Properties;
import java.io.*;
import java.util.Date;
import java.sql.Timestamp;



@WebService(serviceName = "AlertService")
public class AlertService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    public boolean updateState(String code, String desc)
    {
      boolean state=true;
      try{
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nipost_db","nana","");
      Statement cmd = con.createStatement();
      String sql = "insert into states values ('"+code+"','"+desc+"')";
      cmd.executeUpdate(sql);
      }
      catch(Exception ex)
      {
       state = false;
      }
      
      return state;
    
    }
    @WebMethod(operationName = "insertNote")
    public String insertNote(String name,String matno,String inst, String dateOfAvail, String pickadd, String recadd)
    {
     String trackno = getTimeStamp();
     try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Properties prop = new Properties();
            FileReader reader = new FileReader(new File("C:\\properties\\local_properties.properties"));
            prop.load(reader);
            String uname = prop.getProperty("user");
            String pass = prop.getProperty("password");
            String port = prop.getProperty("tcport");
            String dbname = prop.getProperty("dbname");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:"+port+";databaseName="+dbname+";user="+uname+";password="+pass);
            if(con!=null) {
                System.out.println("connection successful");
                Statement sql = con.createStatement();
                sql.executeUpdate("insert into notifications values ('"+name+"','"+matno+"','"+inst+"','"+dateOfAvail+"','"+trackno+"',0,'"+pickadd+"','"+recadd+"')");
                System.out.println("Update successful");

            }
            else trackno = "0000";
        }
        catch(Exception ex)
        {
            //System.out.println(ex.getMessage());
            trackno = "0001: "+ex.getMessage();
        }
     return trackno;
    }
    public String  getTimeStamp()
    {
      Date date = new Date();
      long time = date.getTime();
      return String.valueOf(time);
    }
    
   
}
