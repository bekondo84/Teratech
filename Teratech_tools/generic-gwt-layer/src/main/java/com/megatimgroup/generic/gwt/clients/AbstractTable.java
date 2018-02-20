/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.grid.GridPanel;

/**
 *
 * @author Commercial_2
 */
public  class AbstractTable extends GridPanel{

    //Model de données
    protected AbstractTableModel model ;
    
    
   
    /**
     * 
     */
    public AbstractTable() {
      //  
      this.setFrame(true);
        
      this.setStripeRows(true);
      
      
    }
    
    /**
     * 
     * @param model 
     */
     public AbstractTable(AbstractTableModel model) {
      //  
      this.model = model ;
      this.setFrame(true);        
      this.setStripeRows(true);
      if(model!=null){
            this.setStore(model.getStore());
            this.setColumnModel(model.getColumnModel());
            this.getStore().load(0, 5);         
        }
      
    }

      /**
     * 
     * @param model 
     * @param actionbar 
     */
     public AbstractTable(AbstractTableModel model , boolean actionbar) {
      //  
      this.model = model ;
      this.setFrame(true);        
      this.setStripeRows(true);
      if(model!=null){
            this.setStore(model.getStore());
            this.setColumnModel(model.getColumnModel());
//            this.setTopToolbar(getToolsBar());
            this.getStore().load(0, 5);            
         
        }
      
    }
    /**
     * Construction du pied de table
     * @return 
     */
    private Toolbar getToolsBar(){
        
        Toolbar bar = new Toolbar();
        
        ToolbarButton btAdd = new ToolbarButton("Ajouter", new ButtonListenerAdapter(){

            @Override
            public void onClick(Button button, EventObject e) {
                //To change body of generated methods, choose Tools | Templates.
                super.onClick(button, e); 
            }
             
        });
        return bar ;
    }
    /**
     * Model de la 
     * @return 
     */
    public AbstractTableModel getModel() {
        return model;
    }

    /**
     * Setter du model
     * @param model 
     */
    public void setModel(AbstractTableModel model) {
        this.model = model;        
        if(model!=null){
            this.setStore(model.getStore());
            this.setColumnModel(model.getColumnModel());
//            this.setBottomToolbar(getBottomToolsBar());
            this.getStore().load(0, 5);            
         
        }
    }
    
    /**
     * Fenetre modale de creation d'un nouveau element
     * @return 
     */
    protected  AbstractEditWindow getEditWindow(){
        return null;
    }
//    private abstract 
    
}
