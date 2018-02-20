/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.commons;

/**
 *Classe devant être herite par tous les entites du
 * modeles voulant generer une interface
 * @author Commercial_2
 */
public abstract class AbstractBase {
    
    /**
     * Etat de l'entite valeurs
     * -1 : etat Puis pas de transistion
     * 
     */
    
    protected int state = -1;
    
   
    protected boolean archive = Boolean.FALSE;
    

    /**
     * 
     */
    public AbstractBase() {
    }

    /**
     * 
     * @param state 
     */
    public AbstractBase(int state) {
        
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }
    
    
    
    /**
     * 
     * @return 
     */
    public abstract String[] actions();
    
    
    /**
     * 
     * @param action
     * @return 
     */
    public abstract int nextState(String action);
    
    
}
