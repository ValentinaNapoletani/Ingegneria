/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import controller.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.*;
import java.sql.Connection;
import javax.swing.*;
import model.*;

/**
 *
 * @author Valentina
 */
public class LoginMedicoOld extends javax.swing.JFrame  {
    
    private Medico medico=null;
    private JTextField user;
    private JTextField password;
    private MedicoController mc=null;
    private static Connection c;
    private JCheckBox sostituto;
    private JLabel errore;
        
    public LoginMedicoOld(Connection c) {
        this.c=c; 
        medico=new Medico(c,null,null);
        mc=new MedicoController(c,medico,this);
        initComponents();
        pack();
    }
    
    public void setErrore(String errore){
        this.errore.setText(errore);
    }
    
    private void initComponents() {
        sostituto = new JCheckBox();
        errore = new JLabel();
        errore.setText("");
        JPanel layout = new JPanel(new BorderLayout());
        
    	add(layout);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
    	
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
    	sostituto.setText("Accedi come sostituto");
    	centerPanel.add(userlabel);
    	centerPanel.add(user);	
    	centerPanel.add(passwordlabel);
    	centerPanel.add(password);
        
    	
    	JPanel southPanel = new JPanel();
        layout.add(southPanel,BorderLayout.SOUTH);
        southPanel.add(sostituto);
    	

    	
    	JButton autentica=new JButton("Accedi");
    	southPanel.add(autentica);
        southPanel.add(errore);
        
    	
    	autentica.addActionListener(event -> {mc.autentica(); } );
        
    	autentica.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(255, 117, 24)));
    	autentica.setPreferredSize(new Dimension(60,30));
    	autentica.setFont(new Font("Purisa",Font.BOLD,15));
    	        
    }
    
    public String getUser(){
	return user.getText();
    }
	
    public JTextField getPassword(){
	return password;
    }

    public Medico getMedico(){
        return medico;
    }
    
    public void setMedico(){
        medico.setCodiceRegionale(user.getText());
        medico.setPassword(password.getText());
    }
    
    public boolean sostituto(){
        if(sostituto.isSelected())
            return true;
        else
            return false;
    }
    
    
}
