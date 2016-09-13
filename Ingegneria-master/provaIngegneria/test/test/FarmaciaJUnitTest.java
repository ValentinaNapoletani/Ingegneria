/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.Farmacia;
import controller.SegreteriaController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
public class FarmaciaJUnitTest {
    
    private final Connection connection;

    public FarmaciaJUnitTest() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ingegneria","postgres","123");
    }
    
    
    @Test
    public void testFarmaci(){   
        
        Farmacia f=new Farmacia(connection,"via mazzini","37100","verona");
        
        
        int num=f.numeroPezziFarmacoDisponibili("Buscopan");
        f.ordinaFarmaco("Buscopan", 2);
              
        assertTrue(f.numeroPezziFarmacoDisponibili("Buscopan")==num+2);
        
        for(int i=0;i<f.numeroPezziFarmacoDisponibili("Buscopan");i++)
            f.compraFarmaco("Buscopan", 37);
        
        boolean b= f.controlloPresenzaFarmaco("Buscopan",1);
        assertFalse(b);
    }

    
    
}
