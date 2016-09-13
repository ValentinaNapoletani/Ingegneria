/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import controller.Farmacia;
import java.sql.Connection;
import javax.swing.JTextField;

/**
 *
 * @author Viktor
 */
public class LoginFarmacia extends javax.swing.JFrame {
    private final Connection c;

    public LoginFarmacia(Connection c) {
        this.c=c;
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoIndirizzo = new javax.swing.JTextField();
        campoCAP = new javax.swing.JTextField();
        indirizzoLabel = new javax.swing.JLabel();
        labelCAP = new javax.swing.JLabel();
        pulsanteConferma = new javax.swing.JButton();
        cittaLabel = new javax.swing.JLabel();
        campoCitta = new javax.swing.JTextField();
        messaggioErrore = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        indirizzoLabel.setText("Indirizzo");

        labelCAP.setText("CAP");

        pulsanteConferma.setText("Conferma");
        pulsanteConferma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsanteConfermaActionPerformed(evt);
            }
        });

        cittaLabel.setText("Città");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(messaggioErrore, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(indirizzoLabel)
                                .addComponent(labelCAP)
                                .addComponent(cittaLabel))
                            .addGap(36, 36, 36)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoCAP, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(campoIndirizzo, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(campoCitta, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(122, 122, 122)
                            .addComponent(pulsanteConferma))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cittaLabel)
                    .addComponent(campoCitta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoIndirizzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(indirizzoLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoCAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCAP))
                .addGap(18, 18, 18)
                .addComponent(pulsanteConferma)
                .addGap(18, 18, 18)
                .addComponent(messaggioErrore, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pulsanteConfermaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulsanteConfermaActionPerformed
        String citta=campoCitta.getText();
        String indirizzo=campoIndirizzo.getText();
        String cap = campoCAP.getText();
        if(Farmacia.controlloPresenzaFarmacia(c, citta, indirizzo, cap)){
            Farmacia f = new Farmacia(c, indirizzo, cap, citta);
            FarmaciaView fv = new FarmaciaView(f,c);
            fv.setResizable(false);
            fv.setVisible(true);
        }
        else{
            messaggioErrore.setText("La farmacia non esiste");
        }
    }//GEN-LAST:event_pulsanteConfermaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoCAP;
    private javax.swing.JTextField campoCitta;
    private javax.swing.JTextField campoIndirizzo;
    private javax.swing.JLabel cittaLabel;
    private javax.swing.JLabel indirizzoLabel;
    private javax.swing.JLabel labelCAP;
    private javax.swing.JLabel messaggioErrore;
    private javax.swing.JButton pulsanteConferma;
    // End of variables declaration//GEN-END:variables

    public JTextField getCampoCitta() {
        return campoCitta;
    }

    public JTextField getCampoIndirizzo() {
       return campoIndirizzo;
    }

    public JTextField getCampoCAP() {
        return campoCAP;
    }
}
