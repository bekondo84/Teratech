/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.stock.model.base;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_UNAC")
public class UniteAchat extends BaseElement implements Serializable,Comparable<UniteAchat>{

    @Predicate(label = "Code",optional = false,unique = true,search = true)
    private String code ;    
    
    @Predicate(label = "actif",type = Boolean.class)
    private Boolean actif ;
    
    @ManyToOne
    @JoinColumn(name = "UNGE_ID")
    @Predicate(label = "Unité de gestion",type = UniteGestion.class,target = "many-to-one",search = true)
    private UniteGestion unite ;
    
    @Predicate(label = "Précision",type = Double.class,search = true)
    private Double coeff = 0.0;

    public UniteAchat(String code, Boolean actif, UniteGestion unite) {
        this.code = code;
        this.actif = actif;
        this.unite = unite;
    }

    public UniteAchat(String code, Boolean actif, UniteGestion unite, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.code = code;
        this.actif = actif;
        this.unite = unite;
    }

    public UniteAchat() {
    }

    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public UniteGestion getUnite() {
        return unite;
    }

    public void setUnite(UniteGestion unite) {
        this.unite = unite;
    }

    public Double getCoeff() {
        return coeff;
    }

    public void setCoeff(Double coeff) {
        this.coeff = coeff;
    }
    

    @Override
    public String getDesignation() {
        return code; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "teratechstock"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "Unités d'achat"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Unité d'achat"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(UniteAchat o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
