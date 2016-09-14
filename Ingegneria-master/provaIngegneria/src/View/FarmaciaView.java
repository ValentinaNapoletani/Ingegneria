
package View;

import controller.Farmacia;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JFrame;
import model.Farmaco;
import model.Prescrizione;

/**
 *
 * @author Viktor
 */
public class FarmaciaView extends JFrame {
    private final Connection c;
    private final Farmacia farmacia;
    private ArrayList<String> listaFarmaci;
    private ArrayList<String> farmaciEsistenti;

    public FarmaciaView(Farmacia f, Connection c) {
        this.c = c;
        farmacia=f;
        listaFarmaci = new ArrayList<>();
        farmaciEsistenti= new ArrayList<>();
        farmaciEsistenti=Farmaco.getListaFarmaci(c);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pannelloControlloPresenzaFarmaci = new javax.swing.JPanel();
        nomeFarmacoPresenteLabel = new javax.swing.JLabel();
        pulsanteControllaPresenzaFarmaco = new javax.swing.JButton();
        erroreControlloPresenzaFarmaco = new javax.swing.JLabel();
        quantitaFarmacoPresenteLabel = new javax.swing.JLabel();
        campoQuantitaFarmaciDaControllare = new javax.swing.JTextField();
        comboBoxFarmaciDaControllare = new javax.swing.JComboBox<>();
        pannelloOrdinazioneFarmaci = new javax.swing.JPanel();
        farmacoDaOrdinareLabel = new javax.swing.JLabel();
        farmaciDaOrdinare = new javax.swing.JComboBox<>();
        quantitaFarmacoDaOrdinareLabel = new javax.swing.JLabel();
        campoQuantitaFarmaciDaOrdinare = new javax.swing.JTextField();
        pulsanteOrdina = new javax.swing.JButton();
        erroreOrdinazioneFarmaci = new javax.swing.JLabel();
        pannelloVenditaFarmaci = new javax.swing.JPanel();
        labelCodiceSanitario = new javax.swing.JLabel();
        labelNumeroPrescrizione = new javax.swing.JLabel();
        etichetta = new javax.swing.JLabel();
        campoCodiceSanitario = new javax.swing.JTextField();
        campoNumeroPrescrizione = new javax.swing.JTextField();
        pulsanteVerificaPrescrizione = new javax.swing.JButton();
        pulsanteVendi = new javax.swing.JButton();
        messaggioErrore = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaFarmaciPrescrizione = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        labelScontrino = new javax.swing.JLabel();

        nomeFarmacoPresenteLabel.setText("Nome del farmaco");

        pulsanteControllaPresenzaFarmaco.setText("Controlla");
        pulsanteControllaPresenzaFarmaco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsanteControllaPresenzaFarmacoActionPerformed(evt);
            }
        });

        quantitaFarmacoPresenteLabel.setText("Quantità");

        comboBoxFarmaciDaControllare.setModel(new javax.swing.DefaultComboBoxModel<String>() {

            public int getSize() { return farmaciEsistenti.size(); }
            public String getElementAt(int i) { return farmaciEsistenti.get(i); }
        });

        javax.swing.GroupLayout pannelloControlloPresenzaFarmaciLayout = new javax.swing.GroupLayout(pannelloControlloPresenzaFarmaci);
        pannelloControlloPresenzaFarmaci.setLayout(pannelloControlloPresenzaFarmaciLayout);
        pannelloControlloPresenzaFarmaciLayout.setHorizontalGroup(
            pannelloControlloPresenzaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelloControlloPresenzaFarmaciLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(pannelloControlloPresenzaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pannelloControlloPresenzaFarmaciLayout.createSequentialGroup()
                        .addGroup(pannelloControlloPresenzaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pannelloControlloPresenzaFarmaciLayout.createSequentialGroup()
                                .addComponent(quantitaFarmacoPresenteLabel)
                                .addGap(104, 104, 104)
                                .addComponent(campoQuantitaFarmaciDaControllare, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pannelloControlloPresenzaFarmaciLayout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(pulsanteControllaPresenzaFarmaco))
                            .addComponent(erroreControlloPresenzaFarmaco, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(pannelloControlloPresenzaFarmaciLayout.createSequentialGroup()
                        .addComponent(nomeFarmacoPresenteLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboBoxFarmaciDaControllare, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))))
        );
        pannelloControlloPresenzaFarmaciLayout.setVerticalGroup(
            pannelloControlloPresenzaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelloControlloPresenzaFarmaciLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(pannelloControlloPresenzaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeFarmacoPresenteLabel)
                    .addComponent(comboBoxFarmaciDaControllare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pannelloControlloPresenzaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantitaFarmacoPresenteLabel)
                    .addComponent(campoQuantitaFarmaciDaControllare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(erroreControlloPresenzaFarmaco, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pulsanteControllaPresenzaFarmaco)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Controllo presenza farmaco", pannelloControlloPresenzaFarmaci);

        farmacoDaOrdinareLabel.setText("Farmaco");

        farmaciDaOrdinare.setModel(new javax.swing.DefaultComboBoxModel<String>() {

            public int getSize() { return farmaciEsistenti.size(); }
            public String getElementAt(int i) { return farmaciEsistenti.get(i); }
        });

        quantitaFarmacoDaOrdinareLabel.setText("Quantità");

        pulsanteOrdina.setText("Ordina");
        pulsanteOrdina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsanteOrdinaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pannelloOrdinazioneFarmaciLayout = new javax.swing.GroupLayout(pannelloOrdinazioneFarmaci);
        pannelloOrdinazioneFarmaci.setLayout(pannelloOrdinazioneFarmaciLayout);
        pannelloOrdinazioneFarmaciLayout.setHorizontalGroup(
            pannelloOrdinazioneFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelloOrdinazioneFarmaciLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pannelloOrdinazioneFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelloOrdinazioneFarmaciLayout.createSequentialGroup()
                        .addComponent(farmacoDaOrdinareLabel)
                        .addGap(175, 175, 175))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelloOrdinazioneFarmaciLayout.createSequentialGroup()
                        .addComponent(quantitaFarmacoDaOrdinareLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pannelloOrdinazioneFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoQuantitaFarmaciDaOrdinare, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(farmaciDaOrdinare, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(117, 117, 117))
            .addGroup(pannelloOrdinazioneFarmaciLayout.createSequentialGroup()
                .addGroup(pannelloOrdinazioneFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pannelloOrdinazioneFarmaciLayout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(pulsanteOrdina))
                    .addGroup(pannelloOrdinazioneFarmaciLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(erroreOrdinazioneFarmaci, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        pannelloOrdinazioneFarmaciLayout.setVerticalGroup(
            pannelloOrdinazioneFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelloOrdinazioneFarmaciLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pannelloOrdinazioneFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(farmacoDaOrdinareLabel)
                    .addComponent(farmaciDaOrdinare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pannelloOrdinazioneFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantitaFarmacoDaOrdinareLabel)
                    .addComponent(campoQuantitaFarmaciDaOrdinare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(erroreOrdinazioneFarmaci, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pulsanteOrdina)
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Ordinazione farmaci", pannelloOrdinazioneFarmaci);

        labelCodiceSanitario.setText("Codice Sanitario");

        labelNumeroPrescrizione.setText("Numero prescrizione");

        etichetta.setText("Farmaci relativi alla prescrizione:");

        pulsanteVerificaPrescrizione.setText("Verifica prescrizione");
        pulsanteVerificaPrescrizione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsanteVerificaPrescrizioneActionPerformed(evt);
            }
        });

        pulsanteVendi.setText("Vendi");
        pulsanteVendi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsanteVendiActionPerformed(evt);
            }
        });

        listaFarmaciPrescrizione.setModel(new javax.swing.AbstractListModel<String>() {
            //String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return listaFarmaci.size(); }
            public String getElementAt(int i) { return listaFarmaci.get(i); }
        });
        jScrollPane2.setViewportView(listaFarmaciPrescrizione);

        jScrollPane3.setViewportView(labelScontrino);

        javax.swing.GroupLayout pannelloVenditaFarmaciLayout = new javax.swing.GroupLayout(pannelloVenditaFarmaci);
        pannelloVenditaFarmaci.setLayout(pannelloVenditaFarmaciLayout);
        pannelloVenditaFarmaciLayout.setHorizontalGroup(
            pannelloVenditaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelloVenditaFarmaciLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pannelloVenditaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pannelloVenditaFarmaciLayout.createSequentialGroup()
                        .addGroup(pannelloVenditaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etichetta))
                        .addGap(18, 18, 18)
                        .addGroup(pannelloVenditaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pulsanteVendi)
                            .addGroup(pannelloVenditaFarmaciLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pannelloVenditaFarmaciLayout.createSequentialGroup()
                        .addGroup(pannelloVenditaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCodiceSanitario)
                            .addComponent(labelNumeroPrescrizione))
                        .addGap(29, 29, 29)
                        .addGroup(pannelloVenditaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pannelloVenditaFarmaciLayout.createSequentialGroup()
                                .addComponent(campoNumeroPrescrizione, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pulsanteVerificaPrescrizione, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(campoCodiceSanitario, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(messaggioErrore, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pannelloVenditaFarmaciLayout.setVerticalGroup(
            pannelloVenditaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelloVenditaFarmaciLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pannelloVenditaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodiceSanitario)
                    .addComponent(campoCodiceSanitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pannelloVenditaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNumeroPrescrizione)
                    .addComponent(campoNumeroPrescrizione, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pulsanteVerificaPrescrizione))
                .addGroup(pannelloVenditaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pannelloVenditaFarmaciLayout.createSequentialGroup()
                        .addGroup(pannelloVenditaFarmaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pannelloVenditaFarmaciLayout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelloVenditaFarmaciLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(messaggioErrore, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(etichetta)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addGap(34, 34, 34))
                    .addGroup(pannelloVenditaFarmaciLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pulsanteVendi))))
        );

        jTabbedPane1.addTab("Vendita farmaci", pannelloVenditaFarmaci);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pulsanteControllaPresenzaFarmacoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulsanteControllaPresenzaFarmacoActionPerformed
        
        if(comboBoxFarmaciDaControllare.getSelectedIndex() >= 0 && !campoQuantitaFarmaciDaControllare.getText().isEmpty()){
            String nome  = farmaciEsistenti.get(comboBoxFarmaciDaControllare.getSelectedIndex());
            try{
                int quantita = Integer.parseInt(campoQuantitaFarmaciDaControllare.getText());
                if(quantita > 0){
                    boolean ris = farmacia.controlloPresenzaFarmaco(nome,quantita);
                    if (ris){
                        erroreControlloPresenzaFarmaco.setText("Il farmaco è presente nella farmacia");
                    }
                    else{
                        erroreControlloPresenzaFarmaco.setText("<html>Il farmaco non è presente nella farmacia oppure non ci sono abbastanza confezioni disponibili</html>");
                    }
                }
                else{
                    erroreControlloPresenzaFarmaco.setText("<html>La quantità deve essere un numero maggiore di 0</html>");
                }
            }
            catch(NumberFormatException e){
                erroreControlloPresenzaFarmaco.setText("La quantità deve essere un numero intero");
            }
            
        }
        else{
            erroreControlloPresenzaFarmaco.setText("<html>Entrambi i campi devono essere compilati</html>");
        }
    }//GEN-LAST:event_pulsanteControllaPresenzaFarmacoActionPerformed
    
    private void pulsanteVerificaPrescrizioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulsanteVerificaPrescrizioneActionPerformed
        String codiceFiscale = campoCodiceSanitario.getText();
        int codicePrescrizione;
             
        if(!campoNumeroPrescrizione.getText().equals("")) {
            codicePrescrizione=Integer.parseInt(campoNumeroPrescrizione.getText());                     
            labelScontrino.setText("");
            if(Farmacia.controlloPrescrizione(c, codiceFiscale, codicePrescrizione)){
                messaggioErrore.setText("");
                listaFarmaci=Prescrizione.getFarmaciEquivalenti(codicePrescrizione, c);
                listaFarmaciPrescrizione.updateUI();
            }
            else{
                messaggioErrore.setText("<html>Non esiste il paziente con la seguente prescrizione oppure la prescrizione è scaduta</html>");
                listaFarmaci=new ArrayList<>();
                listaFarmaciPrescrizione.updateUI();
            }
        }
        else { messaggioErrore.setText("Inserire dati mancanti");
               listaFarmaci=new ArrayList<>();
               listaFarmaciPrescrizione.updateUI();
        }
        
    }//GEN-LAST:event_pulsanteVerificaPrescrizioneActionPerformed

    private void pulsanteVendiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulsanteVendiActionPerformed
        int codicePrescrizione = Integer.parseInt(campoNumeroPrescrizione.getText());
        ArrayList<String> lista = listaFarmaciSelezionati();
        ArrayList<String> listaFarmaciComprati = new ArrayList<>();
        int cont = 0;
        String scontrino = "";
        boolean almenoUnFarmacoAssente=false;
        labelScontrino.setText("");
        messaggioErrore.setText("");
        if(lista.size()>0){
            for(String s: lista){
                if(farmacia.controlloPresenzaFarmaco(s)){
                    farmacia.compraFarmaco(s, codicePrescrizione);
                    listaFarmaciComprati.add(s);
                    cont++;
                }
                else{
                    almenoUnFarmacoAssente=true;
                }
            }
            if(cont>0){
                labelScontrino.setText(farmacia.rilasciaScontrino(listaFarmaciComprati));
                if(almenoUnFarmacoAssente){
                    messaggioErrore.setText("<html>Uno o più farmaci tra quelli selezionati non è presente nella farmacia</html>");
                }
                farmacia.impostaPrescrizioneUsata(Integer.parseInt(campoNumeroPrescrizione.getText()));
            }
            else{
               messaggioErrore.setText("<html>Nessun farmaco tra quelli selezionati è presente nella farmacia</html>"); 
            }
        }
        else{
            messaggioErrore.setText("Nessun farmaco selezionato");
        }
    }//GEN-LAST:event_pulsanteVendiActionPerformed

    private void pulsanteOrdinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulsanteOrdinaActionPerformed

        try{
            String farmaco = farmaciEsistenti.get(farmaciDaOrdinare.getSelectedIndex());
            int quantita = Integer.parseInt(campoQuantitaFarmaciDaOrdinare.getText());
            if(quantita > 0){
                if(farmacia.ordinaFarmaco(farmaco, quantita)){
                    erroreOrdinazioneFarmaci.setText("<html>"+quantita+" confezioni di "+farmaco+" ordinate</html>");
                }
                else{
                    erroreOrdinazioneFarmaci.setText("Non è stato possibile ordinare il farmaco");
                }  
            }
            else{
                erroreOrdinazioneFarmaci.setText("<html>La quantità deve essere un numero maggiore di 0</html>");
            }
        }
        catch(NumberFormatException e){
            erroreOrdinazioneFarmaci.setText("La quantità deve essere un numero intero");
        }
        catch(ArrayIndexOutOfBoundsException e){
            erroreOrdinazioneFarmaci.setText("Selezionare un farmaco");
        }
        
    }//GEN-LAST:event_pulsanteOrdinaActionPerformed

    private ArrayList<String> listaFarmaciSelezionati(){
        ArrayList<String> risultato = new ArrayList<String>();
        int[] indici=listaFarmaciPrescrizione.getSelectedIndices();
        System.out.println(indici.length);
        for(int i=0;i<indici.length;i++){
            risultato.add(listaFarmaci.get(indici[i]));
        }
        return risultato;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoCodiceSanitario;
    private javax.swing.JTextField campoNumeroPrescrizione;
    private javax.swing.JTextField campoQuantitaFarmaciDaControllare;
    private javax.swing.JTextField campoQuantitaFarmaciDaOrdinare;
    private javax.swing.JComboBox<String> comboBoxFarmaciDaControllare;
    private javax.swing.JLabel erroreControlloPresenzaFarmaco;
    private javax.swing.JLabel erroreOrdinazioneFarmaci;
    private javax.swing.JLabel etichetta;
    private javax.swing.JComboBox<String> farmaciDaOrdinare;
    private javax.swing.JLabel farmacoDaOrdinareLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCodiceSanitario;
    private javax.swing.JLabel labelNumeroPrescrizione;
    private javax.swing.JLabel labelScontrino;
    private javax.swing.JList<String> listaFarmaciPrescrizione;
    private javax.swing.JLabel messaggioErrore;
    private javax.swing.JLabel nomeFarmacoPresenteLabel;
    private javax.swing.JPanel pannelloControlloPresenzaFarmaci;
    private javax.swing.JPanel pannelloOrdinazioneFarmaci;
    private javax.swing.JPanel pannelloVenditaFarmaci;
    private javax.swing.JButton pulsanteControllaPresenzaFarmaco;
    private javax.swing.JButton pulsanteOrdina;
    private javax.swing.JButton pulsanteVendi;
    private javax.swing.JButton pulsanteVerificaPrescrizione;
    private javax.swing.JLabel quantitaFarmacoDaOrdinareLabel;
    private javax.swing.JLabel quantitaFarmacoPresenteLabel;
    // End of variables declaration//GEN-END:variables
}
