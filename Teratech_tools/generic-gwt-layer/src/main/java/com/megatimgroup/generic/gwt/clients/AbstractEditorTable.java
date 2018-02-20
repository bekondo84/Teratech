/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.event.ContainerListenerAdapter;
import com.gwtext.client.widgets.grid.EditorGridPanel;

/**
 *
 * @author Commercial_2
 */
public  class AbstractEditorTable extends EditorGridPanel{

    //Model de données
    protected AbstractTableModel model ;
    
    
   
    /**
     * 
     */
    public AbstractEditorTable() {
      //  
      this.setFrame(true);
        
      this.setStripeRows(true);
      
      this.setClicksToEdit(1);
      
     
      
    }
    
    /**
     * 
     * @param model 
     */
     public AbstractEditorTable(AbstractTableModel model) {
      //  
      this.model = model ;
      this.setFrame(true);        
      this.setStripeRows(true);
      this.setClicksToEdit(1);
      this.setTopToolbar(getToolsBar());
      if(model!=null){
            this.setStore(model.getStore());
            this.setColumnModel(model.getColumnModel());   
            this.setSelectionModel(model.getCbSelectionModel());      
//            if(!model.getDatas().isEmpty())
        }
      this.addListener(new ContainerListenerAdapter(){

          @Override
          public boolean doBeforeDestroy(Component component) {
              //To change body of generated methods, choose Tools | Templates.
             MessageBox.alert("WARNING", "Record ======= ");
             return true;
          }
            
      });
    }

      /**
     * 
     * @param model 
     * @param actionbar 
     */
     public AbstractEditorTable(AbstractTableModel model , boolean actionbar) {
     
      this.model = model ;
      this.setFrame(true);        
      this.setStripeRows(true);
      this.setClicksToEdit(1);
      if(model!=null){
            this.setStore(model.getStore());
            this.setColumnModel(model.getColumnModel());
            this.setTopToolbar(getToolsBar());
//            this.getStore().load();            
         
        }
      this.addListener(new ContainerListenerAdapter(){

          @Override
          public boolean doBeforeDestroy(Component component) {
              //To change body of generated methods, choose Tools | Templates.
             MessageBox.alert("WARNING", "Record ======= ");
             return true;
          }
            
      });
     
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
                addEmptyLine();
            }
             
        });
        ToolbarButton btDelete = new ToolbarButton("Supprimer", new ButtonListenerAdapter(){

            @Override
            public void onClick(Button button, EventObject e) {
                //To change body of generated methods, choose Tools | Templates.
                super.onClick(button, e); 
                deleteLine();
            }
             
        });
        bar.addButton(btAdd);
        bar.addButton(btDelete);
        return bar ;
    }
    
    /**
     * Mise a jour d'un ligne
     */
    private void addEmptyLine(){
       
        Record firstrecord =  null ;
        if(getStore().getCount()>0){
           firstrecord = getStore().getAt(0);
        }
        if(lineValidator(firstrecord)){
            Record record = model.getRecordDef().createRecord(model.getEmptyData());
            this.stopEditing();
            getStore().insert(0, record);
            this.startEditing(0, 0); 
            
            
        }else{
            MessageBox.alert("WARNING", "Veuillez saisir les champs manquants");
        }
    }
    
    /**
     * Override to implements the correct validator 
     * @param record
     * @return 
     */
    protected boolean lineValidator(Record record){
        return true ;
    }
     /**
     * Mise a jour d'un ligne
     */
    private void deleteLine(){
       
        Record[] records = null ;
        records = model.getCbSelectionModel().getSelections();
        this.stopEditing();
        
        if(records!=null){
            for(Record record : records){
                getStore().remove(record);
//                model.getDatas().remove(model.getData(record));
                Object value = record.getAsObject("value");
                model.getDatas().remove(value);
            }
        
        }
        this.startEditing(0, 0);
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
//            this.getStore().load();            
         
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
    
    private Record currentRecord = null ;
    
    
}
