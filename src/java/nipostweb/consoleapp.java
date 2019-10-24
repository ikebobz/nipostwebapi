/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nipostweb;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author Ikenna
 */
public class consoleapp {
    
    public static void main(String[] args)
    {
       //long time = new Date().getTime();
       //Timestamp ts = new Timestamp(time);
        AlertService as = new AlertService();
        String stamp = as.getTimeStamp();
        System.out.println(stamp);
    
    }
    
}
