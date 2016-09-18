/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaPrescrizioniMain;

import View.LoginFarmacia;
import View.LoginMedico;
import View.LoginSegreteria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author Viktor
 */
public class Avvio extends javax.swing.JFrame {
    private static Connection c;
    private LoginSegreteria ls;
    private LoginMedico lm;
    private LoginFarmacia lf;
    /**
     * Creates new form Avvio
     */
    public Avvio() {
        creaConnessione();
        
        initComponents();
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pulsanteSegreteria = new javax.swing.JButton();
        pulsanteMedico = new javax.swing.JButton();
        pulsanteFarmacia = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pulsanteSegreteria.setText("Segreteria");
        pulsanteSegreteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressionePulsanteSegreteria(evt);
            }
        });

        pulsanteMedico.setText("Medico");
        pulsanteMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressionePulsanteMedico(evt);
            }
        });

        pulsanteFarmacia.setText("Farmacia");
        pulsanteFarmacia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pressionePulsanteFarmacia(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(pulsanteSegreteria)
                .addGap(44, 44, 44)
                .addComponent(pulsanteMedico)
                .addGap(43, 43, 43)
                .addComponent(pulsanteFarmacia)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pulsanteSegreteria)
                    .addComponent(pulsanteMedico)
                    .addComponent(pulsanteFarmacia))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pressionePulsanteSegreteria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pressionePulsanteSegreteria
        ls = new LoginSegreteria(c, this);
        ls.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        ls.setResizable(false);
        ls.setVisible(true);
    }//GEN-LAST:event_pressionePulsanteSegreteria

    private void pressionePulsanteMedico(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pressionePulsanteMedico
        lm = new LoginMedico(c, this);
        lm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        lm.setResizable(false);
        lm.setVisible(true);
    }//GEN-LAST:event_pressionePulsanteMedico

    private void pressionePulsanteFarmacia(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pressionePulsanteFarmacia
        lf = new LoginFarmacia(c);
        lf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        lf.setResizable(false);
        lf.setVisible(true);
    }//GEN-LAST:event_pressionePulsanteFarmacia

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Avvio().setVisible(true);
        });
    }
    
    public LoginMedico getLoginMedico(){
        return lm;
    }
    
    public LoginSegreteria getLoginSegreteria(){
        return ls;
    }
    
    public static void creaConnessione(){
         
            try {
                Class.forName("org.postgresql.Driver");
               //c=DriverManager.getConnection("jdbc:postgresql://151.62.110.35:5432/Ingegneria","postgres","123123");
                c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ingegneria","postgres","123");
                //c=DriverManager.getConnection("jdbc:postgresql://192.168.1.3:5432/Ingegneria","postgres","123123");
            }    
            catch (ClassNotFoundException | SQLException e) {
                System.err.println(e.getClass().getName()+": "+e.getMessage());
                System.exit(0);
            }
            System.out.println("Opened database successfully");
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton pulsanteFarmacia;
    private javax.swing.JButton pulsanteMedico;
    private javax.swing.JButton pulsanteSegreteria;
    // End of variables declaration//GEN-END:variables
}
