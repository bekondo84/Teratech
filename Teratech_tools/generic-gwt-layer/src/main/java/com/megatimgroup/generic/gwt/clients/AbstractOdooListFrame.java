/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.megatimgroup.generic.gwt.commons.AbstractBase;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalSplitPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Ext;
import com.gwtext.client.core.ExtElement;
import com.gwtext.client.core.Function;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Tool;
import com.gwtext.client.widgets.WaitConfig;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.event.GridListenerAdapter;
import com.gwtext.client.widgets.grid.event.GridRowListenerAdapter;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;
import com.gwtext.client.widgets.menu.BaseItem;
import com.gwtext.client.widgets.menu.Item;
import com.gwtext.client.widgets.menu.Menu;
import com.gwtext.client.widgets.menu.event.BaseItemListenerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Commercial_2
 * @param <T>
 */
public abstract class AbstractOdooListFrame<T extends AbstractBase> extends Panel{

    /**
     * 
     * @param parent 
     */
    public AbstractOdooListFrame(ScreenTemplate parent) {
        super();
        this.parent = parent;
        buildView();
    }

    /**
     * 
     * @param title
     * @param parent 
     */
    public AbstractOdooListFrame(String title,ScreenTemplate parent) {
        super(title);
        this.parent = parent;
        buildView();
    }
  

   
    /**
     * Construction de la Fenetre
     */
    public void buildView(){
        
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
        contentPanel = new VerticalSplitPanel();
        contentPanel.setSplitPosition("0%");
        borderPanel.add(contentPanel, new BorderLayoutData(RegionPosition.CENTER));
        //Chargement des données
//        search();
        //Initialisation
        if(getDataModel().getKabanComponents()!=null){
            contentPanel.setBottomWidget(getKabanTable());
        }else {
            
            contentPanel.setBottomWidget(getTablePanel(getDataModel()));
        }
        
        //Add the borderPanel in the panel
        this.add(borderPanel);
          
        //Set the VeiewPort
//        Viewport viewport = new Viewport(this); 
        int width = (int) (Window.getClientWidth()-(Window.getClientWidth()*0.13));
        this.setWidth(width);
        int heigth = Window.getClientHeight()-20;
        this.setHeight(heigth);
    }
    
