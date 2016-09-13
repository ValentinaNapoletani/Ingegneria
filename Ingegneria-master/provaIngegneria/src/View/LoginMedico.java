/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import SistemaPrescrizioniMain.Avvio;
import controller.MedicoController;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Medico;

/**
 *
 * @author Viktor
 */
public class LoginMedico extends javax.swing.JFrame {
    private Medico medico;
    private MedicoController mc;
    private static Connection c;
    /**
     * Creates new form prova2
     */
    public LoginMedico(Connection c) {
        this.c=c; 
        medico=new Medico(c,null,null);
        mc=new MedicoController(c,medico,this);
        initComponents();
                    
        pack();
    }
    
    public void setErrore(String errore){
        this.labelErrore.setText(errore);
    }
    
    public Medico getMedico(){
        return medico;
    }
    
    public boolean sostituto(){
        if(checkBoxSostituto.isSelected())
            return true;
        else
            return false;
    }
    
    public void setMedico(){
        medico.setCodiceRegionale(campoLogin.getText());
        medico.setPassword(campoPassword.getText());
    }
    
    public String getUser(){
	return campoLogin.getText();
    }
	
    public JPasswordField getPassword(){
	return campoPassword;
    }

    public MedicoController getMedicoController(){
        return mc;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        messaggioLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        campoLogin = new javax.swing.JTextField();
        checkBoxSostituto = new javax.swing.JCheckBox();
        pulsanteAccedi = new javax.swing.JButton();
        labelErrore = new javax.swing.JLabel();
        campoPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        messaggioLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        messaggioLabel.setText("Inserisci le tue credenziali:");

        loginLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loginLabel.setText("Login");

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordLabel.setText("Password");

        checkBoxSostituto.setText("Sostituto");

        pulsanteAccedi.setText("Accedi");
        pulsanteAccedi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsanteAccediActionPerformed(evt);
            }
        });

        labelErrore.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(messaggioLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelErrore, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passwordLabel)
                                    .addComponent(loginLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                    .addComponent(campoPassword)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pulsanteAccedi)
                            .addComponent(checkBoxSostituto))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(messaggioLabel)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabel)
                    .addComponent(campoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel)
                    .addComponent(campoPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(checkBoxSostituto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pulsanteAccedi)
                .addGap(18, 18, 18)
                .addComponent(labelErrore, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pulsanteAccediActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulsanteAccediActionPerformed
        mc.autentica();
    }//GEN-LAST:event_pulsanteAccediActionPerformed

    public JButton getJbutton1() {
        return pulsanteAccedi;
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoLogin;
    private javax.swing.JPasswordField campoPassword;
    private javax.swing.JCheckBox checkBoxSostituto;
    private javax.swing.JLabel labelErrore;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel messaggioLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton pulsanteAccedi;
    // End of variables declaration//GEN-END:variables

    public boolean simulaPressioneBottone() {
        pulsanteAccediActionPerformed(null);
        if(mc.getAutenticato()==true)
            return true;
        else return false;
    }

    public JTextField getJTextField1() {
        return campoLogin;
    }

    public JPasswordField getJPassWordField2() {
        return campoPassword;
    }

}
