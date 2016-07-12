/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Medico;
/**
 *
 * @author Valentina
 */
public class Segreteria {
    
    private Connection c=null;
    
    
    public Segreteria(Connection c){
        this.c=c;
    }
    
    public Medico medicoDelPaziente(String codiceFiscale){
        
        Medico m=new Medico(c);
        
        try {
            PreparedStatement pst = c.prepareStatement ( "SELECT \"CodiceRegione\" FROM \"Paziente\" join \"Medico\" on \"Medico\"=\"CodiceRegione\"" ); 
            
            ResultSet rs=pst. executeQuery ();      
            while(rs.next()){
                    m.setCodiceRegionale(rs.getString("CodiceRegione"));
            }  
        }
        
        catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati: " + e. getMessage () );
        }
        return m;
    }

    public void inviaRichiestaPrescrizione(String codiceFiscale,ArrayList<String> farmaci){
            
        try {
            PreparedStatement stmt;
            PreparedStatement stmt2;
            stmt = c.prepareStatement("INSERT INTO \"Richiesta\" (paziente,farmaco) VALUES (?, ?)");
          
            System.out.println(medicoDelPaziente(codiceFiscale));
            if(((medicoDelPaziente(codiceFiscale)).listaPazienti()).contains(codiceFiscale)) {
                for(String f :farmaci) {
                    stmt.clearParameters();
                    stmt.setString(1, codiceFiscale);        
                    stmt.setString(2, f);
                    stmt.executeUpdate();
                }
            System.out.println("Richiesta prescrizione inserita");
            }
            else {
                System.out.println("Paziente non presente ");
            }            
            stmt.close();   
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
                  
    public void consegnaPrescrizione(String codicePrescrizione){
    }
    
}
