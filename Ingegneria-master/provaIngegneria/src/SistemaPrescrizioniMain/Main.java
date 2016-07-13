/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaPrescrizioniMain;
import java.sql.Connection;
import java.sql.DriverManager;
import controller.*;
import java.awt.EventQueue;
import java.util.ArrayList;
import model.Medico;

public class Main {
    
    public Medico medico;
    
    private static Connection c = null;

	public Medico getMedico() {
	 	 return medico; 
	}

	public void setMedico(Medico medico) { 
		 this.medico = medico; 
        }
        
        public static void creaConnessione(){
         
            try {
            Class.forName("org.postgresql.Driver");
            c=DriverManager.getConnection("jdbc:postgresql://192.168.1.11:5432/Ingegneria","postgres","123");
            }    
            catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
             System.exit(0);
            }
            System.out.println("Opened database successfully");
         }
        
        public Connection getConnessione(){       
            return c;
        }        
    
    public static void main(String[] args) {  
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                
                creaConnessione();
                Segreteria segreteria=new Segreteria(c);  
                Medico medico=new Medico(c,"1","10maco");
                MedicoController mc=new MedicoController(c,medico);
                boolean risultato = mc.autenticazione("1", "10maco");
                System.out.println("Risultato autenticazione: "+risultato);
                ArrayList<String> f=new ArrayList<>();
                f.add("Oki");
                f.add("Aspirina");
                segreteria.inviaRichiestaPrescrizione("NPLVNT94D43F861Q",f);
                Farmacia farmacia = new Farmacia(c, "via Mazzini","37100");
                farmacia.compraFarmaco("NPLVNT94D43F861Q","1", 1, "Aspirina");
               // mc.effettuaPrescrizioneConVisita("NPLVNT94D43F861Q", f);
               // mc.effettuaPrescrizioneSuRichiesta("2");
                
                
               
            }
        });
    }
      
}

