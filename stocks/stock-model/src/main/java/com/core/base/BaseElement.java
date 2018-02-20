/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Commercial_2
 */
@MappedSuperclass
@XmlRootElement
public class BaseElement implements Serializable{
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Predicate(label = "id" , nullable = false,optional = false,type = Long.class)
    protected long id =-1;
    
    protected long compareid ;
    
    protected String designation ;
    
    protected String editTitle =null;
    
    protected String listTitle = null ;
    
    protected String moduleName ;
    
    protected Boolean selected = Boolean.FALSE;
    
    //Si true permet de creer l'entite depuis le champs de selection
    protected boolean createonfield = true ;
    
    //Permet de désactiver le button de creation
    protected boolean desablecreate = false ;
    
    protected boolean desabledelete = false ;
    
    //Unique number for entity identifier
    protected String serial ;
    
    //Active la gestion des fichiers joints
    protected boolean activefilelien = false ;
    
    //Contient le script du pied de table personnalisé
    protected String footerScript = null;
    
    protected boolean activatefollower = false ;
    
    /**
     * String that contain the state
     * "[{'draft','Draft'},{'confirmed','Confirmed'}]"
     */   
    @Transient    
   protected List<State> states = new ArrayList<State>(); 
   
     

    public BaseElement() {
    }

    /**
     * 
     * @param id
     * @param designation
     * @param moduleName 
     */
    public BaseElement(long id, String designation, String moduleName) {
        this.id = id;
        this.designation = designation;
        this.moduleName = moduleName;
        this.compareid = id ;
    }

    public long getCompareid() {
        return id;
    }

    public void setCompareid(long compareid) {
        this.compareid = compareid;
    }
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEditTitle() {
        return editTitle;
    }

    public void setEditTitle(String editTitle) {
        this.editTitle = editTitle;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public boolean isCreateonfield() {
        return createonfield;
    }

    public void setCreateonfield(boolean createonfield) {
        this.createonfield = createonfield;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public boolean isDesablecreate() {
        return desablecreate;
    }

    public void setDesablecreate(boolean desablecreate) {
        this.desablecreate = desablecreate;
    }

    public boolean isDesabledelete() {
        return desabledelete;
    }

    public void setDesabledelete(boolean desabledelete) {
        this.desabledelete = desabledelete;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }   

    public boolean isActivefilelien() {
        return activefilelien;
    }

    public void setActivefilelien(boolean activefilelien) {
        this.activefilelien = activefilelien;
    }

    public List<State> getStates() {
        return states;
    }   

    public String getFooterScript() {
        return footerScript;
    }

    public void setFooterScript(String footerScript) {
        this.footerScript = footerScript;
    }

    public boolean isActivatefollower() {
        return activatefollower;
    }

    public void setActivatefollower(boolean activatefollower) {
        this.activatefollower = activatefollower;
    }
   
    /**
     * 
     * @return 
     */
    public String[] searchFields(){return null;}
    
}
