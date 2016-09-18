/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import SistemaPrescrizioniMain.Avvio;
import View.LoginMedico;
import controller.MedicoController;
import controller.SegreteriaController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Medico;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Valentina
 */
public class MedicoJUnitTest {
    
    private final Connection connection;

    public MedicoJUnitTest() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ingegneria","postgres","123");
    }
    
    @Test
    public void testPazientiPerFarmaco(){     
        
        Avvio avvio=new Avvio();
        LoginMedico login=new LoginMedico(connection,avvio);
        Medico medico=new Medico(connection,"1",null);
        MedicoController mc=new MedicoController(connection,medico,login);
        
        assertTrue(mc.pazientiPerFarmaco("Buscopan").contains("Valentina Napoletani"));
    }
    
    @Test
    public void testPrescrizioneSuRichiesta(){     
        
        Avvio avvio=new Avvio();
        LoginMedico login=new LoginMedico(connection,avvio);
        Medico medico=new Medico(connection,"1",null);
        MedicoController mc=new MedicoController(connection,medico,login);
             
        int index=(int)(Math.random()*(mc.listaRichieste().size()-1));
      
        mc.effettuaPrescrizioneSuRichiesta(mc.listaRichieste().get(index));
         
        assertTrue(mc.listaPrescrizioniNonUsate().contains(MedicoController.ultimaPrescrizione(connection)-1) );
        
    }
}
