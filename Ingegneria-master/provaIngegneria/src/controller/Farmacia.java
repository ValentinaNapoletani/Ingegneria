
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Viktor
 */


public class Farmacia {
    private Connection c=null;
    String indirizzo;
    String cap;
    String citta;
    private int ultimoScontrino;
    
    private int rilasciaScontrino(){
        ultimoScontrino++;
        return ultimoScontrino;
    }
    
    public String rilasciaScontrino(ArrayList<String> listaFarmaci){
        String scontrino="";
        double temp;
        double totale=0;
        for(String s: listaFarmaci){
            temp=getPrezzoFarmaco(s);
            scontrino+=s+": "+temp+"<br>";
            totale += temp;
        }
        scontrino +="TOTALE: "+totale+"<br>";
        ultimoScontrino++;
        return "<html>"+scontrino+"<br>---------------<br>Scontrino "+ultimoScontrino+" rilasciato</html>";
    }
    
    
    public Farmacia(Connection c,String indirizzo, String cap, String citta){
        this.c = c;
        this.indirizzo=indirizzo;
        this.cap=cap;
        this.citta=citta;
        ultimoScontrino = (int)(Math.random()*100000000);
    }
    
    public boolean controlloPresenzaFarmaco(String farmaco){
        int quantita;
        quantita=numeroPezziFarmacoDisponibili(farmaco);
        return quantita > 0;
    }
    
    public boolean controlloPresenzaFarmaco(String farmaco, int quantita){
        int quantitaNelDb;    
        quantitaNelDb=numeroPezziFarmacoDisponibili(farmaco);
        return quantitaNelDb >= quantita;
    }
    
    public boolean ordinaFarmaco(String farmaco, int quantita){
        int pezzi = numeroPezziFarmacoDisponibili(farmaco);
        try {
            String sql;
            if(controllaEsistenzaCampoFarmaco(farmaco)){
                sql = "UPDATE \"FarmacoInFarmacia\" SET \"quantita\"= ? WHERE \"indirizzofarmacia\" ilike ? AND \"capfarmacia\"=? AND \"farmaco\"=?";
                PreparedStatement pst;
                pst = c.prepareStatement ( sql );
                pst.clearParameters();
                pst.setString(2, getIndirizzoFarmacia());
                pst.setString(3, cap);
                pst.setString(4, farmaco);
                pst.setInt(1, pezzi+quantita);
                pst. executeUpdate ();
            }
            else{
                sql = "insert into  \"FarmacoInFarmacia\" ( \"quantita\", \"indirizzofarmacia\", \"capfarmacia\", \"farmaco\") VALUES (?,?,?,?)";
                PreparedStatement pst;
                pst = c.prepareStatement ( sql );
                pst.clearParameters();
                pst.setString(2, getIndirizzoFarmacia());
                pst.setString(3, cap);
                pst.setString(4, farmaco);
                pst.setInt(1, quantita);
                pst. executeUpdate ();
            }
            return true;
        } catch (SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
            return false;
        }
        
    }
    
