/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import SistemaPrescrizioniMain.Avvio;
import controller.MedicoController;
import controller.SegreteriaController;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import model.Farmaco;
import model.Prescrizione;


/**
 *
 * @author Valentina
 */
public class SegreteriaView extends javax.swing.JFrame {
    
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    
    private SegreteriaController segreteriaController;
    private ArrayList<Prescrizione> richieste;
    //private ArrayList<String> strings= new ArrayList<>();
    private ArrayList<String> farmaci= new ArrayList<>();
    private Connection c;
    private JTextField jTextField1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private ArrayList<String> listaPrescrizioniDaConsegnare;
    private Avvio avvio;
    
    
    

    /*
    public SegreteriaView(ArrayList<Prescrizione> richieste, SegreteriaController segreteriaController) {
        this.prescrizioni=prescrizioni;
        this.segreteriaController=segreteriaController;
        initComponents();       
    }*/
    
    public SegreteriaView(SegreteriaController s, Connection c, Avvio a){
        this.c=c;
        avvio=a;
        richieste = s.prescrizioniDaConsegnareComePrescrizione();
        segreteriaController=s;
        initComponents(); 
        initTab();
        
        
    }
    
    private void initComponents() {
   
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        
        jButton1 = new javax.swing.JButton();
        listaPrescrizioniDaConsegnare=new ArrayList<>();
       
        richieste = segreteriaController.prescrizioniDaConsegnareComePrescrizione();
        //listaPrescrizioniDaConsegnare = MedicoController.listaRichiestePrescritte(c);
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
  
        impostaListaRichieste();
        
        
        jList2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return listaPrescrizioniDaConsegnare.size(); }

            @Override
            public String getElementAt(int index) {
                return listaPrescrizioniDaConsegnare.get(index);
            }
            
        });
        //jList2.updateUI();
        
        //jList2.addListSelectionListener(event -> MedicoController.oggettoSelezionato(jList2.getSelectedIndex(),strings2));
        
        jScrollPane1.setViewportView(jList2);
        

        jButton1.setText("Consegna Prescrizione");

        
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        

        
       
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
       

        jTabbedPane1.addTab("Creazione richiesta prescrizione", jPanel2);
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
    
    private void initTab(){
        jButton2 = new javax.swing.JButton();
        jTextField1 = new JTextField();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        
        jList1 = new javax.swing.JList<String>();
        jButton2.setText("Genera richiesta prescrizione");
        
        impostaListaRichieste();
        impostaListaFarmaci();
        jList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        aggiornaModelloListaFarmaci();
        
        jScrollPane2.setViewportView(jList1);
        
        jTextField1.setText("");
        jLabel2.setText("Codice Fiscale");
        jLabel3.setText("Lista farmaci");
        jLabel4.setText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup()
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(368, 368, 368)
                
                .addComponent(jButton2)
                .addGap(0, 0, Short.MAX_VALUE))
                
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(63, 63, 63)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(196, 196, 196))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup()
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(60, 94, 94)
                .addComponent(jLabel2)                
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jButton2)
                .addComponent(jLabel4)
                )
                .addContainerGap())
        );
        
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
        pack();
    }
     
    
    public void impostaListaRichieste(){
        
        listaPrescrizioniDaConsegnare=new ArrayList<>();
        for(Prescrizione r: richieste){
            listaPrescrizioniDaConsegnare.add(r.getCodicePrescrizione()+ ". Paziente:  " + r.getPaziente() +" "+ r.getNomePaziente(c) +" "+ r.getCognomePaziente(c));
        }
    }
    
    public void impostaListaFarmaci(){     
        farmaci=Farmaco.getListaFarmaci(c);
    }
    
    
    private void aggiornaModelloListaFarmaci(){
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
           
            public int getSize() { return farmaci.size(); }

            @Override
            public String getElementAt(int index) {
                return farmaci.get(index);
            }
            
        });
        
        
    }
     
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {        
        int[] arr=jList2.getSelectedIndices();
        
        for(int i=0;i<arr.length;i++){
            segreteriaController.consegnaPrescrizione(MedicoController.oggettoSelezionatoSenzaPallino(arr[i],listaPrescrizioniDaConsegnare));
        }
        richieste = segreteriaController.prescrizioniDaConsegnareComePrescrizione();
        impostaListaRichieste();
        for(int i=0;i<arr.length;i++){
            arr[i]=-1;
        }
        jList2.setSelectedIndices(arr);
        this.repaint();
        
    } 
    
    private void jButton2ActionPerformed(ActionEvent evt) {
        if(SegreteriaController.controlloPresenzaPaziente(c, jTextField1.getText())){
            if(listaFarmaciSelezionati().size()==0){
                jLabel4.setText("Nessun farmaco selezionato");
            }
            else if(listaFarmaciSelezionati().size()<=5){
                segreteriaController.inviaRichiestaPrescrizione(jTextField1.getText(), listaFarmaciSelezionati());
                jList1.removeSelectionInterval(0, farmaci.size()-1);
                jLabel4.setText("Richiesta prescrizione generata");
                if(avvio.getLoginMedico() != null)
                    avvio.getLoginMedico().getMedicoController().getMedicoView().aggiornaLista1();
            }
            else{
                jLabel4.setText("Una prescrizione non può contenere più di 5 farmaci");
            }
        }
        else{
            jLabel4.setText("Paziente non presente");
        }
        
    }
    
    private ArrayList<String> listaFarmaciSelezionati(){
        ArrayList<String> risultato = new ArrayList<String>();
        int[] indici=jList1.getSelectedIndices();
        for(int i=0;i<indici.length;i++){
            risultato.add(farmaci.get(indici[i]));
        }
        return risultato;
    }
    
    
}
