/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericdaolayer.dao.tools;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author MGT
 */
public class NOTLIKE<Y extends Comparable<Y>> extends Predicat implements Serializable{
    
    
    //Property name
    private String propertyName ;
    
    //Valeur de comparaison
    
    private String value ;
    
    
    /**
     * Constructeur par défaut
     */
    public NOTLIKE() {
        
    }

    public NOTLIKE(String propertyName, String value) {
        this.propertyName = propertyName;
        this.value = value;
    }
    
    

    
    /**
     * Accesseur 
     * @return 
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Modificateur
     * @param typeOperation 
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

  
    /**
     * Accesseur 
     * @return 
     */
    public String getValue() {
        return value;
    }

    /**
     * Modificateur
     * @param typeOperation 
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Predicate generateJPAPredicat(CriteriaBuilder criteriaBuilder, Root<?> root) {
        return criteriaBuilder.notLike(DAOUtilis.<String>buildPropertyPath(root, propertyName), value);
    }
    
    
}
