
package View;

import SistemaPrescrizioniMain.Avvio;
import controller.MedicoController;
import controller.SegreteriaController;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import model.Farmaco;
import model.Prescrizione;

/**
 *
 * @author Valentina
 */
public class SegreteriaView extends javax.swing.JFrame {
    
    private javax.swing.JButton pulsanteConsegnaPrescrizione;
    private javax.swing.JButton pulsanteGenerazioneRichiesta;
    private javax.swing.JList<String> jListPrescrizioniDaConsegnare;
    private javax.swing.JList<String> listaFarmaciRichiesta;
    private javax.swing.JPanel pannelloConsegnaPrescrizione;
    private javax.swing.JPanel pannelloCreazioneRichiesta;
    private javax.swing.JScrollPane scrollPanePrescrizioniDaConsegnare;
    private javax.swing.JScrollPane scrollPaneListaFarmaciRichiesta;
    private javax.swing.JTabbedPane jTabbedPane1;
    private final SegreteriaController segreteriaController;
    private ArrayList<Prescrizione> prescrizioni;
    private ArrayList<String> farmaci= new ArrayList<>();
    private final Connection c;
    private JTextField campoCodiceFiscale;
    private JLabel codiceFiscaleLabel;
    private JLabel listaFarmaciLabel;
    private JLabel labelErroreRichiesta;
    private ArrayList<String> listaPrescrizioniDaConsegnare;
    private final Avvio avvio;
    private JLabel labelErrore;
    
    
    public SegreteriaView(SegreteriaController s, Connection c, Avvio a){
        this.c=c;
        avvio=a;
        prescrizioni = s.prescrizioniDaConsegnareComePrescrizione();
        segreteriaController=s;
        initComponents(); 
        initTab();
        
        
    }
    
