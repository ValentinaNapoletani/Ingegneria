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
import java.util.LinkedList;

/**
 *
 * @author Viktor
 */
public class Farmaco {
    private String nome;
    private double prezzo;
    
    public String getNome(){
        return nome;
    }
    
    public double getPrezzo(){
        return prezzo;
    }
    
    public Farmaco(String nome, double prezzo){
        this.nome = nome;
        this.prezzo = prezzo;
    }
    
    public static ArrayList<String> getListaFarmaci(Connection c){
        PreparedStatement stmt;
        ArrayList<String> listaRisultati= new ArrayList<String>();
        try{
            stmt=c.prepareStatement("SELECT DISTINCT nome FROM \"Farmaco\"");
            ResultSet rs;
            rs = stmt.executeQuery();
            while(rs.next()){
                listaRisultati.add(rs.getString("nome"));
            }
        }
        catch(SQLException e){
            System.err.println("Errore sql");
        }
        return listaRisultati;
    }
}
