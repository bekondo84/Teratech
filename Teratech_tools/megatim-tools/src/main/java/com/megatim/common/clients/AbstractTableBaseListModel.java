/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.clients;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author BEKONDO KANGUE Dieunedort
 */
public abstract class AbstractTableBaseListModel<T extends Comparable<? super T>> extends AbstractTableModel implements TableModel  {

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
     * Methode de mise a jour du Comparateur
     * @param comparator	Comparateur
     */
    public void setComparator(Comparator<T> comparator) {
    	this.comparator = comparator;
    }
    
    /**
     * M�thode d'obtention du Nombre de ligne de la table
     * @return Nombre de ligne de la table
     */
    @Override
    public int getRowCount() {

        // On retourne le nombre d'�l�ments
        return (elements == null) ? 0 : elements.size();
    }

    /**
     * M�thode d'obtention de l'Etat de mofiabilit� d'une cellule de la Table
     * @param rowIndex  Index de la ligne
     * @param columnIndex   Index de la colonne
     * @return  Etat de mofiabilit� d'une cellule de la Table
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        // Par defaut false
        return false;
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
        fireTableDataChanged();
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
        fireTableRowsInserted(0, 0);

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
        fireTableRowsDeleted(index, index);
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
        fireTableRowsUpdated(index, index);

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
     * M�thode d'obtention du Nom de la colonne identifi�e par son index
     * @param columnIndex   Index de la colonne
     * @return  Nom de la colonne
     */
    @Override
    public abstract String getColumnName(int columnIndex);

    /**
     * M�thode d'obtention de la classe de la colonne
     * @param columnIndex   Index de la colonne
     * @return  Classe de la colonne
     */
    @Override
    public abstract Class<?> getColumnClass(int columnIndex);

    /**
     * M�thode d'obtention de la valeur de la propri�t� bind�e sur la colonne en param�tre de la Table
     * @param data  Donn�e manipul�e
     * @param columnIndex   Index de la colonne
     * @return  Valeur de la propri�t�
     */
    public abstract Object getColoumnValue(T data, int columnIndex);

    /**
     * M�thode de mise � jour de la valeur d'une cellule
     * @param aValue    Nouvelle valeur
     * @param rowIndex  Index de la ligne de la cellule
     * @param columnIndex   Index de la colonne de la cellule
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}

    /**
     * M�thode d'obtention de la valeur d'une cellule
     * @param rowIndex  Index de la ligne de la cellule
     * @param columnIndex   Index de la colonne de la cellule
     * @return Valeur de la cellule
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        // Si la ligne est < 0
        if(rowIndex < 0) return null;
        
        // On r�cup�re l'objet
        T selected = this.elements.get(rowIndex);
        
        // Si l'�l�ment est null
        if(selected == null) return null;
        
        // On retourne la valeur de la propri�t� bind�e sur l'index de colonne
        return getColoumnValue(selected, columnIndex);
    }

}
