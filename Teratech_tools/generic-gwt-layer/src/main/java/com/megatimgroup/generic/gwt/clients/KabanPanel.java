/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.FitLayout;
import java.io.Serializable;

/**
 *
 * @author Commercial_2
 * @param <T>
 */
public abstract class KabanPanel <T extends Serializable> extends Panel{

    /**
     * 
     * @param currentObject
     * @param row
     */
    public KabanPanel(T currentObject , int row) {
        
        super();
        
        this.setBorder(false);
        
        this.setLayout(new FitLayout());
        
        this.index = row ;
        
        btaction = new Button("Consulter");
        
        btaction.addListener(new ButtonListenerAdapter(){

            @Override
            public void onClick(Button button, EventObject e) {
                //To change body of generated methods, choose Tools | Templates.
                if(listener!=null){
                    listener.onClick(index, e);
                }
            }
        
        });
        
        this.setBottomToolbar(btaction);
        
        this.currentObject = currentObject;
        
        this.add(getContentPanel());
    }

    
    /**
     * 
     * @return 
     */
    public T getCurrentObject() {
        return currentObject;
    }

    /**
     * Listener
     * @param listener 
     */
    public void setListener(KabanListener listener) {
        this.listener = listener;
    }

    
    
    /**
     * Contenu de Kaban
     * @return 
     */
    protected abstract Panel getContentPanel();
    
    
    protected T currentObject ;
    
    protected Panel southPanel;
    
    protected Button btaction;
    
    protected KabanListener listener ;
    
    protected  int index ;
    
}
