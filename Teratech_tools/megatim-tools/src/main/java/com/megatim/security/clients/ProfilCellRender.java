/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.security.clients;

import com.megatim.model.test.MenuComponent;
import com.megatim.model.test.MenuComposite;
import com.megatim.model.test.MenuLeaf;
import com.megatim.security.enumerations.EnumStatutAutorisation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author user
 */
public class ProfilCellRender extends MouseAdapter implements TreeCellRenderer{

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
         JLabel myLabel = new JLabel();
        JLabel labelExpended = new JLabel();
         JPanel myPanel = new JPanel(new BorderLayout());
         labelExpended.addMouseListener(this);
        if(value instanceof DefaultMutableTreeNode){
            DefaultMutableTreeNode myNode = (DefaultMutableTreeNode) value ;
            String infoText = "-";
            if(selected){
                myPanel.setBackground(new Color(169, 234, 254));                
            }
            
            if(myNode.getUserObject() instanceof String){
                 myLabel.setIcon(new ImageIcon(getClass().getResource("/com/megatim/tools/images/home.png")));
            }else{                
            
                MenuComponent node = (MenuComponent) myNode.getUserObject();

                if(node instanceof MenuLeaf){

                    if(node.getAutorisation().getStatut().equals(EnumStatutAutorisation.DELETE)){
                        myLabel.setIcon(new ImageIcon(getClass().getResource("/com/megatim/tools/images/button_delete.png")));
                    }else if(node.getAutorisation().getStatut().equals(EnumStatutAutorisation.WRITE)){
                        myLabel.setIcon(new ImageIcon(getClass().getResource("/com/megatim/tools/images/modifier.png")));
                    }else if(node.getAutorisation().getStatut().equals(EnumStatutAutorisation.READ)){
                        myLabel.setIcon(new ImageIcon(getClass().getResource("/com/megatim/tools/images/button_display.png")));
                    }else if(node.getAutorisation().getStatut().equals(EnumStatutAutorisation.NONE)){
                        myLabel.setIcon(new ImageIcon(getClass().getResource("/com/megatim/tools/images/button_cancel.png")));
                    }
                }else if(node instanceof MenuComposite){
                    myLabel.setIcon(new ImageIcon(getClass().getResource("/com/megatim/tools/images/dossier.png")));
                }
            }
            
            infoText+=" "+myNode.getUserObject();
            myLabel.setText(infoText);
        }       
        myPanel.add(BorderLayout.WEST,labelExpended);
        myPanel.add(BorderLayout.CENTER,myLabel);
        return myPanel;
    }
    
}
