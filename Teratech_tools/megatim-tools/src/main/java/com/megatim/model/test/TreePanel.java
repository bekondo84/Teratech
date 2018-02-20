/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.model.test;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.clients.AbstractTreeBaseListModel;
import com.megatim.common.clients.AbstractTreePanel;
import com.megatim.common.utilities.TypeOperation;
import com.megatim.security.clients.ProfilCellRender;
import com.megatim.security.enumerations.EnumStatutAutorisation;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

/**
 *
 * @author DEV_4
 */
public class TreePanel extends AbstractTreePanel<MenuComponent>{

    @Override
    protected JDialog getEditDialog(MenuComponent object, GenericManager manager, AbstractTreeBaseListModel model, TypeOperation typeOperation, JFrame window) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected GenericManager getManager() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected JFrame getApplicationFrame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected TreeCellRenderer getRender() {
        return new ProfilCellRender();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
      if(e.getSource().equals(tree)){
          if(e.isPopupTrigger()){
                //
                TreePath path = tree.getSelectionPath();
                
                //On verifie que le noeud n'est pas la racine
                if(path.getParentPath() != null){ 
                
                    if(tree != null && path != null){
                        //
                        JPopupMenu pop = new JPopupMenu();
                        JMenuItem delete =new JMenuItem(EnumStatutAutorisation.DELETE.toString());
                        pop.add(delete);
                        JMenuItem write = new JMenuItem(EnumStatutAutorisation.WRITE.toString());
                        pop.add(write);
                        JMenuItem read = new JMenuItem(EnumStatutAutorisation.READ.toString());
                        pop.add(read);
                        JMenuItem none = new JMenuItem(EnumStatutAutorisation.NONE.toString());
                        pop.add(none);
                        pop.show(tree, e.getX(), e.getY());
                        delete.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                deleteActionPerformed();
                            }
                        });

                        write.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                writeActionPerformed();
                            }
                        });

                        read.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                readActionPerformed();
                            }
                        });

                        none.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                noneActionPerformed();
                            }
                        });
                        //System.out.println("TreePanel.mouseReleased(MouseEvent e)  :::::::::::::::::::::::::::::::::::::::::::::  "+node.getUserObject());
                 }
              }
          }
        }
    }
    
    private void deleteActionPerformed(){
        
        TreePath path = tree.getSelectionPath();
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        
        if(node.getUserObject() instanceof MenuLeaf){
            ((MenuLeaf)node.getUserObject()).getAutorisation().setStatut(EnumStatutAutorisation.DELETE);
        }else if(node.getUserObject() instanceof MenuComposite){
            nodeParcours((MenuComponent)node.getUserObject(), EnumStatutAutorisation.DELETE);
        }
        tree.treeDidChange();
                
        
    }
    
    private void writeActionPerformed(){
        
        TreePath path = tree.getSelectionPath();
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        
        if(node.getUserObject() instanceof MenuLeaf){
            ((MenuLeaf)node.getUserObject()).getAutorisation().setStatut(EnumStatutAutorisation.WRITE);
        }else if(node.getUserObject() instanceof MenuComposite){
            nodeParcours((MenuComponent)node.getUserObject(), EnumStatutAutorisation.WRITE);
        }
        tree.treeDidChange();

    }
    
    private void readActionPerformed(){
        
        TreePath path = tree.getSelectionPath();
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        
        if(node.getUserObject() instanceof MenuLeaf){
            ((MenuLeaf)node.getUserObject()).getAutorisation().setStatut(EnumStatutAutorisation.READ);
        }else if(node.getUserObject() instanceof MenuComposite){
            nodeParcours((MenuComponent)node.getUserObject(), EnumStatutAutorisation.READ);
        }
        tree.treeDidChange();
    }
    
    private void noneActionPerformed(){
        
        TreePath path = tree.getSelectionPath();
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        
        if(node.getUserObject() instanceof MenuLeaf){
            ((MenuLeaf)node.getUserObject()).getAutorisation().setStatut(EnumStatutAutorisation.NONE);
        }else if(node.getUserObject() instanceof MenuComposite){
            nodeParcours((MenuComponent)node.getUserObject(), EnumStatutAutorisation.NONE);
        }
        tree.treeDidChange();
    }
    
    /**
     * 
     * @param node
     * @param etat 
     */
    private void nodeParcours(MenuComponent node , EnumStatutAutorisation etat){
        
        if(node==null)
                 return ;
        if(node.isLeaf()){
            ((MenuLeaf)node).getAutorisation().setStatut(etat);
        }else {
            List<MenuComponent> components = ((MenuComposite)node).getElements();
            for(MenuComponent c : components){
                nodeParcours(c, etat);
            }
        }
            
    }
    /**
     * 
     * @param index
     * @return 
     */
    private EnumStatutAutorisation getStateFromMenu(int index){
        
        switch(index){
            case 0 : return EnumStatutAutorisation.DELETE;
                
            case 1 : return EnumStatutAutorisation.WRITE;
                
           case 2 : return EnumStatutAutorisation.READ;
               
           case 3 : return EnumStatutAutorisation.NONE;
        }
        return EnumStatutAutorisation.NONE ;
    }
}
