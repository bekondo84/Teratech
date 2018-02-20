/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtextux.client.data.PagingMemoryProxy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Commercial_2
 */
public abstract class AbstractTableModel {

    //Liste des données
    private List datas = new ArrayList();
    
    protected CheckboxSelectionModel cbSelectionModel;
    
    protected KabanListener listener ;
    
    protected boolean selected = false ;
    /**
     * Construction par defaut
     * 
     */
    public AbstractTableModel() {
    }
    
    /**
     * Retoourn le model des Colonnes
     * @return 
     */
    public ColumnModel getColumnModel(){
        return new ColumnModel(getColumnConfigs());
    }
    
    /***
     * Generate Store
     * @return 
     */
    public Store getStore(){       
        
        ArrayReader reader = new ArrayReader(getRecordDef());
        
         if(datas==null||datas.isEmpty()){
             return new Store(reader);
         }
        PagingMemoryProxy  proxy = new PagingMemoryProxy (getRows());
        
        Store store =  new Store(proxy , reader, true);
        
        store.load();
        
        return store ;
    }
    
    /**
     * Retourne la liste des données
     * @return 
     */
    public  Object[][] getRows(){
        //
        if(datas==null||datas.isEmpty()) return null ;
        Object[][] vector = new Object[datas.size()][];
        
        int index = 0 ;
        
        for(Object obj : datas){
            vector[index] = getData(obj);
            index= index+1;
        }
//        MessageBox.alert("Refresh", "TPAPAPAPAPAPA : "+vector);    
        return vector;
    }

    public CheckboxSelectionModel getCbSelectionModel() {
        
        if(cbSelectionModel==null){
            cbSelectionModel = new CheckboxSelectionModel();
        }
        return cbSelectionModel;
    }
    
    /**
     * Retourn une liste de Panel 
     * @return 
     */
    protected KabanPanel[] getKabanComponents(){
        return null;
    }
    /**
     * Renvoi la liste des valeur correspondant
     * @param data
     * @return 
     */
    protected abstract Object[] getData(Object data);
    
    /**
     * Return an empty Row
     * @return 
     */
    protected Object[] getEmptyData(){
        return null;
    }
    
    /**
     * Convertir un record en object 
     * @param record
     * @return 
     */
    protected Object getData(Record record){
        
        return null ;
    }
    /**
     * Renvoie la liste de configuration des colonnes de
     * notre Tableau
     * @return 
     */
    protected abstract BaseColumnConfig[] getColumnConfigs();
    
    /**
     * Construit La structure de definition 
     * @return 
     */
    protected abstract RecordDef getRecordDef();

    /**
     * 
     * @return 
     */
    public List getDatas() {
        return datas;
    }

    /**
     * 
     * @param datas 
     */
    public void setDatas(List datas) {
        
        if(datas == null){
            return ;
        }
        this.datas = datas;
        //Mise a jour des lignes        
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setListener(KabanListener listener) {
        this.listener = listener;
    }
    
    
}
