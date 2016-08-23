package controller;
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

/**
 *
 * @author Valentina
 */
public class MedicoController {
    
    
    private Connection c=null;
    private LoginMedico login;
    private Medico medico;
    private MedicoView mv;

    
    public MedicoController(Connection c,Medico m,LoginMedico login){
        this.c=c;
        this.medico=m; 
        this.login=login;
    }
    
    public LoginMedico getMedicoView() {
        return login; 
    }

    //public void setMedicoview(MedicoView medicoView) { 
     //   this.medicoView = medicoView;            
   // }
       
      
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
        
        String user = login.getUser().getText();
        String password = login.getPassword().getText();
        ArrayList<Richiesta> richieste=new ArrayList<>();
        
        if(autenticazione(user,password)){
           login.setMedico();
              System .out. println (" Autenticato" );
           // model.setConfiguration(new ChessBoard(1));
  
           for (String r: listaRichieste()){
               richieste.add(richiestaConAnagraficaEFarmaco(r));
           }
           
           mv=new MedicoView(richieste,this);
           mv.setVisible(true);
          // mv.setConfiguration();
           login.dispose();  }
    }
    
    public String oggettoSelezionato(int i,ArrayList<String> s){
       System .out. println (" indice elem selezionato : " + i );
       String richiesta=null;
        if(i>0){         
           if(s.get(i).substring(1,2)=="0" || s.get(i).substring(1,2)=="1" || s.get(i).substring(1,2)=="2" || s.get(i).substring(1,2)=="3" || s.get(i).substring(1,2)=="4" || s.get(i).substring(1,2)=="5" || s.get(i).substring(1,2)=="6" || s.get(i).substring(1,2)=="7" || s.get(i).substring(1,2)=="8" || s.get(i).substring(1,2)=="9"){
                richiesta=s.get(i).substring(0, 2);       
                System .out. println (" elem selezionato : " + richiesta );
           }
           else {
               richiesta=s.get(i).substring(0, 1);       
               System .out. println (" elem selezionato : " + richiesta );
           }
        }
          
       return richiesta; 
      
    }
    		
    public Prescrizione getPrescrizionenonusata() {     
	 	 return null; 
    }

    public ArrayList<String> pazientiPerFarmaco(String farmaco) { 
        ArrayList<String> listaPazienti = new ArrayList<String>();
        try {
            String sql = "SELECT DISTINCT \"nome\" FROM \"FarmacoInRicetta\" JOIN \"Prescrizione\" ON \"FarmacoInRicetta\".\"codiceprescrizione\"=\"Prescrizione\".\"codice\" WHERE \"nomefarmaco\"=?";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, farmaco);
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                listaPazienti.add(rs.getString("nome"));
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

    public Prescrizione verificaUsoPrescrizione() { 
        
		return null;
    }
    
    public ArrayList<String> listaPrescrizioniNonUsate(){
        ArrayList<String> listaPrescrizioni = new ArrayList<String>();
        try {
            String sql = "SELECT \"codice\" FROM \"Prescrizione\" WHERE \"usata\"=false";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                listaPrescrizioni.add(rs.getString("codice"));
            }
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return listaPrescrizioni;
        
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
    
    public void effettuaPrescrizioneConVisita(String codiceFiscale, ArrayList<String> farmaci){ 
                
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
                  
    }
        
        
    private Richiesta ricavaDatiRichiesta(String codiceRichiesta){
            
        Richiesta r=new Richiesta(null,codiceRichiesta,null,null,null,new ArrayList<String>());
           
        try {
            PreparedStatement pst = c.prepareStatement ( "SELECT \"paziente\",\"nomefarmaco\",\"codice\" FROM \"Richiesta\" join \"farmacoInRichiesta\" on codice=codicerichiesta WHERE codice=?"); 

            pst.clearParameters(); 
            pst.setString(1, codiceRichiesta);
           
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
        
    //modfica richiesta modello per ri settere modello lista dopo che il medico fa prescrizione(ma non è ancore tolta da ricieste) nuova lista basata sul modello non su query
    public void effettuaPrescrizioneSuRichiesta(String codiceRichiesta){ 
             
        String n=numeroPrescrizioni() + "";
        ArrayList<String> farmaci=null; 
  
        farmaci=(ricavaDatiRichiesta(codiceRichiesta).getFarmaci());
        String codiceFiscale=ricavaDatiRichiesta(codiceRichiesta).getPaziente();
 
        try {
            PreparedStatement stmt;
            PreparedStatement stmt2;
            PreparedStatement stmt3;
            stmt = c.prepareStatement("INSERT INTO \"Prescrizione\" (Codice,Paziente,Medico,Data,\"CodiceRichiesta\") VALUES (?, ?, ?,CURRENT_DATE,?)");
            stmt2 = c.prepareStatement("INSERT INTO \"FarmacoInRicetta\" (codiceprescrizione,nomefarmaco,tipoacquisto) VALUES (?, ?, ?)");
            stmt3 = c.prepareStatement("UPDATE \"Richiesta\" SET prescritta=true WHERE paziente = ? AND codice = ? ");
            stmt.clearParameters();
            stmt.setString(1, n);
            stmt.setString(2, codiceFiscale);
            stmt.setString(3, medico.getCodiceRegionale());  
            stmt.setString(4, codiceRichiesta);
            if(medico.listaPazienti().contains(codiceFiscale)) {
                stmt.executeUpdate();
            
                for(String f :farmaci) {
                    stmt2.clearParameters(); 
                    stmt2.setString(1, n);
                    stmt2.setString(2, f );
                    stmt2.setString(3, null);
                    stmt2.executeUpdate();
                }
                stmt3.setString(1, codiceFiscale);
                stmt3.setString(2, codiceRichiesta);
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
        //rimuovi dalla lista l'elemento selezionato
        //mv.setLista(mv.getLista().remove
        //(mv.getLista().getSelectedIndex()));
        
         mv.getLista().setModel(new javax.swing.AbstractListModel<String>() {
             
            public int getSize() { return mv.getRichiesteNonPrescritte().size(); }
            public String getElementAt(int i) { return mv.getRichiesteNonPrescritte().get(i); }
        });
        
        
    }
        
    public ArrayList<String> listaRichieste(){
            
        ArrayList<String> lista=new ArrayList<>(); 
        try {
            PreparedStatement pst = c.prepareStatement ( "SELECT codice FROM \"Richiesta\" WHERE \"prescritta\"=false " ); 
            pst.clearParameters(); 
          
            ResultSet rs=pst.executeQuery ();      
            while(rs.next()){
                lista.add(rs.getString("codice")); 
            }  
        }
        catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return lista;
    }
        
    public Richiesta richiestaConAnagraficaEFarmaco(String codiceRichiesta){
            

        ArrayList<String> farmaci=null; 
       
        String paziente=ricavaDatiRichiesta(codiceRichiesta).getPaziente();
        farmaci=ricavaDatiRichiesta(codiceRichiesta).getFarmaci();
            
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
    
    public void configurationChange(){
    }
}
