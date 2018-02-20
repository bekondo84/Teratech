package com.megatim.security.clients;

import java.awt.Dimension;
import java.awt.Frame;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.megatim.common.clients.AbstractEditTemplateDialog;
import com.megatim.common.clients.CommonsUtilities;
import com.megatim.common.utilities.MessagesBundle;
import com.megatim.common.utilities.ResourcesBundle;
import com.megatim.common.utilities.TypeOperation;
import com.megatim.security.model.Autorisation;

/**
 * Boite de dialogue d'edition AutorisationDialog
 *
 * @since Sun Sep 18 21:53:38 CEST 2016
 *
 */
public class AutorisationDialog
        extends AbstractEditTemplateDialog<Autorisation, Long> {

    private MessagesBundle bundle;
    private ResourcesBundle resourcesbundle;

    /**
     * Constructeur par defaut
     *
     */
    public AutorisationDialog() {
        interne = true;
    }

    /**
     * Constructeur avec parametres
     *
     * @param parent
     */
    public AutorisationDialog(Frame parent) {
        super(parent);
        this.setSize(500, 300);
        interne = true;
    }

    /**
     * Constructeur avec parametres
     *
     * @param parent
     * @param title
     */
    public AutorisationDialog(Frame parent, String title) {
        super(parent, title);
        this.setSize(500, 300);
        interne = true;
    }

    /**
     * Constructeur avec parametres
     *
     * @param parent
     * @param typeOperation
     * @param modal
     */
    public AutorisationDialog(JFrame parent, Boolean modal, TypeOperation typeOperation) {
        super(parent, modal, typeOperation);
        this.setSize(500, 300);
        interne = true;
        this.setSize(new Dimension((int) this.getPreferredSize().getWidth(),250));
    }

    /**
     * Methode permettant de retourner la cle primaire
     *
     * @param object
     * @return java.lang.Long
     */
    @Override
    public Long getPrimaryKey(Autorisation object) {
        return object.getId();
    }

    /**
     * Methode permettant de retourner nom de l'action
     *
     * @return java.lang.String
     */
    @Override
    public String getActionName() {
        return null;
    }

    /**
     * Methode permettant de retourner les parametres pour le reporting
     *
     * @return java.util.Map
     */
    @Override
    public Map getReportParameters() {
        return null;
    }

    /**
     * Methode permettant de retourner le nom du fichier Jasper
     *
     * @return java.lang.String
     */
    @Override
    public String getJasperFileName() {
        return null;
    }

    /**
     * Methode permettant de retourner le titre de la fenetre
     *
     * @return java.lang.String
     */
    @Override
    public String getWindowTitle() {
        return (MessagesBundle.getMessage("autorisation.edit"));
    }

    /**
     * Methode permettant de retourner l'icone de la fenetre
     *
     * @return javax.swing.ImageIcon
     */
    @Override
    public ImageIcon getImage() {
        try {
            ImageIcon icon = null;
            icon = CommonsUtilities.getImageUser();
            return icon;
        } catch (Exception ex) {;
        }
        return null;
    }

    /**
     * Methode permettant de retourner le panel des champs
     *
     * @return javax.swing.JPanel
     */
    @Override
    public JPanel getFiledsPanel() {
        if ((middlePanel == null)) {
            middlePanel = new AutorisationEditPanel();
        }
        return (middlePanel);
    }

    /**
     * Methode permettant de retourner l'instance de la fenetre principale
     *
     * @return javax.swing.JFrame
     */
    @Override
    public JFrame getApplicationFrame() {
        //return com.optigestcom.views.principal.PrincipalScreen.FRAME ;
        return null;
    }

    /**
     * Methode permettant de retourner nom complet de la classe
     *
     * @return java.lang.String
     */
    @Override
    public String getWindowClassName() {
        return "com.megatim.security.clients.AutorisationIFrame";
    }

}
