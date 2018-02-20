/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.commons;

import java.io.Serializable;

/**
 *
 * @author Commercial_2
 */
public class FilterRule implements Serializable{
    
    private String fieldName ;
    
    private Comparable value ;
    
    private EnumConnector predicate ;

    /**
     * 
     */
    public FilterRule() {
    }

    /**
     * 
     * @param fieldName
     * @param value
     * @param predicate 
     */
    public FilterRule(String fieldName, Comparable value, EnumConnector predicate) {
        this.fieldName = fieldName;
        this.value = value;
        this.predicate = predicate;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Comparable getValue() {
        return value;
    }

    public void setValue(Comparable value) {
        this.value = value;
    }

    public EnumConnector getPredicate() {
        return predicate;
    }

    public void setPredicate(EnumConnector predicate) {
        this.predicate = predicate;
    }

   
    
    
}
