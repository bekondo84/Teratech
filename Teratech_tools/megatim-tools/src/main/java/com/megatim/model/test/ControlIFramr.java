/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.model.test;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.clients.AbstractListValidationTemplateFrame;
import com.megatim.common.clients.AbstractTableBaseListModel;
import com.megatim.common.clients.PaginationStep;
import com.megatim.common.utilities.TypeOperation;
import java.io.Serializable;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author DEV_4
 */
public class ControlIFramr extends AbstractListValidationTemplateFrame<CompteUser, CompteUser, CompteUser, Serializable>{

    /**
     * 
     */
    public ControlIFramr() {
    }

    
    
    @Override
    protected JPanel getCriteriaPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Serializable getPrimaryKey(CompteUser Object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected GenericManager getManager() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected GenericManager getValideManager() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected GenericManager getRejetManager() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected PaginationStep getPagination() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected JFrame getApplicationFrame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getActionName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getWindowTitle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractTableBaseListModel getTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getWindowClassName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ImageIcon getImage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Map getReportParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getJasperFileName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected JDialog getEditDialog(CompteUser object, GenericManager manager, TypeOperation typeOperation, JFrame window) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected JDialog getValideEditDialog(CompteUser object, GenericManager manager, TypeOperation typeOperation, JFrame window) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected JDialog getRejetEditDialog(CompteUser object, GenericManager manager, TypeOperation typeOperation, JFrame window) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected CompteUser validateWapper(CompteUser object) {
        return null; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected CompteUser rejectWapper(CompteUser object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
