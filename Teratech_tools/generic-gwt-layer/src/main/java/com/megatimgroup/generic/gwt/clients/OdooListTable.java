/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.PagingToolbar;
import com.gwtext.client.widgets.ToolTip;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.event.FieldListenerAdapter;
import com.gwtext.client.widgets.grid.GridPanel;

/**
 *
 * @author Commercial_2
 */
public  class OdooListTable extends GridPanel{

    //Model de données
    protected AbstractTableModel model ;
    
   
    /**
     * 
     */
    public OdooListTable() {
      //  
      this.setFrame(true);
        
      this.setStripeRows(true);      
      
    }
    
    /**
     * 
     * @param model
     */
    public OdooListTable(AbstractTableModel model) {
      this.model = model ;
      this.setFrame(true);        
      this.setStripeRows(true); 
      if(model!=null){
        if(model.selected){
            this.setSelectionModel(model.getCbSelectionModel());
        }
        this.setStore(model.getStore());
        this.setColumnModel(model.getColumnModel());
//            this.setBottomToolbar(getBottomToolsBar());
//        this.getStore().load(0, 5);        
      }
    }

    /**
     * Construction du pied de table
     * @return 
     */
    private PagingToolbar getBottomToolsBar(){
        
        final PagingToolbar pagingToolbar = new PagingToolbar(model.getStore());
        pagingToolbar.setPageSize(5);  
        pagingToolbar.setDisplayInfo(true);  
        pagingToolbar.setDisplayMsg("Displaying companies {0} - {1} of {2}");  
        pagingToolbar.setEmptyMsg("No records to display");  
  
        NumberField pageSizeField = new NumberField();  
        pageSizeField.setWidth(40);  
        pageSizeField.setSelectOnFocus(true);  
        pageSizeField.addListener(new FieldListenerAdapter() {  
            @Override
            public void onSpecialKey(Field field, EventObject e) {  
                if (e.getKey() == EventObject.ENTER) {  
                    int pageSize = Integer.parseInt(field.getValueAsString());  
                    pagingToolbar.setPageSize(pageSize);  
                }  
            }  
        });  
  
        ToolTip toolTip = new ToolTip("Enter page size");  
        toolTip.applyTo(pageSizeField); 
          
        pagingToolbar.addField(pageSizeField);  
//        grid.setBottomToolbar(pagingToolbar);  
//        store.load(0, 5);  
        return pagingToolbar;
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
            if(model.selected){
                this.setSelectionModel(model.getCbSelectionModel());
            }
            this.setStore(model.getStore());
            this.setColumnModel(model.getColumnModel());
//            this.setBottomToolbar(getBottomToolsBar());
            this.getStore().load();  
        }
//        this.getUpdateManager().refresh();
    }
    
    
//    private abstract 
    
}
