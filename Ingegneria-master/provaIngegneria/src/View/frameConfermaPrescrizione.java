/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Valentina
 */
public class frameConfermaPrescrizione extends JFrame {
    
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
    
    
    public frameConfermaPrescrizione(ArrayList<String> farmaci,String pazienteCF, MedicoView mv){
       this.farmaci=farmaci;
       this.pazienteCF=pazienteCF;
       this.mv=mv;
       initComponent();
       
    }
    
    private void impostaLabel(){
        ArrayList<String> dati = new ArrayList<String>();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        farmaciJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            @Override
            public int getSize() { return strings.length; }
            @Override
            public String getElementAt(int i) { return strings[i]; }
        });
       
        jScrollPane1.setViewportView(farmaciJList);

        annulla.setText("Annulla");
        conferma.setText("Conferma");
        impostaLabel();
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(annulla)
                .addGap(18, 18, 18)
                .addComponent(conferma)
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cf)
                    .addComponent(paziente)
                    .addComponent(via)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(conferma))
                        .addContainerGap(41, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(medico)
                        .addGap(90, 90, 90)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
        
    }
    
}
