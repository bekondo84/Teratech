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
@Table(name = "T_CIVILITE")
public class Civilite extends BaseElement implements Serializable,Comparable<Civilite>{

    @Predicate(label = "Civilité",unique = true,optional = false)
    private String code;
    
    @Predicate(label = "Abréviation")
    private String shortcut ;

    public Civilite() {
    }

    /**
     * 
     * @param code
     * @param shortcut
     * @param id
     * @param designation
     * @param moduleName 
     */
    public Civilite(String code, String shortcut, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.code = code;
        this.shortcut = shortcut;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }
    
     @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return code; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "Civilités"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Civilité"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCompareid() {
        return id; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(Civilite o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
