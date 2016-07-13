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
import java.util.ArrayList;
import model.Medico;
import model.Segreteria;
/**
 *
 * @author Valentina
 */
public class SegreteriaController {
    
    private Connection c=null;
    private Segreteria segreteria=null;
    
    public SegreteriaController(Connection c,Segreteria segreteria){
        this.c=c;
        this.segreteria=segreteria;
    }
    
    public Medico medicoDelPaziente(String codiceFiscale){
        
        Medico m=new Medico(c,"0001","10maco");
        
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
//ok
    private int numeroRichieste(){
        
        String nRichieste=null;
        int n=0;
        
        try {
            String sql = "SELECT count(*) as num FROM \"Richiesta\"";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            ResultSet rs=pst. executeQuery ();      
            while(rs.next()){
                 nRichieste=rs.getString("num");
                 n = Integer.parseInt(nRichieste);
            }
        }
        
        catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        
        return n+1;
    }
    //ok
    public void inviaRichiestaPrescrizione(String codiceFiscale,ArrayList<String> farmaci){
            
        String n=numeroRichieste() + "";
        
        try {
            PreparedStatement stmt;
            PreparedStatement stmt2;
            stmt = c.prepareStatement("INSERT INTO \"Richiesta\" (paziente,codice) VALUES (?, ? )");
            stmt2 = c.prepareStatement("INSERT INTO \"farmacoInRichiesta\" (codicerichiesta,nomefarmaco) VALUES (?, ?)");
          
            System.out.println(medicoDelPaziente(codiceFiscale));
            if(((medicoDelPaziente(codiceFiscale)).listaPazienti()).contains(codiceFiscale)) {
                
                stmt.clearParameters();
                stmt.setString(1, codiceFiscale);        
                stmt.setString(2, n);
                stmt.executeUpdate();
                
                for(String f :farmaci) {
                    stmt2.clearParameters();
                    stmt2.setString(1, n);        
                    stmt2.setString(2, f);
                    stmt2.executeUpdate();
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
    //controllo se la la richesta è stat prescritta e se il medico è di questa segreteria
    public ArrayList<String> prescrizioniDaConsengare(){
        
        ArrayList<String> prescrizioni=new ArrayList<>();
         try {
           
           PreparedStatement stmt;
           stmt = c.prepareStatement(" SELECT \"codice\" FROM \"Richiesta\" join \"Prescrizione\" on \"Richiesta.codice\"=\"CodicePrescrizione\" join \"Medico\" on \"CodiceRegione\"=\"medico\" join \"Segreteria\" on \"codicesegreteria\"=\"codice\" WHERE \"codice\"=? ");
           stmt.clearParameters();
           stmt.setString(1, segreteria.getCodiceSegreteria()); 
           
           ResultSet rs=stmt.executeQuery ();      
            while(rs.next()){
                prescrizioni.add(rs.getString("codice"));
            }     
           stmt.close();   
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        
        return prescrizioni;
    }

    
    //ok
    public void consegnaPrescrizione(String codicePrescrizione){
 
        try {
           //String rp=richiestaPrescritta(codicePrescrizione);
           PreparedStatement stmt;
           stmt = c.prepareStatement("DELETE FROM \"Richiesta\" WHERE \"codice\"=?  ");
            
            //if(rp!=null){
               
                stmt.clearParameters();
                stmt.setString(1, codicePrescrizione);          
                stmt.executeUpdate();
                System.out.println("Richiesta prescrizione eliminata, prescrizione effettuata");
           // }
           //else {
             //   System.out.println("Errore:La prescrizione non è pronta!");
            //}            
            stmt.close();   
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    //torna il codice richiesta espletata oppure null
    /*private String richiestaPrescritta(String codicePrescrizione){
        
        String cod=null;
        
         try {
           
           PreparedStatement stmt;
           stmt = c.prepareStatement(" SELECT \"codice\" FROM \"Richiesta\" join \"Prescrizione\" on \"Richiesta.codice\"=\"CodicePrescrizione\" WHERE \"Prescrizione.codice\"=? ");
           stmt.clearParameters();
           stmt.setString(1, codicePrescrizione); 
           
           ResultSet rs=stmt.executeQuery ();      
            while(rs.next()){
                cod=rs.getString("codice");
            }  
 
           System.out.println("Richiesta prescrizione eliminata, prescrizione effettuata e consegnata");           
           stmt.close();   
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
         
       return null; 
    }*/
    
}
