/*                                            
 * To change this license header, choose License Headers in Project Properties.                                           
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.clients;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author divers
 */
public class Notification extends javax.swing.JDialog {

    private Boolean ifDialogResize = false;

    /**
     * Creates new form Alerte
     *
     * @param modal
     * @param description
     * @param details
     * @param typenotification
     */
    public Notification(Frame parent, boolean modal, NotificationType typenotification, String description, String details) {
        initComponents();
        this.setTitle(getWindowtitle(typenotification));
        if (typenotification.equals(NotificationType.ERROR)) {
            this.description.setForeground(Color.red);
        }
        if (typenotification.equals(NotificationType.WARNING)) {
            this.description.setForeground(new Color(245, 125, 0));
        }
        if (typenotification.equals(NotificationType.SUCCES)) {
            this.description.setForeground(new Color(103, 193, 0));
        }
        this.description.setText(description);
        this.details.setText(details);
        this.setIconImage(getImageIcon(typenotification));
        this.resizeDialog();
        buildviews();
    }

    public static void getNotificationDialog(Frame parent, boolean modal, NotificationType typenotification, String description, String details) {
        Notification alerte = new Notification(parent, true, typenotification, description, details);
        alerte.setModal(modal);
        alerte.setLocationRelativeTo(parent);
        alerte.setVisible(true);
    }

    private String getWindowtitle(NotificationType type) {
        if (type.equals(NotificationType.SUCCES)) {
            this.setTitle(type.toString());
        } else {

        }
        return type.toString();
    }

    /**
     * Permet de redimensionner la fenetre lors du clic dur le bouton details
     */
    private void resizeDialog() {
        if (!this.ifDialogResize) {
            this.setSize(500, 175);
        } else {
            this.setSize(500, 275);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        imprimerBtn = new javax.swing.JButton();
        detailsBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        details = new javax.swing.JTextArea();
        description = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        imprimerBtn.setText("Quitter");
        imprimerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimerBtnActionPerformed(evt);
            }
        });

        detailsBtn.setText("Details");
        detailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(401, Short.MAX_VALUE)
                .addComponent(detailsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imprimerBtn)
                .addGap(8, 8, 8))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imprimerBtn)
                    .addComponent(detailsBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        details.setEditable(false);
        details.setColumns(20);
        details.setRows(5);
        jScrollPane2.setViewportView(details);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        description.setFont(new java.awt.Font(CommonsUtilities.POLICE_APPLICATION, 1, 12)); // NOI18N
        description.setForeground(new java.awt.Color(255, 0, 0));
        description.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        description.setText("Echec Operation");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(description, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void detailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsBtnActionPerformed
        // TODO add your handling code here:
        if (this.ifDialogResize) {
            this.ifDialogResize = false;
        } else {
            this.ifDialogResize = true;
        }

        this.resizeDialog();
    }//GEN-LAST:event_detailsBtnActionPerformed

    private void imprimerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimerBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_imprimerBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel description;
    private javax.swing.JTextArea details;
    private javax.swing.JButton detailsBtn;
    private javax.swing.JButton imprimerBtn;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    @SuppressWarnings("empty-statement")
    private Image getImageIcon(NotificationType typenotification) {
        try {
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(typenotification.toString().toLowerCase() + ".png"));

            return icon.getImage();
        } catch (Exception ex) {;
        }
        return null;
    }

    private void buildviews() {
        description.setIcon(new ImageIcon(ClassLoader.getSystemResource("com/megatim/tools/images/echec.png")));
        this.setIconImage((new ImageIcon(ClassLoader.getSystemResource("com/megatim/tools/images/echec.png"))).getImage());
    }
}