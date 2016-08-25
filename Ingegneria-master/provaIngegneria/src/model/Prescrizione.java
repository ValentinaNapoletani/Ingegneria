/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private String codicePrescrizione=null;
    private boolean usata=false;
    
    
    public Prescrizione(String codicePaziente,String codicePrescrizione){
        this.codicePaziente=codicePaziente;
        this.codicePrescrizione=codicePrescrizione;
    }
    
    
    
    
    public String getCodicePrescrizione(){
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
}
