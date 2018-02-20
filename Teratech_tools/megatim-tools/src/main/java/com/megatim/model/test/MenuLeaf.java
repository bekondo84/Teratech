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
public class MenuLeaf extends MenuComponent{

    public MenuLeaf() {
    }

    public MenuLeaf(String name, Object value) {
        super(name, value);
    }
    
    public MenuLeaf(String name, Object value, Autorisation state) {
        super(name, value, state);
    }
    
    @Override
    public boolean isLeaf() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MenuComponent> subList() {
       //To change body of generated methods, choose Tools | Templates.
        return null;
    }
    
    
}
