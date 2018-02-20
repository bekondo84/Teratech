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
@Table(name = "T_PAYS")
public class Pays extends BaseElement implements Serializable,Comparable<Pays>{
    
     @Predicate(label = "image",target = "image",search = false)
     private String image ;
     
    @Predicate(label = "Nom du pays" ,optional = false,unique = true,search = true)
    private String intitule ;
    
    @Predicate(label = "Code du pays" ,optional = false,updatable = false,unique = true,search = true)
    private String code;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesignation() {
        return intitule; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "teratechstock"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "PAYS"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "PAYS"; //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public int compareTo(Pays o) {
        return code.compareTo(o.code); //To change body of generated methods, choose Tools | Templates.
    }
}
