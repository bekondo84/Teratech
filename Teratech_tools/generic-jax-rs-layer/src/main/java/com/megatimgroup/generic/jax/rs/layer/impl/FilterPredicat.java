/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.jax.rs.layer.impl;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Commercial_2
 */
@XmlRootElement
public class FilterPredicat implements Serializable{
    
    
    private String fieldName ;
    
     private String fieldLabel ;
    
    private String type ;
    
    private String fieldValue ;
    
    private String[] searchfields = null ;
    

    public FilterPredicat() {
    }
    
    public FilterPredicat(String value) {
        
//        System.out.println(FilterPredicat.class.getName()+" ::::  "+value);
    }

    /**
     * 
     * @param fieldName
     * @param oper
     * @param value 
     */
    public FilterPredicat(String fieldName, String oper, String value) {
        this.fieldName = fieldName;
        this.type = oper;
        this.fieldValue = value;
    }
    
    /**
     * 
     * @param filter 
     */
    public FilterPredicat(FilterPredicat filter) {
        this.fieldName = filter.fieldName;
        this.type = filter.type;
        this.fieldValue = filter.fieldValue;
        this.fieldLabel = filter.fieldLabel;
        this.searchfields = filter.searchfields;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }   

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public String[] getSearchfields() {
        return searchfields;
    }

    public void setSearchfields(String[] searchfields) {
        this.searchfields = searchfields;
    }

    @Override
    public String toString() {
        return "FilterPredicat{" + "fieldName=" + fieldName + ", fieldLabel=" + fieldLabel + ", type=" + type + ", fieldValue=" + fieldValue + ", searchfields=" + searchfields + '}';
    }

        
    
}
