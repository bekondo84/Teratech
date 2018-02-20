/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.gwtext.client.widgets.PaddedPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.TableLayout;

/**
 *
 * @author Commercial_2
 */
public class OdooKabanTable extends Panel{

    public OdooKabanTable(AbstractTableModel model) {
        super();
        this.model =model ;
//        this.model.setListener(listener);
        buildComponents(model);
    }

   
    /**
     * 
     */
    private void buildComponents(AbstractTableModel model){
        
        this.setLayout(new FitLayout());
        
        wrapperPanel = new Panel();
        wrapperPanel.setAutoScroll(true);  
        wrapperPanel.setBorder(true);  
//        wrapperPanel.setBodyStyle("border-style:dotted;border-color:blue;");  
        wrapperPanel.setLayout(new TableLayout(3));  
        //this add a blue background to the panel content area  
        wrapperPanel.setBaseCls("x-plain");          
        if(model!=null&&model.getKabanComponents()!=null&&model.getKabanComponents().length>0){
            for(KabanPanel p : model.getKabanComponents()){
                wrapperPanel.add(new PaddedPanel(p,0,0,10,10));
            }
        }
        this.add(wrapperPanel);
    }

    /**
     * 
     * @return 
     */
    public AbstractTableModel getModel() {
        return model;
    }

    
    
   
    
    
    //Model de données
    protected AbstractTableModel model ;
    
    protected Panel wrapperPanel ;
    
    protected KabanListener listener ;
    
}
