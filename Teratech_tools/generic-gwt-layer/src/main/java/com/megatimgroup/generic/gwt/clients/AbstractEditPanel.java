/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.megatimgroup.generic.gwt.commons.AbstractBase;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalSplitPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Function;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Tool;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.WaitConfig;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.menu.BaseItem;
import com.gwtext.client.widgets.menu.Item;
import com.gwtext.client.widgets.menu.Menu;
import com.gwtext.client.widgets.menu.event.BaseItemListenerAdapter;

/**
 *
 * @author Commercial_2
 * @param <T>
 */
public abstract  class AbstractEditPanel<T extends AbstractBase> extends Panel{

    /**
     * 
     * @param type
     * @param parent
     * @param object
     */
    public AbstractEditPanel(EditPanelType type,ScreenTemplate parent,T object) {
        
        super();
        this.windowType = type ;
        this.parent = parent ;
        this.currentObject = object;
         buildComponents();
    }

    /**
     * 
     * @param title 
     * @param type 
     * @param parent 
     * @param object 
     */
    public AbstractEditPanel(String title,EditPanelType type,ScreenTemplate parent,T object) {
        super(title);
        this.windowType = type ;
        this.parent = parent ;
        this.currentObject = object;
         buildComponents();
    }

    
    
    /**
     * Construction des composants
     */
    private void buildComponents(){
        
        //Mise en place de gestionnaire de contenu
        this.setLayout(new FitLayout());
        
//        this.setBorder(false);
        
//        this.setFloating(true);
        
        this.addTool(new Tool(Tool.CLOSE, new Function() {

            @Override
            public void execute() {
                //To change body of generated methods, choose Tools | Templates.
                MessageBox.alert("Refresh", "The Refresh tool was clicked");  
            }
        }));
        
        
        int width = (int) (Window.getClientWidth() - Window.getClientWidth()*0.13) ;
        
        int heigth = Window.getClientHeight() - 72;
        
        Panel borderPanel = new Panel();
        
        borderPanel.setBorder(false);
        
        borderPanel.setLayout(new BorderLayout());
        
        Panel footerPanel = new Panel();
        footerPanel.setBorder(false);
//        footerPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
        footerPanel.setLayout(new HorizontalLayout(10));
        footerPanel.setBodyStyle("background-color:#FFFF88");
        Panel btPanel = new Panel();
        btPanel.setLayout(new HorizontalLayout(3));
        btPanel.setBorder(false);
        btAction1 = new Button("Modifier");
        btAction2 = new Button("Créer");        
//        btAction1.setSize("90", "30");        
        btPanel.add(btAction1);
        btPanel.add(btAction2);
        btPanel.setMargins(0,0, 5, 5);
        footerPanel.add(btPanel);        
        btAction1.addListener(new ButtonListenerAdapter(){

            @Override
            public void onClick(Button button, EventObject e) {
                 //To change body of generated methods, choose Tools | Templates.
                btAction1ActionPerformed();
            }
               
        });
        btAction2.addListener(new ButtonListenerAdapter(){

            @Override
            public void onClick(Button button, EventObject e) {
                 //To change body of generated methods, choose Tools | Templates.
                btAction2ActionPerformed();
            }
               
        });
        Panel secondPanel = new Panel();        
        secondPanel.setBorder(false);
        btAction3 = new Button("Imprimer");
        btAction4 = new Button("Actions");
        btAction3.setMenu(getPrintMenu());
        btAction4.setMenu(getActionMenu());
        secondPanel.setLayout(new HorizontalLayout(3));
        secondPanel.add(btAction3);
        secondPanel.add(btAction4);
        int width1 =(int)Window.getClientWidth()/2 -200;
        secondPanel.setMargins(0, width1, 0, 0);
        footerPanel.add(secondPanel);
        btAction3.addListener(new ButtonListenerAdapter(){

            @Override
            public void onClick(Button button, EventObject e) {
                 //To change body of generated methods, choose Tools | Templates.
                btAction3ActionPerformed();
            }
               
        });
        btAction4.addListener(new ButtonListenerAdapter(){

            @Override
            public void onClick(Button button, EventObject e) {
                 //To change body of generated methods, choose Tools | Templates.
                btAction4ActionPerformed();
            }
               
        });
        Panel thirdPanel = new Panel();
        thirdPanel.setBorder(false);
        thirdPanel.setLayout(new HorizontalLayout(2));
        lbPage = new Label(" Pages ");
        btLeftAction = new Button("<");
        btRigthAction = new Button(">");
        thirdPanel.add(lbPage);
        thirdPanel.add(btLeftAction);
        thirdPanel.add(btRigthAction);
        width1 = Window.getClientWidth()-700;
        thirdPanel.setMargins(0,0, width1, 0);
        footerPanel.add(thirdPanel);
        btLeftAction.addListener(new ButtonListenerAdapter(){

            @Override
            public void onClick(Button button, EventObject e) {
                 //To change body of generated methods, choose Tools | Templates.
               btLeftActionPerformed();
            }
               
        });
        
        btRigthAction.addListener(new ButtonListenerAdapter(){

            @Override
            public void onClick(Button button, EventObject e) {
                 //To change body of generated methods, choose Tools | Templates.
                btRigthActionPerformed();
            }
               
        });
        if(windowType.equals(EditPanelType.NEW)||windowType.equals(EditPanelType.UPDATE)){
            btAction1.setText("Sauvegarder");
            btAction2.setText("Annuler");
            btAction3.setVisible(false);
            btAction4.setVisible(false);
            lbPage.setVisible(false);
            btLeftAction.setVisible(false);
            btRigthAction.setVisible(false);
        }else if(windowType.equals(EditPanelType.VIEW)){
            btAction1.setText("Modifier");
            btAction2.setText("Créer");
            btAction3.setVisible(true);
            btAction4.setVisible(true);
            lbPage.setVisible(true);
            btLeftAction.setVisible(true);
            btRigthAction.setVisible(true);
        }
        BorderLayoutData southData = new BorderLayoutData(RegionPosition.NORTH);
        southData.setMinSize(35);
        footerPanel.setHeight(25);
        borderPanel.add(footerPanel,southData);        
       
        content = new VerticalSplitPanel();
        content.setSplitPosition("0%");
        content.setBottomWidget(buildMiddlePanel());
        if(windowType.equals(EditPanelType.VIEW)){
            fillView(currentObject);            
            desabledField();
        }else if(windowType.equals(EditPanelType.UPDATE)){
            fillView(currentObject);      
        }
        borderPanel.add(content, new BorderLayoutData(RegionPosition.CENTER));
        
        this.add(borderPanel);
        
        this.setSize(width, heigth);
        
    }
    
    
    /**
     * Construction du panel des données
     * @return 
     */
    protected Panel buildMiddlePanel(){
        
        Panel panel = new Panel();        
        
//        panel.setLayout(new FitLayout());
        
        //Construction de la barre d'outils
        if(buildToolsBar()!=null){
            panel.setTopToolbar(buildToolsBar());
        }
        //Creation du contenu
        contentPanel = getContentPanel();
        if(contentPanel!=null){
            contentPanel.setBorder(true);
            contentPanel.setBodyStyle("background-color:#E6E2AF");
            int width = (int) (Window.getClientWidth()-Window.getClientWidth()*0.14)-120;
            int heigth = Window.getClientHeight() - 100;
            contentPanel.setSize("100%", "100%");
            contentPanel.setHeight(heigth);
//            contentPanel.setMargins(20, 120, 120, 0);
            contentPanel.setMargins(20, 20, 20, 0);
            panel.add(contentPanel);
        }
//        panel.setBodyStyle("background-color:#FFFF00");
        return panel;
    }
    
