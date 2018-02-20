package com.megatim.security.clients;

import java.awt.Frame;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.megatim.common.clients.AbstractEditTemplateDialog;
import com.megatim.common.clients.CommonsUtilities;
import com.megatim.common.clients.Messages;
import com.megatim.common.clients.NotificationType;
import com.megatim.common.utilities.MessagesBundle;
import com.megatim.common.utilities.ResourcesBundle;
import com.megatim.common.utilities.TypeOperation;
import com.megatim.security.cryptographie.symetrique.DESEncrypter;
import com.megatim.security.manager.connection.SecurityCenter;
import com.megatim.security.model.Utilisateur;

/**
 * Boite de dialogue d'edition UtilisateurDialog
 *
 * @since Sun Sep 18 21:53:37 CEST 2016
 *
 */
public class UtilisateurDialog
        extends AbstractEditTemplateDialog<Utilisateur, String> {

    private MessagesBundle bundle;
    private ResourcesBundle resourcesbundle;

    /**
     * Constructeur par defaut
     *
     */
    public UtilisateurDialog() {
    }

    /**
     * Constructeur avec parametres
     *
     * @param parent
     */
    public UtilisateurDialog(Frame parent) {
        super(parent);
        interne = false;
    }

    /**
     * Constructeur avec parametres
     *
     * @param parent
     * @param title
     */
    public UtilisateurDialog(Frame parent, String title) {
        super(parent, title);
        interne = false;
    }

    /**
     * Constructeur avec parametres
     *
     * @param parent
     * @param typeOperation
     * @param modal
     */
    public UtilisateurDialog(JFrame parent, Boolean modal, TypeOperation typeOperation) {
        super(parent, modal, typeOperation);
        interne = false;
    }

    /**
     * Methode permettant de retourner la cle primaire
     *
     * @param object
     * @return java.lang.String
     */
    @Override
    public String getPrimaryKey(Utilisateur object) {
        return object.getLogin();
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
        return (MessagesBundle.getMessage("utilisateur.edit"));
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
            middlePanel = new UtilisateurEditPanel();
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
        return "com.megatim.security.clients.UtilisateurIFrame";
    }
    
    /**
     * Mehtode appeller apres le charcgement de l'objet
     * @return 
     */
    @Override
    protected boolean afterSetCurrentObject() {
        
        if(typeOperation.equals(TypeOperation.UPDATE)){
            
            //On recupere le panel
            UtilisateurEditPanel panel = (UtilisateurEditPanel)middlePanel;
            
            //On rend invisible champs du mot de passe
            removeFieldsPassword(panel);
        }
        return super.afterSetCurrentObject();
    }
    
    /**
     * Methode appeller avant de persiter l'objet en bd
     * @return 
     */
    @Override
    protected boolean beforeSave() {
        
        if(typeOperation.equals(TypeOperation.NEW)){
        
            //On crypte le mot de passe et on met à jour l'etat de l'objet
            currentObject.setPassword(SecurityCenter.encrypte(currentObject.getPassword()));
        }
        
        return super.beforeSave();
    }
    
    @Override
    protected boolean beforeDelete() {
        
        if(!currentObject.getProfil().isIsAdmin()){
            return true;
        }else{
            
            //Afficher la fenetre d'erreur    
            Messages.Messages(null, true, NotificationType.ERROR, "OPERATION IMPOSSIBLE !", "Vous ne pouvez pas supprimer un super utilisateur","");
            return false;
        }
    }
    
    /**
     * Methode permettant de rendre invisibles les champs du mot de passe
     * @param panel 
     */
    private void removeFieldsPassword(UtilisateurEditPanel panel){
        
        //On rend invisible ces champs
        panel.getPassword().setVisible(false);
        panel.getLbmpassword().setVisible(false);
        panel.getLbpassword().setVisible(false);
    }
}
