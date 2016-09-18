package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import View.*;
import model.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Valentina
 */
public class MedicoController {
    
    
    private Connection c=null;
    private final LoginMedico login;
    private final Medico medico;
    private MedicoView mv;
    private boolean autenticato;

    
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
       
   public Connection getConnection(){
        return c;
   }   
    
    
    public boolean autenticazione(String codiceRegione, String password){
        int occorrenze=0;
        try {
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
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        } 
        return occorrenze>0;
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
                listaRichieste().stream().forEach((r) -> {
                    richieste.add(richiestaConAnagraficaEFarmaco(r));
                });
           
                mv=new MedicoView(richieste,this);
                mv.setResizable(false);
                mv.setVisible(true);
                
                login.dispose();
            }
             
        }
        else{
            login.setErrore("Autenticazione fallita");
        }
    }
    
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
        ArrayList<String> risultato = new ArrayList<>();
        System.out.println(indici.length);
        for(int i=0;i<indici.length;i++){
            risultato.add(farmaci.get(indici[i]));
        }
        return risultato;
    }

    public ArrayList<String> impostaDatiPerPrescrizione(String pazienteCF){
        
        ArrayList<String> dati = new ArrayList<>();
        
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
    
    
    public ArrayList<String> pazientiPerFarmaco(String farmaco) { 
        ArrayList<String> listaPazienti = new ArrayList<>();
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
        ArrayList<String> listaReazioni = new ArrayList<>();
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
        ArrayList<Integer> listaPrescrizioni = new ArrayList<>();
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
        ArrayList<String> listaPrescrizioni = new ArrayList<>();
        String s;
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
  
    public ArrayList<String> farmacoPerPaziete(String codiceFiscale, int periodo) {
        String p="";
        ArrayList<String> risultato = new ArrayList<>();
        switch(periodo){
            case 0: p=""; break;
            case 1: p="AND data > CURRENT_DATE - interval '1 month'";break;
            case 2: p="AND data > CURRENT_DATE - interval '3 month'";break;
            case 3: p="AND data > CURRENT_DATE - interval '6 month'";break;
            case 4: p="AND data > CURRENT_DATE - interval '1 year'";break;
            default: {
                try {
                    throw new Exception("periodo errato");
                } catch (Exception ex) {
                    Logger.getLogger(MedicoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String sql="SELECT nomefarmaco, count(*) as occorrenze FROM \"Prescrizione\" JOIN \"FarmacoInRicetta\" ON \"Prescrizione\".codice = \"FarmacoInRicetta\".codiceprescrizione WHERE \"medico\"=? AND paziente=? "+p+" GROUP BY nomefarmaco ORDER BY nomefarmaco"; 
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
            stmt = c.prepareStatement("INSERT INTO \"Prescrizione\" (Codice,Paziente,Medico,Data, datascadenza,consegnata) VALUES (?, ?, ?,CURRENT_DATE, CURRENT_DATE+ interval '6 month',true)");
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
        }
        
                  
    }
        
        
    public static Richiesta ricavaDatiRichiesta(int codiceRichiesta, Connection c){
            
        Richiesta r=new Richiesta(null,codiceRichiesta,null,null,null,new ArrayList<>());
           
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
            
        ArrayList<String> farmaci; 
  
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
                if(login.getAvvio().getLoginSegreteria() != null){
                    login.getAvvio().getLoginSegreteria().getSegreteriaController().getSegreteriaView().impostaListaRichieste();
                }
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
            PreparedStatement pst = c.prepareStatement ( "SELECT codice FROM \"Richiesta\" WHERE \"prescritta\"=false AND paziente IN (SELECT \"CodiceSanitario\" FROM \"Paziente\" WHERE \"Medico\"=?) ORDER BY codice" ); 
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
            

        ArrayList<String> farmaci; 
       
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
            case 0: p=""; break;
            case 1: p="AND data > CURRENT_DATE - interval '1 month'";break;
            case 2: p="AND data > CURRENT_DATE - interval '3 month'";break;
            case 3: p="AND data > CURRENT_DATE - interval '6 month'";break;
            case 4: p="AND data > CURRENT_DATE - interval '1 year'";break;
            default: {
                try {
                    throw new Exception("periodo errato");
                } catch (Exception ex) {
                    Logger.getLogger(MedicoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String sql="SELECT count(*) as occorrenze FROM \"Prescrizione\" JOIN \"FarmacoInRicetta\" ON \"Prescrizione\".codice = \"FarmacoInRicetta\".codiceprescrizione WHERE \"medico\"=? "+p+" AND nomefarmaco = ?"; 
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
        ArrayList<String> listaPrescrizioni = new ArrayList<>();
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
        ArrayList<String> listaReazioni = new ArrayList<>();
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
        
        
        for (int i=0; i< (mv.getFrameP().getFarmaciContrastanti()).size() ;i=i+2){
            try {
                PreparedStatement stmt;
                stmt = c.prepareStatement("INSERT INTO \"FarmaciContrastanti\" (farmaco1,farmaco2, prescrizione) VALUES (?, ?, ?)");
                         
                stmt.setString(1, (mv.getFrameP().getFarmaciContrastanti()).get(i));
                stmt.setString(2, (mv.getFrameP().getFarmaciContrastanti()).get(i+1));
                stmt.setInt(3, ultimaPrescrizione(c)-1);
           
                stmt.executeUpdate();
                stmt.close();
                System.out.println("Farmaci in contrasto inseriti");
            
            } 
            
            catch (SQLException e) {
                System.out.println("Errore nell'estrazione dei dati");
                System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                System.exit(0);            
            }
        }
        
    }
    
    public ArrayList<String> getFarmaciContrastanti(){
        ArrayList<String> risultato=new ArrayList<>();
        try {
            
            String sql = "SELECT * FROM \"FarmaciContrastanti\" ";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                risultato.add("<html>Prescrizione n°: "+rs.getInt("prescrizione")+"<br><table>"+rs.getString("farmaco1")+", "+rs.getString("farmaco2")+"</html>");
            }
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        } 
        return risultato;
    }

    public boolean getAutenticato() {
       return autenticato;
    }
}
