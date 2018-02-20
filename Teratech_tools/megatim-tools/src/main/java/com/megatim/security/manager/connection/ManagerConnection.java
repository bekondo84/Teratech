
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.security.manager.connection;

import com.megatim.security.model.Utilisateur;
import com.megatim.security.principal.UserPrincipal;

import java.util.ArrayList;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 *
 * @author user
 */
public class ManagerConnection {
    
    public static String loginModuleName; 
    public static CallbackHandler callbackHandler;
    public static String loginModuleFileConfigPath;
    public static Subject subject = new Subject();
    public static UserPrincipal currentUser;
    public static LoginContext lc;
    public static ArrayList users = new ArrayList();
    
    public static boolean login(String loginModuleName, CallbackHandler callbackHandler, String loginModuleFileConfigPath) {
        //Initialisation
        ManagerConnection.loginModuleName = loginModuleName;
        ManagerConnection.callbackHandler = callbackHandler;
        ManagerConnection.loginModuleFileConfigPath = loginModuleFileConfigPath;
        
        System.setProperty("java.security.auth.login.config", ManagerConnection.loginModuleFileConfigPath);
        
        try {    
            lc = new LoginContext(ManagerConnection.loginModuleName,ManagerConnection.callbackHandler);
            lc.login();
        } catch (LoginException ex) {
            return false;
        }
        
        return true; 
    }

    public static void logout() {
     
        try {    
            lc.logout();
        } catch (LoginException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static boolean authenticateUser(Utilisateur user){
    	boolean reponse=false;
    	if(user.getLogin().equals("ADMIN")&user.getPassword().equals("admin")){
    		reponse=true;
    	}else{
    		reponse=false;
    	}
    	
    	return reponse;
    }

}

