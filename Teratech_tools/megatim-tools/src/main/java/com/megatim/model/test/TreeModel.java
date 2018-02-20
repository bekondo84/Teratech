/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.model.test;

import com.megatim.common.clients.AbstractTreeBaseListModel;
import java.util.List;
import javax.swing.tree.TreeNode;

/**
 *
 * @author DEV_4
 */
public class TreeModel extends AbstractTreeBaseListModel<MenuComponent>{

    public TreeModel(TreeNode root) {
        super(root);
    }

    @Override
    public Object getColoumnValue(MenuComponent data, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isComponent(MenuComponent data) {
       return data.isLeaf(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MenuComponent getSubElement(MenuComponent data, int index) {
       return data.subList().get(index); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MenuComponent> getSubElements(MenuComponent data) {
        return data.subList(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int subElementCount(MenuComponent data) {
        return data.subList().size(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String rootName() {
        return "GEPA"; //To change body of generated methods, choose Tools | Templates.
    }
    
}
