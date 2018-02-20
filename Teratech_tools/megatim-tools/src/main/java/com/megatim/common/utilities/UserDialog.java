/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

import com.megatim.model.test.CompteUser;
import com.megatim.common.clients.AbstractEditTemplateDialog;
import java.awt.Frame;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author DEV_4
 */
public class UserDialog extends AbstractEditTemplateDialog<CompteUser, String>{

    public UserDialog(JFrame parent, boolean modal, TypeOperation type) {
        super(parent, modal, type);
    }

    public UserDialog(Frame owner) {
        super(owner);
    }

    public UserDialog(Frame owner, String title) {
        super(owner, title);
    }

    public UserDialog() {
    }

    
    
    @Override
    protected JPanel getFiledsPanel() {
        
        if(middlePanel==null)
                 middlePanel= new CompteUserEditPanel(); 

        return middlePanel;
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getPrimaryKey(CompteUser Object) {
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
        return "Edition d'un Compte utilisateur"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getWindowClassName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ImageIcon getImage() {
        return new ImageIcon(ClassLoader.getSystemResource("com/megatim/tools/images/personnel.png")); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Map getReportParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getJasperFileName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args){
        
        try {
            UserDialog dialog = new UserDialog();
            CompteUser user = new CompteUser();
            dialog.setCurrentObject(user);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(UserDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UserDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
