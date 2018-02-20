/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.QuickTipsConfig;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.ToolbarMenuButton;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.WaitConfig;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.menu.BaseItem;
import com.gwtext.client.widgets.menu.Menu;

/**
 *
 * @author Commercial_2
 */
public abstract class ScreenTemplate extends Composite{

    
    protected Panel northPanel ;
    
    protected Panel southPanel ;
    
    protected HorizontalSplitPanel centerPanel ;
    
    protected Panel westPanel;
    
    protected final Panel panel ;
    
    protected Toolbar menuBar ;
    
    protected ToolbarMenuButton btAdmin ;
    /**
     * 
     */
    public ScreenTemplate() {
        
        showProgress();
        panel = new Panel();  
        panel.setBorder(false);  
//        panel.setPaddings(15);  
        panel.setLayout(new FitLayout());  
        panel.setTopToolbar(getMenuBar());
        Panel borderPanel = new Panel();  
        borderPanel.setLayout(new BorderLayout());  
  
//        //add north panel  
//        northPanel = new Panel();  
////        northPanel.setHtml("<p>north panel</p>");  
//        northPanel.setHeight(32);  
//        northPanel.setTopToolbar(getMenuBar());
//        borderPanel.add(northPanel, new BorderLayoutData(RegionPosition.NORTH));  
//        
        //add south panel  
        southPanel = new Panel();  
        southPanel.setHtml("<p>south panel</p>");  
        southPanel.setHeight(25);  
        southPanel.setBorder(false);
        southPanel.setBodyStyle("background-color:#CDEB8B");  
//        southPanel.setCollapsible(true);  
//        southPanel.setTitle("South");  
  
//        BorderLayoutData southData = new BorderLayoutData(RegionPosition.SOUTH);  
//        southData.setMinSize(30);  
//        southData.setMaxSize(200);  
//        southData.setMargins(new Margins(0, 0, 0, 0));  
//        southData.setSplit(true);  
        borderPanel.add(southPanel, new BorderLayoutData(RegionPosition.SOUTH));  
  
        //add east panel  
//        eastPanel = new Panel();  
//        eastPanel.setHtml("<p>east panel</p>");  
//        eastPanel.setTitle("East Side");  
//        eastPanel.setCollapsible(true);  
//        eastPanel.setWidth(225);  
//  
//        BorderLayoutData eastData = new BorderLayoutData(RegionPosition.EAST);  
//        eastData.setSplit(true);  
//        eastData.setMinSize(175);  
//        eastData.setMaxSize(400);  
//        eastData.setMargins(new Margins(0, 0, 5, 0));  
//  
//        borderPanel.add(eastPanel, eastData);  
//  
//        westPanel = new Panel();  
//        westPanel.setHtml("<p>west panel</p>");  
//        westPanel.setTitle("West");  
//        westPanel.setBodyStyle("background-color:#EEEEEE");  
//        westPanel.setCollapsible(true);  
//        westPanel.setWidth(200);  
//  
//        BorderLayoutData westData = new BorderLayoutData(RegionPosition.WEST);  
//        westData.setSplit(true);  
//        westData.setMinSize(175);  
//        westData.setMaxSize(400);  
//        westData.setMargins(new Margins(0, 5, 0, 0));  
//  
//        borderPanel.add(westPanel, westData);  
  
        centerPanel = new HorizontalSplitPanel();  
        centerPanel.setSplitPosition("12%");
//        centerPanel.set
        centerPanel.setLeftWidget(new Panel("Navigation"));
        centerPanel.setRightWidget(new Panel("Contenue"));
//        centerPanel.setHtml("<p>center panel</p>");  
//        centerPanel.setBodyStyle("background-color:#C3D9FF");  
        borderPanel.add(centerPanel, new BorderLayoutData(RegionPosition.CENTER));  
  
        panel.add(borderPanel);  
        
        Viewport viewport = new Viewport(panel);  
        
        initWidget(panel);
        
//        RootLayoutPanel.get().add(panel);
    }

    
//    /**
//     *
//     * @param widget
//     */
//    public void setHeader(Panel widget) {
//
//        centerPanel.setRightWidget(new Panel("BLBLBLBLBLBLBLBLBLBLBLBLBLB"));
//
////        widget
////        widget.setHeight(30);
////        northPanel.add(widget);
////        widget.setVisible(true);
//    }

    /**
     * Affiche le panel dans la zone
     * @param panel 
     */
    public void setContentPanel(Panel panel) {
//        Panel contener = new Panel();
//        contener.setLayout(new FitLayout());
//        contener.add(panel);
        this.centerPanel.setRightWidget(panel);
    }
    
    /**
     * Constraction de la liste des modules installes
     * @return 
     */
    protected abstract void loadModules();
    
    /**
     * Implementation de la logique
     */
    protected abstract void applicationsActionPerformed();
    
    protected abstract void configurationActionPerformed();
    
    /**
     * Show the progress bar
     */
    protected void showProgress(){
        
        MessageBox.show(new MessageBoxConfig(){
                {   setMsg("Saving your data, please wait...");  
                        setProgressText("Saving...");  
                        setWidth(300);  
                        setWait(true);  
                        setWaitConfig(new WaitConfig() {  
                            { 
                                setInterval(200);  
                            }  
                        });  
//                        setAnimEl(button.getId()); 
               }
        });
    }
    
    
    protected void hideProgress(){
        MessageBox.hide();
    }
    /**
     * 
     * @return 
     */
    protected Menu adminMenu(){
        
        Menu menu = new Menu();
        
        BaseItem item = new BaseItem();
        item.setTitle("Deconnexion");
        menu.addItem(item);
        
        return menu;
    } 
    
    /**
     * 
     * @param modules 
     */
    protected  void displayModules(ToolbarButton[] modules){
        if(modules!=null&&modules.length>0){
             for(ToolbarButton module : modules){
                 menuBar.addButton(module);
             }
         }
         //headerPanel.add(menuBar2);
            ToolbarButton item_1 = new ToolbarButton();
            item_1.setText("Applications");
            item_1.addListener(new ButtonListenerAdapter(){

                @Override
                public void onClick(Button button, EventObject e) {
                    //To change body of generated methods, choose Tools | Templates.                    
                     applicationsActionPerformed();
//                    midllePanel.setVisible(true);
                    
                }
                
            });
            QuickTipsConfig tipconf = new QuickTipsConfig();
            tipconf.setText("This is the administrator Menu");
            item_1.setTooltip(tipconf);
            ToolbarButton item_2 = new ToolbarButton();
            item_2.setText("Configuration");
            item_2.addListener(new ButtonListenerAdapter(){

            @Override
            public void onClick(Button button, EventObject e) {
                 //To change body of generated methods, choose Tools | Templates.
               
                configurationActionPerformed();
            }
                
            });
            menuBar.addButton(item_1);
            menuBar.addButton(item_2);
            menuBar.addFill();   
            btAdmin = new ToolbarMenuButton("Administration");
//            btAdmin.setMenu(adminMenu());
            menuBar.addButton(btAdmin);
    }
    /**
     * Renvoie la barre d'outil
     * @return 
     */
    public  Toolbar getMenuBar(){
         //To change body of generated methods, choose Tools | Templates.
         menuBar = new Toolbar();  
         //Ajoutes des modules deja installées
         loadModules();         
        return menuBar;
    }
    
}
