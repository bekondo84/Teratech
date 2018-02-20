
package com.megatim.security.clients;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.clients.AbstractListTemplateFrame;
import com.megatim.common.clients.AbstractTableBaseListModel;
import com.megatim.common.clients.PaginationStep;
import com.megatim.common.context.ToolsContext;
import com.megatim.common.enumeration.TreeState;
import com.megatim.common.services.IocContext;
import com.megatim.common.utilities.MessagesBundle;
import com.megatim.common.utilities.ResourcesBundle;
import com.megatim.common.utilities.TypeOperation;
import com.megatim.model.test.MenuComponent;
import com.megatim.security.model.Autorisation;
import com.megatim.security.model.Profil;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import net.sf.jasperreports.engine.base.JRBaseParameter;


/**  
 * Fenetre interne ProfilIFrame

 * @since Sun Sep 18 21:53:38 CEST 2016
 * 
 */
public class ProfilIFrame
    extends AbstractListTemplateFrame<Profil, String>
{

    private MessagesBundle bundle;
    private ResourcesBundle resourcesbundle;
    public final static String FRAME_NAME = ("com.megatim.security.clients.ProfilIFrame");
    public final static String ACTION_NAME = "profilAction";

    /**
     * Constructeur par defaut
     * 
     */
    public ProfilIFrame() {
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
         return "profilAction" ; 
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
        params.put("entityName", "Profils");

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
        String report =System.getProperty("user.dir") + File.separator + "/reports/" + "EdtProfil";

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
        return (MessagesBundle.getMessage("profil.list"));
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
        icon = new ImageIcon(getClass().getResource(ResourcesBundle.getResource("profil.list.image")));
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
            criteriaPanel = new ProfilCriteriaPanel();
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
    public GenericManager<Profil, String> getManager()
        throws Exception
    {
        IocContext context = new IocContext();
        return (GenericManager)context.lookup("com.megatim.security.core.impl.Profil.ProfilManagerImpl");
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
             model = new ProfilModel();
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
        return "com.megatim.security.clients.ProfilIFrame" ;
    }

    /**
     * Methode permettant de retourner nom complet de la classe
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public JDialog getEditDialog(Profil object, GenericManager manager, TypeOperation typeOperation, JFrame window)
        throws Exception
    {
        if ((object==null)) {
             object = new Profil(); 
             object.setAutorisations(ProfilBuilder.autorisationBuilder(ToolsContext.LISTE_ITEMS));
        }
        ProfilBuilder.clearCache();
        object.setMenus(ProfilBuilder.profilTreeBuilder(ToolsContext.LISTE_ITEMS, new ArrayList<MenuComponent>(), object.getAutorisations(),TreeState.VIEW_CREATE));
        ProfilDialog  dialog = new ProfilDialog(getApplicationFrame() ,true, typeOperation) ;
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
