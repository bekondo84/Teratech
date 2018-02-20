/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.gwtext.client.core.EventObject;

/**
 * Listener pour object Kaban
 * @author Commercial_2
 */
public interface KabanListener {
    
    /**
     * 
     * @param row : index de l'element selectionnée
     * @param e : 
     */
    public void onClick(int row , EventObject e);
}
