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
public class MetaColumn implements Serializable,Comparable<MetaColumn>{

    private String type ;
    
    private boolean search = Boolean.FALSE;    
     
    private String target ;
    
    private String fieldName ;
    
    private String fieldLabel;   
    
    private String value ;
    
    private String[] states ; 
    
    private boolean optional = true ;
    
    private boolean unique = false ; 
    
    private boolean updatable = true ;
    
    private boolean editable = true ;
    
    private boolean hide = false ;
        
    private Integer min =null;
    
    private Integer max = null ;
    
    private String pattern ;
    
    private short sequence = 0 ;
    
    private short colsequence = 0 ;
    
    private String[] searchfields = new String[]{};
    
    private boolean customfooter = false ;
    
     private String footerScript = null ;
    
    private MetaData metaData ;

    /**
     * 
     */
    public MetaColumn() {
    }

    /**
     * 
     * @param type
     * @param fieldName
     * @param fieldLabel
     * @param search
     * @param target
     * @param metaData 
     */
    public MetaColumn(String type, String fieldName, String fieldLabel,boolean search, String target, MetaData metaData) {
        this.type = type;
        this.fieldName = fieldName;
        this.fieldLabel = fieldLabel;
        this.target = target;
        this.metaData = metaData;
        this.search = search;
    }
    
     public MetaColumn(MetaColumn column) {
        this.type = column.type;
        this.fieldName = column.fieldName;
        this.fieldLabel = column.fieldLabel;
        this.target = column.target;
        this.metaData = null;
        this.search = column.search;
        this.value = column.value;
        this.states = column.states;
        this.optional = column.optional;
        this.unique = column.unique;
        this.updatable = column.updatable;
        this.editable=column.editable;
        this.hide = column.hide;
        this.min = column.min;
        this.max = column.max;
        this.pattern = column.pattern;
        this.sequence = column.sequence;
        this.colsequence = column.colsequence;
        this.searchfields = column.searchfields;
        this.customfooter = column.customfooter;
        this.footerScript = column.footerScript;
    }

    public short getSequence() {
        return sequence;
    }

    public void setSequence(short sequence) {
        this.sequence = sequence;
    }

    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }    
    

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String values) {
        this.value = values;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        if(min<=0){
            return ;
        }
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        if(max<=0){
            return ;
        }
        this.max = max;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isUpdatable() {
        return updatable;
    }

    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    public short getColsequence() {
        return colsequence;
    }

    public void setColsequence(short colsequence) {
        this.colsequence = colsequence;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public String[] getSearchfields() {
        return searchfields;
    }

    public void setSearchfields(String[] searchfields) {
        this.searchfields = searchfields;
    }

    public String[] getStates() {
        return states;
    }

    public void setStates(String[] states) {
        this.states = states;
    }

    public boolean isCustomfooter() {
        return customfooter;
    }

    public void setCustomfooter(boolean customfooter) {
        this.customfooter = customfooter;
    }

    public String getFooterScript() {
        return footerScript;
    }

    public void setFooterScript(String footerScript) {
        this.footerScript = footerScript;
    }

    
   
    @Override
    public String toString() {
        return "MetaColumn{" + "type=" + type + ", search=" + search + ", fieldName=" + fieldName + ", fieldLabel=" + fieldLabel + ", target=" + target + ", values=" + value + ", metaData=" + metaData + '}';
    }
    
    
    
    @Override
    public int compareTo(MetaColumn o) {
        return fieldName.compareTo(o.fieldName); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        MetaColumn column = new MetaColumn(this);
        if(metaData!=null){
            column.setMetaData((MetaData) metaData.clone());
        }
        return column; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
