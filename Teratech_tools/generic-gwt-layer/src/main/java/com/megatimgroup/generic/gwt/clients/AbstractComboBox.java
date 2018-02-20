/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.form.ComboBox;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Commercial_2
 * @param <T>
 */
public abstract class AbstractComboBox<T extends Serializable> extends ComboBox{

    
    /**
     * 
     * @param fieldLabel 
     */
    public AbstractComboBox(String fieldLabel) {
        super(fieldLabel);
         buildComponents();
    }

    /**
     * 
     * @param fieldLabel
     * @param name 
     */
    public AbstractComboBox(String fieldLabel, String name) {
        super(fieldLabel, name);
         buildComponents();
    }

    /**
     * 
     * @param fieldLabel
     * @param name
     * @param width 
     */
    public AbstractComboBox(String fieldLabel, String name, int width) {
        super(fieldLabel, name, width);
        buildComponents();
    }

    
    /**
     * 
     */
    private void buildComponents(){
        this.setMinChars(1);  
        this.setFieldLabel(getFieldLabel()); 
        ArrayReader reader = new ArrayReader(getRecordDef());
        Store store = new Store(reader);
        this.setStore(store);  
        this.setDisplayField(getDisplayField());  
        this.setMode(ComboBox.LOCAL);  
        this.setTriggerAction(ComboBox.ALL);  
        this.setEmptyText("Enter company");  
        this.setLoadingText("Searching...");  
        this.setTypeAhead(true);  
        this.setSelectOnFocus(true);  
        this.setWidth(250);  
        this.setPageSize(10);  
    }
    
    /**
     * 
     * @return 
     */
    protected abstract String getDisplayField();
    /**
     * 
     * @return 
     */
    protected abstract String[] getColomns();
    
    /**
     * 
     * @param datas
     * @return 
     */
    protected abstract void setDatas(List<T> datas);
    
    /**
     * 
     * @return 
     */
    protected abstract RecordDef getRecordDef();
    
    /**
     * 
     * @param data
     * @return 
     */
    protected abstract Object[] convert( T data);
}