      /**
        * Renvoie l'entête de la page
        * @return 
        */
       public  Panel getHeaderPanel() {
           
            /*if(headerPanel==null)*/{
            headerPanel = new Panel();            
            headerPanel.setLayout(new VerticalLayout(5));
            headerPanel.setHeight(70);
            headerPanel.setWidth(500);
            headerPanel.setBodyStyle("background-color:#FFFF88");
            
            //Construction du panel de recherche
            Panel searchPanel = new Panel();
            searchPanel.setBorder(false);
            ComboBox searchBox = new ComboBox();
            searchBox.setMinChars(1);
            searchBox.setStore(getDataStore());
            searchBox.setDisplayField(getDisplayField());
            searchBox.setMode(ComboBox.LOCAL);
            searchBox.setEmptyText("Entrer le texte");
            searchBox.setLoadingText("Chargement ....");
            searchBox.setTypeAhead(true);
            searchBox.setSelectOnFocus(true);
            searchBox.setWidth(500);
            searchBox.setHeight(30);
            searchBox.setHideTrigger(true);
            searchPanel.add(searchBox);
            int width = Window.getClientWidth()-700;
            searchPanel.setMargins(5, width, 5, 5);
            headerPanel.add(searchPanel);
            //Construction du panel de second level
            Panel filterPanel = new Panel();
            filterPanel.setBorder(false);
            filterPanel.setLayout(new HorizontalLayout(5));
            Panel firstPanel = new Panel();
            firstPanel.setLayout(new HorizontalLayout(3));
            firstPanel.setBorder(false);
            btNew = new Button("Créer");
            btNew.setMinWidth(80);
            btNew.addListener(new ButtonListenerAdapter(){

                @Override
                public void onClick(Button button, EventObject e) {
                    //To change body of generated methods, choose Tools | Templates.
                    btNewActionPerformed();
                }
                
            });
            firstPanel.add(btNew);
            btImport = new Button("Importer");
//            btImport.setSize("90", "30");
            firstPanel.add(btImport);
            btImport.setMinWidth(80);
//            firstPanel.setHeight(31);
            filterPanel.add(firstPanel);
            
            Panel secondPanel = new Panel();
            secondPanel.setBorder(false);
            secondPanel.setLayout(new HorizontalLayout(3));
            btPrint = new Button("Imprimer");
            
            btPrint.setMinWidth(80);
//            btPrint.setSize("90", "30");
            if(getPrintMenu()!=null){
                btPrint.setMenu(getPrintMenu());
            }
            secondPanel.add(btPrint);
            btAction = new Button("Actions");
            btAction.setMinWidth(80);
//            btAction.setSize("90", "30");
            btAction.setMenu(getActionMenu());
            secondPanel.add(btAction);
            secondPanel.setMargins(0, width-340, 5, 5);
//            secondPanel.setHeight(31);            
            filterPanel.add(secondPanel);
            
            Panel thirdPanel = new Panel();
            thirdPanel.setBorder(false);
            thirdPanel.setLayout(new HorizontalLayout(3));
            btFilter = new Button("Filter");
            btFilter.setMinWidth(80);
//            btFilter.setSize("90", "30");
            if(getFilterMenu()!=null){
                btFilter.setMenu(getFilterMenu());
            }
            thirdPanel.add(btFilter);
            btGroupe = new Button("Grouper par");
            btGroupe.setMinWidth(80);
//            btGroupe.setSize("90", "30");
            if(getGroupeMenu()!=null){
                btGroupe.setMenu(getGroupeMenu());
            }
//            thirdPanel.setHeight(31);
            thirdPanel.add(btGroupe);
            thirdPanel.setMargins(0, 10, 5, 5);
            filterPanel.add(thirdPanel);
            
            Panel fourPanel = new Panel();
            fourPanel.setBorder(false);
            btLeft = new Button(" < ");
            btLeft.addListener(new ButtonListenerAdapter(){

                @Override
                public void onClick(Button button, EventObject e) {
                    //To change body of generated methods, choose Tools | Templates.
                    btLeftActionPerformed();
                }
                
            });
            fourPanel.setLayout(new HorizontalLayout(2));
            lbPage = new Label(" Pages ");
            lbPage.setWidth("80");
            fourPanel.add(lbPage);
            fourPanel.add(btLeft);
            btRigth = new Button(" > ");
            btRigth.addListener(new ButtonListenerAdapter(){

                @Override
                public void onClick(Button button, EventObject e) {
                    //To change body of generated methods, choose Tools | Templates.
                    btRigthActionPerformed();
                }
                
            });
            fourPanel.add(btRigth);
            fourPanel.setMargins(0, 20, 5, 5);
            filterPanel.add(fourPanel);
            Panel fivePanel = new Panel();
            fivePanel.setBorder(false);
            fivePanel.setLayout(new HorizontalLayout(2));
            btTable = new Button("Table");
            btTable.addListener(new ButtonListenerAdapter(){

                @Override
                public void onClick(Button button, EventObject e) {
                   //To change body of generated methods, choose Tools | Templates.
                     super.onClick(button, e); 
                     contentPanel.setBottomWidget(getTablePanel(null));
                }
               
            });
            
           fivePanel.add(btTable);
            btKaban = new Button("Kaban");
            btKaban.addListener(new ButtonListenerAdapter(){

                @Override
                public void onClick(Button button, EventObject e) {
                   //To change body of generated methods, choose Tools | Templates.
                     super.onClick(button, e); 
                     contentPanel.setBottomWidget(getKabanTable());
                }
               
            });
            fivePanel.add(btKaban);
            width = Window.getClientWidth();
            fivePanel.setMargins(0, 0, width+500, 5);
            filterPanel.add(fivePanel);
            headerPanel.add(filterPanel);
            
        }
        return headerPanel;
       }

