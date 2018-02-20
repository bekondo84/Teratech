/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.clients;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

/**
 *
 * @author DEV_4
 * @param <T>
 */
public abstract  class AbstractTreeBaseListModel<T extends Comparable<? super T>> extends DefaultTreeModel implements TreeModel{

    
    
     /**
     * ID Genere par Eclipse
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Comparateur d'instance d'onjets
     */
    protected Comparator<T> comparator = null;
    
    /**
     * Liste des �l�ments de la Table
     */
    protected List<T> elements = new ArrayList<T>();
    
    /**
     * Racine de l'arbre
     */
    //protected TreeNode root =null;
    /**
     * Constructeur 
     * @param root 
     */
     public AbstractTreeBaseListModel(TreeNode root) {
        super(root);
        this.root = (MutableTreeNode) root ;
    }

    
    
    /**
     * Methode de mise a jour du Comparateur
     * @param comparator	Comparateur
     */
    public void setComparator(Comparator<T> comparator) {
    	this.comparator = comparator;
    }
    
   

    /**
     * M�thode de mise � jour des �l�ments de la table
     * @param elements  Elements de la table
     */
    public void setElements(List<T> elements) {
        // Si la nouvelle collection est nulle
        if(elements == null) this.elements = new ArrayList<T>();
        else this.elements = elements;
        
        // S'il y a un comparateur
        if(comparator != null) Collections.sort(this.elements, comparator);
        else Collections.sort(this.elements);
        // On signale le changement
        ((DefaultMutableTreeNode) root).removeAllChildren();
        treeBuilder((DefaultMutableTreeNode) root, elements);
    }

    /**
     * M�thode d'ajout d'un element dans la liste
     * @param element   Element � ajouter
     */
    public void addElement(T element) {

        // Si l'�l�ment est null
        if(element == null) return;

        // Si l'�l�ment existe d�ja
        if(this.elements.indexOf(element) > 0) return;

        // Ajout
        if(!this.exist(element))
            this.elements.add(0, element);
        else 
           this.updateElement(elements.indexOf(element), element);

        // Signalisation
        //fireTableRowsInserted(0, 0);

        // S'il y a un comparateur
        if(comparator != null) Collections.sort(this.elements, comparator);
        else Collections.sort(this.elements);
        
    }

    /**
     * M�thode de suppression d'un Objet de la table
     * @param index   Index de l'Objet � Supprimer
     */
    public void deleteElement(int index) {

        // Si l'�l�ment n'existe pas
        if(index < 0) return;

        // On supprime
        this.elements.remove(index);

        // on signale
        //fireTableRowsDeleted(index, index);
    }

    /**
     * M�thode de suppression d'un Objet de la table
     * @param element   Objet � Supprimer
     */
    public void deleteElement(T element) {

        // Si l'�l�ment est null
        if(element == null) return;

        // Recherche de l'�l�ment
        int index = this.elements.indexOf(element);

        // Suppression
        deleteElement(index);
    }
    
    /**
     * M�thode de mise � jour d'un Objet de la table
     * @param element   Nouvel Objet
     */
    public void updateElement(int index, T element) {
        
        // Si l'�l�ment est null
        if(element == null) return;

        // Si l'index est < 0
        if(index < 0) return;

        // On supprime
        this.elements.set(index, element);

        // on signale
        //fireTableRowsUpdated(index, index);

        // S'il y a un comparateur
        if(comparator != null) Collections.sort(this.elements, comparator);
        else Collections.sort(this.elements);
    }

    /**
     * M�thode d'obtention d'un �l�ment par son index
     * @param index Index de l'�l�ment
     * @return  Element choisit
     */
    public T getElement(int index) {

        // Si l'Index est n�gatif
        if(index < 0 || index >= this.elements.size()) return null;

        // On retourne l'Objet
        return this.elements.get(index);
    }

    /**
     * M�thode d'obtention de la liste des �l�ments de la table
     * @return  Liste des Elements de la table
     */
    public List<T> getElements() {

        // On retourne la liste
        return this.elements;
    }

    /**
     * M�thode d'obtention de la liste des �l�ments de la table
     * @param indexes Indexes des �l�ments � retourner
     * @return Liste des Elements de la table correspondant aux indexes
     */
    public List<T> getElements(int [] indexes) {

        // Si la liste des indexes est vide
        if(indexes == null || indexes.length == 0) return null;

        // Liste � retourner
        List<T> datas = new ArrayList<T>();

        // Parcours
        for(int index : indexes) {

            // Obtention de l'objet
            T data = getElement(index);

            // Si l'objet n'Est pas null
            if(data != null) datas.add(data);
        }

        // On retourne la liste
        return datas;
    }


    /**
     * M�thode de recherche de l'existance d'un �l�ment
     * @param element   Element recherch�
     * @return  Etat de pr�sence
     */
    public boolean exist(T element) {

        // Si l'�l�ment recherch� est null
        if(element == null) return false;

        // On retourne son �tat de pr�sence
        return (this.elements.indexOf(element) < 0) ? false: true;
    }

   

    /**
     * M�thode d'obtention de la valeur de la propri�t� bind�e sur la colonne en param�tre de la Table
     * @param data  Donn�e manipul�e
     * @param columnIndex   Index de la colonne
     * @return  Valeur de la propri�t�
     */
    public abstract Object getColoumnValue(T data, int columnIndex);

    /**
     * Renvoie true si l'element est un composant false sinon
     * @param data
     * @return 
     */
    public abstract Boolean isComponent(T data);
    
    /**
     * Renvoie le sous composant en position index
     * @param data
     * @param index
     * @return 
     */
    public abstract T getSubElement(T data , int index );
    
    /**
     * Renvoie la sous liste 
     * @param data
     * @return 
     */
    public abstract List<T> getSubElements(T data);
    
    /**
     * Renvoie le nombre de sous composant d'un composite
     * @param data
     * @return 
     */
    public abstract int subElementCount(T data);
    
    /**
     * Renvoie le nom de la racine
     * @return 
     */
    public abstract String rootName();
    /**
     * Fonction de construction de
     * @param parent
     * @param elements 
     */
    public  void treeBuilder(DefaultMutableTreeNode parent , List<T> elements){
        
        if( elements == null) 
                return ;  
        ((DefaultMutableTreeNode)root).setUserObject(rootName());
        for( T data : elements ){
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(data);
            parent.add(node);      
            System.out.println("AbstractTreeBaseListModel.treeBuilder(DefaultMutableTreeNode parent , List<T> elements) ::::::::::::::::::::::: "+data+" :::::::::::::::::::::::::: "+getSubElements(data));
            if(!isComponent(data)){
                treeBuilder(node, getSubElements(data));
            }              
            
        }
    }
    
}
