/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Valentina
 */
public class Medico{
    
  //private ArrayList<String> pazienti=new ArrayList<>();
  private Connection c=null;
  private String codiceRegionale;
  private String password;
  
  public Medico(Connection c,String codiceRegionale,String password){
         this.c=c;
         this.codiceRegionale=codiceRegionale;
         this.password=password;
         
  } 
  
  public Medico(Connection c){
         this.c=c;    
  } 
  
  public void setCodiceRegionale(String codiceRegionale) { 
      this.codiceRegionale=codiceRegionale;           
  }
  
  public String getCodiceRegionale() { 
      return codiceRegionale;          
  }
  
   public String getPassword() { 
      return password;          
  }
  
 /* public void setListaPazienti(ArrayList<String> listaPazienti) { 
     this.pazienti=listaPazienti;          
  }*/
  
  /*public ArrayList<String> getListaPazienti() { 
     return pazienti;          
  }*/
   
   public ArrayList<String> listaPazienti(){
            ArrayList<String> pazienti=new ArrayList<>();
        
        try {
            PreparedStatement pst = c.prepareStatement ( "SELECT \"CodiceSanitario\"  FROM \"Paziente\" join \"Medico\" on \"Medico\"=\"CodiceRegione\" WHERE \"Password\"=? and \"CodiceRegione\"=?" ); 
                                                
            pst.clearParameters(); 
            pst.setString(1,password);
            pst.setString(2,codiceRegionale);
                     
            ResultSet rs=pst.executeQuery ();      
            while(rs.next()){
                    pazienti.add(rs.getString("CodiceSanitario"));
            }  
        }
        
        catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return pazienti;
        }
        

	public ArrayList<String> farmacoPerPeriodo(String tipoPeriodo,String periodo) { 
		  return null;
	 }
  
}
