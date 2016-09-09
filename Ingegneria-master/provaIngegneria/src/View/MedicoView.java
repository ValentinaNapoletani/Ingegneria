/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import controller.MedicoController;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import model.Farmaco;
import model.Richiesta;

/**
 *
 * @author Valentina
 */
public class MedicoView extends javax.swing.JFrame {
    
    private javax.swing.JButton jButton1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private JCheckBox richiesteConsorziati;
    
    private javax.swing.JButton jButton2;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;

    private javax.swing.JPanel jPanel3;
    
    private javax.swing.JLabel jLabel1;
    private JLabel jLabel13;
    private JCheckBox jCheckBox2;
    
         
    private MedicoController medicoController;
    private ArrayList<Richiesta> richieste;
    private ArrayList<Richiesta> richiesteTotali;
    private ArrayList<String> strings= new ArrayList<>();
    private ArrayList<String> listaFarmaci= new ArrayList<>();
    private JList jList3;
   
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JCheckBox jCheckBox1;
    private JTextField jTextField4;
    
      
     
    private frameConfermaPrescrizione frameP;

    //tab 3
    private javax.swing.JPanel jPanel4;
    private JButton jButton3;
    private JScrollPane jScrollPane4;
    private ArrayList<String> prescrizioniNonUsate;
    private JTextField jTextField5;
    
    //tab4
    private JButton jButton4;
    private JList jList4;
    private JScrollPane jScrollPane5;
    private JComboBox jComboBox1;
    private JPanel jPanel5;
    private ArrayList<String> farmaciPrescrittiDaMedico;
    
    //tab5
    private JLabel jLabel5;
    private JComboBox jComboBox2;
    private JLabel jLabel6;
    private JComboBox jComboBox3;
    private JLabel jLabel7;
    private JButton jButton5;
    private JPanel jPanel6;
    
    //tab6
    private JButton jButton6;
    private JPanel jPanel7;
    private JLabel jLabel8;
    private JComboBox jComboBox4;
    private JList jList6;
    private JScrollPane jScrollPane6;
    private ArrayList<String> listaPazientiPerFarmaco;
    
    //tab7
    private JLabel jLabel9;
    private JLabel jLabel10;
    private JComboBox jComboBox5;
    private JList jList5;
    private JScrollPane jScrollPane7;
    private JButton jButton7;
    private JPanel jPanel8;
    private JTextField jTextField3;
    private ArrayList<String> listaFarmaciPaziente;
    
    //tab8
    private JList jList7;
    private JScrollPane jScrollPane8;
    private JButton jButton8;
    private JPanel jPanel9;
    private ArrayList<String> listFarmaciGenericiAcquistati;
    private JTextField jTextFieldPaziente;
    
    //tab9
    private JButton jButton9;
    private JScrollPane jScrollPane9;
    private JList jList8;
    private JPanel jPanel10;
    private ArrayList<String> listaReazioni;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel17;
    private JLabel jLabel18;
    private JLabel jLabel19;
    
    public MedicoView(ArrayList<Richiesta> richieste,MedicoController medicoController) {
        this.richieste=richieste;
        this.medicoController=medicoController;
        
        initComponents();       
    }
    
    public ArrayList<String> getRichiesteNonPrescritte(){
        return strings;
    }
    
     public MedicoController getMedicoController(){
        return medicoController;
    }
     
     
    public frameConfermaPrescrizione getFrameP(){
        return frameP;       
    }
    
    public void impostaStringaRichiesta(){
        
        ArrayList<String> codric=new ArrayList<>();
        ArrayList<Richiesta> temp;
        
        String stringaFarmaci="";
        
        if(!richiesteConsorziati.isSelected()){
            temp= richieste;
        }
        else{
            temp= richiesteTotali;
        }
        
        for(Richiesta r: temp){
            for(String s:r.getFarmaci()) {
                stringaFarmaci+="<br>•" + s; 
            }
            codric.add("<html>" + r.getCodiceRichiesta()+ " " + r.getCognomePaziente() + " " + r.getNomePaziente() + " " + r.getPaziente() + "<table>" + stringaFarmaci +"<br><br>" + "<html>");
            stringaFarmaci="";
            
            strings=codric;
        }
        

    }
    
       public void impostaListaFarmaci(){
        
       for( String f: Farmaco.getListaFarmaci(medicoController.getConnection())){
                listaFarmaci.add(f);
                
        }
    }
    
    
    public JList<String> getLista(){
        return jList1;
    }
    