       /**
        * 
        */
       private void enabledOrDesable(){
           
           if(getSelectedObjects()==null||getSelectedObjects().isEmpty()){
               btPrint.setDisabled(true);
               btAction.setDisabled(true);
           }else{
               btPrint.setDisabled(false);
               btAction.setDisabled(false);
           }
           
           if(model.getKabanComponents()==null){
               btKaban.setDisabled(true);
               btTable.setDisabled(true);
           }else{
                btKaban.setDisabled(false);
               btTable.setDisabled(false);
           }
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
        * Source de données
        * @return 
        */
       protected abstract Store getDataStore();
       
       /**
        * Champs a afficher
        * @return 
        */
       protected abstract String getDisplayField();
     
       
       
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
        * 
        * @return 
        */
       protected Menu getFilterMenu(){
           
           return null ;
       }
       
       /**
        * 
        * @return 
        */
       protected Menu getGroupeMenu(){
           
           return null ;
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
        * Construction de la table
     * @param model
        * @return 
        */
       public  OdooListTable  getTablePanel(AbstractTableModel model){           
          
          if(model==null){
              table = new OdooListTable();
              table.setModel(getDataModel());       
          }else {
              table = new OdooListTable(getDataModel());
          }
          table.addGridRowListener(new GridRowListenerAdapter(){

              @Override
              public void onRowDblClick(GridPanel grid, int rowIndex, EventObject e) {
                  //To change body of generated methods, choose Tools | Templates.
                  rowDbClickActionPerformed(grid, rowIndex, e);
              }

              @Override
              public void onRowClick(GridPanel grid, int rowIndex, EventObject e) {
                 //To change body of generated methods, choose Tools | Templates.
                   enabledOrDesable();
              }
                
          });
          table.addGridListener(new GridListenerAdapter(){

              @Override
              public void onClick(EventObject e) {
                 //To change body of generated methods, choose Tools | Templates.
                   super.onClick(e); 
                   enabledOrDesable();
              }
                   
                  
          });
          return table ;
       }
       
       /**
        * Construction de la table Kaban
        * @return 
        */
       public  OdooKabanTable getKabanTable(){
           //Verification si les kaban exist
//          if(getDataModel().getKabanComponents()==null){
//              return null ;
//          } 
           kaban = new OdooKabanTable(getDataModel());
           enabledOrDesable();
           return kaban;
       }
       
       /**
        * Creation d'un fenetre d'edition
        * @param object
        * @param windowType
        * @return 
        */
       protected abstract AbstractEditPanel getEditPanel(T object , EditPanelType windowType);
       
       /**
        * Liste des entites selectionnées
        * @return 
        */
       protected List<T> getSelectedObjects(){ 
           
           List arrays = new ArrayList();
           
           if(model==null||model.getCbSelectionModel()==null||
                   model.getCbSelectionModel().getSelections().length<=0){
               return arrays;
           }
           
           for(int index = 0 ; index<model.getDatas().size();index++){
               if(model.getCbSelectionModel().isSelected(index)){
                   arrays.add(model.getDatas().get(index));
               }
           }
           return arrays;
       }
       
       /**
        * Renvoie un model de données
        * @return 
        */
       public abstract AbstractTableModel getDataModel();
       
       /**
        * 
        * @param datas 
        */
       protected  void setDatas(List<T> datas){
           this.elements = datas ;
//           MessageBox.alert("SetDatas", ""+elements);
//           getDataModel();
           if(getDataModel().getKabanComponents()!=null){
               contentPanel.setBottomWidget(getKabanTable());
           }else {
               contentPanel.setBottomWidget(getTablePanel(getDataModel()));
           }
       }
       
       /**
        * Recherche des informations en base de données 
        * selon les valeurs du filtres et de la pagination
        */
       protected abstract void search();
       /**
        * 
        */
       protected void btNewActionPerformed(){           
           parent.setContentPanel(getEditPanel(null, EditPanelType.NEW));
       }
       /**
        * Action a executer en cas de click sur le bouton
        * gauche
        */
       protected void btLeftActionPerformed(){
           
       }
       
       /**
        * 
        */
       protected void btRigthActionPerformed(){
           
       }
       
      
       
       /**
        * 
        * @param grid
        * @param rowIndex
        * @param e 
        */
       protected void rowDbClickActionPerformed(GridPanel grid, int rowIndex, EventObject e){
           
           T currentObject = (T) model.getDatas().get(rowIndex);
           parent.setContentPanel(getEditPanel(currentObject, EditPanelType.VIEW));
//           MessageBox.alert("Vous avez cliquez 2 fois sur la ligne "+rowIndex+" ---- ");
       }
       //Listes de entites pour cette interfaces
       protected List<T> elements ;
       
       //Table contenant les données
       protected OdooListTable table ;
       
       //Tableau de Kaban
       protected OdooKabanTable kaban ;
       
       //Model des données
       protected AbstractTableModel model ;     
       
        //Entête de la page
        protected Panel headerPanel ;

        //Pied de Page
//        protected Panel footerPanel ;

        //Panel de contenu
        protected Panel midllePanel ;
        
        protected VerticalSplitPanel contentPanel;

        //Panel des options
        protected Panel optionsPanel ;
        
        protected Button btNew ;
        
        protected Button btImport ;
        
        protected Button btPrint ;
        
        protected Button btAction;
        
        protected Button btFilter ;
        
        protected Button btGroupe;
        
        protected Label lbPage ;
        
        protected Button btLeft;
        
        protected Button btRigth;
        
        protected Button btKaban;
        
        protected Button btTable;        
       
        protected ScreenTemplate parent ;
        
        protected ExtElement element = Ext.get("mask-panel");
}
