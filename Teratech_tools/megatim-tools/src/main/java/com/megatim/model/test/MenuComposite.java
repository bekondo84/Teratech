/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.model.test;

import com.megatim.security.enumerations.EnumStatutAutorisation;
import com.megatim.security.model.Autorisation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DEV_4
 */
public class MenuComposite extends MenuComponent implements Serializable{

    
    private List<MenuComponent> elements = new ArrayList<MenuComponent>();
    
    /**
     * 
     */
    public MenuComposite() {
    }

    
    /**
     * 
     * @param name
     * @param value 
     */
    public MenuComposite(String name, Object value) {
        super(name, value);
    }
    
    public MenuComposite(String name, Object value, Autorisation state) {
        super(name, value, state);
    }

    public List<MenuComponent> getElements() {
        return elements;
    }

    public void setElements(List<MenuComponent> elements) {
        this.elements = elements;
    }

    @Override
    public boolean isLeaf() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MenuComponent> subList() {
        //To change body of generated methods, choose Tools | Templates.
        return elements;
    }
    
    
    
}