    public JList<String> getLista2(){
        return jList2;
    }
    
    
    public ArrayList<String> getListaFarmaci(){
        return listaFarmaci;
    }
        
    private  void creaFinestra(){
        ArrayList <String> prova =listaFarmaci;
        frameP= new frameConfermaPrescrizione(medicoController.listaFarmaciSelezionati(jList2.getSelectedIndices(),listaFarmaci),jTextFieldPaziente.getText(),this,"visita");
        frameP.setVisible(true);
        frameP.setSize(600, 500);
    }
    
    private  void creaFinestra2(){
        if(codiceRichiestaSelezionata() != null){
            ArrayList<String> listaFarmaciTemp = medicoController.richiestaConAnagraficaEFarmaco(Integer.parseInt(codiceRichiestaSelezionata())).getFarmaci();
            frameP= new frameConfermaPrescrizione(listaFarmaciTemp,medicoController.richiestaConAnagraficaEFarmaco(Integer.parseInt(codiceRichiestaSelezionata())).getPaziente(),this,"richiesta");
            frameP.setVisible(true);
            frameP.setSize(600, 500);
        }
        else{
            jLabel13.setText("Nessuna richiesta selezionata");
        }
    }
    
    public String codiceRichiestaSelezionata(){
        return MedicoController.oggettoSelezionatoConHtml(jList1.getSelectedIndex(),strings);
    }
    
    
    private void initComponents() {
   
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new JLabel();
        jLabel13.setText("");
        jCheckBox2 = new JCheckBox();
      

        jLabel1 = new javax.swing.JLabel();
       
        jLabel11 = new javax.swing.JLabel();

        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldPaziente = new JTextField();


        richiesteConsorziati = new JCheckBox();
        richiesteTotali = new ArrayList<>();

        
        initTabRicercaPrescrizioniNonUsate();
        initTabFarmaciPrescrittiDalMedico();
        initTabQuantitaFarmaci();
        initTabListaPazientiPerFarmaco();
        initTabListaFarmaciPaziente();
        initTabFarmaciGenericiComprati();
        initTabReazioniAvverse();
    
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
  
        impostaStringaRichiesta();
        
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setFont(new java.awt.Font("Corbel", 0, 14)); 
        
        jList1.setModel(new javax.swing.AbstractListModel<String>() {

            @Override
            public int getSize() { return strings.size(); }
            @Override
            public String getElementAt(int i) { return strings.get(i); }
        });
        
        jList1.addListSelectionListener(event -> jList1Selection(event));
        
        richiesteConsorziati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listenerCheckBox(evt);
            }
        });
        
        
        jScrollPane1.setViewportView(jList1);
        richiesteConsorziati.setText("<html>Visualizza richieste anche<br>dei medici consorziati<html>");
        jButton1.setText("Effettua Prescrizione");
        jButton1.addActionListener(event -> jButton1ActionPerformed(event));
        
        jCheckBox2.setText("<html> Alcuni farmaci potrebbero provocare<br> controindicazioni date dai fattori di rischio<br> del paziente<html>"); 
         
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
               .addGroup(jPanel1Layout.createParallelGroup()
                .addComponent(jCheckBox2)
                .addComponent(jLabel13)
                .addComponent(richiesteConsorziati)
                .addComponent(jButton1))
                .addGap(0, 10, Short.MAX_VALUE))
                
        );
       jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup()    
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(10,10,10)
            .addComponent(jCheckBox2)
            .addGap(10,10,10)
            .addComponent(jLabel13)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(richiesteConsorziati)
                .addGap(10,10,10)
                .addComponent(jButton1))
                .addContainerGap())
        );
       
        jTabbedPane1.addTab("Prescrizioni da effettuare", jPanel1);
     
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
       
        jTabbedPane1.addTab("Prescrizioni Con Visita", jPanel2);
        
        
        impostaListaFarmaci();
        
        jList2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jList2.setFont(new java.awt.Font("Corbel", 0, 14)); 
        
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
  
            @Override
            public int getSize() { return listaFarmaci.size(); }
            @Override
            public String getElementAt(int i) { return listaFarmaci.get(i); }
        });
         
        jScrollPane2.setViewportView(jList2);
        
        //farjList2.addListSelectionListener(event -> MedicoController.farmaciSelezionati(jList2.getSelectedIndices(),listaFarmaci));


        jButton2.setText("Conferma");
        jButton2.addActionListener(event -> jButton2ActionPerformed(event));
        
        jLabel1.setText("Inserisci codice fiscale del paziente:");
        
        
        jTextFieldPaziente.addKeyListener(new KeyAdapter() { 
             public void keyReleased(KeyEvent e) { 
                jTextFieldPazienteKeyPressed(e); 
             }        
         }); 
          
         jCheckBox1.setText("Alcuni farmaci potrebbero provocare controindicazioni date dai fattori di rischio del paziente"); 


        
javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jTextFieldPaziente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBox1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE,400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 61, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPaziente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jCheckBox1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                            
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
         
         
        //aggiunta terza tab
        jTabbedPane1.addTab("Prescrizioni non usate", jPanel4);
        jTabbedPane1.addTab("Farmaci prescritti in generale", jPanel5);
        jTabbedPane1.addTab("Quantità farmaci prescritti dal medico", jPanel6);
        jTabbedPane1.addTab("Pazienti ai quali è stato prescritto un certo farmaco", jPanel7);
        jTabbedPane1.addTab("Lista di farmaci prescritti ad un paziente", jPanel8);
        jTabbedPane1.addTab("Farmaci generici comprati", jPanel9);
        jTabbedPane1.addTab("Reazioni avverse comunicate", jPanel10);
     
        pack();
    }
     
    private void initTabRicercaPrescrizioniNonUsate(){
        jPanel4 = new JPanel();
        jButton3 = new JButton();
        jScrollPane4 = new JScrollPane();
        jList3 = new JList();
        jTextField5 = new JTextField();
        prescrizioniNonUsate=new ArrayList<>();
        jButton3.setText("Verifica esistenza prescrizioni non usate");
        jLabel14 = new JLabel();
        jLabel14.setText("Codice fiscale paziente:");
        jLabel19 = new JLabel();
        
        
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(20,20,20)
                        .addComponent(jTextField5)
                        .addGap(77, 77, 77)
                        .addComponent(jButton3)
                        .addGap(212, 212, 212))
                    .addGroup(jPanel4Layout.createParallelGroup()  
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(55, Short.MAX_VALUE))
                        .addComponent(jLabel19))))
             
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel14)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel19)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jList3.setModel(new javax.swing.AbstractListModel<String>() {

            @Override
            public int getSize() { return prescrizioniNonUsate.size(); }
            @Override
            public String getElementAt(int i) { return prescrizioniNonUsate.get(i); }
        });
        
        jScrollPane4.setViewportView(jList3);
        
        
        jButton3.addActionListener(event -> listenerButtonPrescrizioniNonUsate());
        
    }
    
    private void initTabFarmaciPrescrittiDalMedico(){
        jButton4= new JButton();
        jList4 = new JList();
        jScrollPane5 = new JScrollPane();
        jComboBox1 = new JComboBox();
        jPanel5 = new JPanel();
        farmaciPrescrittiDaMedico = new ArrayList<>();
        
        jButton4.setText("Verifica farmaci prescritti nel periodo...");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jList4.setModel(new javax.swing.AbstractListModel<String>() { 
            public int getSize() { return farmaciPrescrittiDaMedico.size(); }
            public String getElementAt(int i) { return farmaciPrescrittiDaMedico.get(i); }
        });
        jScrollPane5.setViewportView(jList4);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ultimo mese", "Ultimo anno" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jButton4)
                        .addGap(38, 38, 38)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        
    }
    
    private void initTabQuantitaFarmaci(){
        jLabel5 = new JLabel();
        jComboBox2 = new JComboBox();
        jLabel6 = new JLabel();
        jComboBox3 = new JComboBox();
        jLabel7 = new JLabel();
        jButton5 = new JButton();
        jPanel6 = new JPanel();
        jLabel5.setText("Quantità di farmaco del tipo:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<String>() {
            public int getSize() { return listaFarmaci.size(); }
            public String getElementAt(int i) { return listaFarmaci.get(i); }
        });

        jLabel6.setText("Prescritta nel periodo:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ultimo mese", "Ultimo trimestre", "Ultimo semestre", "Ultimo anno" }));

        jLabel7.setText("");

        jButton5.setText("Controlla");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }

            
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(93, 93, 93)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox2, 0, 144, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jLabel7))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(jButton5)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(43, 43, 43))
        );

        
    }
    
    private void initTabListaPazientiPerFarmaco(){
        jLabel8 = new JLabel();
        jComboBox4 = new JComboBox();
        jList6 = new JList();
        jButton6 = new JButton();
        jPanel7 = new JPanel();
        jScrollPane6 = new JScrollPane();
        listaPazientiPerFarmaco = new ArrayList<>();
        jLabel16=new JLabel();
        
        jLabel8.setText("Farmaco");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<String>() {
            public int getSize() { return listaFarmaci.size(); }
            public String getElementAt(int i) { return listaFarmaci.get(i); }
        });

        jList6.setModel(new javax.swing.AbstractListModel<String>() {
            
            public int getSize() { return listaPazientiPerFarmaco.size(); }
            public String getElementAt(int i) { return listaPazientiPerFarmaco.get(i); }
        });
        jScrollPane6.setViewportView(jList6);

        jButton6.setText("Cerca pazienti ai quali è stato prescritto il farmaco selezionato");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }

            
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(65, 65, 65)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel16)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                       
                        .addGap(85, 85, 85)
                        .addComponent(jButton6)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(43, 43, 43))
        );

        
    }
    
    private void initTabListaFarmaciPaziente(){
        jLabel9 = new JLabel();
        jLabel9.setText("Codice sanitario paziente");
        jLabel10 = new JLabel();
        jComboBox5 = new JComboBox();
        jList5 = new JList();
        jScrollPane7 = new JScrollPane();
        jButton7 = new JButton();
        jPanel8 = new JPanel();
        jTextField3 = new JTextField();
        listaFarmaciPaziente = new ArrayList<>();
        jLabel17=new JLabel();

        jLabel10.setText("Periodo");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ultimo mese", "Ultimo trimestre", "Ultimo semestre", "Ultimo anno" }));

        jList5.setModel(new javax.swing.AbstractListModel<String>() {
            
            public int getSize() { return listaFarmaciPaziente.size(); }
            public String getElementAt(int i) { return listaFarmaciPaziente.get(i); }
        });
        jScrollPane7.setViewportView(jList5);

        jButton7.setText("Cerca");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3)
                            .addComponent(jComboBox5, 0, 146, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jButton7))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel17)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(30, 30, 30))
        );

        
    }
    
    private void initTabFarmaciGenericiComprati(){
        jList7 = new JList();
        jScrollPane8 = new JScrollPane();
        jButton8 = new JButton();
        jPanel9 = new JPanel(); 
        jTextField4 = new JTextField();
        listFarmaciGenericiAcquistati=new ArrayList<>();
        jLabel15=new JLabel();
        jLabel18=new JLabel();
        
        
        jList7.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return listFarmaciGenericiAcquistati.size(); }
            public String getElementAt(int i) { return listFarmaciGenericiAcquistati.get(i); }
        });
        jScrollPane8.setViewportView(jList7);

        jButton8.setText("Controlla");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        
        jLabel15.setText("Codice fiscale paziente:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(20,20,20)
                        .addComponent(jTextField4)
                        .addGap(77, 77, 77)
                        .addComponent(jButton8)
                        .addGap(212, 212, 212))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18))
                        .addContainerGap(55, Short.MAX_VALUE))))
                    
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jButton8)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20,20,20)   
                .addComponent(jLabel18)
                .addContainerGap(58, Short.MAX_VALUE))
        );

    }
    
    private void initTabReazioniAvverse(){
        jButton9 = new JButton();
        jList8 = new JList();
        jScrollPane9 = new JScrollPane();
        jPanel10 = new JPanel();
        listaReazioni = new ArrayList<>();
        jButton9.setText("Controlla reazioni avverse comunicate");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jList8.setModel(new javax.swing.AbstractListModel<String>() {   
            public int getSize() { return listaReazioni.size(); }
            public String getElementAt(int i) { return listaReazioni.get(i); }
        });
        jScrollPane9.setViewportView(jList8);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jButton9))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jButton9)
                .addGap(44, 44, 44)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        
    }
    
    private void listenerCheckBox(ActionEvent evt){
        strings = new ArrayList<>();
        updateQueryListaRichieste();
        impostaStringaRichiesta();
        jList1.updateUI();
    }
    
    private void listenerButtonPrescrizioniNonUsate(){
        String testo = jTextField5.getText();
        boolean pazienteok=false;
        
        for(String paz: getMedicoController().getMedico().listaPazienti())
            if(testo.equals(paz))
                pazienteok=true;
        
        if(testo.equals("")){
            prescrizioniNonUsate=medicoController.listaPrescrizioniNonUsateConData();
            jLabel19.setText("");
        }
        else if(!testo.equals("") && pazienteok){
            prescrizioniNonUsate=medicoController.listaPrescrizioniNonUsateConData(testo);
            jLabel19.setText("");
        }
        else{
            jLabel19.setText("Paziente non presente,codice fiscale errato");
            prescrizioniNonUsate=new ArrayList<>();
        }
        jList3.updateUI();
        
    }
    
    private void jButton4ActionPerformed(ActionEvent evt) {
        int indice = jComboBox1.getSelectedIndex();
        String periodo="";
        if(indice == 0)
            periodo = "Ultimo mese";         
        else{
            if(indice == 1)
                periodo = "Ultimo anno";
            else{
                System.out.println("Messaggio di errore, selezione sbagliata");
            }
        }
                
        farmaciPrescrittiDaMedico=medicoController.farmaciPrescrittiDaMedicoNelPeriodo(periodo);
        jList4.updateUI();
       
    }
    
    private void jButton5ActionPerformed(ActionEvent evt) {
        if(jComboBox2.getSelectedIndex()!= -1)
            jLabel7.setText("Numero occorrenze: "+medicoController.quantitaFarmacoPrescrittoDaUnMedicoNelPeriodo(listaFarmaci.get(jComboBox2.getSelectedIndex()), jComboBox3.getSelectedIndex()));
        else { jLabel7.setText("Selezionare almeno un farmaco");
        }
    }
    
    private void jButton6ActionPerformed(ActionEvent evt) {
        if(jComboBox4.getSelectedIndex()!= -1){
            jLabel16.setText("");
            listaPazientiPerFarmaco=medicoController.pazientiPerFarmaco(listaFarmaci.get(jComboBox4.getSelectedIndex()));
            jList6.updateUI();
        }
        else jLabel16.setText("Selezionare un farmaco");
    }
    
    private void jButton7ActionPerformed(ActionEvent evt) {
        String codice=jTextField3.getText();
        boolean pazienteok=false;
        
       for(String paz: this.getMedicoController().getMedico().listaPazienti())
           if(codice.equals(paz))
               pazienteok=true;
        
        if(!codice.isEmpty() && jComboBox5.getSelectedIndex()>= 0 ){
            if(pazienteok) {
                jLabel17.setText("");
                listaFarmaciPaziente=medicoController.farmacoPerPaziete(codice, jComboBox5.getSelectedIndex());
                jList5.updateUI();
            }
            else  {
                jLabel17.setText("Il codice fiscale del paziente è errato, il paziente non esiste");
                listaFarmaciPaziente=new ArrayList<>();
                jList5.updateUI();
            }
        }
        else{
            jLabel17.setText("Il codice fiscale non deve essere nullo e il periodo deve essere selezionato");
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void updateQueryListaRichieste(){
        ArrayList<Richiesta> r=new ArrayList<>();
        if(!richiesteConsorziati.isSelected()){
            for(Integer s:medicoController.listaRichieste()){
                r.add(medicoController.richiestaConAnagraficaEFarmaco(s));
            }
            richieste=r;
        }
        else{
            for(String s:medicoController.listaRichiesteConsorzio()){
                r.add(medicoController.richiestaConAnagraficaEFarmaco(Integer.parseInt(s)));
            }
            richiesteTotali=r;
        }
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        
        
        // medicoController.effettuaPrescrizioneSuRichiesta(MedicoController.oggettoSelezionato(jList1.getSelectedIndex(),strings));
        creaFinestra2();
       
        updateQueryListaRichieste();
        
        
    }
    
    public void aggiornaLista1(){
        updateQueryListaRichieste();
        impostaStringaRichiesta();
        jList1.clearSelection();
        jList1.updateUI();
    }
    
    private void jButton2ActionPerformed(ActionEvent evt) {
        boolean pazienteok=false;
        for(String cf: medicoController.getlistaPazienti())
            if(jTextFieldPaziente.getText().equals(cf))
                pazienteok=true;
       
        try{
        if(!(jLabel11.getText().equals("")))
            jLabel11.setText("");
        
        if(pazienteok){
            if(medicoController.listaFarmaciSelezionati(jList2.getSelectedIndices(),listaFarmaci).size()>=5 && jTextFieldPaziente.getText().equals("")) 
                jLabel11.setText(  "<html>" + "•Una prescrizione non può contenere più di 5 farmaci<br>" + "•Inserire paziente <html>");
            else if(medicoController.listaFarmaciSelezionati(jList2.getSelectedIndices(),listaFarmaci).isEmpty() && jTextFieldPaziente.getText().equals("") )
                jLabel11.setText(  "<html>" + "•Selezionare almeno un farmaco" + "<br>•Inserire paziente <html>"); 
            else if(jTextFieldPaziente.getText().equals(""))
                jLabel11.setText(  "<html>" + jLabel11.getText()+ "<br>" + "•Inserire paziente <html>");
            else if(medicoController.listaFarmaciSelezionati(jList2.getSelectedIndices(),listaFarmaci).size()>=5)
                jLabel11.setText("Una prescrizione non può contenere più di 5 farmaci");
            else if(medicoController.listaFarmaciSelezionati(jList2.getSelectedIndices(),listaFarmaci).isEmpty())
                jLabel11.setText("<html>" + jLabel11.getText() + "<br>Selezionare almeno un farmaco<html>");
            else if(medicoController.listaFarmaciSelezionati(jList2.getSelectedIndices(),listaFarmaci).size()<=5 && !(jTextFieldPaziente.getText().equals("")) )
                creaFinestra();}  
        else jLabel11.setText("Il paziente non esiste oppure ha un alto medico di base");   
        }      
        
        catch(Exception e){ 
            jLabel11.setText("<html>" + jLabel11.getText() + "<br>eccez<html>");
        }
      
    }
    
    private void jButton8ActionPerformed(ActionEvent evt) {
        String testo=jTextField4.getText();
        boolean pazienteok=false;
        
        for(String paz: getMedicoController().getMedico().listaPazienti())
            if(paz.equals(testo))
                pazienteok=true;
            
        if(testo.equals("")){
            listFarmaciGenericiAcquistati = medicoController.getFarmaciGenericiAcquistati(); 
             jLabel18.setText("");
        }
        else if(!testo.equals("") && pazienteok) {
            listFarmaciGenericiAcquistati = medicoController.getFarmaciGenericiAcquistati(testo);
            jLabel18.setText("");
        }    
        else{
            jLabel18.setText("Il codice fiscale è errato");
            listFarmaciGenericiAcquistati=new ArrayList<>();
        }
        jList7.updateUI();
        
    }
    
    private void jButton9ActionPerformed(ActionEvent evt) {
        listaReazioni= medicoController.getListaReazioniAvverseSegnalate();
        jList8.updateUI();
    }
    
    private void jTextFieldPazienteKeyPressed(KeyEvent e) {   
        boolean pazienteok=false;
         
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            for(String cf: medicoController.getlistaPazienti())
                if(jTextFieldPaziente.getText().equals(cf))
                pazienteok=true;
      
            if(pazienteok){
                jLabel11.setText(""); 
                if( (medicoController.getFattoriDiRischio(jTextFieldPaziente.getText())).isEmpty())
                    jLabel12.setText("Il paziente non ha fattori di rischio");
                else jLabel12.setText(settaFattori(jTextFieldPaziente.getText()) );
            }
            else {
                jLabel11.setText("<html>Il paziente non esiste oppure ha un altro medico di base</html>"); 
                jLabel12.setText("");
            }
        } 
    }
    
    public String settaFattori(String paziente){
        String s="<html>Fattori di rischio del paziente:<br>";
         for(String fattore: medicoController.getFattoriDiRischio(paziente))
             s+= "•" + fattore + "<br><html>";
         
        return s;
    }
    
     
    
    private void jList1Selection(ListSelectionEvent e) {   
        jLabel13.setText("");
        if(!jList1.isSelectionEmpty()){
            String richiesta = medicoController.oggettoSelezionatoConHtml(jList1.getSelectedIndex(), strings);
        
            int richiestaInt = Integer.parseInt(richiesta);
        
            String paziente=medicoController.richiestaConAnagraficaEFarmaco(richiestaInt).getPaziente();
        
            if( (medicoController.getFattoriDiRischio(paziente)).isEmpty()) 
                jLabel13.setText("Il paziente non ha fattori di rischio");
            else 
                jLabel13.setText(settaFattori(paziente) );
        }
    }
    
    public void deselezionaLista2(){
        jList2.clearSelection();
    }
    
    public JCheckBox getCheckBox1(){
        return jCheckBox1;
    }
    
    public JCheckBox getCheckBox2(){
        return jCheckBox2;
    }
    
    
    
}


