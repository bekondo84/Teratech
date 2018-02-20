/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.clients.AbstractTableBaseListModel;
import com.megatim.model.test.CompteUser;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author DEV_4
 */
public class CompteUserTablePanel extends AbstractTablePanel<CompteUser>{

    
    
    public CompteUserTablePanel() {
        
        super();
    }

    
    
    @Override
    protected JDialog getEditDialog(CompteUser object, GenericManager manager, AbstractTableBaseListModel model, TypeOperation typeOperation, JFrame window) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected GenericManager getManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected JFrame getApplicationFrame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
