/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Commercial_2
 * @param <T>
 */
public abstract class AbstractCellTable<T extends Serializable> extends Panel{

    /**
     * 
     */
    public AbstractCellTable() {
        buildComponent();
    }

    /**
     * 
     * @param title 
     */
    public AbstractCellTable(String title) {
        super(title);
        buildComponent();
    }

    /**
     * 
     * @param title
     * @param width
     * @param height 
     */
    public AbstractCellTable(String title, int width, int height) {
        super(title, width, height);
        buildComponent();
    }
    
    /**
     * 
     */
    protected void buildComponent(){
        this.setLayout(new FitLayout());
        this.setTopToolbar(getHeaderToolBar());
       
        //Construction du table
        dataGrid = new DataGrid();
        dataGrid.setWidth("100%");
        /*
        * Do not refresh the headers every time the data is updated. The footer
        * depends on the current data, so we do not disable auto refresh on the
        * footer.
        */
       dataGrid.setAutoHeaderRefreshDisabled(true);

        //Creation du DataProvider
        dataProvider = new ListDataProvider<T>();
        dataProvider.addDataDisplay(dataGrid);
        datas = dataProvider.getList();
         // Attach a column sort handler to the ListDataProvider to sort the list.
        sortHandler = new ListHandler<T>(datas);
        dataGrid.addColumnSortHandler(sortHandler);
         // Add a selection model so we can select cells.
        final SelectionModel<T> selectionModel = new MultiSelectionModel<T>();
        dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager
            .<T> createCheckboxManager());
         //Column Update
       for(TableColumn col : getColumnModel(selectionModel,sortHandler)){
           dataGrid.addColumn(col.getColumn(),col.getColName());
       }
        this.add(dataGrid);
    }
    
    /**
     * Build the column model data 
     * for the table
     * @param selectionModel
     * @param sorthandler
     * @return 
     */
    protected abstract List<TableColumn> getColumnModel(SelectionModel<T> selectionModel,
           ListHandler<T> sorthandler);

    /**
     * 
     * @param datas 
     */
    public void setDatas(List<T> datas) {
       
        dataProvider.setList(datas);
//         dataProvider.addDataDisplay(dataGrid);
        this.datas = dataProvider.getList();
        sortHandler.setList(datas);
        dataProvider.refresh();
       
    }
    
    public void addData(T data){
//        this.datas = dataProvider.getList();
        datas.add(0, data);
//        dataProvider.setList(datas);
//        sortHandler.setList(datas);
        dataProvider.refresh();
    }
    
    /**
     * 
     * @param data
     * @param index 
     */
    public void remodeData(T data){
//        this.datas = dataProvider.getList();
        datas.remove(data);
//        dataProvider.setList(datas);
//       sortHandler.setList(datas);
        dataProvider.refresh();
    }
    /**
     * 
     * @param data 
     */
    public void removeData(List<T> data){
//        this.datas = dataProvider.getList();
        datas.removeAll(data);
//        dataProvider.setList(datas);
        dataProvider.refresh();
    }
    
    /**
     * Sous classe de column
     */
    public class TableColumn{
        
        private String colName ;
        
        private Column column ;

        /**
         * 
         * @param colName
         * @param column 
         */
        public TableColumn(String colName, Column column) {
            this.colName = colName;
            this.column = column;
        }

        public String getColName() {
            return colName;
        }

        public void setColName(String colName) {
            this.colName = colName;
        }

        public Column getColumn() {
            return column;
        }

        public void setColumn(Column column) {
            this.column = column;
        }
        
        
    }
    /**
     * 
     */
    protected abstract void newAction();
    
     /**
     * 
     */
    protected abstract void removeAction();
    /**
     * 
     * @return 
     */
    protected Toolbar getHeaderToolBar(){
        Toolbar bar = new Toolbar();
        bar.addButton(new ToolbarButton("Ajouter",new ButtonListenerAdapter(){

            @Override
            public void onClick(Button button, EventObject e) {
                //To change body of generated methods, choose Tools | Templates.
                newAction();
            }
                   
        }));
        bar.addButton(new ToolbarButton("Supprimer",new ButtonListenerAdapter(){

            @Override
            public void onClick(Button button, EventObject e) {
                //To change body of generated methods, choose Tools | Templates.
                removeAction();
            }
                   
        }));
        return bar ;
    }
    
    protected DataGrid<T> dataGrid ;
    
    protected List<T> datas ;
    
    protected ListDataProvider<T> dataProvider =null ;
    
    protected ListHandler<T> sortHandler  = null;
}
