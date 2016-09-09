package controller;
import SistemaPrescrizioniMain.Avvio;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import View.*;
import model.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;

/**
 *
 * @author Valentina
 */
public class MedicoController {
    
    
    private Connection c=null;
    private LoginMedico login;
    private Medico medico;
    private MedicoView mv;
    private boolean autenticato;
    private Avvio avvio;

    
    public MedicoController(Connection c,Medico m,LoginMedico login){
        this.c=c;
        this.medico=m; 
        this.login=login;
    }

    public LoginMedico getMedicoLogin() {
        return login; 
    }
    
    public MedicoView getMedicoView(){
        return mv;
    }

    public Medico getMedico(){
        return medico;
    }
    //public void setMedicoview(MedicoView medicoView) { 
     //   this.medicoView = medicoView;            
   // }
       
   public Connection getConnection(){
        return c;
   }   
    
    
    private boolean autenticazione(String codiceRegione, String password){
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
    
    public void autentica(){
        
        String user = login.getUser();
        String password = login.getPassword().getText();
        ArrayList<Richiesta> richieste=new ArrayList<>();
        autenticato = false;
        
        if(autenticazione(user,password)){
            if(!login.sostituto()) {
                login.setMedico();
                System .out. println (" Autenticato" );
                autenticato = true;
            }
            else{
                if(!getCodiceSostituito(login.getUser()).equals("")){
                    medico.setCodiceRegionale(getCodiceSostituito(login.getUser()));
                    autenticato = true;
                }
                else{
                    login.setErrore("Il medico autenticato non è un medico sostituto");
                }
            } 
            if(autenticato){
                for (Integer r: listaRichieste()){
                    richieste.add(richiestaConAnagraficaEFarmaco(r));
                }
           
                mv=new MedicoView(richieste,this);
                mv.setResizable(false);
                mv.setVisible(true);
                
                // mv.setConfiguration();
                login.dispose();
            }
             
        }
        else{
            login.setErrore("Autenticazione fallita");
        }
    }
    
    //sbagliato con codice richiesta a 3 cifre
    public static String oggettoSelezionatoConHtml(int i,ArrayList<String> s){
        System .out. println (" indice elem selezionato : " + i );
        String richiesta=null;
        //richiesta= s.get(i).substring(1,2);
        if(i>=0){       
            if(s.get(i).substring(8,9).equals("0") || s.get(i).substring(8,9).equals("1") || s.get(i).substring(8,9).equals("2") || s.get(i).substring(8,9).equals("3") || s.get(i).substring(8,9).equals("4") || s.get(i).substring(8,9).equals("5") || s.get(i).substring(8,9).equals("6")|| s.get(i).substring(8,9).equals("7") || s.get(i).substring(8,9).equals("8") || s.get(i).substring(8,9).equals("9")){
                richiesta=s.get(i).substring(6, 9);       
                System .out. println (" elem selezionato : " + richiesta );
            }
            else if(s.get(i).substring(7,8).equals("0") || s.get(i).substring(7,8).equals("1") || s.get(i).substring(7,8).equals("2") || s.get(i).substring(7,8).equals("3") || s.get(i).substring(7,8).equals("4") || s.get(i).substring(7,8).equals("5") || s.get(i).substring(7,8).equals("6")|| s.get(i).substring(7,8).equals("7") || s.get(i).substring(7,8).equals("8") || s.get(i).substring(7,8).equals("9")){
                richiesta=s.get(i).substring(6, 8);       
                System .out. println (" elem selezionato : " + richiesta );
            }
            else {
                richiesta=s.get(i).substring(6, 7);       
                System .out. println (" elem selezionato 2: " + richiesta );
            }
        }
          
       return richiesta; 
      
    }
    
    public static String oggettoSelezionato(int i,ArrayList<String> s){
        System .out. println (" indice elem selezionato : " + i );
        String richiesta=null;
        //richiesta= s.get(i).substring(1,2);
        if(i>=0){     
            if(s.get(i).substring(3,4).equals("0") || s.get(i).substring(3,4).equals("1") || s.get(i).substring(3,4).equals("2") || s.get(i).substring(3,4).equals("3") || s.get(i).substring(3,4).equals("4") || s.get(i).substring(3,4).equals("5") || s.get(i).substring(3,4).equals("6")|| s.get(i).substring(3,4).equals("7") || s.get(i).substring(3,4).equals("8") || s.get(i).substring(3,4).equals("9")){
                richiesta=s.get(i).substring(1, 4);       
                System .out. println (" elem selezionato : " + richiesta );
            }
            if(s.get(i).substring(2,3).equals("0") || s.get(i).substring(2,3).equals("1") || s.get(i).substring(2,3).equals("2") || s.get(i).substring(2,3).equals("3") || s.get(i).substring(2,3).equals("4") || s.get(i).substring(2,3).equals("5") || s.get(i).substring(2,3).equals("6")|| s.get(i).substring(2,3).equals("7") || s.get(i).substring(2,3).equals("8") || s.get(i).substring(2,3).equals("9")){
                richiesta=s.get(i).substring(1, 3);       
                System .out. println (" elem selezionato : " + richiesta );
            }
            else {
                richiesta=s.get(i).substring(1, 2);       
                System .out. println (" elem selezionato 2: " + richiesta );
            }
        }
          
        return richiesta; 
      
    }
    
    
    public static String oggettoSelezionatoSenzaPallino(int i,ArrayList<String> s){
        System .out. println (" indice elem selezionato : " + i );
        String richiesta=null;
        //richiesta= s.get(i).substring(1,2);
        if(i>=0){     
            if(s.get(i).substring(2,3).equals("0") || s.get(i).substring(2,3).equals("1") || s.get(i).substring(2,3).equals("2") || s.get(i).substring(2,3).equals("3") || s.get(i).substring(2,3).equals("4") || s.get(i).substring(2,3).equals("5") || s.get(i).substring(2,3).equals("6")|| s.get(i).substring(2,3).equals("7") || s.get(i).substring(2,3).equals("8") || s.get(i).substring(2,3).equals("9")){
                richiesta=s.get(i).substring(0, 3);       
                System .out. println (" elem selezionato : " + richiesta );
            }
            if(s.get(i).substring(1,2).equals("0") || s.get(i).substring(1,2).equals("1") || s.get(i).substring(1,2).equals("2") || s.get(i).substring(1,2).equals("3") || s.get(i).substring(1,2).equals("4") || s.get(i).substring(1,2).equals("5") || s.get(i).substring(1,2).equals("6")|| s.get(i).substring(1,2).equals("7") || s.get(i).substring(1,2).equals("8") || s.get(i).substring(1,2).equals("9")){
                richiesta=s.get(i).substring(0, 2);       
                System .out. println (" elem selezionato : " + richiesta );
            }
            else {
                richiesta=s.get(i).substring(0, 1);       
                System .out. println (" elem selezionato 2: " + richiesta );
            }
        }
          
        return richiesta; 
      
    }
   
    public ArrayList<String> listaFarmaciSelezionati(int[] indici,ArrayList<String> farmaci){
        ArrayList<String> risultato = new ArrayList<String>();
        System.out.println(indici.length);
        for(int i=0;i<indici.length;i++){
            risultato.add(farmaci.get(indici[i]));
        }
        return risultato;
    }

    public ArrayList<String> impostaDatiPerPrescrizione(String pazienteCF){
        
        ArrayList<String> dati = new ArrayList<String>();
        
        try {
            String sql = "SELECT \"Paziente\".\"Via\",\"Paziente\".\"Nome\",\"Paziente\".\"Cognome\",\"Medico\".\"Nome\",\"Medico\".\"Cognome\",CURRENT_DATE FROM \"Paziente\" JOIN \"Medico\" ON \"Medico\"=\"CodiceRegione\" WHERE \"CodiceSanitario\"=? ";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, pazienteCF);
            ResultSet rs=pst. executeQuery();
            while(rs.next()){
                dati.add(rs.getString(1));
                dati.add(rs.getString(2));
                dati.add(rs.getString(3));
                dati.add(rs.getString(4));
                dati.add(rs.getString(5));
                dati.add(rs.getString(6));
                dati.add(ultimaPrescrizione(c)+ "");
            }
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        } 
        
        return dati;
    }
    
