
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Valentina
 */
public class Prescrizione {
    
    private String codicePaziente=null;
    private int codicePrescrizione=0;
    private boolean usata=false;
    
    
    public Prescrizione(String codicePaziente,int codicePrescrizione){
        this.codicePaziente=codicePaziente;
        this.codicePrescrizione=codicePrescrizione;
    }
    
    
    
    
    public int getCodicePrescrizione(){
        return codicePrescrizione;
    }
    
    public String getPaziente(){
        return codicePaziente;
    }
    
    public String getNomePaziente(Connection c){
        String risultato="";
        try {
           
           PreparedStatement stmt;
           stmt = c.prepareStatement(" SELECT \"Nome\" FROM \"Paziente\" WHERE \"Paziente\".\"CodiceSanitario\"= ? ");
           stmt.clearParameters();
           stmt.setString(1, codicePaziente); 
           
           ResultSet rs=stmt.executeQuery ();      
            while(rs.next()){
                risultato=rs.getString("Nome");
            }     
           stmt.close();   
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return risultato;
    }
    
    public String getCognomePaziente(Connection c){
        String risultato="";
        try {
           
           PreparedStatement stmt;
           stmt = c.prepareStatement(" SELECT \"Cognome\" FROM \"Paziente\" WHERE \"CodiceSanitario\"= ? ");
           stmt.clearParameters();
           stmt.setString(1, codicePaziente); 
           
           ResultSet rs=stmt.executeQuery ();      
            while(rs.next()){
                risultato=rs.getString("Cognome");
            }     
           stmt.close();   
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return risultato;
    }
    
    public static ArrayList<String> getFarmaci(String codicePrescrizione, Connection c){
        ArrayList<String> farmaci=new ArrayList<>();
         try {
           
           PreparedStatement stmt;
           stmt = c.prepareStatement(" SELECT \"nomefarmaco\" FROM \"FarmacoInRicetta\" WHERE \"codiceprescrizione\"= ? ");
           stmt.clearParameters();
           stmt.setString(1, codicePrescrizione); 
           
           ResultSet rs=stmt.executeQuery ();      
            while(rs.next()){
                farmaci.add(rs.getString("nomefarmaco"));
            }     
           stmt.close();   
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return farmaci;
    }
    
    public static ArrayList<String> getFarmaciEquivalenti(int codicePrescrizione, Connection c){
        ArrayList<String> farmaci=new ArrayList<>();
         try {
            PreparedStatement stmt;
            stmt = c.prepareStatement(" SELECT distinct \"farmaco\" FROM \"Contenuto\" WHERE \"principio\" IN (SELECT \"principio\" FROM \"FarmacoInRicetta\" JOIN \"Contenuto\" ON \"nomefarmaco\"=\"farmaco\"  WHERE  \"codiceprescrizione\"= ?) UNION SELECT \"nomefarmaco\" as farmaco FROM \"FarmacoInRicetta\" WHERE \"codiceprescrizione\"= ? ");
            stmt.clearParameters();
            stmt.setInt(1, codicePrescrizione); 
            stmt.setInt(2, codicePrescrizione); 
           
            ResultSet rs=stmt.executeQuery ();      
            while(rs.next()){
                farmaci.add(rs.getString("farmaco"));
                System.out.println(rs.getString("farmaco"));
            }     
            stmt.close();   
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage());
        }
        return farmaci;
    }
}
