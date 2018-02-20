/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Panel;


/**
 *Implementation de la fenetre principal
 * @author Bekondo K D
 */
public abstract class Principal_1 extends DockPanel{

    
    //Entête de la page
    protected Panel headerPanel ;
    
    //Pied de Page
    protected Panel footerPanel ;
    
    //Panel de contenu
    protected Panel midllePanel ;
    
    //Panel des options
    protected Panel optionsPanel ;
    
    /**
     * Constructeur
     */
    public Principal_1() {
        
        super();
        
        viewBuilder();
    }
    
    /**
     * Construction de la Vue
     */
    private void viewBuilder(){
        
        this.setBorderWidth(5);
        
        this.setSize("100%", "100%");        
       
        //Ajout du panel Nord
        this.add(getHeaderPanel(),DockPanel.NORTH);
        
        this.setCellHeight(getHeaderPanel(), "45px");
        
        this.add(getFooterPanel(), DockPanel.SOUTH);
        
        this.setCellHeight(getFooterPanel(), "25px");        
       
        //Creation Horizontal
        HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        splitPanel.setSplitPosition("200px");
        splitPanel.setLeftWidget(getOptionsPanel());
        splitPanel.setRightWidget(getMiddlePanel());
        //this.add(getOptionsPanel(), DockPanel.WEST);
        //this.setCellHeight(getOptionsPanel(), "150px");
        
        this.add(splitPanel, DockPanel.CENTER);        
        //this.setCellHeight(splitPanel, "250px");
        
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
    public abstract Panel getMiddlePanel();
}
