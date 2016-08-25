/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import controller.MedicoController;
import java.util.ArrayList;
import javax.swing.JList;
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
     
    private frameConfermaPrescrizione frameP;

    
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
    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
  
        impostaStringaRichiesta();
        
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setFont(new java.awt.Font("Corbel", 0, 14)); 
        
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            //ArrayList<String> strings= impostaStringaRichiesta();

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
     
        pack();
    }
     
    
}
