/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
}
