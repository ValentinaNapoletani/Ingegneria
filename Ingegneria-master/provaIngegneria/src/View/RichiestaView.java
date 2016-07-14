/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.*;
import model.Richiesta;

/**
 *
 * @author Valentina
 */
public class RichiestaView extends JPanel{
    
    private Richiesta richiesta;
    private JLabel codiceRichiesta;
    private JLabel codPaziente;
    private JLabel nome;
    private JLabel cognome;
    private JLabel data;
    private JLabel farmaci;
    
    public RichiestaView(Richiesta richiesta){
        this.richiesta=richiesta;
    }
    
    public void setRichiesta(){
       codiceRichiesta=new JLabel(richiesta.getCodiceRichiesta());
       codiceRichiesta.setFont(new Font("Purisa",Font.BOLD,30));
  
    }
    
    private void initComponents() {
        
        JPanel layout = new JPanel(new GridLayout());
        add(layout);
        
        layout.add(codiceRichiesta);
    }
    
    
    
    
}
