/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaPrescrizioniMain;
import View.*;
import java.sql.Connection;
import java.sql.DriverManager;
import controller.*;
import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import model.Medico;
import model.Segreteria;

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
            c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ingegneria","postgres","123");
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
                
                LoginMedico login=new LoginMedico(c);
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                login.setVisible(true);
                
                Segreteria segreteria=new Segreteria(c,"111"); 
                SegreteriaController segreteriaController=new SegreteriaController(c,segreteria);  
                Medico medico=new Medico(c,"1","10maco");
                MedicoController mc=new MedicoController(c,medico,login);
                ArrayList<String> f=new ArrayList<>();
                f.add("Oki");
                f.add("Aspirina");
                segreteriaController.inviaRichiestaPrescrizione("NPLVNT94D43F861Q",f);
               // mc.effettuaPrescrizioneConVisita("NPLVNT94D43F861Q", f);
                //mc.effettuaPrescrizioneSuRichiesta("1");               
                
                
                
               
            }
        });
    }
      
}

