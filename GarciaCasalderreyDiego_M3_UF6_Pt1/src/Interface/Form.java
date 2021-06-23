/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Classes.Categoria;
import Controller.Controlador;
import java.awt.Color;
import static java.lang.Double.parseDouble;

/**
 *
 * @author diegoplays
 */
public final class Form extends javax.swing.JDialog {

    static Controlador cnt;
    int codi;

    /**
     * Constructor de Form. Inicialitza els components i s'adapta al codi de
     * Vaixell introduït.
     *
     * @param cnt Controlador.
     * @param parent JFrame pare.
     * @param modal si apareix a sobre de tot o no.
     * @param codi codi del Vaixell. -1 si es vol afegir i no modificar.
     */
    public Form(Controlador cnt, java.awt.Frame parent, boolean modal, int codi) {
        super(parent, modal);
        setLocationRelativeTo(null);
        Form.cnt = cnt;
        initComponents();
        MostrarCategories();
        this.codi = codi;
        PreparaFormDelMode();
    }

    /**
     * Omple el ComboBox amb totes les Categories de la BD.
     */
    private void MostrarCategories() {
        ComboBoxCategoria.removeAllItems();
        cnt.getCategories("nom").forEach((c) -> {
            ComboBoxCategoria.addItem(c.getNom());
        });
    }

