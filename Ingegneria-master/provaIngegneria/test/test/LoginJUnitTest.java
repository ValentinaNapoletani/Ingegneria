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
     
    @Test
     public void testLoginSegreteria() throws SQLException {
        Connection connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ingegneria","postgres","123123");
        Avvio avvio=new Avvio();
        LoginSegreteria login=new LoginSegreteria(connection,avvio);
        Segreteria segreteria=new Segreteria(connection,"1");
        SegreteriaController sc=new SegreteriaController(connection,segreteria,login);
        assertTrue(sc.autenticazione("1"));
         
     }
     
    @Test
     public void testLoginSegreteriaFail() throws SQLException {
        Connection connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ingegneria","postgres","123123");
        Avvio avvio=new Avvio();
        LoginSegreteria login=new LoginSegreteria(connection,avvio);
        Segreteria segreteria=new Segreteria(connection,null);
        SegreteriaController sc=new SegreteriaController(connection,segreteria,login);
        assertFalse(sc.autenticazione("12f"));
         
     }
   
     
    @Test
     public void testLoginMedicoFail() throws SQLException {
        Connection connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ingegneria","postgres","123123");
        LoginMedico login=new LoginMedico(connection);
        Medico medico=new Medico(connection,null,null);
        MedicoController mc=new MedicoController(connection,medico,login);
        login.getJTextField1().setText("1");
        login.getJPassWordField2().setText("10maco");
        login.simulaPressioneBottone();
        boolean pr=mc.getAutenticato();
        assertTrue(mc.getAutenticato());
        
    }
     
}
