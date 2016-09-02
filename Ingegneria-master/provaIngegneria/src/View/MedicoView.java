/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import controller.MedicoController;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
    
    private javax.swing.JButton jButton2;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel jPanel3;
    
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel jLabel1;
    
         
    private MedicoController medicoController;
    private ArrayList<Richiesta> richieste;
    private ArrayList<String> strings= new ArrayList<>();
    private ArrayList<String> listaFarmaci= new ArrayList<>();
    private JList jList3;
     
    private frameConfermaPrescrizione frameP;

    //tab 3
    private javax.swing.JPanel jPanel4;
    private JButton jButton3;
    private JScrollPane jScrollPane4;
    private ArrayList<String> prescrizioniNonUsate;
    
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
    
       public void impostaListaFarmaci(){
        
       for( String f: Farmaco.getListaFarmaci(medicoController.getConnection())){
                listaFarmaci.add("•" + f);
                
        }
    }
    
    
    public JList<String> getLista(){
        return jList1;
    }
    
        
    private  void creaFinestra(){
        frameP= new frameConfermaPrescrizione(MedicoController.farmaciSelezionati(jList2.getSelectedIndices(),listaFarmaci),jTextField2.getText(),this);
        frameP.setVisible(true);
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
        jTextField2 = new javax.swing.JTextField();
        //jScrollPane3 = new javax.swing.JScrollPane();
        //jTextPane1 = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        
        initTabRicercaPrescrizioniNonUsate();
        initTabFarmaciPrescrittiDalMedico();
        initTabQuantitaFarmaci();
        initTabListaPazientiPerFarmaco();
        initTabListaFarmaciPaziente();
    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
  
        impostaStringaRichiesta();
        
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setFont(new java.awt.Font("Corbel", 0, 14)); 
        
        jList1.setModel(new javax.swing.AbstractListModel<String>() {

            @Override
            public int getSize() { return strings.size(); }
            @Override
            public String getElementAt(int i) { return strings.get(i); }
        });
        
        jList1.addListSelectionListener(event -> MedicoController.oggettoSelezionato(jList1.getSelectedIndex(),strings));

        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Effettua Prescrizione");
        jButton1.addActionListener(event -> medicoController.effettuaPrescrizioneSuRichiesta(MedicoController.oggettoSelezionato(jList1.getSelectedIndex(),strings)));
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
       
      
       /*jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
*/
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
        
        jList2.addListSelectionListener(event -> MedicoController.farmaciSelezionati(jList2.getSelectedIndices(),listaFarmaci));


        jButton2.setText("jButton2");
        jButton2.addActionListener(event -> creaFinestra());
        
        jLabel1.setText("Inserisci codice fiscale del paziente:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 75, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(30, 30, 30))
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
     
        pack();
    }
     
    private void initTabRicercaPrescrizioniNonUsate(){
        jPanel4 = new JPanel();
        jButton3 = new JButton();
        jScrollPane4 = new JScrollPane();
        jList3 = new JList();
        prescrizioniNonUsate=new ArrayList<>();
        jButton3.setText("Verifica esistenza prescrizioni non usate");
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                //.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                    
                .addGap(130, 130, 130))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 86, 86)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton3)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            public String getElementAt(int i) { return listaFarmaci.get(i).substring(1); }
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
        
        jLabel8.setText("Farmaco");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<String>() {
            public int getSize() { return listaFarmaci.size(); }
            public String getElementAt(int i) { return listaFarmaci.get(i).substring(1); }
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
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                        .addComponent(jButton7)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(30, 30, 30))
        );

        
    }
    
    private void listenerButtonPrescrizioniNonUsate(){
        prescrizioniNonUsate=medicoController.listaPrescrizioniNonUsateConData();
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
        jLabel7.setText("Numero occorrenze: "+medicoController.quantitaFarmacoPrescrittoDaUnMedicoNelPeriodo(listaFarmaci.get(jComboBox2.getSelectedIndex()).substring(1), jComboBox3.getSelectedIndex()));
        
    }
    
    private void jButton6ActionPerformed(ActionEvent evt) {
        listaPazientiPerFarmaco=medicoController.pazientiPerFarmaco(listaFarmaci.get(jComboBox4.getSelectedIndex()).substring(1));
        jList6.updateUI();
    }
    
    private void jButton7ActionPerformed(ActionEvent evt) {
        String codice=jTextField3.getText();
        if(!codice.isEmpty() && jComboBox5.getSelectedIndex()>= 0 ){
            listaFarmaciPaziente=medicoController.farmacoPerPaziete(codice, jComboBox5.getSelectedIndex());
            jList5.updateUI();
        }
        else{
            System.err.println("Il codice fiscale non deve essere nullo e il periodo deve essere selezionato");
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
