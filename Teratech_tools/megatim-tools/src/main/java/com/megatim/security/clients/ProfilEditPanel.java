
package com.megatim.security.clients;

import com.megatim.common.annotations.Champ;
import com.megatim.common.clients.AbstractTreePanel;
import com.megatim.common.clients.ActionGroup;
import com.megatim.common.services.IocContext;
import com.megatim.common.utilities.MessagesBundle;
import com.megatim.model.test.TreeModel;
import com.megatim.model.test.TreePanel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;


/**
 * Panel d'edition ProfilEditPanel

 * @since Sun Sep 18 21:53:38 CEST 2016
 * 
 */
public class ProfilEditPanel
    extends JPanel
{

    private MessagesBundle bundle;
    private MessagesBundle resourcebundle;
    public final static String FRAME_NAME = ("com.megatim.security.clients.ProfilIFrame");
    private IocContext context = new IocContext();
    @Champ(mappedBy = "nom", type = java.lang.String.class, nullable = false, update = false, errorMessageField = "lbmnom", errorMessage = "profil.nom.error")
    private JTextField nom;
    private JLabel lbnom;
    private JLabel lbmnom;
    @Champ(mappedBy = "description", type = java.lang.String.class, errorMessageField = "lbmdescription", errorMessage = "profil.description.error")
    private JTextField description;
    private JLabel lbdescription;
    private JLabel lbmdescription;
    @Champ(mappedBy = "menus", type = java.util.List.class, errorMessageField = "lbmautorisations", errorMessage = "profil.autorisations.error")
    private AbstractTreePanel autorisations;
    //private AbstractTreeBaseListModel model = null;
    //private AbstractTreePanel autorisationsTree = null;
    private DefaultMutableTreeNode root  = null;
    
   
    
    public ProfilEditPanel() {
        initComponents() ; 
        
        //On desactive tous les boutons
        manageButtons();
    }
    
    /**
     * On desactive tous les boutons
     */
    private void manageButtons(){
        autorisations.enableAddButton(false);
        autorisations.enableEditButton(false);
        autorisations.enableViewButton(false);
        autorisations.enableDeleteButton(false);
    }
    
    /**
     * Methode permettant de generer l'arbre
     * @param autorisations
     * @param state 
     
    public void generateTree(List<Autorisation> autorisations, TreeState state){
        
        //On modifie le render
        this.setLayout(new BorderLayout());
        // Code of sub-components - not shown here
        autorisationsTree = new TreePanel();
        root = new DefaultMutableTreeNode();
        model = new TreeModel(root);
        model.setElements(menuBuilder(null,TreeState.VIEW_CREATE));
        // Layout setup code - not shown here
        autorisationsTree.setModel(model);
        // Code adding the component to the parent container - not shown here
        this.add(autorisationsTree, BorderLayout.CENTER);
    }
    
    private List<MenuComponent> menuBuilder(List<Autorisation> autorisations, TreeState state){
        
        //Definitions
        ToolsContext.TREE = new ArrayList<MenuComponent>();
        
        //On construit l'abre
        profilTreeBuilder(ToolsContext.LISTE_ITEMS, ToolsContext.TREE, autorisations, state);
        
        return ToolsContext.TREE;
    }
    
  */
    
  /**
   * Methode permettant de retourner tous les enfants
   * @param menu
   * @return 
   */
  private static List getChildrenOfMenu(ActionGroup menu){
      List children = new ArrayList();
      
      for(int i=0; i<menu.getGroupes().size(); i++){
        children.add(menu.getGroupes().get(i));
      }
      
      for(int i=0; i<menu.getActions().size(); i++){
        children.add(menu.getActions().get(i));
      }
      
      return children;
  }  
    
    /**
     * Getter
     * 
     * @return
     *     javax.swing.JTextField
     */
    public JTextField getNom() {
        return nom;
    }

    /**
     * Getter
     * 
     * @return
     *     javax.swing.JTextField
     */
    public JTextField getDescription() {
        return description;
    }

    /**
     * Methode permettant d'initialiser les composants
     * 
     */
    private void initComponents() {
        nom = new JTextField() ;
        nom.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lbnom = new JLabel();
        lbmnom = new JLabel();
         lbnom.setText(MessagesBundle.getMessage( "profil.nom"));
        description = new JTextField() ;
        description.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lbdescription = new JLabel();
        lbmdescription = new JLabel();
        lbdescription.setText(MessagesBundle.getMessage( "profil.description"));
        autorisations = new TreePanel();
        root = new DefaultMutableTreeNode();
        autorisations.setModel( new TreeModel(root));
        GroupLayout layout = new GroupLayout((this));
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        GroupLayout.ParallelGroup col1h = (layout.createParallelGroup() );
        GroupLayout.ParallelGroup col2h = (layout.createParallelGroup() );
        GroupLayout.ParallelGroup col3h = (layout.createParallelGroup() );
        GroupLayout.ParallelGroup col1v;
        GroupLayout.ParallelGroup col2v;
        GroupLayout.ParallelGroup col3v;
        GroupLayout.SequentialGroup hg = (layout.createSequentialGroup());
        GroupLayout.ParallelGroup hgp = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hgp.addGroup(hg);
        layout.setHorizontalGroup(hgp);
        GroupLayout.ParallelGroup hv = (layout.createParallelGroup());
        GroupLayout.SequentialGroup sg = layout.createSequentialGroup();
        layout.setVerticalGroup(hv) ;
        hv.addGroup(sg);
        // Positionnement des elements 
        col1h.addComponent(lbnom, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmnom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup colnomv = (layout.createParallelGroup() );
        colnomv.addComponent(lbnom, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        colnomv.addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        colnomv.addComponent( lbmnom, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(colnomv) ; 
        // Positionnement des elements 
        col1h.addComponent(lbdescription, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmdescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup coldescriptionv = (layout.createParallelGroup() );
        coldescriptionv.addComponent(lbdescription, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        coldescriptionv.addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        coldescriptionv.addComponent( lbmdescription, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(coldescriptionv) ; 
        // Positionnement des elements 
        hgp.addComponent(autorisations, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        sg.addComponent(autorisations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hg.addGroup(col1h) ; 
        hg.addGroup(col2h) ; 
        hg.addGroup(col3h) ; 
    }

}
