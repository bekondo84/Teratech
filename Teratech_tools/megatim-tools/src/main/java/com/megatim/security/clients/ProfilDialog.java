
package com.megatim.security.clients;

import com.megatim.common.clients.AbstractEditTemplateDialog;
import com.megatim.common.clients.AbstractTableBaseListModel;
import com.megatim.common.clients.CommonsUtilities;
import com.megatim.common.clients.Messages;
import com.megatim.common.clients.NotificationType;
import com.megatim.common.context.ToolsContext;
import com.megatim.common.enumeration.TreeState;
import com.megatim.common.utilities.MessagesBundle;
import com.megatim.common.utilities.ResourcesBundle;
import com.megatim.common.utilities.TypeOperation;
import com.megatim.model.test.MenuComponent;
import com.megatim.model.test.MenuComposite;
import com.megatim.model.test.MenuLeaf;
import com.megatim.security.model.Autorisation;
import com.megatim.security.model.Profil;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Boite de dialogue d'edition ProfilDialog

 * @since Sun Sep 18 21:53:38 CEST 2016
 * 
 */
public class ProfilDialog
    extends AbstractEditTemplateDialog<Profil, String>
{

    private MessagesBundle bundle;
    private ResourcesBundle resourcesbundle;

    /**
     * Constructeur par defaut
     * 
     */
    public ProfilDialog() {
    }

    /**
     * Constructeur avec parametres
     * 
     * @param parent
     */
    public ProfilDialog(Frame parent) {
         super(parent);
        interne = false ;
    }

    /**
     * Constructeur  avec parametres
     * 
     * @param parent
     * @param title
     */
    public ProfilDialog(Frame parent, String title) {
         super(parent , title);
        interne = false ;
    }

    /**
     * Constructeur  avec parametres
     * 
     * @param parent
     * @param typeOperation
     * @param modal
     */
    public ProfilDialog(JFrame parent, Boolean modal, TypeOperation typeOperation) {
         super(parent , modal ,typeOperation );
         this.setSize(700, 520);
        interne = false ;
    }

    /**
     * Methode permettant de retourner la cle primaire
     * 
     * @param object
     * @return
     *     java.lang.String
     */
    @Override
    public String getPrimaryKey(Profil object) {
        return object.getNom() ;
    }

    /**
     * Methode permettant de retourner nom de l'action
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public String getActionName() {
         return null ; 
    }

    /**
     * Methode permettant de retourner les parametres pour le reporting
     * 
     * @return
     *     java.util.Map
     */
    @Override
    public Map getReportParameters() {
         return null ; 
    }

    /**
     * Methode permettant de retourner le nom du fichier Jasper
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public String getJasperFileName() {
         return null ; 
    }

    /**
     * Methode permettant de retourner le titre de la fenetre
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public String getWindowTitle() {
        return (MessagesBundle.getMessage("profil.edit"));
    }

    /**
     * Methode permettant de retourner l'icone de la fenetre
     * 
     * @return
     *     javax.swing.ImageIcon
     */
    @Override
    public ImageIcon getImage() {
        try{
        ImageIcon icon = null;
        icon = CommonsUtilities.getImageUser();
        return icon;
        }catch(Exception ex){;}
        return null;
    }

    /**
     * Methode permettant de retourner le panel des champs
     * 
     * @return
     *     javax.swing.JPanel
     */
    @Override
    public JPanel getFiledsPanel() {
        if ((middlePanel==null)) {
            middlePanel = new ProfilEditPanel() ;
        }
        return (middlePanel);
    }

    /**
     * Methode permettant de retourner l'instance de la fenetre principale
     * 
     * @return
     *     javax.swing.JFrame
     */
    @Override
    public JFrame getApplicationFrame() {
        //return com.optigestcom.views.principal.PrincipalScreen.FRAME ;
        return null;
    }

    /**
     * Methode permettant de retourner nom complet de la classe
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public String getWindowClassName() {
         return "com.megatim.security.clients.ProfilIFrame" ;
    }    

   
    @Override
    protected boolean beforeSave() {System.out.println("*************************** "+currentObject.isIsAdmin());
        
        if(!currentObject.isIsAdmin()){
            conciliation(currentObject.getMenus(), currentObject.getAutorisations());      
            return true;
        }else{
            //Afficher la fenetre d'erreur    
            Messages.Messages(null, true, NotificationType.ERROR, "OPERATION IMPOSSIBLE !", "Vous ne pouvez pas modifier les parametres d'un profil super utilisateur","");
            return false;
        }
    }
    
    @Override
    protected boolean beforeDelete() {
        
        if(!currentObject.isIsAdmin()){
            return true;
        }else{
            
            //Afficher la fenetre d'erreur    
            Messages.Messages(null, true, NotificationType.ERROR, "OPERATION IMPOSSIBLE !", "Vous ne pouvez pas supprimer un profil super utilisateur","");
            return false;
        }
    }
    
    /**
     * 
     * @param menus
     * @param autorisations 
     */
    private void conciliation(List<MenuComponent> menus , List<Autorisation> autorisations){
        
        for(MenuComponent c : menus){
            
            if(c instanceof MenuLeaf){
                
                if(autorisations.contains(c.getAutorisation())){
                    int index = autorisations.indexOf(c.getAutorisation());
                    autorisations.get(index).setStatut(c.getAutorisation().getStatut());
                    //System.out.println("ProfilDialog.conciliation(List<MenuComponent> menus , List<Autorisation> autorisations) :::::::::::::::::::::::  "+c+" :::::::  "+c.getAutorisation()+" ::::::::::::::::::: "+c.getAutorisation().getStatut()+" :::::::::::::::::: "+autorisations.indexOf(c.getAutorisation())+" :::::: "+autorisations.get(index).getId());
                }
            }else{
                conciliation(((MenuComposite)c).getElements(), autorisations);
            }
        }
        
    }
    
    
}
