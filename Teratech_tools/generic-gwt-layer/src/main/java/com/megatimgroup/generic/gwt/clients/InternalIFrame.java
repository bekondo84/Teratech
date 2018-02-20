/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.gwtext.client.core.Function;
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Tool;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;

/**
 *
 * @author Commercial_2
 */
public abstract class InternalIFrame extends Panel{

    public InternalIFrame() {
        super();
        buildView();
    }

    public InternalIFrame(String title) {
        super(title);
        buildView();
    }
  

    public InternalIFrame(JavaScriptObject jsObj) {
        super(jsObj);
        buildView();
    }

    public InternalIFrame(Element element) {
        super(element);
        buildView();
    }
    
    
    /**
     * Construction de la Fenetre
     */
    private void buildView(){
        
//         this.setBorder(false);  
//        this.setPaddings(15);  
        this.setLayout(new FitLayout()); 
        this.addTool(new Tool(Tool.CLOSE, new Function() {

                public void execute() {
                    //To change body of generated methods, choose Tools | Templates.
                    MessageBox.alert("Refresh", "The Refresh tool was clicked");  
                }
            }));
        //Create the Border Pane
        Panel borderPanel = new Panel();  
        borderPanel.setLayout(new BorderLayout());  
        //Ajout des 
        
        //Add north Panel
        borderPanel.add(getHeaderPanel(), new BorderLayoutData(RegionPosition.NORTH));
        
        //Add South Panel
//        borderPanel.add(getFooterPanel(), new BorderLayoutData(RegionPosition.SOUTH));       
        
//         //Add Center Panel       
        //Add Center Panel
        borderPanel.add(getContentPanel(), new BorderLayoutData(RegionPosition.CENTER));
        
        //Add the borderPanel in the panel
        this.add(borderPanel);
        //Set the VeiewPort
//        Viewport viewport = new Viewport(this);  
    }
    
      /**
        * Renvoie l'entête de la page
        * @return 
        */
       public  Panel getHeaderPanel() {
           
            if(headerPanel==null){
            headerPanel = new Panel();            
            headerPanel.setLayout(new BorderLayout());
            headerPanel.setHeight(130);
            headerPanel.setWidth(500);
//            headerPanel.setDraggable(true);
            Panel first = new Panel("Block 1");
//            first.setWidth(400);
            Panel second = getSeachActionPanel();
//            second.setBodyStyle("background-color:#FFFF88");
            BorderLayoutData eastData = new BorderLayoutData(RegionPosition.EAST);
            second.setWidth(150);
            eastData.setMinSize(150);
            headerPanel.add(second,eastData);
            headerPanel.add(getSearchCriteriaPanel(),new BorderLayoutData(RegionPosition.CENTER)); 
           
            
//            first.setSize("70%", "100%");
        }
        return headerPanel;
       }

       protected Panel getSeachActionPanel(){
           
           Panel panel = new Panel();
           
           VerticalLayout layout = new VerticalLayout(10) ;
           
           panel.setLayout(layout);
           
            panel.setBodyStyle("background-color:#FFFF88");            
         
           panel.setBorder(false);
           
           CustomButton btSearch = new CustomButton("Search");           
           
           
            CustomButton btReset = new CustomButton("Reset");
            
            btSearch.setSize("130", "30");
            btReset.setSize("130", "30");
           
            Panel searchPanel = new Panel();
            
            searchPanel.setBorder(false);
            
            searchPanel.setMargins(20,5,5,10);
            
            Panel resetPanel = new Panel();
            
            resetPanel.setBorder(false);
            
            resetPanel.setMargins(0,5,5,0);
            
           searchPanel.add(btSearch);
           
           resetPanel.add(btReset);
           
           panel.add(searchPanel);
           
           panel.add(resetPanel);
           
           return panel;
           
       }
       /**
        * Panel de critrere
        * @return 
        */
       public abstract Panel getSearchCriteriaPanel();
       /**
        * Renvoi le pied de page
        * @return 
        */
//       public abstract Panel getFooterPanel();

       
       /**
        * 
        * @return 
        */
       public  Panel getActionsPanel(){
           
           Panel panel = new Panel();
           
           panel.setLayout(new VerticalLayout(10));
           panel.setBorder(false);
           
           panel.setWidth(150);
           
           panel.setBodyStyle("background-color:#c0c0c0");
           
           Panel btPanel = new Panel();
           btPanel.setMargins(20, 5, 5, 10);
           btPanel.setBorder(false);
           btNew = new CustomButton("Nouveau");
           btNew.setSize("130", "30");
           btPanel.add(btNew);           
           panel.add(btPanel);
           
           btPanel = new Panel();
           btPanel.setMargins(5, 5, 5, 10);
           btPanel.setBorder(false);
           btUpdate = new CustomButton("Modifier");
           btUpdate.setSize("130", "30");
           btPanel.add(btUpdate);           
           panel.add(btPanel);
           
           btPanel = new Panel();
           btPanel.setMargins(5, 5, 5, 10);
           btPanel.setBorder(false);
           btDelete = new CustomButton("Supprimer");
           btDelete.setSize("130", "30");
           btPanel.add(btDelete);           
           panel.add(btPanel);
           
           btPanel = new Panel();
           btPanel.setMargins(5, 5, 5, 10);
           btPanel.setBorder(false);
           btCancel = new CustomButton("Quitter");
           btCancel.setSize("130", "30");
           btPanel.add(btCancel);
           
           panel.add(btPanel);
           return panel;
       }
       
       
       
       /**
        * 
        * @return 
        */
        public  abstract Panel getMiddlePanel();

       /**
        * 
        * @return 
        */
       public  Panel getContentPanel(){
           
           Panel panel = new Panel();
           
           panel.setBorder(false);
           
           panel.setLayout(new BorderLayout());
           
           BorderLayoutData eastData =  new BorderLayoutData(RegionPosition.EAST);
        
           eastData.setMinSize(120);

           eastData.setMargins(new Margins(0, 0, 5, 0));

           panel.add(getActionsPanel(),eastData);
        
           panel.add(getMiddlePanel(), new BorderLayoutData(RegionPosition.CENTER));
           
           return panel;
       }
       
         //Entête de la page
        protected Panel headerPanel ;

        //Pied de Page
//        protected Panel footerPanel ;

        //Panel de contenu
        protected Panel midllePanel ;

        //Panel des options
        protected Panel optionsPanel ;
        
        protected CustomButton btNew ;
        
        protected CustomButton btUpdate ;
        
        protected CustomButton btDelete ;
        
        protected CustomButton btCancel;
}
