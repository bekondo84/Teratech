/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.security.principal;

import com.megatim.security.model.Utilisateur;
import java.security.Principal;

/**
 *
 * @author user
 */
public class UserPrincipal implements Principal, java.io.Serializable{
    
    private String name;
    private Utilisateur userConnected;
    
    public UserPrincipal(String name){
        this.name = name;
    }
    
    public UserPrincipal(String name, Utilisateur userConnected){
        this.name = name;
        this.userConnected = userConnected;
    }
    
    public String getName() {
        return this.name;
    }

    public Utilisateur getUserConnected() {
        return userConnected;
    }

    public void setUserConnected(Utilisateur userConnected) {
        this.userConnected = userConnected;
    }
    
    public boolean equals(Object o) {
        if (o == null)
            return false;

        if (this == o)
            return true;

        if (!(o instanceof UserPrincipal))
            return false;
        UserPrincipal that = (UserPrincipal)o;

        if (this.getName().equals(that.getName()))
            return true;
        return false;
    }

}