    private void initComponents() {
        labelErrore = new JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pannelloConsegnaPrescrizione = new javax.swing.JPanel();
        pannelloCreazioneRichiesta = new javax.swing.JPanel();
        scrollPanePrescrizioniDaConsegnare = new javax.swing.JScrollPane();
        scrollPaneListaFarmaciRichiesta = new javax.swing.JScrollPane();
        jListPrescrizioniDaConsegnare = new javax.swing.JList<>();
        
        pulsanteConsegnaPrescrizione = new javax.swing.JButton();
        listaPrescrizioniDaConsegnare=new ArrayList<>();
       
        prescrizioni = segreteriaController.prescrizioniDaConsegnareComePrescrizione();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
  
        impostaListaRichieste();
        
        
        jListPrescrizioniDaConsegnare.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        jListPrescrizioniDaConsegnare.setModel(new javax.swing.AbstractListModel<String>() {
            @Override
            public int getSize() { return listaPrescrizioniDaConsegnare.size(); }

            @Override
            public String getElementAt(int index) {
                return listaPrescrizioniDaConsegnare.get(index);
            }
            
        });

        scrollPanePrescrizioniDaConsegnare.setViewportView(jListPrescrizioniDaConsegnare);
        pulsanteConsegnaPrescrizione.setText("Consegna Prescrizione");

        pulsanteConsegnaPrescrizione.addActionListener((java.awt.event.ActionEvent evt) -> {
            azioneConsegnaPrescrizione(evt);
        });
        

        
       
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pannelloConsegnaPrescrizione);
        pannelloConsegnaPrescrizione.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(scrollPanePrescrizioniDaConsegnare, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup()
                .addComponent(labelErrore)
                .addComponent(pulsanteConsegnaPrescrizione))
                .addGap(0, 10, Short.MAX_VALUE))
        );
       jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup()
            .addComponent(scrollPanePrescrizioniDaConsegnare, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(10,10,10)
            .addComponent(labelErrore)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pulsanteConsegnaPrescrizione)
                .addContainerGap())
        );
       

        jTabbedPane1.addTab("Creazione richiesta prescrizione", pannelloCreazioneRichiesta);
        jTabbedPane1.addTab("Consegna prescrizione", pannelloConsegnaPrescrizione);

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
        pulsanteGenerazioneRichiesta = new javax.swing.JButton();
        campoCodiceFiscale = new JTextField();
        codiceFiscaleLabel = new JLabel();
        listaFarmaciLabel = new JLabel();
        labelErroreRichiesta = new JLabel();
        
        listaFarmaciRichiesta = new JList<>();
        pulsanteGenerazioneRichiesta.setText("Genera richiesta prescrizione");
        
        impostaListaRichieste();
        impostaListaFarmaci();
        listaFarmaciRichiesta.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        aggiornaModelloListaFarmaci();
        
        scrollPaneListaFarmaciRichiesta.setViewportView(listaFarmaciRichiesta);
        
        campoCodiceFiscale.setText("");
        codiceFiscaleLabel.setText("Codice Fiscale");
        listaFarmaciLabel.setText("Lista farmaci");
        labelErroreRichiesta.setText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(pannelloCreazioneRichiesta);
        pannelloCreazioneRichiesta.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup()
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(368, 368, 368)
                
                .addComponent(pulsanteGenerazioneRichiesta)
                .addGap(0, 0, Short.MAX_VALUE))
                
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listaFarmaciLabel)
                    .addComponent(codiceFiscaleLabel)
                    .addComponent(scrollPaneListaFarmaciRichiesta, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(63, 63, 63)
                    .addComponent(labelErroreRichiesta)
                    .addComponent(campoCodiceFiscale, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(196, 196, 196))
        );
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup()
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(60, 94, 94)
                .addComponent(codiceFiscaleLabel)                
                .addComponent(campoCodiceFiscale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(listaFarmaciLabel)
                .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(scrollPaneListaFarmaciRichiesta, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(pulsanteGenerazioneRichiesta)
                .addComponent(labelErroreRichiesta)
                )
                .addContainerGap())
        );
        
        pulsanteGenerazioneRichiesta.addActionListener((java.awt.event.ActionEvent evt) -> {
            azioneGeneraRichiesta(evt);
        });
        
        pack();
    }
     
    
    public void impostaListaRichieste(){
        prescrizioni = segreteriaController.prescrizioniDaConsegnareComePrescrizione();
        listaPrescrizioniDaConsegnare=new ArrayList<>();
        prescrizioni.stream().forEach((r) -> {
            listaPrescrizioniDaConsegnare.add(r.getCodicePrescrizione()+ ". Paziente:  " + r.getPaziente() +" "+ r.getNomePaziente(c) +" "+ r.getCognomePaziente(c));
        });
        jListPrescrizioniDaConsegnare.updateUI();
        
    }
    
    public void impostaListaFarmaci(){     
        farmaci=Farmaco.getListaFarmaci(c);
    }
    
    
    private void aggiornaModelloListaFarmaci(){
        listaFarmaciRichiesta.setModel(new javax.swing.AbstractListModel<String>() {
           
            @Override
            public int getSize() { return farmaci.size(); }

            @Override
            public String getElementAt(int index) {
                return farmaci.get(index);
            }           
        }); 
    }
     
    private void azioneConsegnaPrescrizione(java.awt.event.ActionEvent evt) {        
        int[] arr=jListPrescrizioniDaConsegnare.getSelectedIndices();
        if(arr.length>0){
            for(int i=0;i<arr.length;i++){
                segreteriaController.consegnaPrescrizione(MedicoController.oggettoSelezionato(arr[i],listaPrescrizioniDaConsegnare));
            }
            
            impostaListaRichieste();
            for(int i=0;i<arr.length;i++){
                arr[i]=-1;
            }
            jListPrescrizioniDaConsegnare.setSelectedIndices(arr);
            this.repaint();
        }
        else{
            labelErrore.setText("<html>Nessuna prescrizione selezionata</html>");
        }
        
    } 
    
    private void azioneGeneraRichiesta(ActionEvent evt) {       
        if(listaFarmaciSelezionati().isEmpty()){
            labelErroreRichiesta.setText("Nessun farmaco selezionato");
        }
        else if(listaFarmaciSelezionati().size()<=5){
            if(SegreteriaController.controlloPresenzaPaziente(c, campoCodiceFiscale.getText())){
                segreteriaController.inviaRichiestaPrescrizione(campoCodiceFiscale.getText(), listaFarmaciSelezionati());
                listaFarmaciRichiesta.removeSelectionInterval(0, farmaci.size()-1);
                labelErroreRichiesta.setText("Richiesta prescrizione generata");
                segreteriaController.updateMedicoView();
            }
            else{
                labelErroreRichiesta.setText("Paziente non presente");
            }
        }
        else{
            labelErroreRichiesta.setText("<html>Una prescrizione non può contenere più di 5 farmaci</html>");
        }
    }
    
    private ArrayList<String> listaFarmaciSelezionati(){
        ArrayList<String> risultato = new ArrayList<>();
        int[] indici=listaFarmaciRichiesta.getSelectedIndices();
        for(int i=0;i<indici.length;i++){
            risultato.add(farmaci.get(indici[i]));
        }
        return risultato;
    }
}
