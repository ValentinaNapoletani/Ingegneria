/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;

/**
 *
 * @author Valentina
 */
public class Segreteria {
    
  private Connection c=null;
  private String codice;
  
  public Segreteria(Connection c,String codice){
      this.codice=codice;
      this.c=c;
  }
    
  public String getCodiceSegreteria() {
      return codice;
  }
}
