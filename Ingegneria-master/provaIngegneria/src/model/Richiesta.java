/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Valentina
 */
public class Richiesta {
    
    private String codicePaziente=null;
    private String codiceRichiesta=null;
    private String nomePaziente=null;
    private String cognomePaziente=null;
    private String dataNascitaPaziente;
    private ArrayList<String> farmaci=null;

 
    public Richiesta(String codicePaziente,String codiceRichiesta,String nomePaziente,String cognomePaziente,String dataNascitaPaziente,ArrayList<String> farmaci){
        this.codicePaziente=codicePaziente;
        this.codiceRichiesta=codiceRichiesta;
        this.nomePaziente=nomePaziente;
        this.cognomePaziente=cognomePaziente;
        this.farmaci=farmaci;
        this.dataNascitaPaziente=dataNascitaPaziente;
    }
    
    
    public void setPaziente(String paziente){
        this.codicePaziente=paziente;
    }
    
     public void setNomePaziente(String nomePaziente){
        this.nomePaziente=nomePaziente;
    }
     
      public void setCognomePaziente(String cognomePaziente){
        this.cognomePaziente=cognomePaziente;
    }
      
       public void setDataNascitaPaziente(String dataNascitaPaziente){
        this.dataNascitaPaziente=dataNascitaPaziente;
    }
       
    
    public void aggiungiAListaFarmaci(String farmaco){
        farmaci.add(farmaco);
    }
    
    public ArrayList<String> getFarmaci(){
        return farmaci;
    }
    
    public String getPaziente(){
        return codicePaziente;
    }
    
    public String getCodiceRichiesta(){
        return codiceRichiesta;
    }
    
     public String getNomePaziente(){
        return nomePaziente;
    }
     
      public String getCognomePaziente(){
        return cognomePaziente;
      }
      
       public String getDataNascitaPaziente(){
        return dataNascitaPaziente;
    }
    
       //getFarmaci non è funzionante
    
    public static ArrayList<String> getFarmaci(String codiceRichiesta, Connection c){
        ArrayList<String> farmaci=new ArrayList<>();
         try {
           
           PreparedStatement stmt;
           stmt = c.prepareStatement(" SELECT \"nomefarmaco\" FROM \"farmacoInRichiesta\" WHERE \"codicerichiesta\"= ? ");
           stmt.clearParameters();
           stmt.setString(1, codiceRichiesta); 
           
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
       
}
