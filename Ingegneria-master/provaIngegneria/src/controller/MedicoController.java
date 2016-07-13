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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
       
        
        
    public boolean autenticazione(String codiceRegione, String password){
        try {
            int occorrenze;
            String sql = "SELECT count(*) as num FROM \"Medico\" WHERE \"CodiceRegione\"=? AND \"Password\"=?";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, codiceRegione);
            pst.setString(2, password);
            ResultSet rs=pst. executeQuery ();
            rs.next();
            occorrenze = rs.getInt("num");
            System.out.println(occorrenze);
            
            if(occorrenze>0)
                return true;
            else
                return false;
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        } 
        return false;
    }   
		/*String whiteGamer = hiFrame.getWhite().getText();
		String blackGamer = hiFrame.getBlack().getText();
		view.getFrame().setLabel(whiteGamer,blackGamer);
		
		model.setConfiguration(new ChessBoard(1));
		MedicoView2.dispose();

	*/
            
        
                
       
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

    public ArrayList<Farmaco> listaFarmaci() {
        ArrayList<Farmaco> listaFarmaci = new ArrayList<Farmaco>();
        try {
            String sql = "SELECT \"nome\", \"costo\" FROM \"Farmaco\"";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                listaFarmaci.add(new Farmaco(rs.getString("nome"),rs.getDouble("costo")));
            }
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return listaFarmaci;
    }

	public int quantitaFarmacoPrescritto( String tipoPeriodo, String periodo) { 
		return 0; }

	public boolean farmacoGenerico(String codicePrescrizione){ 
		return false;
	 } 
        
    public ArrayList<String> farmacoPerPaziete(String codiceFiscale, String tipoPeriodo,int periodo) { 
	ArrayList<String> listaFarmaci = new ArrayList<String>();
        String dataInizio = null;
        String dataFine = null;
        Calendar calendar = GregorianCalendar.getInstance();
        int giorno = calendar.get(Calendar.DAY_OF_MONTH);
        int anno = calendar.get(Calendar.YEAR);
        int mese = calendar.get(Calendar.MONTH);
        Date data;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        data = (Date) calendar.getTime();
        switch(tipoPeriodo){
            case("mese"): 
                if(mese < periodo){
                    data.setYear(data.getYear()-1);
                }
                data.setMonth(periodo);
                data.setDate(1);
                dataInizio = df.format(data);
                data.setDate(31);
                dataFine = df.format(data);
                break;
            case("semestre"):
                if(mese < 6 && periodo == 2)
                    data.setYear(anno-1);
                if(periodo == 1){
                    data.setMonth(1);
                    data.setDate(1);
                    dataInizio = df.format(data);
                    data.setMonth(6);
                    data.setDate(31);
                    dataFine = df.format(data);
                }
                else {
                    data.setMonth(7);
                    data.setDate(1);
                    dataInizio = df.format(data);
                    data.setMonth(12);
                    data.setDate(31);
                    dataFine = df.format(data);
                }
                break;
                
            case("trimestre"):
                if((periodo-1)*3 > mese){
                    data.setYear(anno-1);
                }
                if(periodo == 1){
                    data.setMonth(1);
                    data.setDate(1);
                    dataInizio = df.format(data);
                    data.setMonth(3);
                    data.setDate(31);
                    dataFine = df.format(data);
                }
                if(periodo == 2){
                    data.setMonth(4);
                    data.setDate(1);
                    dataInizio = df.format(data);
                    data.setMonth(6);
                    data.setDate(31);
                    dataFine = df.format(data);
                }
                if(periodo == 3){
                    data.setMonth(7);
                    data.setDate(1);
                    dataInizio = df.format(data);
                    data.setMonth(9);
                    data.setDate(31);
                    dataFine = df.format(data);
                }
                if(periodo == 4){
                    data.setMonth(10);
                    data.setDate(1);
                    dataInizio = df.format(data);
                    data.setMonth(12);
                    data.setDate(31);
                    dataFine = df.format(data);
                }
                break;
                
            case("anno"):
                data.setYear(periodo);
                data.setMonth(1);
                data.setDate(1);
                dataInizio = df.format(data);
                data.setMonth(12);
                data.setDate(31);
                dataFine = df.format(data);
                break;
            default : System.err.println("Tipo periodo non valido");
        }
        
        try {
            String sql = "SELECT \"nomefarmaco\" FROM \"FarmacoInRicetta\" JOIN \"Prescrizione\" ON \"Prescrizione.codice\"=\"FarmacoInRicetta.codiceprescrizione\" WHERE \"paziente\"=? AND \"data\" >= ? AND \"dat\" <= ?  " ;
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, codiceFiscale);
            pst.setString(2, dataInizio);
            pst.setString(3, dataFine);
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                listaFarmaci.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        }

        return listaFarmaci;
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
    
	public String effettuaPrescrizioneConVisita(String codiceFiscale, ArrayList<String> farmaci){ 
                
            String n=numeroPrescrizioni() + "";
            ArrayList<String> l=new ArrayList<>(); 

        try {
            PreparedStatement stmt;
            PreparedStatement stmt2;
            stmt = c.prepareStatement("INSERT INTO \"Prescrizione\" (Codice,Paziente,Medico,Data) VALUES (?, ?, ?,CURRENT_DATE)");
            stmt2 = c.prepareStatement("INSERT INTO \"FarmacoInRicetta\" (codiceprescrizione,nomefarmaco,tipoacquisto) VALUES (?, ?, ?)");
            
                      
            if(medico.listaPazienti().contains(codiceFiscale)) {
               
                stmt.clearParameters(); 
                stmt.setString(1, n);
                stmt.setString(2, codiceFiscale);
                stmt.setString(3, medico.getCodiceRegionale());        
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
        
       
        public String effettuaPrescrizioneSuRichiesta(String codiceRichiesta){ 
             
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
                System.out.println("Prescrizione su richiesta effettuata");
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
