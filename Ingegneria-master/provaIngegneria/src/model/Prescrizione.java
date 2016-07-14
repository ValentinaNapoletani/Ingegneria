/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Valentina
 */
public class Prescrizione {
    
    private String codicePaziente=null;
    private String codicePrescrizione=null;
    private boolean usata=false;
    
    public Prescrizione(String codicePaziente,String codicePrescrizione){
        this.codicePaziente=codicePaziente;
        this.codicePrescrizione=codicePrescrizione;
    }
    
    
}
