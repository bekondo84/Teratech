/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.model.test;

import com.megatim.security.enumerations.EnumStatutAutorisation;
import com.megatim.security.model.Autorisation;
import java.util.List;

/**
 *
 * @author DEV_4
 */
public abstract class MenuComponent implements  Comparable<MenuComponent>{
    
    private String name ;
    private Object value ;
    private Autorisation autorisation ;
    

    /**
     * 
     */
    public MenuComponent() {
    }

    /**
     * 
     * @param name
     * @param value 
     */
    public MenuComponent(String name, Object value) {
        this.name = name;
        this.value = value;
    }
    
    public MenuComponent(String name, Object value, Autorisation state) {
        this.name = name;
        this.value = value;
        this.autorisation = state;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Autorisation getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(Autorisation autorisation) {
        this.autorisation = autorisation;
    }
    
    
    @Override
    public int compareTo(MenuComponent o) {
       return name.compareTo(o.name); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public abstract boolean isLeaf();
    
    public abstract List<MenuComponent> subList();

    @Override
    public String toString() {
        return name ;
    }
    
    
    
}
