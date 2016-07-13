/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provaingegneria;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Valentina
 */
public class ProvaIngegneria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TO Connection c = null;
      Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c=DriverManager.getConnection("jdbc:postgresql://192.168.1.11:5432/Ingegneria","postgres","123");
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
    }
    
}
