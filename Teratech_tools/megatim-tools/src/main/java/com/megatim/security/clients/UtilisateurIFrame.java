
package com.megatim.security.clients;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.clients.AbstractListTemplateFrame;
import com.megatim.common.clients.AbstractTableBaseListModel;
import com.megatim.common.clients.PaginationStep;
import com.megatim.common.context.ToolsContext;
import com.megatim.common.services.IocContext;
import com.megatim.common.utilities.MessagesBundle;
import com.megatim.common.utilities.ResourcesBundle;
import com.megatim.common.utilities.TypeOperation;
import com.megatim.security.model.Autorisation;
import com.megatim.security.model.Utilisateur;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.base.JRBaseParameter;


/**
 * Fenetre interne UtilisateurIFrame

 * @since Sun Sep 18 21:53:38 CEST 2016
 * 
 */
public class UtilisateurIFrame
    extends AbstractListTemplateFrame<Utilisateur, String>
{

    private MessagesBundle bundle;
    private ResourcesBundle resourcesbundle;
    public final static String FRAME_NAME = ("com.megatim.security.clients.UtilisateurIFrame");
    public final static String ACTION_NAME = "utilisateurAction";

    /**
     * Constructeur par defaut
     * 
     */
    public UtilisateurIFrame() {
         super();
    }

    /**
     * Methode permettant de retourner la cle primaire
     * 
     * @param object
     * @return
     *     java.lang.String
     */
    @Override
    public String getPrimaryKey(Utilisateur object) {
        return object.getLogin() ;
    }

    /**
     * Methode permettant de retourner nom de l'action
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public String getActionName() {
         return "utilisateurAction" ; 
    }

    /**
     * Methode permettant de retourner les parametres pour le reporting
     * 
     * @return
     *     java.util.Map
     */
     @Override
    public Map getReportParameters() {
        Map params = new HashMap();
        params.put("epaName", "");
        params.put("entityName", "Utilisateurs");

        // On positionne la locale
        params.put(JRBaseParameter.REPORT_LOCALE, Locale.FRENCH);
        // Construction du Bundle
      // ResourceBundle bundle = ReportHelper.getInstace();
         //Ajout du bundle dans les parametres
        params.put(JRBaseParameter.REPORT_RESOURCE_BUNDLE, ResourceBundle.getBundle("reports/report_FR"));
        //params.put(ReportsParameter.REPORT_IMAGE_REPOSITORY, ReportHelper.reportFileimage);

        return params;
    }

    /**
     * Methode permettant de retourner le nom du fichier Jasper
     *
     * @return java.lang.String
     */
    @Override
    public String getJasperFileName() {
        String report =System.getProperty("user.dir") + File.separator + "/reports/" + "EdtUsers";

        if ((getSelectedObjects() == null || getSelectedObjects().size() == 0 || getSelectedObjects().isEmpty())) {
            return report;

        } else {
            if ((getSelectedObjects().size() == 1)) {
                return report;
            } else {
                return report;
            }
        }
    }

    /**
     * Methode permettant de retourner le titre de la fenetre
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public String getWindowTitle() {
        return (MessagesBundle.getMessage("utilisateur.list"));
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
        icon = new ImageIcon(getClass().getResource(ResourcesBundle.getResource("utilisateur.list.image")));
        return icon;
        } catch(Exception ex ){;}
        return null;
    }

    /**
     * Methode permettant de retourner le panel des champs
     * 
     * @return
     *     javax.swing.JPanel
     */
    @Override
    public JPanel getCriteriaPanel() {
        if ((criteriaPanel==null)) {
            criteriaPanel = new UtilisateurCriteriaPanel();
        }
        return (criteriaPanel);
    }

    /**
     * Methode permettant de retourner le manager
     * 
     * @return
     *     com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager
     */
    @Override
    public GenericManager<Utilisateur, String> getManager()
        throws Exception
    {
        IocContext context = new IocContext();
        return (GenericManager)context.lookup("com.megatim.security.core.impl.Utilisateur.UtilisateurManagerImpl");
    }

    /**
     * Methode permettant de retourner le modele de tableau
     * 
     * @return
     *     com.megatim.common.clients.AbstractTableBaseListModel
     */
    @Override
    public AbstractTableBaseListModel getTableModel() {
        if ((model==null)) {
             model = new UtilisateurModel();
        }
        return (model);
    }

    /**
     * Methode permettant de retourner le nom complet de la classe
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public String getWindowClassName() {
        return "com.megatim.security.clients.UtilisateurIFrame" ;
    }

    /**
     * Methode permettant de retourner nom complet de la classe
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public JDialog getEditDialog(Utilisateur object, GenericManager manager, TypeOperation typeOperation, JFrame window)
        throws Exception
    {
        if ((object==null)) {
             object = new Utilisateur();
        }
        UtilisateurDialog  dialog = new UtilisateurDialog(getApplicationFrame() ,true, typeOperation) ;
         dialog.setCurrentObject(object);
         dialog.setManager(manager);
        return (dialog);
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
     * Methode permettant de retourner une instance de la pagination
     * 
     * @return
     *     com.megatim.common.clients.PaginationStep
     */
    @Override
    public PaginationStep getPagination() {
        pagination =  new PaginationStep(20);
        return pagination ; 
    }
    
    @Override
    protected Autorisation getAutorisation() {
        return ToolsContext.getAutorisation(ACTION_NAME);
    }

}
