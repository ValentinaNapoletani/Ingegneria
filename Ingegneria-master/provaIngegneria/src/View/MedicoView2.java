/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import controller.MedicoController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.*;
import javax.swing.*;
import model.Medico;

/**
 *
 * @author Valentina
 */
public class MedicoView2 extends javax.swing.JFrame  {
    
    private Medico medico=null;
    private JTextField user;
    private JTextField password;
        
    public MedicoView2()  {
        initComponents();
        pack();
    }
    
    private void initComponents() {
        
        JPanel layout = new JPanel(new BorderLayout());
    	add(layout);
    	
    	JPanel northPanel = new JPanel(new GridLayout(2,1));
    	layout.add(northPanel,BorderLayout.NORTH);
    	
    	
    	JLabel wellcomeLabel = new JLabel("   Benvenuto");
    	JLabel namesLabel = new JLabel("Inserisci le tue credenziali:");
    	northPanel.add(    wellcomeLabel);
    	northPanel.add(    namesLabel);
    	
    	wellcomeLabel.setFont(new Font("Purisa",Font.BOLD,40));
    	namesLabel.setFont(new Font("Purisa",Font.BOLD,20));
    	wellcomeLabel.setForeground(new Color(255, 117, 24));
    	namesLabel.setForeground(new Color(255, 117, 24));
    	
    	JPanel centerPanel = new JPanel(new GridLayout(2,2));
    	layout.add(centerPanel,BorderLayout.CENTER);
    	
    	JLabel userlabel = new JLabel("User:", JLabel.RIGHT);
    	JLabel passwordlabel = new JLabel("Password:", JLabel.RIGHT );
    	
    	userlabel.setFont(new Font("Tahoma",Font.PLAIN,20));
    	passwordlabel.setFont(new Font("Tahoma",Font.PLAIN,20));
    	
    	userlabel.setForeground(Color.black);
    	
    	this.user = new JTextField();
    	this.password = new JTextField();
    	
    	user.setFont(new Font("Purisa",Font.BOLD,18));
    	password.setFont(new Font("Purisa",Font.BOLD,18));
    	user.setForeground(Color.ORANGE);
    	password.setForeground(Color.ORANGE);
    	
    	centerPanel.add(userlabel);
    	centerPanel.add(user);	
    	centerPanel.add(passwordlabel);
    	centerPanel.add(password);
    	
    	JPanel southPanel = new JPanel();
    	layout.add(southPanel,BorderLayout.SOUTH);

    	
    	JButton autentica=new JButton("Accedi");
    	southPanel.add(autentica);
    	
    	autentica.addActionListener(event -> medicoController.autenticazione());
        
    	autentica.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(255, 117, 24)));
    	autentica.setPreferredSize(new Dimension(60,30));
    	autentica.setFont(new Font("Purisa",Font.BOLD,15));
    	
        this.user= new JTextField();
    	this.password = new JTextField();
          
    }
    
    public JTextField getUser(){
		return user;
	}
	
    public JTextField getPassword(){
	return password;
    }

  
    
    
    
}
