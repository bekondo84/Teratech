/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.services;



/**
 *
 * @author DEV_4
 */
public class CurrentConnectedUser {
    
    //private static User currentUser ;

    /**
     * 
     * @return 
     
    public static User getCurrentUser() {
        return currentUser;
    }*/

    /**
     * 
     * @param currentUser 
     
    public static void setCurrentUser(User currentUser) {
        CurrentConnectedUser.currentUser = currentUser;
    }
    */
    /**
     * Verifie que l'utilisateur courant dispose des authorisation
     * necessaire pour executer une methode
     * @param roleName
     * @return 
     
    public static boolean isCurrentUserInRole(String[] rolesName){
        //System.out.println("CurrentConnectedUser.isCurrentUserInRole(String[] rolesName) ::::::::::::: "+currentUser);
        if(currentUser==null||currentUser.getRole()==null)
                     return true;
        //Aucun role n'est d�fini pour cette application
        if(rolesName==null||rolesName.length==0)
                                return true ;
        
        for(String roleName : rolesName){
            
            if(roleName.equalsIgnoreCase(currentUser.getRole().getName()))
                   return true;
        }
        
        return false;
    }*/
}
