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
public class BETWEEN<Y extends Comparable<Y>> extends Predicat implements Serializable{
    
    
    //Property name
    private String propertyName ;
    
    //Valeur de comparaison
    
    private Y value_1 ;
    
    //Valeur de comparaison
    
    private Y value_2 ;
    
    /**
     * Constructeur par défaut
     */
    public BETWEEN() {
        
    }

    public BETWEEN(String propertyName, Y value_1,Y value_2) {
        this.propertyName = propertyName;
        this.value_1 = value_1;
        this.value_2 = value_2;
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
    public Y getValue_1() {
        return value_1;
    }

     /**
     * Modificateur
     * @param typeOperation 
     */
    public void setValue_1(Y value_1) {
        this.value_1 = value_1;
    }

    /**
     * Accesseur 
     * @return 
     */
    public Y getValue_2() {
        return value_2;
    }

     /**
     * Modificateur
     * @param typeOperation 
     */
    public void setValue_2(Y value_2) {
        this.value_2 = value_2;
    }  
    

    @Override
    public Predicate generateJPAPredicat(CriteriaBuilder criteriaBuilder, Root<?> root) {
        return criteriaBuilder.between(DAOUtilis.<Y>buildPropertyPath(root, propertyName), value_1,value_2);
    }
    
    
}
