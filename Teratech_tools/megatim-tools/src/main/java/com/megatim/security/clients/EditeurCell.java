/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.megatim.security.clients;

import com.megatim.security.enumerations.EnumStatutAutorisation;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author divers
 */
class EditeurCell extends AbstractCellEditor 
              implements TableCellEditor {
  EnumStatutAutorisation valeurCourante;
  JComboBox<EnumStatutAutorisation> options;
  EnumStatutAutorisation tab[] = {EnumStatutAutorisation.NONE, EnumStatutAutorisation.READ, EnumStatutAutorisation.WRITE, EnumStatutAutorisation.READ.DELETE};
  
  public EditeurCell() {
    options = new JComboBox(tab);
    
    options.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboItemtsStateChanged(evt);
            }
    });
  }
  
  @Override
  public Object getCellEditorValue() {
      return valeurCourante;
  }
  @Override
  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    
    //On recupere la valeur  
    valeurCourante = (EnumStatutAutorisation) value;
      
    //On remet l'index à zeo
    options.setSelectedIndex(0);
    return options;
  }

  private void comboItemtsStateChanged(java.awt.event.ItemEvent evt) {                                          
        valeurCourante = (EnumStatutAutorisation)options.getSelectedItem();System.out.println("=============================== "+valeurCourante);
    }
}
