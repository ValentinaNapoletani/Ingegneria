
package View;

import controller.MedicoController;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Valentina
 */
public final class frameConfermaPrescrizione extends JFrame {
    
    ArrayList<String> farmaci;
    String pazienteCF;
    MedicoView mv;
    
    private javax.swing.JButton annulla;
    private javax.swing.JButton conferma;
    private javax.swing.JLabel medico;
    private javax.swing.JLabel codPrescr;
    private javax.swing.JLabel data;
    private javax.swing.JLabel cf;
    private javax.swing.JLabel paziente;
    private javax.swing.JLabel via;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> farmaciJList;
    private javax.swing.JButton interazioneFarmaci;
    private javax.swing.JLabel labelInterazione;
    private ArrayList<String> farmaciContrastanti;
    private boolean premuto=false;
    private final String modalita;
    private String stringaFarmaci="";
 
    
    public frameConfermaPrescrizione(ArrayList<String> farmaci,String pazienteCF, MedicoView mv,String modalita){
       this.farmaci=farmaci;
       this.pazienteCF=pazienteCF;
       this.mv=mv;
       this.modalita=modalita;
       initComponent();
       
    }
    
    private void impostaLabel(){
        ArrayList<String> dati = new ArrayList<>();
        dati=(mv.getMedicoController()).impostaDatiPerPrescrizione(pazienteCF);
         
        
        via.setText(dati.get(0));
        paziente.setText(dati.get(1) +  " " + dati.get(2));
        medico.setText("Dott." + dati.get(3) +  " " + dati.get(4));
        data.setText(dati.get(5));
        cf.setText(pazienteCF);
        codPrescr.setText("Prescrizione num:" + dati.get(6));
        
    }
     
    public JLabel getMedico(){
       return medico;
    }
    
    public JLabel getCodice(){
       return codPrescr;
    }
    
    public JLabel getPaziente(){
       return paziente;
    }
    
    public JLabel getCF(){
       return cf;
    }
    
    public JLabel getVia(){
       return via;
    }
    
    public JLabel getData(){
       return data;
    }
    
    public void initComponent(){
        
        medico = new javax.swing.JLabel();
        codPrescr = new javax.swing.JLabel();
        data = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        annulla = new javax.swing.JButton();
        conferma = new javax.swing.JButton();
        cf = new javax.swing.JLabel();
        paziente= new javax.swing.JLabel();
        via = new javax.swing.JLabel();
        farmaciJList = new javax.swing.JList<>();
        interazioneFarmaci = new javax.swing.JButton();
        labelInterazione  = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
 
        farmaciJList.setModel(new javax.swing.AbstractListModel<String>() {
            
            @Override
            public int getSize() { return farmaci.size(); }
            @Override
            public String getElementAt(int i) { return farmaci.get(i); }
        });
       
       
        jScrollPane1.setViewportView(farmaciJList);

        annulla.setText("Annulla");
        conferma.setText("Conferma");
        impostaLabel();
        
        annulla.addActionListener(event -> annullaActionPerformed(event));
        conferma.addActionListener(event -> confermaActionPerformed(event));
       
        interazioneFarmaci.setText("Interazione farmaci");
        interazioneFarmaci.addActionListener(event -> farmaciActionPerformed(event));
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelInterazione, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                        .addGap(10,10,10)
                        .addComponent(interazioneFarmaci, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(annulla)
                .addGap(18, 18, 18)
                .addComponent(conferma)
                .addGap(43, 43, 43))
            .addComponent(labelInterazione, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cf)
                    .addComponent(paziente)
                    .addComponent(via)                    
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(interazioneFarmaci, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(codPrescr)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addComponent(data))
                        .addGap(76, 76, 76))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(medico)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cf)
                    .addComponent(codPrescr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paziente)
                    .addComponent(data))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(via)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(annulla)
                            .addComponent(conferma)
                            .addGroup(layout.createSequentialGroup()
                            .addComponent(interazioneFarmaci)
                            .addComponent(labelInterazione)))
                        .addContainerGap(41, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(medico)       
                        .addGap(90, 90, 90)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
        
    }

    private void annullaActionPerformed(ActionEvent event) {
        this.dispose();
    }

    private void confermaActionPerformed(ActionEvent event) {  
        if("visita".equals(modalita)){ 
            mv.getMedicoController().effettuaPrescrizioneConVisita(pazienteCF, farmaci);
            if(mv.getCheckBox1().isSelected())
                mv.getMedicoController().impostaRischioPrescrizione();
        }
        else {
            mv.getMedicoController().effettuaPrescrizioneSuRichiesta(Integer.parseInt(mv.codiceRichiestaSelezionata()));
            if(mv.getCheckBox2().isSelected())
                mv.getMedicoController().impostaRischioPrescrizione();
        }
        if(premuto) {
            mv.getMedicoController().aggiornaDbConFarmaciContrastanti();
            premuto=false;
        }
        mv.aggiornaLista1(); //non va ???
        mv.deselezionaLista2();
        this.dispose();
    }

   
    private void farmaciActionPerformed(ActionEvent event) {
        
        premuto=true;
        
        ArrayList<String> farmaciPrescrizione=new ArrayList<>();
        String richiesta;
        richiesta = MedicoController.oggettoSelezionatoConHtml(mv.getLista().getSelectedIndex(),mv.getRichiesteNonPrescritte() );
       
        if("visita".equals(modalita)) 
            farmaciPrescrizione= mv.getMedicoController().listaFarmaciSelezionati(mv.getLista2().getSelectedIndices(),mv.getListaFarmaci());      
        else if("richiesta".equals(modalita))
            farmaciPrescrizione=mv.getMedicoController().richiestaConAnagraficaEFarmaco(Integer.parseInt(richiesta)).getFarmaci();
                     
        farmaciContrastanti=mv.getMedicoController().listaFarmaciSelezionati(farmaciJList.getSelectedIndices(), farmaciPrescrizione);
        
     
       
        if(farmaciContrastanti.size()==2) {
            stringaFarmaci+="<br> - " + farmaciContrastanti.get(0) + ", " + farmaciContrastanti.get(1);
            labelInterazione.setText("<html>Coppie di farmaci in contrasto:" + stringaFarmaci+"</html>");
       }
       else if(farmaciContrastanti.size()>2)
           labelInterazione.setText("Selezionare solo 2 farmaci");
       else labelInterazione.setText("Devono essere selezionati 2 farmaci");
       farmaciJList.clearSelection();
    }
    
    public ArrayList<String> getFarmaciContrastanti(){
        return farmaciContrastanti;
    }
    
}
