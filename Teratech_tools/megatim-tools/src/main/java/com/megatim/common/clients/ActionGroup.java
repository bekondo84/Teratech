/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.clients;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DEV_4
 */
public class ActionGroup {
    
    private String name ;
    
    private String label ;
    
    private boolean independent = false ;
    
    private boolean vmenu = true ;
    
    private String prefix = "";
    
    private boolean separator = false ;
    
    private List<ActionDetail> actions = new ArrayList<ActionDetail>();

    private List<ActionGroup> groupes = new ArrayList<ActionGroup>();
    /**
     * 
     */
    public ActionGroup() {
    }

    
    
    public ActionGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ActionDetail> getActions() {
        return actions;
    }

    public void setActions(List<ActionDetail> actions) {
        this.actions = actions;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ActionGroup> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<ActionGroup> groupes) {
        this.groupes = groupes;
    }

    public boolean isIndependent() {
        return independent;
    }

    public void setIndependent(boolean independent) {
        this.independent = independent;
    }

    public boolean isVmenu() {
        return vmenu;
    }

    public void setVmenu(boolean vmenu) {
        this.vmenu = vmenu;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public boolean isSeparator() {
        return separator;
    }

    public void setSeparator(boolean separator) {
        this.separator = separator;
    }

    
    
    @Override
    public String toString() {
        return "ActionGroup{" + "name=" + name + ", actions=" + actions + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ActionGroup other = (ActionGroup) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
    
}