     /**
        * Construction de la liste des options d'impression
        * @return 
        */
       protected  Menu getPrintMenu(){
           
           Menu menu =new Menu();
           
           Item item = new Item("Imprimer ...");
           
           menu.addItem(item);
           
           item.addListener(new BaseItemListenerAdapter(){

               @Override
               public void onClick(BaseItem item, EventObject e) {
                  //To change body of generated methods, choose Tools | Templates.
                    super.onClick(item, e); 
                    MessageBox.alert("Le module d'impression est en cours d'implementation");
               }
           
               
           });
           return menu;
       }
       
      
       
       /**
        * Construction du menu des actions
        * Vous pouvez override cette metode
        * @return 
        */
       protected Menu getActionMenu(){
           
           Menu menu = new Menu();
           
           Item item = new Item("Exporter");
           
           menu.addItem(item);
           
           item = new Item("Archiver");
                   
           menu.addItem(item);
           
           item = new Item("Déarchiver");
                  
           menu.addItem(item);
           
           item = new Item("Supprimer");
           
           menu.addItem(item);
           
           return menu;
       }
       
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
        * Desactive les champs
        */
     protected abstract void desabledField();
    /**
     * Renvoie le panel contenant les données
     * @return 
     */
    protected abstract Panel getContentPanel();
    
