/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.achat.model.base;

import com.core.base.BaseElement;
import java.io.Serializable;

/**
 *
 * @author BEKO
 */
public class Article extends BaseElement implements Serializable,Comparable<Article>{

    private String code ;
    
    private String intitule ;

    /**
     * 
     */
    public Article() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    
    
    
    
    @Override
    public int compareTo(Article o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
