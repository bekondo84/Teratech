/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.jax.rs.layer.impl;

import java.io.Serializable;

/**
 *
 * @author Commercial_2
 */
public class State implements Serializable{
    
    private String code ;
    
    private String intitule;

    /**
     * 
     */
    public State() {
    }

    /**
     * 
     * @param code
     * @param intitule 
     */
    public State(String code, String intitule) {
        this.code = code;
        this.intitule = intitule;
    }
    
    /**
     * 
     * @param state 
     */
     public State(State state) {
        this.code = state.code;
        this.intitule = state.intitule;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    
    
}
