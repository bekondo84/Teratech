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
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_CAPR")
public class CategorieProduit extends BaseElement implements Serializable,Comparable<CategorieProduit>{

    @Predicate(label = "Intitulé",optional = false,nullable = false,unique =true,search=true)
    private String code ;
    
    @Predicate(label = "Suivi en gestion de stock",type = Boolean.class)
    private Boolean suiviestock ;

    /**
     * 
     * @param code
     * @param suiviestock 
     */
    public CategorieProduit(String code, Boolean suiviestock) {
        this.code = code;
        this.suiviestock = suiviestock;
    }

    /**
     * 
     * @param code
     * @param suiviestock
     * @param id
     * @param designation
     * @param moduleName 
     */
    public CategorieProduit(String code, Boolean suiviestock, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.code = code;
        this.suiviestock = suiviestock;
    }

    public CategorieProduit() {
    }
    
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getSuiviestock() {
        return suiviestock;
    }

    public void setSuiviestock(Boolean suiviestock) {
        this.suiviestock = suiviestock;
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
        return "Catégories de produits"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Catégorie de produits"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(CategorieProduit o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