    /**
     * Construction de la barre d'outils
     * @return 
     */
    protected abstract Toolbar buildToolsBar();
    
    /**
     * Collected les valeur des champs et valide le contenu des
     * champs
     * @return 
     */
    protected abstract T dataCollected();
    
    /**
     * 
     * @param entity 
     */
    protected abstract void fillView(T entity);
    
     /**
        * Creation d'un fenetre d'edition
        * @param object
        * @param windowType
        * @return 
        */
    protected abstract AbstractEditPanel getEditPanel(T object , EditPanelType windowType);
    
    /**
     * 
     * @return 
     */
    protected abstract AbstractOdooListFrame getListPanel();
    
    /**
     * Enregistrement d'un nouveau element
     */
    protected abstract void save();
    
    /**
     * Mise a jour d'un nouveau element
     */
    protected abstract void update();
    
    /**
     * 
     */
    protected void btAction1ActionPerformed(){
        
        if(windowType.equals(EditPanelType.VIEW)){
            parent.setContentPanel(getEditPanel(currentObject, EditPanelType.UPDATE));
        }else if(windowType.equals(EditPanelType.NEW)){
            //Sauvegarde des données
            save();
        }else if(windowType.equals(EditPanelType.UPDATE)){
            update();
        }
    }
    
    /**
     * 
     */
    protected void btAction2ActionPerformed(){
        
        if(windowType.equals(EditPanelType.VIEW)){
            parent.setContentPanel(getEditPanel(currentObject, EditPanelType.NEW));
        }else if(windowType.equals(EditPanelType.NEW)){
            //Quiiter retour a la fenetre parent
            parent.setContentPanel(getListPanel());
        }else if(windowType.equals(EditPanelType.UPDATE)){
            //Retour a la fenetre edition en consultation
            parent.setContentPanel(getEditPanel(currentObject, EditPanelType.VIEW));
        }
    }
    
    /**
     * 
     */
    protected void btAction3ActionPerformed(){
        
    }
    
    /**
     * 
     */
    protected void btAction4ActionPerformed(){
        
    }
    
    /**
     * 
     */
    protected void btLeftActionPerformed(){
        
    }
    
     /**
     * 
     */
    protected void btRigthActionPerformed(){
        
    }
    
    protected VerticalSplitPanel content ;
    
    protected Button btAction1 ;
    
    protected Button btAction2;
    
    protected Button btAction3;
    
    protected Button btAction4;
    
    protected Button btLeftAction;
    
    protected Button btRigthAction;
    
    protected Label lbPage;
    
    protected Toolbar toolBar ;
    
    protected Panel contentPanel ;    
    
    protected EditPanelType windowType = EditPanelType.VIEW;
    
    protected ScreenTemplate parent ;
    
    protected T currentObject ;
    
}
