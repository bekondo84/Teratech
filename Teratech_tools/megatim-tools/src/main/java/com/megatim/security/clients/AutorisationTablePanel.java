
package com.megatim.security.clients;

import javax.swing.JDialog;
import javax.swing.JFrame;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.clients.AbstractTableBaseListModel;
import com.megatim.common.services.IocContext;
import com.megatim.common.utilities.AbstractTablePanel;
import com.megatim.common.utilities.TypeOperation;
import com.megatim.security.model.Autorisation;

public class AutorisationTablePanel
    extends AbstractTablePanel<Autorisation>
{

    private IocContext context;

    public AutorisationTablePanel() {
        super() ; 
         context = new IocContext();
    }

    @Override
    public JDialog getEditDialog(Autorisation object, GenericManager manager, AbstractTableBaseListModel model, TypeOperation typeOperation, JFrame window)
        throws Exception
    {
        if ((object==null)) {
             object = new Autorisation();
        }
        com.megatim.security.clients.AutorisationDialog  dialog = new com.megatim.security.clients.AutorisationDialog(getApplicationFrame() , true , typeOperation) ;
         dialog.setCurrentObject(object);
         dialog.setManager(manager);
         dialog.setModel(model);
        return (dialog);
    }

    /**
     * /**  **<!---->/
     * 
     */
    @Override
    public GenericManager getManager()
        throws Exception
    {
        return (GenericManager)context.lookup("com.megatim.security.core.impl.Autorisation.AutorisationManagerImpl");
    }

    public JFrame getApplicationFrame() {
        //return com.optigestcom.views.principal.PrincipalScreen.FRAME ;
        return null;
    }

}
