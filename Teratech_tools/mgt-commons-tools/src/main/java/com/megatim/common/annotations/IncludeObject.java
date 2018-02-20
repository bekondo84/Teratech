/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.annotations;

/**
 *
 * @author Commercial_2
 */
public class IncludeObject {
    
    private Class[] classes ;
    
    private String[] champs ;
    
    private Connector connector ;
    
    private Class<?> type ;
    
    private String valeur ;

    
    /**
     * 
     * @param classes
     * @param champs
     * @param connector
     * @param type
     * @param valeur 
     */
    public IncludeObject(Class[] classes, String[] champs, Connector connector, Class<?> type, String valeur) {
        this.classes = classes;
        this.champs = champs;
        this.connector = connector;
        this.type = type;
        this.valeur = valeur;
    }

    /**
     * 
     */
    public IncludeObject() {
    }

    public Class[] getClasses() {
        return classes;
    }

    public void setClasses(Class[] classes) {
        this.classes = classes;
    }

    public String[] getChamps() {
        return champs;
    }

    public void setChamps(String[] champs) {
        this.champs = champs;
    }

    public Connector getConnector() {
        return connector;
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
    
    
    
    
    
}