    /**
     * Prepara l'estructura del formulari depenent del codi del Vaixell; si
     * aquest és -1, entra en mode d'afegir, si és un altre, introdueix les
     * dades del vaixell a modificar dins dels seus TextFields respectius.
     */
    private void PreparaFormDelMode() {
        if (codi == -1) {
            SubmitButton.setText("Afegir");
        } else {
            // Aquest codi es podria simplificar molt important el vaixell.
            // Però així no interactuem amb el vaixell directament sinó que
            // tot passa pel controlador.
            TextNom.setText(cnt.getVaixell(codi).getNom());
            TextNom.setBackground(Color.lightGray);
            TextRating.setText(Double.toString(cnt.getVaixell(codi).getRating()));
            TextRating.setBackground(Color.lightGray);
            TextClub.setText(cnt.getVaixell(codi).getClub());
            TextClub.setBackground(Color.lightGray);
            TextTempsReal.setText(Double.toString(cnt.getVaixell(codi).getTempsReal()));
            TextTempsReal.setBackground(Color.lightGray);
            ComboBoxCategoria.setSelectedIndex(cnt.getPosicioByIdCategoria(
                    cnt.getVaixell(codi).getId_categoria(), ComboBoxCategoria.getItemCount()));
            CheckBoxSenior.setSelected(cnt.getVaixell(codi).isSenior());
            switch (cnt.getVaixell(codi).getTipusVaixell()) {
                case 0:
                    BotoRegata.setSelected(true);
                    break;
                case 1:
                    BotoCreuer.setSelected(true);
                    break;
                case 2:
                    BotoCR.setSelected(true);
                    break;
            }
            SubmitButton.setEnabled(true);
            SubmitButton.setText("Actualitzar");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The cntent of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        LabelNom = new javax.swing.JLabel();
        LabelCategoria = new javax.swing.JLabel();
        LabelRating = new javax.swing.JLabel();
        LabelClub = new javax.swing.JLabel();
        LabelTipusVaixell = new javax.swing.JLabel();
        LabelSenior = new javax.swing.JLabel();
        LabelTemps = new javax.swing.JLabel();
        SubmitButton = new javax.swing.JButton();
        TextNom = new javax.swing.JTextField();
        ComboBoxCategoria = new javax.swing.JComboBox<>();
        TextRating = new javax.swing.JTextField();
        TextClub = new javax.swing.JTextField();
        BotoRegata = new javax.swing.JRadioButton();
        BotoCreuer = new javax.swing.JRadioButton();
        BotoCR = new javax.swing.JRadioButton();
        CheckBoxSenior = new javax.swing.JCheckBox();
        Titol = new javax.swing.JLabel();
        LabelErrorClub = new javax.swing.JLabel();
        LabelErrorRating = new javax.swing.JLabel();
        TextTempsReal = new javax.swing.JTextField();
        LabelErrorTempsReal = new javax.swing.JLabel();
        ButtonAfegirCategoria = new javax.swing.JButton();
        LabelErrorNom = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        jLabel1.setText("jLabel1");

        jTextField3.setText("jTextField1");

        setTitle("Formulari de vaixell");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        LabelNom.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelNom.setText("Nom:");

        LabelCategoria.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelCategoria.setText("Categoria:");

        LabelRating.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelRating.setText("Rating:");

        LabelClub.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelClub.setText("Club:");

        LabelTipusVaixell.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelTipusVaixell.setText("Tipus de vaixell:");

        LabelSenior.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelSenior.setText("Senior?");

        LabelTemps.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LabelTemps.setText("Temps real:");

        SubmitButton.setText("Afegir");
        SubmitButton.setEnabled(false);
        SubmitButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SubmitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonPressed(evt);
            }
        });

        TextNom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                focusGainedNom(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                focusLostNom(evt);
            }
        });

        ComboBoxCategoria.setMaximumRowCount(20);

        TextRating.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                focusGainedRating(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                FocusLostRating(evt);
            }
        });
        TextRating.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                keyTypedRating(evt);
            }
        });

        TextClub.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                focusGainedClub(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                FocusLostClub(evt);
            }
        });
        TextClub.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                keyTypedClub(evt);
            }
        });

        buttonGroup1.add(BotoRegata);
        BotoRegata.setText("Regata");
        BotoRegata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotoRegataActionPerformed(evt);
            }
        });

        buttonGroup1.add(BotoCreuer);
        BotoCreuer.setText("Creuer");
        BotoCreuer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotoCreuerActionPerformed(evt);
            }
        });

        buttonGroup1.add(BotoCR);
        BotoCR.setText("C-R");
        BotoCR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotoCRActionPerformed(evt);
            }
        });

        Titol.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        Titol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titol.setText("FORMULARI DE VAIXELL");

        LabelErrorClub.setForeground(new java.awt.Color(255, 51, 51));

        LabelErrorRating.setForeground(new java.awt.Color(255, 51, 51));

        TextTempsReal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextTempsRealFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TextTempsRealFocusLost(evt);
            }
        });

        LabelErrorTempsReal.setForeground(new java.awt.Color(255, 51, 51));

        ButtonAfegirCategoria.setText("Afegir...");
        ButtonAfegirCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAfegirCategoriaActionPerformed(evt);
            }
        });

        LabelErrorNom.setForeground(new java.awt.Color(255, 51, 51));
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelTemps)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextTempsReal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelNom, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(LabelCategoria, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(LabelRating, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(LabelClub, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(LabelTipusVaixell, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(TextNom, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ComboBoxCategoria, javax.swing.GroupLayout.Alignment.LEADING, 0, 175, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ButtonAfegirCategoria))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(BotoRegata, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BotoCreuer, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BotoCR, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(TextClub, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(LabelErrorClub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(TextRating, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(LabelErrorRating, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGap(9, 9, 9))))
                            .addComponent(Titol, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LabelSenior)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CheckBoxSenior)
                                .addGap(63, 63, 63)
                                .addComponent(LabelErrorTempsReal, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(LabelErrorNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(SubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {LabelCategoria, LabelClub, LabelNom, LabelRating, LabelSenior, LabelTemps, LabelTipusVaixell});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BotoCR, BotoRegata});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {TextClub, TextRating});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(Titol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(LabelErrorNom, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNom)
                    .addComponent(TextNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelCategoria)
                    .addComponent(ComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonAfegirCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelRating)
                        .addComponent(TextRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LabelErrorRating, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelErrorClub, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelClub)
                        .addComponent(TextClub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BotoRegata, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(BotoCreuer, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(BotoCR, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LabelTipusVaixell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelTemps)
                    .addComponent(TextTempsReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelErrorTempsReal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelSenior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CheckBoxSenior, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(SubmitButton)
                .addGap(27, 27, 27))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {LabelCategoria, LabelClub, LabelNom, LabelRating, LabelSenior, LabelTemps, LabelTipusVaixell});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {BotoCR, BotoCreuer, BotoRegata});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {LabelErrorRating, TextRating});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {LabelErrorClub, TextClub});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotoRegataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotoRegataActionPerformed
        ActualitzarBoto();
    }//GEN-LAST:event_BotoRegataActionPerformed

    private void BotoCreuerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotoCreuerActionPerformed
        ActualitzarBoto();
    }//GEN-LAST:event_BotoCreuerActionPerformed

    private void BotoCRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotoCRActionPerformed
        ActualitzarBoto();
    }//GEN-LAST:event_BotoCRActionPerformed

    private void FocusLostRating(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FocusLostRating
        if (cnt.verificaRating(TextRating.getText())) {
            TextRating.setBackground(Color.lightGray);
            LabelErrorRating.setText("");
        } else {
            TextRating.setBackground(Color.white);
            LabelErrorRating.setForeground(Color.red);
            LabelErrorRating.setText("[!] Nombre real del 0 al 100");
        }
        ActualitzarBoto();
    }//GEN-LAST:event_FocusLostRating

    private void keyTypedClub(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyTypedClub
        if (TextClub.getText().length() == 50) {
            evt.setKeyChar((char) 0);
        } else if (TextClub.getText().length() > 50) {
            TextClub.setText(TextClub.getText().substring(0, 50));
        }
    }//GEN-LAST:event_keyTypedClub

    private void FocusLostClub(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FocusLostClub
        if (cnt.verificaClub(TextClub.getText())) {
            TextClub.setBackground(Color.lightGray);
            LabelErrorClub.setText("");
        } else {
            TextClub.setBackground(Color.white);
            LabelErrorClub.setForeground(Color.red);
            LabelErrorClub.setText("[!] Mínim 3 alfanumèrics");
        }
        ActualitzarBoto();
    }//GEN-LAST:event_FocusLostClub

    private void focusLostNom(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_focusLostNom
        int lenText = TextNom.getText().length();
        if (lenText >= 3 && lenText <= 50) {
            TextNom.setBackground(Color.lightGray);
            LabelErrorNom.setText("");
        } else {
            TextNom.setBackground(Color.white);
            LabelErrorNom.setText("[!] El nom ha de contenir entre 3 i 30 caràcters");
        }
        ActualitzarBoto();

    }//GEN-LAST:event_focusLostNom

    private void focusGainedNom(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_focusGainedNom
        TextNom.setBackground(Color.white);
    }//GEN-LAST:event_focusGainedNom

    private void keyTypedRating(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyTypedRating
        if (evt.getKeyChar() == 44 || evt.getKeyChar() == 39) {
            evt.setKeyChar('.');
        }
    }//GEN-LAST:event_keyTypedRating

    private void focusGainedRating(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_focusGainedRating
        TextRating.setBackground(Color.white);
    }//GEN-LAST:event_focusGainedRating

    private void focusGainedClub(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_focusGainedClub
        TextClub.setBackground(Color.white);
    }//GEN-LAST:event_focusGainedClub

    private void buttonPressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPressed
        ActualitzarBoto();
        if (SubmitButton.isEnabled()) {
            int idCat = getCategoriaSelected().getId();
            String nom = TextNom.getText();
            double rating = parseDouble(TextRating.getText());
            String ubicacio = TextClub.getText();
            byte tipusVaixell = (BotoRegata.isSelected() ? (byte) 0 : (BotoCreuer.isSelected() ? (byte) 1 : (byte) 2));
            boolean senior = CheckBoxSenior.isSelected();
            double tempsReal = Double.parseDouble(TextTempsReal.getText());
            if (codi == -1) {
                if (cnt.afegirVaixell(idCat, nom, rating, ubicacio, tipusVaixell, senior, tempsReal)) {
                    resetejarFormulari();
                    mostraConfirmacio();
                } else {
                    System.out.println("Alguna cosa ha fallat en afegir el vaixell");
                    System.out.println(idCat + " " + nom + " " + rating + " "
                            + ubicacio + " " + tipusVaixell + " " + senior + " "
                            + tempsReal);
                }
            } else {
                cnt.actualitzarVaixell(codi, idCat, nom, rating, ubicacio, tipusVaixell, senior, tempsReal);
                dispose();
            }
        }
    }//GEN-LAST:event_buttonPressed

    private void TextTempsRealFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextTempsRealFocusGained
        TextTempsReal.setBackground(Color.white);

    }//GEN-LAST:event_TextTempsRealFocusGained

    private void TextTempsRealFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextTempsRealFocusLost
        double temps;
        try {
            temps = Double.parseDouble(TextTempsReal.getText());
            if (temps >= 0 && temps <= 10000) {
                TextTempsReal.setBackground(Color.lightGray);
                LabelErrorTempsReal.setText("");

            }
        } catch (NumberFormatException e) {
            TextTempsReal.setBackground(Color.white);
            LabelErrorTempsReal.setForeground(Color.red);
            LabelErrorTempsReal.setText("[!] temps entre 0 i 10000 min");
        }
        ActualitzarBoto();
    }//GEN-LAST:event_TextTempsRealFocusLost

    private void ButtonAfegirCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAfegirCategoriaActionPerformed
        cnt.mostraCrearCategoria();
        MostrarCategories();

    }//GEN-LAST:event_ButtonAfegirCategoriaActionPerformed
    
    /**
     * Comprova que totes les opcions del formulari siguin correctes;
     * si és així, activa el botó de finalitzar, si no, el desactiva.
     */
    private void ActualitzarBoto() {
        if (TextNom.getBackground() == Color.lightGray
                && TextRating.getBackground() == Color.lightGray
                && TextClub.getBackground() == Color.lightGray
                && (BotoRegata.isSelected() || BotoCreuer.isSelected() || BotoCR.isSelected())
                && TextTempsReal.getBackground() == Color.lightGray) {
            SubmitButton.setEnabled(true);
        } else {
            SubmitButton.setEnabled(false);
        }
    }

    /**
     * Reseteja el formulari, a excepció de la categoria seleccionada,
     * el club i el tipus de vaixell.
     */
    private void resetejarFormulari() {
        SubmitButton.setEnabled(false);
        TextNom.setText("");
        TextRating.setText("");
        TextTempsReal.setText("");
        CheckBoxSenior.setSelected(false);
        TextNom.setBackground(Color.white);
        TextRating.setBackground(Color.white);
        TextTempsReal.setBackground(Color.white);
        LabelErrorNom.setVisible(false);
    }

    /**
     * Mostra un missatge de confirmació indicant que s'ha afegit el Vaixell
     * correctament.
     */
    private void mostraConfirmacio() {
        cnt.VaixellAfegit();
    }

    /**
     * Agafa la Categoria ordenada al ComboBox.
     */
    private Categoria getCategoriaSelected() {
        return cnt.getCatByOrderIndex(ComboBoxCategoria.getSelectedIndex());
    }

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//
//                Form dialog = new Form(cnt, new javax.swing.JFrame(), true, -1);
//
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//
//                    @Override
//
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//
//                        System.exit(0);
//
//                    }
//
//                });
//
//                dialog.setVisible(true);
//
//            }
//
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BotoCR;
    private javax.swing.JRadioButton BotoCreuer;
    private javax.swing.JRadioButton BotoRegata;
    private javax.swing.JButton ButtonAfegirCategoria;
    private javax.swing.JCheckBox CheckBoxSenior;
    private javax.swing.JComboBox<String> ComboBoxCategoria;
    private javax.swing.JLabel LabelCategoria;
    private javax.swing.JLabel LabelClub;
    private javax.swing.JLabel LabelErrorClub;
    private javax.swing.JLabel LabelErrorNom;
    private javax.swing.JLabel LabelErrorRating;
    private javax.swing.JLabel LabelErrorTempsReal;
    private javax.swing.JLabel LabelNom;
    private javax.swing.JLabel LabelRating;
    private javax.swing.JLabel LabelSenior;
    private javax.swing.JLabel LabelTemps;
    private javax.swing.JLabel LabelTipusVaixell;
    private javax.swing.JButton SubmitButton;
    private javax.swing.JTextField TextClub;
    private javax.swing.JTextField TextNom;
    private javax.swing.JTextField TextRating;
    private javax.swing.JTextField TextTempsReal;
    private javax.swing.JLabel Titol;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

}