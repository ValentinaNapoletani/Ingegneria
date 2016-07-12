/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import view.*;
import model.*;

/**
 *
 * @author Valentina
 */
public class MedicoController {
    
    
    private Connection c=null;
    public MedicoView medicoview;
    
    public MedicoController(Connection c){
        this.c=c;
    }
    
	public MedicoView getMedicoview() {
	 	 return medicoview; 
	}

	public void setMedicoview(MedicoView medicoview) { 
		 this.medicoview = medicoview;            
	}
       
        
        public void autenticazione(){
            
        }
                
       
	public PrescrizioneNonUsata getPrescrizionenonusata() {
	 	 return null; 
	}

	public ArrayList<String> pazientiPerFarmaco(String farmaco) { 
		return null;
	 }

	public String reazioniAvverse() { 
		return null;
	 }

	public PrescrizioneNonUsata verificaUsoPrescrizione() { 
		return null;
	 }

	public ArrayList<String> listaFarmaci() { 
		return null;
	 }

	public int quantitaFarmacoPrescritto( String tipoPeriodo, String periodo) { 
		return 0; }

	public boolean farmacoGenerico(String codicePrescrizione){ 
		return false;
	 } 
        
        public ArrayList<String> farmacoPerPaziete(String codiceFiscale, String tipoPeriodo,String periodo) { 
		  return null;
	 } 
        
        //numero da assegnare al ocdice della prescrizione
    private int numeroPrescrizioni(){
        
        String nPres=null;
        int n=0;
        
        try {
            String sql = "SELECT count(*) as num FROM \"Prescrizione\"";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            ResultSet rs=pst. executeQuery ();      
            while(rs.next()){
                 nPres=rs.getString("num");
                 n = Integer.parseInt(nPres);
            }
        }
        
        catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        
        return n+1;
    }
        
	/*public String effettuaPrescrizione(String codiceFiscale, ArrayList<String> farmaci){ 
                
            String n=numeroPrescrizioni() + "";
               
        try {
            PreparedStatement stmt;
            PreparedStatement stmt2;
            stmt = c.prepareStatement("INSERT INTO \"Prescrizione\" (Codice,Paziente,Medico,Data) VALUES (?, ?, ?,?)");
            stmt2 = c.prepareStatement("INSERT INTO \"FarmacoInRicetta\" (codiceprescrizione,nomefarmaco,TipoAquisto) VALUES (?, ?, ?,?)");
            
            stmt.clearParameters(); 
            stmt.setString(1, n);
            stmt.setString(2, codiceFiscale);
            stmt.setString(3, medico);
            stmt.setDate(4, data);
          //se paziente è vero paziente
            if(medicoDelPaziente().listaPazienti().contains(codiceFiscale)) {
            stmt.executeUpdate();
            
            for(String f :farmaci) {
                stmt2.clearParameters(); 
                stmt2.setString(1, n);
                stmt2.setString(2, f );
                stmt2.setString(3, null);
                stmt2.executeUpdate();
            }
            System.out.println("Richiesta prescrizione inserita");
            }
            else {
                System.out.println("Paziente non presente");
            }
            
            stmt.close();
            
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        
        return null;
                                      
	 }*/

	public ArrayList<String> farmacoPerPeriodo(String tipoPeriodo,String periodo) { 
		  return null;
	 }

}
