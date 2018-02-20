/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.stock.model.banques;

import com.core.base.BaseElement;
import com.core.base.State;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Commercial_2
 */
@Entity
@Table(name = "T_BANQUE")
public class Banque extends BaseElement implements Serializable,Comparable<Banque>{
    
    @Predicate(label = "Code banque",updatable = false,optional = false,unique = true,search = true)
    private String code ;
    
    @Predicate(label = "Nom",search = true)
    private String label ;
    
    @Predicate(label = "Adresse",group = true,groupName = "group1",groupLabel = "Informations",search = true)
    private String adresse ;
    
    @Predicate(label = "Téléphone",group = true,groupName = "group1",groupLabel = "Informations",search = true)
    private String tel ;
    
    @Predicate(label = "Fax",group = true,groupName = "group1",groupLabel = "Informations",search = true)
    private String fax ;
    
    @Predicate(label = "Courriel",group = true,groupName = "group1",groupLabel = "Informations",search = true)
    private String courriel ;
    
    @Predicate(label = "Actif",group = true,groupName = "group1",groupLabel = "Informations")
    private Boolean active = true;
    
    private String state = "etabli";

    /**
     * 
     */
    public Banque() {
    }

    /**
     * 
     * @param code
     * @param label
     * @param adresse
     * @param tel
     * @param fax
     * @param courriel
     * @param id
     * @param designation
     * @param moduleName 
     */
    public Banque(String code, String label, String adresse, String tel, String fax, String courriel, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.code = code;
        this.label = label;
        this.adresse = adresse;
        this.tel = tel;
        this.fax = fax;
        this.courriel = courriel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    
    
    @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return label; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "Banques"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Banque"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(Banque o) {
        return code.compareTo(o.code); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<State> getStates() {
        //To change body of generated methods, choose Tools | Templates.
        List<State> etats = new ArrayList<State>();
        State etat = new State("etabli", "Non Confirmé");
        etats.add(etat);
        etat = new State("confirme", "Confirmé");
        etats.add(etat);
        return etats; 
    }
    
    
}
