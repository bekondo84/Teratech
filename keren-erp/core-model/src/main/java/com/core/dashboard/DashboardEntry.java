/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.dashboard;

import com.core.base.BaseElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Commercial_2
 */
public class DashboardEntry extends BaseElement implements Serializable,Comparable<DashboardEntry>{

    private String code ;
    
    private String type; 
    
    private String label ;
    
    private List<DashboardField> fields = new ArrayList<DashboardField>();

    /**
     * 
     * @param code
     * @param type 
     * @param label 
     */
    public DashboardEntry(String code, String type,String label) {
        this.code = code;
        this.type = type;
        this.label = label;
    }

    /**
     * 
     * @param code
     * @param type
     * @param id
     * @param designation
     * @param moduleName 
     */
    public DashboardEntry(String code, String type, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.code = code;
        this.type = type;
    }
    
    public DashboardEntry(DashboardEntry entry) {
        super(entry.id, entry.designation, entry.moduleName);
        this.code = entry.code;
        this.type = entry.type;
        this.label = entry.label;
        
        this.fields = entry.getFields();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DashboardField> getFields() {
        return fields;
    }

    public void setFields(List<DashboardField> fields) {
        this.fields = fields;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
    
    
    @Override
    public int compareTo(DashboardEntry o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
