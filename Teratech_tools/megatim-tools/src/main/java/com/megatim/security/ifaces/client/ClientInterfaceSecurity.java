
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.security.ifaces.client;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.security.principal.UserPrincipal;
import javax.swing.ImageIcon;

/**
 *
 * @author user
 */
public interface ClientInterfaceSecurity {
    
    public void launchClientFrame();
    public void launchClientFrame(UserPrincipal currentUserConnected,Object exerciceBudgetaire);
    public void launchClientFrame(String login, String password);
    public void launchClientFrame(UserPrincipal currentUserConnected);
    public GenericManager getExerciceBudgetaireManager();
    public ImageIcon getIconWindow();
    public boolean ifLoginByServer();
    public String getUrlServerForAuthentification();
}

