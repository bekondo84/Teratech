/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericdaolayer.dao.tools;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

/**
 *
 * @author MGT
 */
public class ISNOTEMPTY<Y extends Comparable<Y>> extends Predicat implements Serializable{
    
    
    //Property name
    private String propertyName ;
    
        
    /**
     * Constructeur par défaut
     */
    public ISNOTEMPTY() {
        
    }

    public ISNOTEMPTY(String propertyName) {
        this.propertyName = propertyName;        
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
  
  
    @Override
    public javax.persistence.criteria.Predicate generateJPAPredicat(CriteriaBuilder criteriaBuilder, Root<?> root) {
        return criteriaBuilder.isNotEmpty((Expression) DAOUtilis.<Y>buildPropertyPath(root, propertyName));
    }
    
    
}