    //return null???
    public Prescrizione getPrescrizionenonusata() {     
	 	 return null; 
    }

    
    public ArrayList<String> pazientiPerFarmaco(String farmaco) { 
        ArrayList<String> listaPazienti = new ArrayList<String>();
        try {
            String sql = "SELECT DISTINCT \"Paziente\".\"Nome\" as nome, \"Paziente\".\"Cognome\" as cognome FROM \"FarmacoInRicetta\" JOIN \"Prescrizione\" ON \"FarmacoInRicetta\".\"codiceprescrizione\"=\"Prescrizione\".\"codice\" JOIN \"Paziente\" ON \"Prescrizione\".\"paziente\" = \"Paziente\".\"CodiceSanitario\" WHERE \"FarmacoInRicetta\".\"nomefarmaco\"=? AND \"Prescrizione\".\"medico\" =?";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, farmaco);
            pst.setString(2, medico.getCodiceRegionale());
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                listaPazienti.add(rs.getString("nome")+" "+rs.getString("cognome"));
                System.out.println(rs.getString("nome")+" "+rs.getString("cognome"));
            }
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        } 
	return listaPazienti;
    }

    
    public ArrayList<String> reazioniAvverse() { 
        ArrayList<String> listaReazioni = new ArrayList<String>();
        try {
            String sql = "SELECT DISTINCT \"nome\" FROM \"Reazione\"";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                listaReazioni.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return listaReazioni;
    }
    
    //ok
    public ArrayList<Integer> listaPrescrizioniNonUsate(){
        ArrayList<Integer> listaPrescrizioni = new ArrayList<Integer>();
        try {
            String sql = "SELECT \"codice\" FROM \"Prescrizione\" WHERE \"usata\"=false";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                listaPrescrizioni.add(rs.getInt("codice"));
            }
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return listaPrescrizioni;
        
    }
    
    //ok
    public ArrayList<String> listaPrescrizioniNonUsateConData(){
        ArrayList<String> listaPrescrizioni = new ArrayList<String>();
        String s=new String();
        try {
            String sql = "SELECT \"codice\", data, paziente FROM \"Prescrizione\" WHERE \"usata\"=false ORDER BY codice";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                s= rs.getInt("codice") + "" ;
                listaPrescrizioni.add("Prescrizione n. " + s  + " prescritta il "+rs.getString("data")+" al paziente con codice sanitario "+rs.getString("paziente"));
            }
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return listaPrescrizioni;
        
    }

    //metodo non usato (costo non usato?)
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

    //non implementato, credo non serva
    public int quantitaFarmacoPrescritto( String tipoPeriodo, String periodo) { 
        return 0; 
    }

    //cosa deve fare questo metodo???
    public boolean farmacoGenerico(String codicePrescrizione){ 
	return false;
    } 
    
    public ArrayList<String> farmacoPerPaziete(String codiceFiscale, int periodo) {
        String p="";
        ArrayList<String> risultato = new ArrayList<>();
        switch(periodo){
            case 0: p="interval '1 month'";break;
            case 1: p="interval '3 month'";break;
            case 2: p="interval '6 month'";break;
            case 3: p="interval '1 year'";break;
            default: {
                try {
                    throw new Exception("periodo errato");
                } catch (Exception ex) {
                    Logger.getLogger(MedicoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String sql="SELECT nomefarmaco, count(*) as occorrenze FROM \"Prescrizione\" JOIN \"FarmacoInRicetta\" ON \"Prescrizione\".codice = \"FarmacoInRicetta\".codiceprescrizione WHERE \"medico\"=? AND paziente=? AND data > CURRENT_DATE - "+p+" GROUP BY nomefarmaco ORDER BY nomefarmaco"; 
        try {
            PreparedStatement pst=c.prepareStatement(sql);
            pst.clearParameters(); 
            pst.setString(1, medico.getCodiceRegionale());
            pst.setString(2, codiceFiscale);
            ResultSet rs=pst.executeQuery ();      
            while(rs.next()){
                risultato.add(rs.getString("nomefarmaco")+" x "+rs.getInt("occorrenze"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return risultato;
        
    }
        
    /*
    public ArrayList<String> farmacoPerPaziete(String codiceFiscale, String tipoPeriodo, int periodo) { 
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
    */
        
    //numero da assegnare al ocdice della prescrizione
    public static int ultimaPrescrizione(Connection c){
        
        int n=0;
        
        try {
            String sql = "SELECT max(codice) as num FROM \"Prescrizione\"";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            ResultSet rs=pst. executeQuery ();      
            while(rs.next()){
                 n=rs.getInt("num");
            }
        }
        
        catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        
        return n+1;
    }
    
    public void effettuaPrescrizioneConVisita(String codiceFiscale, ArrayList<String> farmaci){ 
                
        ArrayList<String> l=new ArrayList<>(); 

        try {
            PreparedStatement stmt;
            PreparedStatement stmt2;
            stmt = c.prepareStatement("INSERT INTO \"Prescrizione\" (Codice,Paziente,Medico,Data, datascadenza) VALUES (?, ?, ?,CURRENT_DATE, CURRENT_DATE+ interval '6 month')");
            stmt2 = c.prepareStatement("INSERT INTO \"FarmacoInRicetta\" (codiceprescrizione,nomefarmaco) VALUES (?, ?)");
                      
            if(medico.listaPazienti().contains(codiceFiscale)) {
               
                stmt.clearParameters(); 
                stmt.setInt(1, ultimaPrescrizione(c));
                stmt.setString(2, codiceFiscale);
                stmt.setString(3, medico.getCodiceRegionale());        
                stmt.executeUpdate();

                for(String f :farmaci) {
                    stmt2.clearParameters(); 
                    stmt2.setInt(1, ultimaPrescrizione(c)-1);
                    stmt2.setString(2, f);
                    stmt2.executeUpdate();
                }   
                System.out.println("Prescrizione effettuata");
            }
            else {
                System.out.println("Paziente non presente(visita)");
            }
            
            stmt.close();
            
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            //System.exit(0); //perchè uscita qui? (vik)
        }
        
                  
    }
        
        
    public static Richiesta ricavaDatiRichiesta(int codiceRichiesta, Connection c){
            
        Richiesta r=new Richiesta(null,codiceRichiesta,null,null,null,new ArrayList<String>());
           
        try {
            PreparedStatement pst = c.prepareStatement ( "SELECT \"paziente\",\"nomefarmaco\",\"codice\" FROM \"Richiesta\" join \"farmacoInRichiesta\" on codice=codicerichiesta WHERE codice=?"); 

            pst.clearParameters(); 
            pst.setInt(1, codiceRichiesta);
           
            ResultSet rs=pst.executeQuery ();                  
            while(rs.next()){
                r.setPaziente(rs.getString("paziente"));
                r.aggiungiAListaFarmaci(rs.getString("nomefarmaco"));
            }  
        }
        catch ( SQLException e) {
                System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return r;
    }
        
    public void effettuaPrescrizioneSuRichiesta(int codiceRichiesta){ 
            
        ArrayList<String> farmaci=null; 
  
        farmaci=(ricavaDatiRichiesta(codiceRichiesta,c).getFarmaci());
        String codiceFiscale=ricavaDatiRichiesta(codiceRichiesta,c).getPaziente();
 
        try {
            PreparedStatement stmt;
            PreparedStatement stmt2;
            PreparedStatement stmt3;
            stmt = c.prepareStatement("INSERT INTO \"Prescrizione\" (Codice,Paziente,Medico,Data,\"CodiceRichiesta\", datascadenza) VALUES (?, ?, ?,CURRENT_DATE,?, CURRENT_DATE + interval '6 month')");
            stmt2 = c.prepareStatement("INSERT INTO \"FarmacoInRicetta\" (codiceprescrizione,nomefarmaco) VALUES (?, ?)");
            stmt3 = c.prepareStatement("UPDATE \"Richiesta\" SET prescritta=true WHERE paziente = ? AND codice = ? ");
            stmt.clearParameters();
            stmt.setInt(1, ultimaPrescrizione(c));
            stmt.setString(2, codiceFiscale);
            stmt.setString(3, medico.getCodiceRegionale());  
            stmt.setInt(4, codiceRichiesta);
            if(medico.listaPazienti().contains(codiceFiscale)) {
                stmt.executeUpdate();
            
                for(String f :farmaci) {
                    stmt2.clearParameters(); 
                    stmt2.setInt(1, ultimaPrescrizione(c)-1);
                    stmt2.setString(2, f );
                    stmt2.executeUpdate();
                }
                stmt3.setString(1, codiceFiscale);
                stmt3.setInt(2, codiceRichiesta);
                stmt3.executeUpdate();
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
          
    }
        
    public ArrayList<Integer> listaRichieste(){
            
        ArrayList<Integer> lista=new ArrayList<>(); 
        try {
            PreparedStatement pst = c.prepareStatement ( "SELECT codice FROM \"Richiesta\" WHERE \"prescritta\"=false AND paziente IN (SELECT \"CodiceSanitario\" FROM \"Paziente\" WHERE \"Medico\"=?)" ); 
            pst.clearParameters(); 
            pst.setString(1, medico.getCodiceRegionale());
            ResultSet rs=pst.executeQuery ();      
            while(rs.next()){
                lista.add(rs.getInt("codice")); 
            }  
        }
        catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return lista;
    }
    
    public ArrayList<String> listaRichiesteConsorzio(){
            
        ArrayList<String> lista=new ArrayList<>(); 
        try {
            PreparedStatement pst = c.prepareStatement ("SELECT codice FROM \"Richiesta\" WHERE \"prescritta\"=false AND paziente IN (SELECT \"CodiceSanitario\" FROM \"Paziente\" where \"Medico\" IN (SELECT medico FROM \"MedicoDelConsorzio\" WHERE consorzio IN (SELECT \"consorzio\" FROM \"MedicoDelConsorzio\" WHERE \"medico\"=?)))" ); 
            pst.clearParameters();
            pst.setString(1, medico.getCodiceRegionale());
          
            ResultSet rs=pst.executeQuery ();      
            while(rs.next()){
                lista.add(rs.getInt("codice") + ""); 
            }  
        }
        catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return lista;
    }
    
    
    
    //funziona
    public ArrayList<String> farmaciPrescrittiDaMedicoNelPeriodo(String periodo){
        PreparedStatement pst;
        ArrayList<String> lista = new ArrayList<>();
        String sql="";
        if(periodo.equals("Ultimo mese")) 
            sql = "SELECT nomefarmaco, count(*) as occorrenze FROM \"Prescrizione\" JOIN \"FarmacoInRicetta\" ON \"Prescrizione\".codice = \"FarmacoInRicetta\".codiceprescrizione WHERE \"medico\"=? AND data > CURRENT_DATE - interval '31 day' GROUP BY nomefarmaco"; 
        else if(periodo.equals("Ultimo anno")){
            sql = "SELECT nomefarmaco, count(*) as occorrenze FROM \"Prescrizione\" JOIN \"FarmacoInRicetta\" ON \"Prescrizione\".codice = \"FarmacoInRicetta\".codiceprescrizione WHERE \"medico\"=? AND data > CURRENT_DATE - interval '1 year' GROUP BY nomefarmaco"; 
        }
        try{
            pst = c.prepareStatement ( sql );
            pst.clearParameters(); 
            pst.setString(1, medico.getCodiceRegionale());
            ResultSet rs=pst.executeQuery ();      
            while(rs.next()){
                lista.add(rs.getString("nomefarmaco")+" x "+rs.getString("occorrenze")); 
            }  
        }
        catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return lista;
    }
    
    public static ArrayList<String> listaRichiestePrescritte(Connection c){
            
        ArrayList<String> lista=new ArrayList<>(); 
        try {
            PreparedStatement pst = c.prepareStatement ( "SELECT codice FROM \"Richiesta\" WHERE \"prescritta\"=true " ); 
            pst.clearParameters(); 
          
            ResultSet rs=pst.executeQuery ();      
            while(rs.next()){
                lista.add(rs.getInt("codice") + ""); 
                System.out.println(rs.getInt("codice"));
            }  
        }
        catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return lista;
    }
        
  
    public Richiesta richiestaConAnagraficaEFarmaco(int codiceRichiesta){
            

        ArrayList<String> farmaci=null; 
       
        String paziente=ricavaDatiRichiesta(codiceRichiesta,c).getPaziente();
        farmaci=ricavaDatiRichiesta(codiceRichiesta,c).getFarmaci();
            
        Richiesta r=new Richiesta(paziente,codiceRichiesta,null,null,null,farmaci);
             
        try {
            PreparedStatement pst = c.prepareStatement ( "SELECT \"Cognome\",\"Nome\",\"DataNascita\" FROM \"Paziente\"  WHERE \"CodiceSanitario\"=?"); 

            pst.clearParameters(); 
            pst.setString(1, paziente);
           
            ResultSet rs=pst.executeQuery ();      
            while(rs.next()){
                r.setNomePaziente(rs.getString("Nome"));
                r.setCognomePaziente(rs.getString("Cognome"));
                r.setDataNascitaPaziente(rs.getString("DataNascita"));
            }  
        }
        catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return r;
    }
    
    //ok
    public int quantitaFarmacoPrescrittoDaUnMedicoNelPeriodo(String farmaco, int periodo){
        String p="";
        int risultato=0;
        switch(periodo){
            case 0: p="interval '1 month'";break;
            case 1: p="interval '3 month'";break;
            case 2: p="interval '6 month'";break;
            case 3: p="interval '1 year'";break;
            default: {
                try {
                    throw new Exception("periodo errato");
                } catch (Exception ex) {
                    Logger.getLogger(MedicoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String sql="SELECT count(*) as occorrenze FROM \"Prescrizione\" JOIN \"FarmacoInRicetta\" ON \"Prescrizione\".codice = \"FarmacoInRicetta\".codiceprescrizione WHERE \"medico\"=? AND data > CURRENT_DATE - "+p+" AND nomefarmaco = ?"; 
        try {
            PreparedStatement pst=c.prepareStatement(sql);
            pst.clearParameters(); 
            pst.setString(1, medico.getCodiceRegionale());
            pst.setString(2, farmaco);
            ResultSet rs=pst.executeQuery ();      
            while(rs.next()){
                risultato = rs.getInt("occorrenze");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return risultato;
    }
    
    public ArrayList<String> getFarmaciGenericiAcquistati(){
        ArrayList<String> listaRisultati= new ArrayList<>();
        String sql = "select \"Prescrizione\".codice as codice, \"Farmaco\".nome as nome, \"Prescrizione\".paziente as paziente from \"Acquisto\" join \"Farmaco\" on \"Acquisto\".farmaco = \"Farmaco\".nome join \"Prescrizione\" on \"Prescrizione\".codice = \"Acquisto\".prescrizione where medico = ? and generico = true";
        try {
            PreparedStatement pst=c.prepareStatement(sql);
            pst.clearParameters(); 
            pst.setString(1, medico.getCodiceRegionale());
            ResultSet rs=pst.executeQuery ();      
            while(rs.next()){
                listaRisultati.add("<html>Prescrizione n. "+rs.getString("codice")+"<br><table> farmaco: "+rs.getString("nome")+"<br> paziente: "+rs.getString("paziente")+"</html>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaRisultati;
    }

    
    public ArrayList<String> getFarmaciGenericiAcquistati(String paziente){
        ArrayList<String> listaRisultati= new ArrayList<>();
        String sql = "select \"Prescrizione\".codice as codice, \"Farmaco\".nome as nome from \"Acquisto\" join \"Farmaco\" on \"Acquisto\".farmaco = \"Farmaco\".nome join \"Prescrizione\" on \"Prescrizione\".codice = \"Acquisto\".prescrizione where medico = ? and generico = true and \"Prescrizione\".paziente = ?";
        try {
            PreparedStatement pst=c.prepareStatement(sql);
            pst.clearParameters(); 
            pst.setString(1, medico.getCodiceRegionale());

            pst.setString(2, paziente);
            ResultSet rs=pst.executeQuery ();      
            while(rs.next()){
                listaRisultati.add("<html>Prescrizione n. "+rs.getString("codice")+"<br><table> farmaco: "+rs.getString("nome")+"</html>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaRisultati;
        
    }
    
    public ArrayList<String> getlistaPazienti() {
        ArrayList<String> listaRisultati= new ArrayList<>();
        String sql = "select \"CodiceSanitario\" as codice from \"Paziente\" where \"Medico\" = ?" ;
        try {
            PreparedStatement pst=c.prepareStatement(sql);
            pst.clearParameters(); 
            pst.setString(1, medico.getCodiceRegionale());
            ResultSet rs=pst.executeQuery ();      
            while(rs.next()){
                listaRisultati.add(rs.getString("codice"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaRisultati;
    }

    public ArrayList<String> listaPrescrizioniNonUsateConData(String paziente) {
        ArrayList<String> listaPrescrizioni = new ArrayList<String>();
        try {
            String sql = "SELECT \"codice\", data FROM \"Prescrizione\" WHERE \"usata\"=false AND \"Prescrizione\".paziente =?";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, paziente);
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                listaPrescrizioni.add("Prescrizione n. "+rs.getInt("codice")+" prescritta il "+rs.getString("data"));
            }
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return listaPrescrizioni;
    }

    public ArrayList<String> getListaReazioniAvverseSegnalate() {
        ArrayList<String> listaReazioni = new ArrayList<String>();
        try {
            String sql = "SELECT \"Reazione\".descrizione as descrizione, \"ReazioneFarmaco\".farmaco as farmaco, \"Reazione\".titolo as titolo   FROM \"ReazioneFarmaco\" JOIN \"Reazione\" ON \"ReazioneFarmaco\".reazione = \"Reazione\".titolo ORDER BY farmaco ";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                if(rs.getString("descrizione") == null){
                    listaReazioni.add("<html>Farmaco: "+rs.getString("farmaco")+"<table><br>Reazione: "+rs.getString("titolo")+"<br><html>");
                }
                else{
                    listaReazioni.add("<html>Farmaco: "+rs.getString("farmaco")+"<table><br>Reazione: "+rs.getString("titolo")+"<br>Descrizione: "+rs.getString("descrizione")+"<br><html>");
                }
            }
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return listaReazioni;
    }

    private boolean autentica(String codiceRegione, String password){
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
    
    private String getCodiceSostituito(String sostituto){
        String risultato="";
        try {
            
            String sql = "SELECT sostituito FROM \"MedicoSostituto\" WHERE sostituto=? ";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, sostituto);
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                risultato = rs.getString("sostituito");
            }
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        } 
        return risultato;
    }
    
    public ArrayList<String> getFattoriDiRischio(String paziente) {
         ArrayList<String> listaRisultati= new ArrayList<>();
        String sql = "select \"fattore\" from \"FattoreRischioPaziente\" where paziente=?";
        try {
            PreparedStatement pst=c.prepareStatement(sql);
            pst.clearParameters(); 
            pst.setString(1, paziente);
            ResultSet rs=pst.executeQuery ();      
            while(rs.next()){
                listaRisultati.add(rs.getString("fattore"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaRisultati;
    }

    public void impostaRischioPrescrizione() {
        try {
            PreparedStatement stmt;
            stmt = c.prepareStatement("UPDATE \"Prescrizione\" SET rischio=true WHERE codice = ?  ");
           
            stmt.setInt(1, ultimaPrescrizione(c)-1);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Prescrizione modificata");
            
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public void aggiornaDbConFarmaciContrastanti() {
        
         try {
            PreparedStatement stmt;
            stmt = c.prepareStatement("INSERT INTO \"FarmaciContrastanti\" (farmaco1,farmaco2, prescrizione) VALUES (?, ?, ?)");
             
            System.out.println((farmaciInContrastoOrdinati(mv.getFrameP().getFarmaciContrastanti())).get(0));
            
            stmt.setString(1, (farmaciInContrastoOrdinati(mv.getFrameP().getFarmaciContrastanti())).get(0));
            stmt.setString(2, (farmaciInContrastoOrdinati(mv.getFrameP().getFarmaciContrastanti())).get(1));
            stmt.setInt(3, ultimaPrescrizione(c)-1);
           
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Farmaci in contrasto inseriti");
            
        } catch (SQLException e) {
            System.out.println("Errore nell'estrazione dei dati");
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
             
        }
        
    }

    private ArrayList<String> farmaciInContrastoOrdinati(ArrayList<String> farmaciContrastanti) {
                  
       ArrayList<String> res=new ArrayList<>();   //primo minore
       
      if( (farmaciContrastanti.get(0)).compareToIgnoreCase((farmaciContrastanti.get(1))) < 0  ) {
          System.out.println(farmaciContrastanti.get(0));        
           
          res.add((farmaciContrastanti.get(0))); 
          res.add((farmaciContrastanti.get(1))); 
      }
      else {
          res.add((farmaciContrastanti.get(1)));
          res.add((farmaciContrastanti.get(0)));  
      }
       
       return res;
    }

}
