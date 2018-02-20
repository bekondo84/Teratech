/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.tiers;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 *
 * @author Commercial_2
 */
@Entity
@Table(name = "T_CPAYMENT")
public class ConditionPaiement extends BaseElement implements Serializable,Comparable<ConditionPaiement>{

    @Predicate(label = "Code",unique = true,optional = false)
    private String code ;
    
    @Predicate(label = "Intitulé")
    private String label ;
    
    @Predicate(label = "Durée(jours)",optional = false,type = Short.class)
    private Short jours = 0;
    
    @Predicate(label = "Actif" ,type = Boolean.class)
    private Boolean active = true;

    /**
     * 
     */
    public ConditionPaiement() {
    }

    /**
     * 
     * @param code
     * @param label
     * @param id
     * @param designation
     * @param moduleName 
     */
    public ConditionPaiement(String code, String label, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.code = code;
        this.label = label;
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

    public Short getJours() {
        return jours;
    }

    public void setJours(Short jours) {
        this.jours = jours;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDesignation() {
        return label; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "Conditions de paiements";  //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Condition de paiement"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCompareid() {
        return id; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
     @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int compareTo(ConditionPaiement o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
