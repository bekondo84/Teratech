/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.google.gwt.core.client.JavaScriptObject;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;

/**
 *
 * @author Commercial_2
 */
public abstract class AbstractEditWindow extends Window{

    /**
     * 
     */
    public AbstractEditWindow() {
        
        super();
        
    }

    /**
     * 
     * @param title 
     */
    public AbstractEditWindow(String title) {
        super(title);
    }

    /**
     * 
     * @param title
     * @param width
     * @param height 
     */
    public AbstractEditWindow(String title, int width, int height) {
        super(title, width, height);
    }

    /**
     * 
     * @param title
     * @param modal
     * @param resizable 
     */
    public AbstractEditWindow(String title, boolean modal, boolean resizable) {
        super(title, modal, resizable);
    }

    /**
     * 
     * @param title
     * @param width
     * @param height
     * @param modal
     * @param resizable 
     */
    public AbstractEditWindow(String title, int width, int height, boolean modal, boolean resizable) {
        super(title, width, height, modal, resizable);
    }

    /**
     * 
     * @param jsObj 
     */
    public AbstractEditWindow(JavaScriptObject jsObj) {
        super(jsObj);
    }
    
    /**
     * 
     */
    private void buildComponents(){
        
        this.setLayout(new FitLayout());
        
        Panel borderPanel = new Panel();
        
        borderPanel.setLayout(new BorderLayout());
        
        Panel footerPanel = new Panel();
        
         int width = (int) (com.google.gwt.user.client.Window.getClientWidth() - com.google.gwt.user.client.Window.getClientWidth()*0.13) ;
        
        int heigth = com.google.gwt.user.client.Window.getClientHeight() - 72;
        
        footerPanel.setBorder(false);
//        footerPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
        footerPanel.setLayout(new HorizontalLayout(10));
        footerPanel.setBodyStyle("background-color:#FFFF88");
        Panel btPanel = new Panel();
        btPanel.setBorder(false);
        btSave = new CustomButton("Enregistrer");        
        btSave.setSize("90", "30");        
        btPanel.add(btSave);
        btPanel.setMargins(5, width-220, 5, 5);
        footerPanel.add(btPanel);       
        
        btCancel = new CustomButton("Quitter");
        btCancel.setSize("90", "30");
        btPanel = new Panel();
        btPanel.setBorder(false);
        btPanel.add(btCancel);
        btPanel.setMargins(5, 10, 5, 5);
        footerPanel.add(btPanel);
         
        BorderLayoutData southData = new BorderLayoutData(RegionPosition.NORTH);
        southData.setMinSize(35);
        footerPanel.setHeight(40);
        borderPanel.add(footerPanel,southData);
        
        borderPanel.add(getContentPanel(), new BorderLayoutData(RegionPosition.CENTER));
        
        this.add(borderPanel);
        
        this.setSize(width, heigth);
    }
    
    /**
     * Renvoie le panel contenant les données
     * @return 
     */
    protected abstract Panel getContentPanel();
    
    
    protected Panel content ;
    
    protected CustomButton btSave ;
    
    protected CustomButton btCancel;
    
}
