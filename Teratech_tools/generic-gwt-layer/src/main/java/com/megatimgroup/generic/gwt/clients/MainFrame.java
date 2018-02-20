/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Container;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 *
 * @author Commercial_2
 */
public abstract class MainFrame {

    
     //Entête de la page
    protected Panel headerPanel ;
    
    //Pied de Page
    protected Panel footerPanel ;
    
    //Panel de contenu
    protected Panel midllePanel ;
    
    //Panel des options
    protected Panel optionsPanel ;
    
    protected Panel borderPanel ;
    
    
    
    
    public MainFrame() {
        
         buildView();
        
    }

   
    
    
    public void buildView(){
        
//         RootPanel.get("HeadPanel").add(getHeaderPanel());         
//        
//         RootPanel.get("LeftPanel").add(getOptionsPanel());
//         
//         RootPanel.get("RightPanelCenter").add(getMiddlePanel());
//         
//         RootPanel.get("FooterPanel").add(getFooterPanel());
          Panel panel = new Panel();
        
        panel.setBorder(false);
        
        panel.setPaddings(15);
        
        panel.setLayout(new FitLayout());
        
        Panel borderPanel = new Panel();
        borderPanel.setLayout(new BorderLayout());
        
        //Add north panel
        borderPanel.add(getHeaderPanel(), new BorderLayoutData(RegionPosition.NORTH));
        
        BorderLayoutData southData = new BorderLayoutData(RegionPosition.SOUTH);
        southData.setMinSize(100);
        southData.setMaxSize(200);
        southData.setMargins(new Margins(0, 0, 0, 0));
        borderPanel.add(getFooterPanel(), southData);
        
        HorizontalSplitPanel centerPanel = new HorizontalSplitPanel();
        centerPanel.setSplitPosition("33%");
        //Extension d'un menu panel
        centerPanel.setLeftWidget(getOptionsPanel());
        centerPanel.setRightWidget(getMiddlePanel());        
        borderPanel.add(centerPanel, new BorderLayoutData(RegionPosition.CENTER));
        
        panel.add(borderPanel);
        
        Viewport viewPort = new Viewport(panel);
        
        RootPanel.get().add(panel);
    }
    
    /**
     * Renvoie l'entête de la page
     * @return 
     */
    public abstract Panel getHeaderPanel() ;
    
    /**
     * Renvoi le pied de page
     * @return 
     */
    public abstract Panel getFooterPanel();
    
    
    /**
     * 
     * @return 
     */
    public abstract Panel getOptionsPanel();
    
    /**
     * 
     * @return 
     */
    public abstract Container getMiddlePanel();
    
}
