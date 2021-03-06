package controller;
import View.LoginSegreteria;
import View.SegreteriaView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Medico;
import model.Prescrizione;
import model.Segreteria;
/**
 *
 * @author Valentina
 */
public class SegreteriaController {
    
    private final Connection c;
    private final Segreteria segreteria;
    private final LoginSegreteria login;
    private boolean autenticato;
    private SegreteriaView segreteriaView;
    
    public SegreteriaController(Connection c,Segreteria segreteria,LoginSegreteria login){
        this.c=c;
        this.segreteria=segreteria;
        this.login=login;
    }
    
    
    public static Medico medicoDelPaziente(String codiceFiscale, Connection c){
        
        Medico m=new Medico(c,null,null);
        
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
    private int prossimaRichiesta(){
        
        int nRichieste;
        int max=0;
        try {
            String sql = "SELECT codice as num FROM \"Richiesta\"";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            ResultSet rs=pst. executeQuery ();      
            while(rs.next()){
                 nRichieste=rs.getInt("num");
                 if(nRichieste> max)
                     max=nRichieste;
            }
        }
        
        catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        
        return max+1;
    }
    //ok
    public void inviaRichiestaPrescrizione(String codiceFiscale,ArrayList<String> farmaci){
        int n=prossimaRichiesta();
        if(farmaci.size()>0){
            try {
                PreparedStatement stmt;
                PreparedStatement stmt2;
                stmt = c.prepareStatement("INSERT INTO \"Richiesta\" (paziente,codice) VALUES (?, ? )");
                stmt2 = c.prepareStatement("INSERT INTO \"farmacoInRichiesta\" (codicerichiesta,nomefarmaco) VALUES (?, ?)");
          
                
                stmt.clearParameters();
                stmt.setString(1, codiceFiscale);        
                stmt.setInt(2, n);
                stmt.executeUpdate();
                
                for(String f :farmaci) {
                    stmt2.clearParameters();
                    stmt2.setInt(1, n);        
                    stmt2.setString(2, f);
                    stmt2.executeUpdate();
                }
                System.out.println("Richiesta prescrizione inserita");
                stmt.close();              
            } catch (SQLException e) {
                System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            }
        }
        else{
            System.out.println("Nessun farmaco selezionato");
        }
    }
    
    public ArrayList<Prescrizione> prescrizioniDaConsegnareComePrescrizione(){
        
        ArrayList<Prescrizione> prescrizioni=new ArrayList<>();
        try {
           
           PreparedStatement stmt;
           stmt = c.prepareStatement("SELECT DISTINCT \"Prescrizione\".codice as codice, \"Richiesta\".\"paziente\" as paziente FROM \"Richiesta\" JOIN \"Prescrizione\" ON \"Richiesta\".codice = \"Prescrizione\".\"CodiceRichiesta\" WHERE \"prescritta\"=true and \"consegnata\"=false AND medico IN (SELECT DISTINCT \"CodiceRegione\" FROM \"Medico\" WHERE codicesegreteria=?) ORDER BY codice");
           stmt.clearParameters();
           stmt.setString(1, segreteria.getCodiceSegreteria()); 
           
           ResultSet rs=stmt.executeQuery ();      
            while(rs.next()){
                //System.out.println(rs.getString("paziente")+" "+rs.getString("codice"));
                prescrizioni.add(new Prescrizione(rs.getString("paziente"),rs.getInt("codice")));
            }     
            stmt.close();   
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        
        return prescrizioni;
    }

    
    //ok
    public void consegnaPrescrizione(String codicePrescrizione){
 
        try {
            PreparedStatement stmt;
            stmt = c.prepareStatement("UPDATE \"Prescrizione\" SET consegnata=true WHERE \"codice\"=?");
            stmt.clearParameters();
            stmt.setInt(1, Integer.parseInt(codicePrescrizione));          
            stmt.executeUpdate();
            stmt.close();   
        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }
    
    public static boolean controlloPresenzaPaziente(Connection c, String codiceSanitario){
        int quantita=0;
        try {
            String sql = "SELECT count(*) as cont FROM \"Paziente\" WHERE \"CodiceSanitario\"=?";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();      
            pst.setString(1, codiceSanitario);

            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                quantita=rs.getInt("cont");
            }
        } catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e.getMessage () );
        }
        return quantita>0;
    }
    
    public void autentica(){
        String codice = login.getJTextFieldCodiceSegreteria().getText();
        autenticato = false;
        
        if(autenticazione(codice)){
            login.setSegreteria();
            System .out. println (" Autenticato" );
            segreteriaView = new SegreteriaView(this, c, login.getAvvio());
            segreteriaView.setResizable(false);
            segreteriaView.setVisible(true);
            login.dispose(); 
            autenticato = true;
            
        }
        else{
            login.getJLabelErrore().setText("Codice segreteria non trovato");
        }      
    }
    
    public boolean autenticazione(String codice){
        int numRisultati=0;
        try {
            PreparedStatement stmt;
            stmt = c.prepareStatement("SELECT COUNT(*) as num FROM \"Segreteria\" WHERE codice = ?");
            stmt.setString(1, codice);
            ResultSet rs=stmt.executeQuery ();      
            while(rs.next()){
                numRisultati=rs.getInt("num");
            }
            System.out.println(numRisultati);
        }
        catch (SQLException e){
            System.err.println("Errore esecuzione query");
        }
        return numRisultati == 1;
    }

    public boolean getAutenticato() {
        return autenticato;
    }

    public SegreteriaView getSegreteriaView(){
        return segreteriaView;
    }
    
    public void updateMedicoView(){
        if(login.getAvvio().getLoginMedico() != null)
                    login.getAvvio().getLoginMedico().getMedicoController().getMedicoView().aggiornaLista1();
    }
}
