package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import SistemaPrescrizioniMain.Avvio;
import View.*;
import controller.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.Action;
import model.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Valentina
 */
public class LoginJUnitTest {
    
   private final Connection connection;

    public LoginJUnitTest() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ingegneria","postgres","123");
    }
     
    @Test
     public void testLoginSegreteria() throws SQLException {
        Avvio avvio=new Avvio();
        LoginSegreteria login=new LoginSegreteria(connection,avvio);
        Segreteria segreteria=new Segreteria(connection,null);
        SegreteriaController sc=new SegreteriaController(connection,segreteria,login);
        login.getJTextFieldCodiceSegreteria().setText("1");
        assertTrue(login.simulaPressioneBottone());
     }
     
    @Test
     public void testLoginSegreteriaFail() throws SQLException {
        Avvio avvio=new Avvio();
        LoginSegreteria login=new LoginSegreteria(connection,avvio);
        Segreteria segreteria=new Segreteria(connection,null);
        SegreteriaController sc=new SegreteriaController(connection,segreteria,login);
        login.getJTextFieldCodiceSegreteria().setText("10");
        assertFalse(login.simulaPressioneBottone());
         
     }
   
     
    @Test
     public void testLoginMedicoFail() throws SQLException {
       
        LoginMedico login=new LoginMedico(connection);
        Medico medico=new Medico(connection,null,null);
        MedicoController mc=new MedicoController(connection,medico,login);
        login.getJTextField1().setText("10");
        login.getJPassWordField2().setText("10maco");
        
        assertFalse( login.simulaPressioneBottone());
        
    }
     
     
    @Test
     public void testLoginFarmacia() throws SQLException {
        
        LoginFarmacia login=new LoginFarmacia(connection);
       
        login.getCampoCitta().setText("verona");
        login.getCampoIndirizzo().setText("via mazzini");
        login.getCampoCAP().setText("37100");
          
        assertTrue( Farmacia.controlloPresenzaFarmacia(connection, login.getCampoCitta().getText(), login.getCampoIndirizzo().getText(),  login.getCampoCAP().getText()));
        
    } 
    
}
