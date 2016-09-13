/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import SistemaPrescrizioniMain.Avvio;
import View.LoginSegreteria;
import controller.SegreteriaController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Medico;
import model.Segreteria;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Valentina
 */
public class SegreteriaJUnitTest {
    
    private final Connection connection;

    public SegreteriaJUnitTest() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ingegneria","postgres","123");
    }
    
    @Test
    public void testControlloPresenzaPaziente(){       
        assertTrue(SegreteriaController.controlloPresenzaPaziente(connection, "NPLVNT94D43F861Q"));
    }
    
    private ArrayList<Integer> listaRichiesteDelMedico(String m){
            
        ArrayList<Integer> lista=new ArrayList<>(); 
        try {
            PreparedStatement pst = connection.prepareStatement ( "SELECT codice FROM \"Richiesta\" WHERE \"prescritta\"=false AND paziente IN (SELECT \"CodiceSanitario\" FROM \"Paziente\" WHERE \"Medico\"=?)" ); 
            pst.clearParameters(); 
            pst.setString(1, m);
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
    
    @Test
    public void testCreaRichiestaPrescrizione(){   
        
        Avvio avvio=new Avvio();
        LoginSegreteria login=new LoginSegreteria(connection,avvio);
        Segreteria segreteria=new Segreteria(connection,null);
        SegreteriaController sc=new SegreteriaController(connection,segreteria,login);
        ArrayList<String> farmaci=new ArrayList<>();
        farmaci.add("Oki");
        
        int r=listaRichiesteDelMedico("1").size();
        sc.inviaRichiestaPrescrizione("NPLVNT94D43F861Q", farmaci);
        int r2=listaRichiesteDelMedico("1").size();
        assertTrue(r2==r+1);
    }
    
    @Test
    public void testMedicoDelPazinte(){   
        
        Avvio avvio=new Avvio();
        LoginSegreteria login=new LoginSegreteria(connection,avvio);
        Segreteria segreteria=new Segreteria(connection,null);
        SegreteriaController sc=new SegreteriaController(connection,segreteria,login);
        Medico medico=new Medico(connection,"2","20maco");
        
        Medico medicopaz=SegreteriaController.medicoDelPaziente("TRNVTR93D22Z138G", connection);
              
        assertTrue(medico.getCodiceRegionale().equals(medicopaz.getCodiceRegionale()));
        
    }
    
    
}
