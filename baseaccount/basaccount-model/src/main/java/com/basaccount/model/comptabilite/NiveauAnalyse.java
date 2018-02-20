/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.comptabilite;

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
@Table(name = "T_NAnalyse")
public class NiveauAnalyse extends BaseElement implements Serializable,Comparable<NiveauAnalyse>{

    @Predicate(label = "Intitulé" ,unique = true,optional = false)
    private String code ;

    /**
     * 
     */
    public NiveauAnalyse() {
    }

    /**
     * 
     * @param code
     * @param id
     * @param designation
     * @param moduleName 
     */
    public NiveauAnalyse(String code, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        return "Niveaux d'analyse"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Niveau d'analyse"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(NiveauAnalyse o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
