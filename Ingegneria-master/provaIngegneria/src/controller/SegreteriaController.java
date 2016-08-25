package controller;
import View.LoginSegreteria;
import View.MedicoView;
import View.SegreteriaView;
import static controller.MedicoController.numeroPrescrizioni;
import java.sql.Connection;
import java.sql.Date;
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
    
    private Connection c=null;
    private Segreteria segreteria=null;
    private SegreteriaView sv;
    private LoginSegreteria login;

    
    public SegreteriaController(Connection c,Segreteria segreteria){
        this.c=c;
        this.segreteria=segreteria;
    }
    
    public static Medico medicoDelPaziente(String codiceFiscale, Connection c){
        
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
    private int prossimaRichiesta(){
        
        String nRichieste=null;
        int max=0;
        try {
            String sql = "SELECT codice as num FROM \"Richiesta\"";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            ResultSet rs=pst. executeQuery ();      
            while(rs.next()){
                 nRichieste=rs.getString("num");
                 if(Integer.parseInt(nRichieste)> max)
                     max=Integer.parseInt(nRichieste);
            }
        }
        
        catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        
        return max+1;
    }
    //ok
    public void inviaRichiestaPrescrizione(String codiceFiscale,ArrayList<String> farmaci){
        String n=prossimaRichiesta() + "";
        if(farmaci.size()>0){
            try {
                PreparedStatement stmt;
                PreparedStatement stmt2;
                stmt = c.prepareStatement("INSERT INTO \"Richiesta\" (paziente,codice) VALUES (?, ? )");
                stmt2 = c.prepareStatement("INSERT INTO \"farmacoInRichiesta\" (codicerichiesta,nomefarmaco) VALUES (?, ?)");
          
                System.out.println(SegreteriaController.medicoDelPaziente(codiceFiscale,c));
                if(((SegreteriaController.medicoDelPaziente(codiceFiscale,c)).listaPazienti()).contains(codiceFiscale)) {
                
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
        else{
            System.out.println("Nessun farmaco selezionato");
        }
    }
    //controllo se la la richesta è stat prescritta e se il medico è di questa segreteria
    //FAI: imposta le richieste del medicoview //modifica con prescritta
    //versione vecchia: tutto da modificare 
    /*
    public ArrayList<String> prescrizioniDaConsegnare(){
        
        ArrayList<String> prescrizioni=new ArrayList<>();
         try {
           
           PreparedStatement stmt;
           stmt = c.prepareStatement(" SELECT \"Richiesta.codice\" FROM \"Richiesta\" join \"Prescrizione\" on \"Richiesta.codice\"=\"CodicePrescrizione\" join \"Medico\" on \"CodiceRegione\"=\"medico\" join \"Segreteria\" on \"codicesegreteria\"=\"Segreteria.codice\" WHERE \"codice\"=? ");
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
    */
    
    public ArrayList<Prescrizione> prescrizioniDaConsegnareComePrescrizione(){
        
        ArrayList<Prescrizione> prescrizioni=new ArrayList<>();
        try {
           
           PreparedStatement stmt;
           stmt = c.prepareStatement("SELECT codice, \"paziente\" FROM \"Richiesta\"  WHERE \"prescritta\"=true and \"consegnata\"=false");//and segreteria giusta...
           stmt.clearParameters();
           //stmt.setString(1, segreteria.getCodiceSegreteria()); 
           
           ResultSet rs=stmt.executeQuery ();      
            while(rs.next()){
                //System.out.println(rs.getString("paziente")+" "+rs.getString("codice"));
                prescrizioni.add(new Prescrizione(rs.getString("paziente"),rs.getString("codice")));
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
           stmt = c.prepareStatement("UPDATE \"Richiesta\" SET consegnata=true WHERE \"codice\"=?");
            
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
