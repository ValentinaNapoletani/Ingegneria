/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Farmaco;

/**
 *
 * @author Viktor
 */

//TODO: aggiungere nella tabella farmacoInFarmacia il prezzo oppure nella tabella farmaco se tutte le farmacie vendono farmaci allo stesso prezzo
public class Farmacia {
    private static int numeroScontrino=0;
    private Connection c=null;
    String indirizzo;
    String cap;
    
    public Farmacia(Connection c,String indirizzo, String cap){
        this.c = c;
        this.indirizzo=indirizzo;
        this.cap=cap;
    }
    
    public boolean controlloPresenzaFarmaco(String farmaco){
        int quantita;
        quantita=numeroPezziFarmacoDisponibili(farmaco);
        if (quantita > 0)
            return true;
        else
            return false;
    }
    
    public boolean controlloPresenzaFarmaco(String farmaco, int quantita){
        int quantitaNelDb;    
        quantitaNelDb=numeroPezziFarmacoDisponibili(farmaco);
        if (quantitaNelDb >= quantita)
            return true;
        else
            return false;
    }
    
    public void ordinaFarmaco(String farmaco, int quantita){
        int pezzi = numeroPezziFarmacoDisponibili(farmaco);
        try {
            String sql = "UPDATE \"FarmacoInFarmacia\" SET \"quantita\"= ? WHERE \"indirizzofarmacia\"=? AND \"capfarmacia\"=? AND \"farmaco\"=?";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            //vale:ho modificato l'ordine dei parametri che avev sbagliato :)
            pst.setString(2, indirizzo);
            pst.setString(3, cap);
            pst.setString(4, farmaco);
            pst.setInt(1, pezzi+quantita);
            pst. executeUpdate ();
        } catch (SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        
    }
    
    /*
    public void ordinaFarmaco(String farmaco, int quantita){
        int ordinato = 0;
        int quantitaNelDb = 0;
        try {
            String sql = "SELECT count(*) as num FROM \"FarmacoOrdinato\" WHERE \"indirizzofarmacia\"=? AND \"capfarmacia\"=? AND \"farmaco\"=?";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, indirizzo);
            pst.setString(2, cap);
            pst.setString(3, farmaco);
            ResultSet rs=pst. executeQuery ();
            rs.next();
            ordinato = rs.getInt("num");
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        if(ordinato > 0){
            try {
                String sql = "SELECT TOP 1 \"quantita\" FROM \"FarmacoOrdinato\" WHERE \"indirizzofarmacia\"=? AND \"capfarmacia\"=? AND \"farmaco\"=?";
                PreparedStatement pst;
                pst = c.prepareStatement ( sql );
                pst.clearParameters();
                pst.setString(1, indirizzo);
                pst.setString(2, cap);
                pst.setString(3, farmaco);
                ResultSet rs=pst. executeQuery ();
                rs.next();
                quantitaNelDb = rs.getInt("quantita");
                sql = "UPDATE \"FarmacoOrdinato\" SET \"quantita\"= ? WHERE \"indirizzofarmacia\"=? AND \"capfarmacia\"=? AND \"farmaco\"=?";
                pst = c.prepareStatement ( sql );
                pst.clearParameters();
                pst.setInt(1, quantita+quantitaNelDb);
                rs=pst.executeQuery ();
                
            } catch (SQLException e) {
                System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
            }
        }
        else {
            try {
                String sql = "INSERT INTO \"FarmacoOrdinato\" (\"indirizzofarmacia\", \"capfarmacia\", \"farmaco\", \"quantita\") VALUES (?,?,?,?)";
                PreparedStatement pst;
                pst = c.prepareStatement ( sql );
                pst.clearParameters();
                pst.setString(1, indirizzo);
                pst.setString(2, cap);
                pst.setString(3, farmaco);
                pst.setInt(4, quantita);
                ResultSet rs=pst. executeQuery ();
            } catch (SQLException e) {
                System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
            }
        }
    }
    */
    
    public void compraFarmaco(String codiceFiscale, String codicePrescrizione, int quantita, String farmaco){
        System.out.println("Il paziente richiede il farmaco");
        //controllare prescrizione per persona
        boolean ok = false;
        try {
            String sql = "SELECT count(*) AS num FROM \"Prescrizione\" WHERE \"paziente\"=? AND \"codice\"=? AND \"Prescrizione\".\"CodiceRichiesta\" IS NULL";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, codiceFiscale);
            pst.setString(2, codicePrescrizione);
                //pst.setString(3, cap);
                //pst.setString(4, farmaco);
                
            ResultSet rs=pst. executeQuery ();
            rs.next();
            int ris = rs.getInt("num");
            if (ris > 0){
                ok = true;
                System.out.println("Il paziente può comprare il farmaco");
            }
        } catch (SQLException e) {
                System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        if(ok){
            int numPezziFarmacoInFarmacia;
            if(controlloPresenzaFarmaco(farmaco ,quantita)){
                System.out.println("Il farmaco è presente nella farmacia");
                numPezziFarmacoInFarmacia = numeroPezziFarmacoDisponibili(farmaco);
                try {
                    String sql = "UPDATE \"FarmacoInFarmacia\" SET \"quantita\"= ? WHERE \"indirizzofarmacia\"=? AND \"capfarmacia\"=? AND \"farmaco\"=?";
                    PreparedStatement pst;
                    pst = c.prepareStatement ( sql );
                    pst.clearParameters();
                    //vale:ho modificato l'ordine dei parametri che avev sbagliato :)
                    pst.setString(2, indirizzo);
                    pst.setString(3, cap);
                    pst.setString(4, farmaco);
                    pst.setInt(1, numPezziFarmacoInFarmacia-quantita);
                    pst. executeUpdate ();
                } catch (SQLException e) {
                    System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
                }
                
                //QUI imposto la prescrizione come usata
                try {
                    String sql = "UPDATE \"Prescrizione\" SET \"usata\"= ? WHERE \"codice\"=?";
                    PreparedStatement pst;
                    pst = c.prepareStatement ( sql );
                    pst.clearParameters();
                    pst.setBoolean(1, true);
                    pst.setString(2, codicePrescrizione);
                    pst. executeUpdate ();
                } catch (SQLException e) {
                    System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
                }

            }
            else{
                System.out.println("Farmaco non presente nella farmacia oppure non ci sono abbastanza confezioni a disposizione, eseguo l'ordine");
                //potrei calcolare la quantità da ordinare cosi: farmaciDaComprare - farmaciNellaFarmacia
                ordinaFarmaco(farmaco, quantita);
            }
        }
    }
    
    private void rilasciaScontrino(Farmaco farmaco, int quantita){
        numeroScontrino++;
        System.out.println("Scontrino numero "+numeroScontrino+": "+farmaco.getNome()+"x"+quantita+"\nPrezzo totale: "+quantita*farmaco.getPrezzo());
    }
    
    private int numeroPezziFarmacoDisponibili(String farmaco){
        int quantita=0;
        try {
            String sql = "SELECT \"quantita\" FROM \"FarmacoInFarmacia\" WHERE \"indirizzofarmacia\"=? AND \"capfarmacia\"=? AND \"farmaco\"=?";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, indirizzo);        
            pst.setString(2, cap);
            pst.setString(3, farmaco);
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                quantita=rs.getInt("quantita");
            }
        } catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return quantita;
    }
}
