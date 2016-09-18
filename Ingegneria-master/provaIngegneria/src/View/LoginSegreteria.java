
package View;

import SistemaPrescrizioniMain.Avvio;
import controller.SegreteriaController;
import java.sql.Connection;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Segreteria;

/**
 *
 * @author Viktor
 */
public class LoginSegreteria extends javax.swing.JFrame {
    Connection c;
    Avvio avvio;
    Segreteria segreteria;
    SegreteriaController sc;

    public LoginSegreteria(Connection c, Avvio a) {
        avvio=a;
        this.c=c;
        segreteria=new Segreteria(c,null);
        sc=new SegreteriaController(c,segreteria,this);
        initComponents();
    }

    private LoginSegreteria() {
        initComponents();
       
    }
    
    public void setSegreteria(){
        segreteria.setCodice(jTextFieldCodiceSegreteria.getText());
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTesto = new javax.swing.JLabel();
        jTextFieldCodiceSegreteria = new javax.swing.JTextField();
        pulsanteConferma = new javax.swing.JButton();
        jLabelErrore = new javax.swing.JLabel();

        jLabelTesto.setText("Inserisci il codice della segreteria");

        pulsanteConferma.setText("Conferma");
        pulsanteConferma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsanteConfermaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(pulsanteConferma))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelTesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldCodiceSegreteria)
                            .addComponent(jLabelErrore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabelTesto)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldCodiceSegreteria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(pulsanteConferma)
                .addGap(18, 18, 18)
                .addComponent(jLabelErrore, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pulsanteConfermaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulsanteConfermaActionPerformed
        sc.autentica();     
    }//GEN-LAST:event_pulsanteConfermaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelErrore;
    private javax.swing.JLabel jLabelTesto;
    private javax.swing.JTextField jTextFieldCodiceSegreteria;
    private javax.swing.JButton pulsanteConferma;
    // End of variables declaration//GEN-END:variables

    public JTextField getJTextFieldCodiceSegreteria() {
        return jTextFieldCodiceSegreteria;
    }

    public JLabel getJLabelErrore() {
       return jLabelErrore;
    }

    public Avvio getAvvio() {
        return avvio;
    }
    
    public SegreteriaController getSegreteriaController(){
        return sc;
    }

    public boolean simulaPressioneBottone() {
        pulsanteConfermaActionPerformed(null);
        if(sc.getAutenticato()==true)
            return true;
        else return false;
    }
}