    private String getIndirizzoFarmacia(){
       String indirizzoNew="";
        try {
            String sql = "SELECT \"indirizzo\" FROM \"Farmacia\" WHERE \"indirizzo\" ilike ? AND \"cap\"=?";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, indirizzo);        
            pst.setString(2, cap);
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                indirizzoNew=rs.getString("indirizzo");
            }
        } catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return indirizzoNew;
    }
       
    public boolean compraFarmaco(String farmaco, int prescrizione){
        int numPezziFarmacoInFarmacia;
        if(controlloPresenzaFarmaco(farmaco)){
            numPezziFarmacoInFarmacia = numeroPezziFarmacoDisponibili(farmaco);
            try {
                String sql;
                if(controllaEsistenzaCampoFarmaco(farmaco)){
                    sql = "UPDATE \"FarmacoInFarmacia\" SET \"quantita\"= ? WHERE \"indirizzofarmacia\" ilike ? AND \"capfarmacia\"=? AND \"farmaco\"=?";
                    PreparedStatement pst;
                    pst = c.prepareStatement ( sql );
                    pst.clearParameters();
                    pst.setInt(1, numPezziFarmacoInFarmacia-1);
                    pst.setString(2, indirizzo);
                    pst.setString(3, cap);
                    pst.setString(4, farmaco);
                    pst.executeUpdate ();
                    sql = "INSERT INTO \"Acquisto\" (\"prescrizione\", \"farmaco\") VALUES (?,?)";
                    pst = c.prepareStatement ( sql );
                    pst.clearParameters();
                    pst.setInt(1, prescrizione);
                    pst.setString(2, farmaco);
                    pst.executeUpdate ();
                    return true;
                }
            } catch (SQLException e) {
                System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
                return false;
            }
        }
        else{
            return false;
        }
        return false;
    }
    
    private boolean controllaEsistenzaCampoFarmaco(String farmaco){
        int num=0;
        try {
            String sql = "Select count(*) as cont FROM \"FarmacoInFarmacia\" WHERE \"indirizzofarmacia\" ilike ? AND \"capfarmacia\"=? AND \"farmaco\"=?";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, indirizzo);
            pst.setString(2, cap);
            pst.setString(3, farmaco);
            ResultSet rs=pst.executeQuery ();
            while(rs.next()){
                num=rs.getInt("cont");
            }
        } catch (SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return num>0;
    }
    
    /*
    private void rilasciaScontrino(Farmaco farmaco, int quantita){
        numeroScontrino++;
        System.out.println("Scontrino numero "+numeroScontrino+": "+farmaco.getNome()+"x"+quantita+"\nPrezzo totale: "+quantita*farmaco.getPrezzo());
    }
    */
    
    public int numeroPezziFarmacoDisponibili(String farmaco){
        int quantita=0;
        try {
            String sql = "SELECT \"quantita\" FROM \"FarmacoInFarmacia\" WHERE \"indirizzofarmacia\" ilike ? AND \"capfarmacia\"=? AND \"farmaco\"=?";
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
    
    public static boolean controlloPresenzaFarmacia(Connection c,String citta, String indirizzo, String cap){
        int risultato=0;
        try {
            String sql = "SELECT count(*) as cont FROM \"Farmacia\" WHERE \"citta\" ilike ? AND \"indirizzo\" ilike ? AND \"cap\"=?";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, citta);
            pst.setString(2, indirizzo);
            pst.setString(3, cap);
            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                risultato=rs.getInt("cont");
            }
        } catch (SQLException e) {
            System .err. println (" Problema durante estrazione dati : " + e. getMessage () );
        } 
        return risultato>0;
    }

    public static boolean controlloPrescrizione(Connection c, String codiceSanitario, int codicePrescrizione){
        int quantita=0;
        try {
            String sql = "SELECT count(*) as cont FROM \"Prescrizione\" WHERE \"codice\"=? AND \"paziente\"=? and datascadenza>=CURRENT_DATE and consegnata=?";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setInt(1, codicePrescrizione);        
            pst.setString(2, codiceSanitario);
            pst.setBoolean(3, true);

            ResultSet rs=pst. executeQuery ();
            while(rs.next()){
                quantita=rs.getInt("cont");
            }
        } catch ( SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e.getMessage () );
        }
        return quantita>0;
    }
    
    public void impostaPrescrizioneUsata(int numeroPrescrizione){
        try {
            String sql = "UPDATE \"Prescrizione\" SET usata=true WHERE codice=?";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setInt(1, numeroPrescrizione);        
            pst. executeUpdate ();
        } catch ( SQLException e) {
            System .out. println (" Problema durante inserimento dati : " + e.getMessage () );
        }
    }
    
    public double getPrezzoFarmaco(String farmaco){
        double risultato = 0;
        try {
            String sql = "Select prezzo FROM \"Farmaco\" WHERE \"nome\"=? ";
            PreparedStatement pst;
            pst = c.prepareStatement ( sql );
            pst.clearParameters();
            pst.setString(1, farmaco);
            ResultSet rs=pst.executeQuery ();
            while(rs.next()){
                risultato=rs.getDouble("prezzo");
            }
        } catch (SQLException e) {
            System .out. println (" Problema durante estrazione dati : " + e. getMessage () );
        }
        return risultato;
    }
    
}
