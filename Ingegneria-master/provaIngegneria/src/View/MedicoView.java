package View;

import controller.MedicoController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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

    private JButton pulsanteTabRichiestePrescrizione;
    private JList<String> listaRichiestePrescrizione;
    private JPanel pannelloPrescrizioniDaEffettuare;
    private JScrollPane scrollPanelistaRichiestePrescrizione;
    private JTabbedPane jTabbedPane1;
    private JCheckBox richiesteConsorziati;

    private JButton pulsanteConferma;
    private JList<String> listaFarmaci2;
    private JPanel pannelloPrescrizioniConVisita;
    private JScrollPane scrollPaneListaFarmaci;

    private JPanel pannelloPrescrizioniConVisita2;

    private JLabel labelInserisciCodiceFiscale;
    private JLabel labelFattoriDiRischio;
    private JCheckBox checkBoxPresenzaFarmaciConControindicazioni;

    private MedicoController medicoController;
    private ArrayList<Richiesta> richieste;
    private ArrayList<Richiesta> richiesteTotali;
    private ArrayList<String> richiesteNonPrescritte = new ArrayList<>();
    private ArrayList<String> listaFarmaci = new ArrayList<>();
    private JList listaPrescrizioniNonUsate;

    private javax.swing.JLabel messaggioErroreSelezioneFarmaco;
    private javax.swing.JLabel labelFattoriDiRischio2;
    private javax.swing.JCheckBox checkBoxControindicazioni;
    private JTextField codiceFiscaleFarmaciGenericiComprati;

    private frameConfermaPrescrizione frameP;

    //tab 3
    private javax.swing.JPanel pannelloPrescrizioniNonUsate;
    private JButton pulsanteVerificaPrescrizione;
    private JScrollPane scrollPanePrescrizioniNonUsate;
    private ArrayList<String> prescrizioniNonUsate;
    private JTextField jTextField5;

    //tab4
    private JButton pulsanteFarmaciNelPerido;
    private JList listaFarmaciPrescrittiDaMedico;
    private JScrollPane scrollPaneFarmaciPrescrittiDaMedico;
    private JComboBox comboBoxPeriodoPrescrizione;
    private JPanel pannelloFarmaciPrescrittiInGenerale;
    private ArrayList<String> farmaciPrescrittiDaMedico;

    //tab5
    private JLabel labelTipoFarmaco;
    private JComboBox comboBoxPeriodoQuantitaFarmaci;
    private JLabel labelPrescrittaNelPeriodo;
    private JComboBox comboBoxPeriodiQuantitaFarmaci;
    private JLabel labelRispostaQuantitaFarmaci;
    private JButton controllaQuantitaFarmaci;
    private JPanel pannelloQuantitaFarmaciPrescrittiDalMedico;

    //tab6
    private JButton pulsanteRicercaPazienti;
    private JPanel pannelloPazientiPerFarmaco;
    private JLabel labelFrmaco;
    private JComboBox comboBoxListaFarmaci;
    private JList listaPazientiPerFarmaco2;
    private JScrollPane scrollPanePazientiPerFrmaco;
    private ArrayList<String> listaPazientiPerFarmaco;

    //tab7
    private JLabel labelCodiceSanitarioPaziente;
    private JLabel labelPeriodo;
    private JComboBox comboBoxPeriodoFarmaciPaziente;
    private JList listaFarmaciPaziente2;
    private JScrollPane scrollPaneFarmaciPaziente;
    private JButton cercaFarmaciPaziente;
    private JPanel pannelloFarmaciPerPaziente;
    private JTextField codiceSanitarioTabFarmaciPaziente;
    private ArrayList<String> listaFarmaciPaziente;

    //tab8
    private JList listaFarmaciGenerici;
    private JScrollPane scrollPaneFarmaciGenerici;
    private JButton controlloFarmaciGenerici;
    private JPanel panelloFarmaciGenerici;
    private ArrayList<String> listFarmaciGenericiAcquistati;
    private JTextField jTextFieldPaziente;

    //tab9
    private JButton pulsanteControlloReazioniAvverse;
    private JScrollPane scrollPaneListaReazioni;
    private JList listaReazioni2;
    private JPanel pannelloReazioniAvverse;
    private ArrayList<String> listaReazioni;
    private JLabel codiceFiscaleLabel;
    private JLabel codiceFiscaleTabFarmaciGenericiComprati;
    private JLabel errorePazientiPerFarmaco;
    private JLabel erroreTabListaFarmaciPaziente;
    private JLabel erroreTabFarmaciGenerici;
    private JLabel errorePrescrizioniNonUsate;

    //tab10
    private ArrayList<String> farmaciContrastanti;
    private JButton pulsanteCercaFarmaciContrastanti;
    private JPanel pannelloFarmaciContrastanti;
    private JScrollPane scrollPaneFarmaciContrastanti;
    private JList listaFarmacicontrastanti;

    public MedicoView(ArrayList<Richiesta> richieste, MedicoController medicoController) {
        this.richieste = richieste;
        this.medicoController = medicoController;

        initComponents();
    }

    public ArrayList<String> getRichiesteNonPrescritte() {
        return richiesteNonPrescritte;
    }

    public MedicoController getMedicoController() {
        return medicoController;
    }

    public frameConfermaPrescrizione getFrameP() {
        return frameP;
    }

    public void impostaStringaRichiesta() {

        ArrayList<String> codric = new ArrayList<>();
        ArrayList<Richiesta> temp;

        String stringaFarmaci = "";

        if (!richiesteConsorziati.isSelected()) {
            temp = richieste;
        } else {
            temp = richiesteTotali;
        }

        for (Richiesta r : temp) {
            for (String s : r.getFarmaci()) {
                stringaFarmaci += "<br>•" + s;
            }
            codric.add("<html>" + r.getCodiceRichiesta() + " " + r.getCognomePaziente() + " " + r.getNomePaziente() + " " + r.getPaziente() + "<table>" + stringaFarmaci + "<br><br>" + "<html>");
            stringaFarmaci = "";

            richiesteNonPrescritte = codric;
        }

    }

    public void impostaListaFarmaci() {

        for (String f : Farmaco.getListaFarmaci(medicoController.getConnection())) {
            listaFarmaci.add(f);

        }
    }

    public JList<String> getLista() {
        return listaRichiestePrescrizione;
    }

    public JList<String> getLista2() {
        return listaFarmaci2;
    }

    public ArrayList<String> getListaFarmaci() {
        return listaFarmaci;
    }

    private void creaFinestra() {
        frameP = new frameConfermaPrescrizione(medicoController.listaFarmaciSelezionati(listaFarmaci2.getSelectedIndices(), listaFarmaci), jTextFieldPaziente.getText(), this, "visita");
        frameP.setVisible(true);
        frameP.setSize(600, 500);
    }

    private void creaFinestra2() {
        if (codiceRichiestaSelezionata() != null) {
            ArrayList<String> listaFarmaciTemp = medicoController.richiestaConAnagraficaEFarmaco(Integer.parseInt(codiceRichiestaSelezionata())).getFarmaci();
            frameP = new frameConfermaPrescrizione(listaFarmaciTemp, medicoController.richiestaConAnagraficaEFarmaco(Integer.parseInt(codiceRichiestaSelezionata())).getPaziente(), this, "richiesta");
            frameP.setVisible(true);
            frameP.setSize(600, 500);
        } else {
            labelFattoriDiRischio.setText("Nessuna richiesta selezionata");
        }
    }

    public String codiceRichiestaSelezionata() {
        return MedicoController.oggettoSelezionatoConHtml(listaRichiestePrescrizione.getSelectedIndex(), richiesteNonPrescritte);
    }

    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pannelloPrescrizioniDaEffettuare = new javax.swing.JPanel();
        pannelloPrescrizioniConVisita = new javax.swing.JPanel();
        scrollPanelistaRichiestePrescrizione = new javax.swing.JScrollPane();
        listaRichiestePrescrizione = new javax.swing.JList<>();
        pulsanteTabRichiestePrescrizione = new javax.swing.JButton();
        pannelloPrescrizioniConVisita = new javax.swing.JPanel();
        pannelloPrescrizioniConVisita2 = new javax.swing.JPanel();
        scrollPaneListaFarmaci = new javax.swing.JScrollPane();
        listaFarmaci2 = new javax.swing.JList<>();
        pulsanteConferma = new javax.swing.JButton();
        labelFattoriDiRischio = new JLabel();
        labelFattoriDiRischio.setText("");
        checkBoxPresenzaFarmaciConControindicazioni = new JCheckBox();

        labelInserisciCodiceFiscale = new javax.swing.JLabel();

        messaggioErroreSelezioneFarmaco = new javax.swing.JLabel();
        checkBoxControindicazioni = new javax.swing.JCheckBox();
        labelFattoriDiRischio2 = new javax.swing.JLabel();
        jTextFieldPaziente = new JTextField();

        richiesteConsorziati = new JCheckBox();
        richiesteTotali = new ArrayList<>();

        initTabRicercaPrescrizioniNonUsate();
        initTabFarmaciPrescrittiDalMedico();
        initTabQuantitaFarmaci();
        initTabPazientiPerFarmaco();
        initTabListaFarmaciPaziente();
        initTabFarmaciGenericiComprati();
        initTabReazioniAvverse();
        initTabFarmaciContrastanti();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        impostaStringaRichiesta();

        listaRichiestePrescrizione.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaRichiestePrescrizione.setFont(new java.awt.Font("Corbel", 0, 14));

        listaRichiestePrescrizione.setModel(new javax.swing.AbstractListModel<String>() {

            @Override
            public int getSize() {
                return richiesteNonPrescritte.size();
            }

            @Override
            public String getElementAt(int i) {
                return richiesteNonPrescritte.get(i);
            }
        });

        listaRichiestePrescrizione.addListSelectionListener(event -> selezioneNellaLista(event));

        richiesteConsorziati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listenerCheckBox(evt);
            }
        });

        scrollPanelistaRichiestePrescrizione.setViewportView(listaRichiestePrescrizione);
        richiesteConsorziati.setText("<html>Visualizza richieste anche<br>dei medici consorziati<html>");
        pulsanteTabRichiestePrescrizione.setText("Effettua Prescrizione");
        pulsanteTabRichiestePrescrizione.addActionListener(event -> azioneRichiestaPrescrizione(event));

        checkBoxPresenzaFarmaciConControindicazioni.setText("<html> Alcuni farmaci potrebbero provocare<br> controindicazioni date dai fattori di rischio<br> del paziente<html>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pannelloPrescrizioniDaEffettuare);
        pannelloPrescrizioniDaEffettuare.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(scrollPanelistaRichiestePrescrizione, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(checkBoxPresenzaFarmaciConControindicazioni)
                                .addComponent(labelFattoriDiRischio)
                                .addComponent(richiesteConsorziati)
                                .addComponent(pulsanteTabRichiestePrescrizione))
                        .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createParallelGroup()
                        .addComponent(scrollPanelistaRichiestePrescrizione, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(checkBoxPresenzaFarmaciConControindicazioni)
                                .addGap(10, 10, 10)
                                .addComponent(labelFattoriDiRischio)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(richiesteConsorziati)
                                .addGap(10, 10, 10)
                                .addComponent(pulsanteTabRichiestePrescrizione))
                        .addContainerGap())
        );

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

        impostaListaFarmaci();

        listaFarmaci2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listaFarmaci2.setFont(new java.awt.Font("Corbel", 0, 14));

        listaFarmaci2.setModel(new javax.swing.AbstractListModel<String>() {
            @Override
            public int getSize() {
                return listaFarmaci.size();
            }

            @Override
            public String getElementAt(int i) {
                return listaFarmaci.get(i);
            }
        });

        scrollPaneListaFarmaci.setViewportView(listaFarmaci2);

        pulsanteConferma.setText("Conferma");
        pulsanteConferma.addActionListener(event -> azioneConfermaListaFarmaciDaPrescrivere(event));

        labelInserisciCodiceFiscale.setText("Inserisci codice fiscale del paziente:");

        jTextFieldPaziente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                pressioneCampoPaziente(e);
            }
        });

        checkBoxControindicazioni.setText("Alcuni farmaci potrebbero provocare controindicazioni date dai fattori di rischio del paziente");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(pannelloPrescrizioniConVisita2);
        pannelloPrescrizioniConVisita2.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(messaggioErroreSelezioneFarmaco, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pulsanteConferma))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addComponent(labelInserisciCodiceFiscale, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(39, 39, 39)
                                                        .addComponent(jTextFieldPaziente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(checkBoxControindicazioni)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addComponent(scrollPaneListaFarmaci, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(41, 41, 41)
                                                        .addComponent(labelFattoriDiRischio2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 61, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelInserisciCodiceFiscale)
                                .addComponent(jTextFieldPaziente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(scrollPaneListaFarmaci, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelFattoriDiRischio2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(checkBoxControindicazioni)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                        .addComponent(pulsanteConferma)
                                        .addGap(30, 30, 30))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(messaggioErroreSelezioneFarmaco)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(pannelloPrescrizioniConVisita);
        pannelloPrescrizioniConVisita.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pannelloPrescrizioniConVisita2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pannelloPrescrizioniConVisita2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Prescrizioni da effettuare", pannelloPrescrizioniDaEffettuare);
        jTabbedPane1.addTab("Prescrizioni Con Visita", pannelloPrescrizioniConVisita);
        jTabbedPane1.addTab("Prescrizioni non usate", pannelloPrescrizioniNonUsate);
        jTabbedPane1.addTab("Farmaci prescritti in generale", pannelloFarmaciPrescrittiInGenerale);
        jTabbedPane1.addTab("Quantità farmaci prescritti dal medico", pannelloQuantitaFarmaciPrescrittiDalMedico);
        jTabbedPane1.addTab("Pazienti ai quali è stato prescritto un certo farmaco", pannelloPazientiPerFarmaco);
        jTabbedPane1.addTab("Lista di farmaci prescritti ad un paziente", pannelloFarmaciPerPaziente);
        jTabbedPane1.addTab("Farmaci generici comprati", panelloFarmaciGenerici);
        jTabbedPane1.addTab("Reazioni avverse comunicate", pannelloReazioniAvverse);
        jTabbedPane1.addTab("Farmaci contrastanti", pannelloFarmaciContrastanti);

        pack();
    }

    private void initTabRicercaPrescrizioniNonUsate() {
        pannelloPrescrizioniNonUsate = new JPanel();
        pulsanteVerificaPrescrizione = new JButton();
        scrollPanePrescrizioniNonUsate = new JScrollPane();
        listaPrescrizioniNonUsate = new JList();
        jTextField5 = new JTextField();
        prescrizioniNonUsate = new ArrayList<>();
        pulsanteVerificaPrescrizione.setText("Verifica esistenza prescrizioni non usate");
        codiceFiscaleLabel = new JLabel();
        codiceFiscaleLabel.setText("Codice fiscale paziente:");
        errorePrescrizioniNonUsate = new JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(pannelloPrescrizioniNonUsate);
        pannelloPrescrizioniNonUsate.setLayout(jPanel4Layout);

        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(codiceFiscaleLabel)
                                        .addGap(20, 20, 20)
                                        .addComponent(jTextField5)
                                        .addGap(77, 77, 77)
                                        .addComponent(pulsanteVerificaPrescrizione)
                                        .addGap(212, 212, 212))
                                .addGroup(jPanel4Layout.createParallelGroup()
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(scrollPanePrescrizioniNonUsate, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(55, Short.MAX_VALUE))
                                        .addComponent(errorePrescrizioniNonUsate))))
        );
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pulsanteVerificaPrescrizione)
                                .addComponent(codiceFiscaleLabel)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(scrollPanePrescrizioniNonUsate, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(errorePrescrizioniNonUsate)
                        .addContainerGap(58, Short.MAX_VALUE))
        );
        listaPrescrizioniNonUsate.setModel(new javax.swing.AbstractListModel<String>() {

            @Override
            public int getSize() {
                return prescrizioniNonUsate.size();
            }

            @Override
            public String getElementAt(int i) {
                return prescrizioniNonUsate.get(i);
            }
        });
        listaPrescrizioniNonUsate.setSelectionModel(new DisabledItemSelectionModel());
        scrollPanePrescrizioniNonUsate.setViewportView(listaPrescrizioniNonUsate);
        pulsanteVerificaPrescrizione.addActionListener(event -> listenerButtonPrescrizioniNonUsate());

    }

    private void initTabFarmaciPrescrittiDalMedico() {
        pulsanteFarmaciNelPerido = new JButton();
        listaFarmaciPrescrittiDaMedico = new JList();
        scrollPaneFarmaciPrescrittiDaMedico = new JScrollPane();
        comboBoxPeriodoPrescrizione = new JComboBox();
        pannelloFarmaciPrescrittiInGenerale = new JPanel();
        farmaciPrescrittiDaMedico = new ArrayList<>();
        pulsanteFarmaciNelPerido.setText("Verifica farmaci prescritti nel periodo...");
        pulsanteFarmaciNelPerido.addActionListener((java.awt.event.ActionEvent evt) -> {
            azionePulsanteFarmaciNelPeriodo(evt);
        });

        listaFarmaciPrescrittiDaMedico.setModel(new javax.swing.AbstractListModel<String>() {
            @Override
            public int getSize() {
                return farmaciPrescrittiDaMedico.size();
            }

            @Override
            public String getElementAt(int i) {
                return farmaciPrescrittiDaMedico.get(i);
            }
        });
        listaFarmaciPrescrittiDaMedico.setSelectionModel(new DisabledItemSelectionModel());
        scrollPaneFarmaciPrescrittiDaMedico.setViewportView(listaFarmaciPrescrittiDaMedico);

        comboBoxPeriodoPrescrizione.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Ultimo mese", "Ultimo anno"}));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(pannelloFarmaciPrescrittiInGenerale);
        pannelloFarmaciPrescrittiInGenerale.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(86, 86, 86)
                                        .addComponent(scrollPaneFarmaciPrescrittiDaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(pulsanteFarmaciNelPerido)
                                        .addGap(38, 38, 38)
                                        .addComponent(comboBoxPeriodoPrescrizione, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pulsanteFarmaciNelPerido)
                                .addComponent(comboBoxPeriodoPrescrizione, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addComponent(scrollPaneFarmaciPrescrittiDaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(58, Short.MAX_VALUE))
        );

    }

    private void initTabQuantitaFarmaci() {
        labelTipoFarmaco = new JLabel();
        comboBoxPeriodoQuantitaFarmaci = new JComboBox();
        labelPrescrittaNelPeriodo = new JLabel();
        comboBoxPeriodiQuantitaFarmaci = new JComboBox();
        labelRispostaQuantitaFarmaci = new JLabel();
        controllaQuantitaFarmaci = new JButton();
        pannelloQuantitaFarmaciPrescrittiDalMedico = new JPanel();
        labelTipoFarmaco.setText("Quantità di farmaco del tipo:");

        comboBoxPeriodoQuantitaFarmaci.setModel(new javax.swing.DefaultComboBoxModel<String>() {
            @Override
            public int getSize() {
                return listaFarmaci.size();
            }

            @Override
            public String getElementAt(int i) {
                return listaFarmaci.get(i);
            }
        });

        labelPrescrittaNelPeriodo.setText("Prescritta nel periodo:");

        comboBoxPeriodiQuantitaFarmaci.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"In generale", "Ultimo mese", "Ultimo trimestre", "Ultimo semestre", "Ultimo anno"}));

        labelRispostaQuantitaFarmaci.setText("");

        controllaQuantitaFarmaci.setText("Controlla");
        controllaQuantitaFarmaci.addActionListener((java.awt.event.ActionEvent evt) -> {
            azioneControlloQuantitaFarmaci(evt);
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(pannelloQuantitaFarmaciPrescrittiDalMedico);
        pannelloQuantitaFarmaciPrescrittiDalMedico.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(labelTipoFarmaco)
                                                .addComponent(labelPrescrittaNelPeriodo))
                                        .addGap(93, 93, 93)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(comboBoxPeriodoQuantitaFarmaci, 0, 144, Short.MAX_VALUE)
                                                .addComponent(comboBoxPeriodiQuantitaFarmaci, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(218, 218, 218)
                                        .addComponent(labelRispostaQuantitaFarmaci))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(195, 195, 195)
                                        .addComponent(controllaQuantitaFarmaci)))
                        .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelTipoFarmaco)
                                .addComponent(comboBoxPeriodoQuantitaFarmaci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelPrescrittaNelPeriodo)
                                .addComponent(comboBoxPeriodiQuantitaFarmaci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addComponent(controllaQuantitaFarmaci)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(labelRispostaQuantitaFarmaci)
                        .addGap(43, 43, 43))
        );

    }

    private void initTabPazientiPerFarmaco() {
        labelFrmaco = new JLabel();
        comboBoxListaFarmaci = new JComboBox();
        listaPazientiPerFarmaco2 = new JList();
        pulsanteRicercaPazienti = new JButton();
        pannelloPazientiPerFarmaco = new JPanel();
        scrollPanePazientiPerFrmaco = new JScrollPane();
        listaPazientiPerFarmaco = new ArrayList<>();
        errorePazientiPerFarmaco = new JLabel();

        labelFrmaco.setText("Farmaco");
        
        comboBoxListaFarmaci.setModel(new javax.swing.DefaultComboBoxModel<String>() {
            @Override
            public int getSize() {
                return listaFarmaci.size();
            }

            @Override
            public String getElementAt(int i) {
                return listaFarmaci.get(i);
            }
        });

        listaPazientiPerFarmaco2.setModel(new javax.swing.AbstractListModel<String>() {

            @Override
            public int getSize() {
                return listaPazientiPerFarmaco.size();
            }

            @Override
            public String getElementAt(int i) {
                return listaPazientiPerFarmaco.get(i);
            }
        });
        listaPazientiPerFarmaco2.setSelectionModel(new DisabledItemSelectionModel());
        scrollPanePazientiPerFrmaco.setViewportView(listaPazientiPerFarmaco2);

        pulsanteRicercaPazienti.setText("Cerca pazienti ai quali è stato prescritto il farmaco selezionato");
        pulsanteRicercaPazienti.addActionListener((java.awt.event.ActionEvent evt) -> {
            azioneRicercaPazienti(evt);
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(pannelloPazientiPerFarmaco);
        pannelloPazientiPerFarmaco.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(scrollPanePazientiPerFrmaco, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                        .addComponent(labelFrmaco)
                                                        .addGap(65, 65, 65)
                                                        .addComponent(comboBoxListaFarmaci, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(errorePazientiPerFarmaco)))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(85, 85, 85)
                                        .addComponent(pulsanteRicercaPazienti)))
                        .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelFrmaco)
                                .addComponent(comboBoxListaFarmaci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(scrollPanePazientiPerFrmaco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(errorePazientiPerFarmaco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(pulsanteRicercaPazienti)
                        .addGap(43, 43, 43))
        );

    }

    private void initTabListaFarmaciPaziente() {
        labelCodiceSanitarioPaziente = new JLabel();
        labelCodiceSanitarioPaziente.setText("Codice sanitario paziente");
        labelPeriodo = new JLabel();
        comboBoxPeriodoFarmaciPaziente = new JComboBox();
        listaFarmaciPaziente2 = new JList();
        scrollPaneFarmaciPaziente = new JScrollPane();
        cercaFarmaciPaziente = new JButton();
        pannelloFarmaciPerPaziente = new JPanel();
        codiceSanitarioTabFarmaciPaziente = new JTextField();
        listaFarmaciPaziente = new ArrayList<>();
        erroreTabListaFarmaciPaziente = new JLabel();

        labelPeriodo.setText("Periodo");

        comboBoxPeriodoFarmaciPaziente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"In generale", "Ultimo mese", "Ultimo trimestre", "Ultimo semestre", "Ultimo anno"}));

        listaFarmaciPaziente2.setModel(new javax.swing.AbstractListModel<String>() {

            @Override
            public int getSize() {
                return listaFarmaciPaziente.size();
            }

            @Override
            public String getElementAt(int i) {
                return listaFarmaciPaziente.get(i);
            }
        });
        listaFarmaciPaziente2.setSelectionModel(new DisabledItemSelectionModel());
        scrollPaneFarmaciPaziente.setViewportView(listaFarmaciPaziente2);

        cercaFarmaciPaziente.setText("Cerca");
        cercaFarmaciPaziente.addActionListener((java.awt.event.ActionEvent evt) -> {
            azioneRicercaFarmaciPaziente(evt);
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(pannelloFarmaciPerPaziente);
        pannelloFarmaciPerPaziente.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(59, Short.MAX_VALUE)
                        .addComponent(scrollPaneFarmaciPaziente, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 300, 500))
                .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(labelCodiceSanitarioPaziente)
                                                .addComponent(labelPeriodo))
                                        .addGap(34, 34, 34)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(codiceSanitarioTabFarmaciPaziente)
                                                .addComponent(comboBoxPeriodoFarmaciPaziente, 0, 146, Short.MAX_VALUE)))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(178, 178, 178)
                                        .addComponent(cercaFarmaciPaziente))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(erroreTabListaFarmaciPaziente)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelCodiceSanitarioPaziente)
                                .addComponent(codiceSanitarioTabFarmaciPaziente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelPeriodo)
                                .addComponent(comboBoxPeriodoFarmaciPaziente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(scrollPaneFarmaciPaziente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(erroreTabListaFarmaciPaziente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(cercaFarmaciPaziente)
                        .addGap(30, 30, 30))
        );

    }

    private void initTabFarmaciGenericiComprati() {
        listaFarmaciGenerici = new JList();
        scrollPaneFarmaciGenerici = new JScrollPane();
        controlloFarmaciGenerici = new JButton();
        panelloFarmaciGenerici = new JPanel();
        codiceFiscaleFarmaciGenericiComprati = new JTextField();
        listFarmaciGenericiAcquistati = new ArrayList<>();
        codiceFiscaleTabFarmaciGenericiComprati = new JLabel();
        erroreTabFarmaciGenerici = new JLabel();

        listaFarmaciGenerici.setModel(new javax.swing.AbstractListModel<String>() {
            @Override
            public int getSize() {
                return listFarmaciGenericiAcquistati.size();
            }

            @Override
            public String getElementAt(int i) {
                return listFarmaciGenericiAcquistati.get(i);
            }
        });
        listaFarmaciGenerici.setSelectionModel(new DisabledItemSelectionModel());
        scrollPaneFarmaciGenerici.setViewportView(listaFarmaciGenerici);

        controlloFarmaciGenerici.setText("Controlla");
        controlloFarmaciGenerici.addActionListener((java.awt.event.ActionEvent evt) -> {
            azioneControlloFarmaciGenerici(evt);
        });

        codiceFiscaleTabFarmaciGenericiComprati.setText("Codice fiscale paziente:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(panelloFarmaciGenerici);
        panelloFarmaciGenerici.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(codiceFiscaleTabFarmaciGenericiComprati)
                                        .addGap(20, 20, 20)
                                        .addComponent(codiceFiscaleFarmaciGenericiComprati)
                                        .addGap(77, 77, 77)
                                        .addComponent(controlloFarmaciGenerici)
                                        .addGap(212, 212, 212))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup()
                                                .addComponent(scrollPaneFarmaciGenerici, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(erroreTabFarmaciGenerici))
                                        .addContainerGap(55, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(codiceFiscaleTabFarmaciGenericiComprati)
                                .addComponent(controlloFarmaciGenerici)
                                .addComponent(codiceFiscaleFarmaciGenericiComprati, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(scrollPaneFarmaciGenerici, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(erroreTabFarmaciGenerici)
                        .addContainerGap(58, Short.MAX_VALUE))
        );

    }

    private void initTabReazioniAvverse() {
        pulsanteControlloReazioniAvverse = new JButton();
        listaReazioni2 = new JList();
        scrollPaneListaReazioni = new JScrollPane();
        pannelloReazioniAvverse = new JPanel();
        listaReazioni = new ArrayList<>();
        pulsanteControlloReazioniAvverse.setText("Controlla reazioni avverse comunicate");
        pulsanteControlloReazioniAvverse.addActionListener((java.awt.event.ActionEvent evt) -> {
            azioneControlloReazioniAvverse(evt);
        });

        listaReazioni2.setModel(new javax.swing.AbstractListModel<String>() {
            @Override
            public int getSize() {
                return listaReazioni.size();
            }

            @Override
            public String getElementAt(int i) {
                return listaReazioni.get(i);
            }
        });
        listaReazioni2.setSelectionModel(new DisabledItemSelectionModel());
        scrollPaneListaReazioni.setViewportView(listaReazioni2);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(pannelloReazioniAvverse);
        pannelloReazioniAvverse.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(138, 138, 138)
                                        .addComponent(pulsanteControlloReazioniAvverse))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(74, 74, 74)
                                        .addComponent(scrollPaneListaReazioni, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(pulsanteControlloReazioniAvverse)
                        .addGap(44, 44, 44)
                        .addComponent(scrollPaneListaReazioni, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))
        );

    }

    private void initTabFarmaciContrastanti() {
        farmaciContrastanti = new ArrayList<>();
        pulsanteCercaFarmaciContrastanti = new JButton();
        listaFarmacicontrastanti = new JList();
        scrollPaneFarmaciContrastanti = new JScrollPane();
        pannelloFarmaciContrastanti = new JPanel();
        pulsanteCercaFarmaciContrastanti.addActionListener((java.awt.event.ActionEvent evt) -> {
            aggiornamentoFarmaciContrastanti(evt);
        });
        pulsanteCercaFarmaciContrastanti.setText("Cerca");

        listaFarmacicontrastanti.setModel(new javax.swing.AbstractListModel<String>() {
            @Override
            public int getSize() {
                return farmaciContrastanti.size();
            }

            @Override
            public String getElementAt(int i) {
                return farmaciContrastanti.get(i);
            }
        });
        listaFarmacicontrastanti.setSelectionModel(new DisabledItemSelectionModel());
        scrollPaneFarmaciContrastanti.setViewportView(listaFarmacicontrastanti);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(pannelloFarmaciContrastanti);
        pannelloFarmaciContrastanti.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap(90, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addComponent(pulsanteCercaFarmaciContrastanti)
                                        .addGap(100, 400, 500))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addComponent(scrollPaneFarmaciContrastanti, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(79, 350, 500))))
        );
        jPanel10Layout.setVerticalGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(pulsanteCercaFarmaciContrastanti)
                        .addGap(37, 37, 37)
                        .addComponent(scrollPaneFarmaciContrastanti, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(49, Short.MAX_VALUE))
        );

    }

    private void listenerCheckBox(ActionEvent evt) {
        richiesteNonPrescritte = new ArrayList<>();
        updateQueryListaRichieste();
        impostaStringaRichiesta();
        listaRichiestePrescrizione.clearSelection();
        listaRichiestePrescrizione.updateUI();
    }

    private boolean pazienteOk(String testo) {
        return getMedicoController().getMedico().listaPazienti().stream().anyMatch((paz) -> (testo.equals(paz)));
    }

    private void listenerButtonPrescrizioniNonUsate() {
        String testo = jTextField5.getText();

        if (testo.equals("")) {
            prescrizioniNonUsate = medicoController.listaPrescrizioniNonUsateConData();
            errorePrescrizioniNonUsate.setText("");
        } else if (!testo.equals("") && pazienteOk(testo)) {
            prescrizioniNonUsate = medicoController.listaPrescrizioniNonUsateConData(testo);
            errorePrescrizioniNonUsate.setText("");
        } else {
            errorePrescrizioniNonUsate.setText("Paziente non presente,codice fiscale errato");
            prescrizioniNonUsate = new ArrayList<>();
        }
        listaPrescrizioniNonUsate.updateUI();

    }

    private void azionePulsanteFarmaciNelPeriodo(ActionEvent evt) {
        int indice = comboBoxPeriodoPrescrizione.getSelectedIndex();
        String periodo = "";
        switch (indice) {
            case 0:
                periodo = "Ultimo mese";
                break;
            case 1:
                periodo = "Ultimo anno";
                break;
            default:
                System.err.println("Messaggio di errore, selezione sbagliata");
                break;
        }

        farmaciPrescrittiDaMedico = medicoController.farmaciPrescrittiDaMedicoNelPeriodo(periodo);
        listaFarmaciPrescrittiDaMedico.updateUI();

    }

    private void azioneControlloQuantitaFarmaci(ActionEvent evt) {
        if (comboBoxPeriodoQuantitaFarmaci.getSelectedIndex() != -1) {
            labelRispostaQuantitaFarmaci.setText("Numero occorrenze: " + medicoController.quantitaFarmacoPrescrittoDaUnMedicoNelPeriodo(listaFarmaci.get(comboBoxPeriodoQuantitaFarmaci.getSelectedIndex()), comboBoxPeriodiQuantitaFarmaci.getSelectedIndex()));
        } else {
            labelRispostaQuantitaFarmaci.setText("Selezionare almeno un farmaco");
        }

    }

    private void azioneRicercaPazienti(ActionEvent evt) {
        if (comboBoxListaFarmaci.getSelectedIndex() != -1) {
            errorePazientiPerFarmaco.setText("");
            listaPazientiPerFarmaco = medicoController.pazientiPerFarmaco(listaFarmaci.get(comboBoxListaFarmaci.getSelectedIndex()));
            listaPazientiPerFarmaco2.updateUI();
        } else {
            errorePazientiPerFarmaco.setText("Selezionare un farmaco");
        }
    }

    private void azioneRicercaFarmaciPaziente(ActionEvent evt) {
        String codice = codiceSanitarioTabFarmaciPaziente.getText();

        if (!codice.isEmpty() && comboBoxPeriodoFarmaciPaziente.getSelectedIndex() >= 0) {
            if (pazienteOk(codice)) {
                erroreTabListaFarmaciPaziente.setText("");
                listaFarmaciPaziente = medicoController.farmacoPerPaziete(codice, comboBoxPeriodoFarmaciPaziente.getSelectedIndex());
                listaFarmaciPaziente2.updateUI();
            } else {
                erroreTabListaFarmaciPaziente.setText("Il codice fiscale del paziente è errato, il paziente non esiste");
                listaFarmaciPaziente = new ArrayList<>();
                listaFarmaciPaziente2.updateUI();
            }
        } else {
            erroreTabListaFarmaciPaziente.setText("Il codice fiscale non deve essere nullo e il periodo deve essere selezionato");
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void updateQueryListaRichieste() {
        ArrayList<Richiesta> r = new ArrayList<>();
        if (!richiesteConsorziati.isSelected()) {
            medicoController.listaRichieste().stream().forEach((s) -> {
                r.add(medicoController.richiestaConAnagraficaEFarmaco(s));
            });
            richieste = r;
        } else {
            for (String s : medicoController.listaRichiesteConsorzio()) {
                r.add(medicoController.richiestaConAnagraficaEFarmaco(Integer.parseInt(s)));
            }
            richiesteTotali = r;
        }
    }

    private void azioneRichiestaPrescrizione(ActionEvent evt) {
        creaFinestra2();
        updateQueryListaRichieste();
    }

    public void aggiornaLista1() {
        updateQueryListaRichieste();
        impostaStringaRichiesta();
        listaRichiestePrescrizione.clearSelection();
        listaRichiestePrescrizione.updateUI();
    }

    private void azioneConfermaListaFarmaciDaPrescrivere(ActionEvent evt) {

        try {
            if (!(messaggioErroreSelezioneFarmaco.getText().equals(""))) {
                messaggioErroreSelezioneFarmaco.setText("");
            }

            if (pazienteOk(jTextFieldPaziente.getText())) {
                if (medicoController.listaFarmaciSelezionati(listaFarmaci2.getSelectedIndices(), listaFarmaci).size() >= 5 && jTextFieldPaziente.getText().equals("")) {
                    messaggioErroreSelezioneFarmaco.setText("<html>" + "•Una prescrizione non può contenere più di 5 farmaci<br>" + "•Inserire paziente <html>");
                } else if (medicoController.listaFarmaciSelezionati(listaFarmaci2.getSelectedIndices(), listaFarmaci).isEmpty() && jTextFieldPaziente.getText().equals("")) {
                    messaggioErroreSelezioneFarmaco.setText("<html>" + "•Selezionare almeno un farmaco" + "<br>•Inserire paziente <html>");
                } else if (jTextFieldPaziente.getText().equals("")) {
                    messaggioErroreSelezioneFarmaco.setText("<html>" + messaggioErroreSelezioneFarmaco.getText() + "<br>" + "•Inserire paziente <html>");
                } else if (medicoController.listaFarmaciSelezionati(listaFarmaci2.getSelectedIndices(), listaFarmaci).size() >= 5) {
                    messaggioErroreSelezioneFarmaco.setText("Una prescrizione non può contenere più di 5 farmaci");
                } else if (medicoController.listaFarmaciSelezionati(listaFarmaci2.getSelectedIndices(), listaFarmaci).isEmpty()) {
                    messaggioErroreSelezioneFarmaco.setText("<html>" + messaggioErroreSelezioneFarmaco.getText() + "<br>Selezionare almeno un farmaco<html>");
                } else if (medicoController.listaFarmaciSelezionati(listaFarmaci2.getSelectedIndices(), listaFarmaci).size() <= 5 && !(jTextFieldPaziente.getText().equals(""))) {
                    creaFinestra();
                }
            } else {
                messaggioErroreSelezioneFarmaco.setText("Il paziente non esiste oppure ha un altro medico di base");
            }
        } catch (Exception e) {
            messaggioErroreSelezioneFarmaco.setText("<html>" + messaggioErroreSelezioneFarmaco.getText() + "<br>eccez<html>");
        }

    }

    private void azioneControlloFarmaciGenerici(ActionEvent evt) {
        String testo = codiceFiscaleFarmaciGenericiComprati.getText();

        if (testo.equals("")) {
            listFarmaciGenericiAcquistati = medicoController.getFarmaciGenericiAcquistati();
            erroreTabFarmaciGenerici.setText("");
        } else if (!testo.equals("") && pazienteOk(testo)) {
            listFarmaciGenericiAcquistati = medicoController.getFarmaciGenericiAcquistati(testo);
            erroreTabFarmaciGenerici.setText("");
        } else {
            erroreTabFarmaciGenerici.setText("Il codice fiscale è errato");
            listFarmaciGenericiAcquistati = new ArrayList<>();
        }
        listaFarmaciGenerici.updateUI();

    }

    private void azioneControlloReazioniAvverse(ActionEvent evt) {
        listaReazioni = medicoController.getListaReazioniAvverseSegnalate();
        listaReazioni2.updateUI();
    }

    private void pressioneCampoPaziente(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (pazienteOk(jTextFieldPaziente.getText())) {
                messaggioErroreSelezioneFarmaco.setText("");
                if ((medicoController.getFattoriDiRischio(jTextFieldPaziente.getText())).isEmpty()) {
                    labelFattoriDiRischio2.setText("Il paziente non ha fattori di rischio");
                } else {
                    labelFattoriDiRischio2.setText(settaFattori(jTextFieldPaziente.getText()));
                }
            } else {
                messaggioErroreSelezioneFarmaco.setText("<html>Il paziente non esiste oppure ha un altro medico di base</html>");
                labelFattoriDiRischio2.setText("");
            }
        }
    }

    public String settaFattori(String paziente) {
        String s = "<html>Fattori di rischio del paziente:<br>";
        s = medicoController.getFattoriDiRischio(paziente).stream().map((fattore) -> "•" + fattore + "<br><html>").reduce(s, String::concat);
        return s;
    }

    private void selezioneNellaLista(ListSelectionEvent e) {
        labelFattoriDiRischio.setText("");
        if (!listaRichiestePrescrizione.isSelectionEmpty()) {
            String richiesta = MedicoController.oggettoSelezionatoConHtml(listaRichiestePrescrizione.getSelectedIndex(), richiesteNonPrescritte);

            int richiestaInt = Integer.parseInt(richiesta);

            String paziente = medicoController.richiestaConAnagraficaEFarmaco(richiestaInt).getPaziente();

            if ((medicoController.getFattoriDiRischio(paziente)).isEmpty()) {
                labelFattoriDiRischio.setText("Il paziente non ha fattori di rischio");
            } else {
                labelFattoriDiRischio.setText(settaFattori(paziente));
            }
        }
    }

    private void aggiornamentoFarmaciContrastanti(ActionEvent evt) {
        farmaciContrastanti = medicoController.getFarmaciContrastanti();
        listaFarmacicontrastanti.updateUI();
    }

    public void deselezionaLista2() {
        listaFarmaci2.clearSelection();
    }

    public JCheckBox getCheckBox1() {
        return checkBoxControindicazioni;
    }

    public JCheckBox getCheckBox2() {
        return checkBoxPresenzaFarmaciConControindicazioni;
    }

    class DisabledItemSelectionModel extends DefaultListSelectionModel {

        @Override
        public void setSelectionInterval(int index0, int index1) {
            super.setSelectionInterval(-1, -1);
        }
    }

}
