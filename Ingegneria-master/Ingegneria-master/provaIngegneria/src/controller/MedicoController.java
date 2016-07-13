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
import view.*;
import model.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Valentina
 */
public class MedicoController {
    
    
    private Connection c=null;
    //private MedicoView medicoview;
    private Medico medico;
    
    public MedicoController(Connection c,Medico m){
        this.c=c;
        this.medico=m; 
    }
    
	/*public MedicoView getMedicoview() {
	 	 return medicoview; 
	}

	public void setMedicoview(MedicoView medicoview) { 
		 this.medicoview = medicoview;            
	}*/
       
        
        
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
     
    
   // di quale richiesta?????
   // oppure senza???
   
	public String effettuaPrescrizioneConVisita(String codiceFiscale, ArrayList<String> farmaci){ 
                
            String n=numeroPrescrizioni() + "";
            ArrayList<String> l=new ArrayList<>(); 

        try {
            PreparedStatement stmt;
            PreparedStatement stmt2;
            stmt = c.prepareStatement("INSERT INTO \"Prescrizione\" (Codice,Paziente,Medico,Data) VALUES (?, ?, ?,CURRENT_DATE)");
            stmt2 = c.prepareStatement("INSERT INTO \"FarmacoInRicetta\" (codiceprescrizione,nomefarmaco,tipoacquisto) VALUES (?, ?, ?)");
            
            stmt.clearParameters(); 
            stmt.setString(1, n);
            stmt.setString(2, codiceFiscale);
            stmt.setString(3, medico.getCodiceRegionale());          
            if(medico.listaPazienti().contains(codiceFiscale)) {
                stmt.executeUpdate();
            
                for(String f :farmaci) {
                    stmt2.clearParameters(); 
                    stmt2.setString(1, n);
                    stmt2.setString(2, f );
                    stmt2.setString(3, null);
                    stmt2.executeUpdate();
                }
                System.out.println("Prescrizione effettuata");
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
                                      
	 }
        
        
        private String[] ricavaDatiRichiesta(String codiceRichiesta){
            String[] s=new String[2];
           
            try {
                PreparedStatement pst = c.prepareStatement ( "SELECT \"paziente\",\"nomefarmaco\" FROM \"Richiesta\" join \"farmacoInRichiesta\" on codice=codicerichiesta WHERE codice=?"); 

                pst.clearParameters(); 
                pst.setString(1, codiceRichiesta);
           
                ResultSet rs=pst.executeQuery ();      
                while(rs.next()){
                    s[0]=rs.getString("paziente"); 
                    s[1]=rs.getString("nomefarmaco"); 
                    }  
            }
        
            catch ( SQLException e) {
                System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
            return s;
        }
        //attenta! non inserisce non trova il paziente problema conricevidatidarichiesta
         //incrementa lista di prescrizioni fatte da passare alla segreteria con richiesta
        public String effettuaPrescrizioneSuRichiesta(String codiceRichiesta){ 
             int  n2=numeroPrescrizioni();   
            String n=numeroPrescrizioni() + "";
            ArrayList<String> farmaci=new ArrayList<>(); 
            farmaci.add(ricavaDatiRichiesta(codiceRichiesta)[1]);
            String codiceFiscale=ricavaDatiRichiesta(codiceRichiesta)[0];
 
        try {
            PreparedStatement stmt;
            PreparedStatement stmt2;
            stmt = c.prepareStatement("INSERT INTO \"Prescrizione\" (Codice,Paziente,Medico,Data) VALUES (?, ?, ?,CURRENT_DATE)");
            stmt2 = c.prepareStatement("INSERT INTO \"FarmacoInRicetta\" (codiceprescrizione,nomefarmaco,tipoacquisto) VALUES (?, ?, ?)");
            
            stmt.clearParameters(); 
            stmt.setString(1, n);
            stmt.setString(2, codiceFiscale);
            stmt.setString(3, medico.getCodiceRegionale());          
            if(medico.listaPazienti().contains(codiceFiscale)) {
                stmt.executeUpdate();
            
                for(String f :farmaci) {
                    stmt2.clearParameters(); 
                    stmt2.setString(1, n);
                    stmt2.setString(2, f );
                    stmt2.setString(3, null);
                    stmt2.executeUpdate();
                }
                System.out.println("Prescrizione effettuata");
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
                                      
	 }


}
