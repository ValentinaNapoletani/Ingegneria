/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import controller.MedicoController;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import model.Richiesta;

/**
 *
 * @author Valentina
 */
public class MedicoView extends javax.swing.JFrame {
    
    private javax.swing.JButton jButton1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    
    private MedicoController medicoController;
    private ArrayList<Richiesta> richieste;
    private ArrayList<String> strings= new ArrayList<>();

    
    public MedicoView(ArrayList<Richiesta> richieste,MedicoController medicoController) {
        this.richieste=richieste;
        this.medicoController=medicoController;
        initComponents();       
    }
    
    public ArrayList<String> getRichiesteNonPrescritte(){
        return strings;
    }
     
    public void impostaStringaRichiesta(){
        
        ArrayList<String> codric=new ArrayList<>();
        
        String stringaFarmaci="";
        
        for(Richiesta r: richieste){
            for(String s:r.getFarmaci()) {
                stringaFarmaci+= "\n "
                        + "•" + s; 
            }
            codric.add(r.getCodiceRichiesta()+ " " + r.getCognomePaziente() + " " + r.getNomePaziente() + " " + r.getPaziente() +"\n\t"+ stringaFarmaci);
            stringaFarmaci="";
            
            strings=codric;
        }
    }
    
    /*public void valueChanged(ListSelectionEvent e) {
         if (e.getValueIsAdjusting() == false) {

        if (jList1.getSelectedIndex() == -1) {
        //No selection, disable fire button.
            jButton1.setEnabled(false);

        } else {
        //Selection, enable the fire button.
             jButton1.setEnabled(true);
        }
    }
    }*/
    
    public JList<String> getLista(){
        return jList1;
    }
        
    
     private void initComponents() {
   
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        
    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
  
        impostaStringaRichiesta();
        
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setFont(new java.awt.Font("Corbel", 0, 14)); 
        
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            //ArrayList<String> strings= impostaStringaRichiesta();

            public int getSize() { return strings.size(); }
            public String getElementAt(int i) { return strings.get(i); }
        });
        
        jList1.addListSelectionListener(event -> medicoController.oggettoSelezionato(jList1.getSelectedIndex(),strings));

        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Effettua Prescrizione");
        jButton1.addActionListener(event -> medicoController.effettuaPrescrizioneSuRichiesta(medicoController.oggettoSelezionato(jList1.getSelectedIndex(),strings)));
        jButton1.addActionListener(event -> initComponents());
       
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(0, 10, Short.MAX_VALUE))
        );
       jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
       
       javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(0, 10, Short.MAX_VALUE))
        );
       jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab1", jPanel1);
        jTabbedPane1.addTab("tab2", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
       

        pack();
    }
}
