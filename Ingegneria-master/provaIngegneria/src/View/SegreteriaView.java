/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import controller.MedicoController;
import controller.SegreteriaController;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import model.Prescrizione;
import model.Richiesta;

/**
 *
 * @author Valentina
 */
public class SegreteriaView extends javax.swing.JFrame {
    
    private javax.swing.JButton jButton1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    
    private SegreteriaController segreteriaController;
    private ArrayList<Prescrizione> prescrizioni;
    private ArrayList<String> strings= new ArrayList<>();
    private ArrayList<String> strings2= new ArrayList<>();
    private Connection c;
    private SegreteriaController sc;
    
    
    

    
    public SegreteriaView(ArrayList<Prescrizione> prescrizioni, SegreteriaController segreteriaController) {
        this.prescrizioni=prescrizioni;
        this.segreteriaController=segreteriaController;
        initComponents();       
    }
    
    public SegreteriaView(SegreteriaController s, Connection c){
        this.c=c;
        prescrizioni = s.prescrizioniDaConsegnareComePrescrizione();
        initComponents(); 
        sc=s;
        
    }
    
    public ArrayList<String> getRichiesteNonPrescritte(){
        return strings;
    }
    
    
        
    
     private void initComponents() {
   
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        //jList1 = new javax.swing.JList<String>();
        jList2 = new javax.swing.JList<String>();
        jButton1 = new javax.swing.JButton();
        if(c != null)
            strings2 = MedicoController.listaRichiestePrescritte(c);
                
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
  
        impostaStringaPrescrizione();
        /*
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setFont(new java.awt.Font("Corbel", 0, 14)); 
        
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            //ArrayList<String> strings= impostaStringaRichiesta();

            public int getSize() { return strings.size(); }
            public String getElementAt(int i) { return strings.get(i); }
        });*/
        
        jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            //ArrayList<String> strings= impostaStringaRichiesta();
            

            public int getSize() { return strings2.size(); }

            @Override
            public String getElementAt(int index) {
                System.out.println(strings2.get(index));
                //return new Checkbox(strings2.get(index)).setLabel(label);
                return strings2.get(index);
            }
            
        });
        
        //jList1.addListSelectionListener(event -> MedicoController.oggettoSelezionato(jList1.getSelectedIndex(),strings));
        jList2.addListSelectionListener(event -> MedicoController.oggettoSelezionato(jList2.getSelectedIndex(),strings2));

        jScrollPane1.setViewportView(jList2);

        jButton1.setText("Consegna Prescrizione");
        jButton1.addActionListener(event -> segreteriaController.consegnaPrescrizione(MedicoController.oggettoSelezionato(jList2.getSelectedIndex(),strings2)));
        //jButton1.addActionListener(event -> initComponents());
       
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

        jTabbedPane1.addTab("Creazione prescrizione", jPanel2);
        jTabbedPane1.addTab("Consegna prescrizione", jPanel1);

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
     
    public void impostaStringaPrescrizione(){
        
        ArrayList<String> codric=new ArrayList<>();
        
        String stringaFarmaci="";

        for(Prescrizione r: prescrizioni){
            for(String s:r.getFarmaci(c)) {
                stringaFarmaci+= "\n "
                        + "•" + s; 
            }
            codric.add(r.getCodicePrescrizione()+ " " + r.getCognomePaziente(c) + " " + r.getNomePaziente(c) + " " + r.getPaziente() +"\n\t"+ stringaFarmaci);
            stringaFarmaci="";
            
            strings=codric;
        }
    }
     
    
}
