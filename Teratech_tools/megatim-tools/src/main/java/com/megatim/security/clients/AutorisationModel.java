
package com.megatim.security.clients;

import com.megatim.common.clients.AbstractTableBaseListModel;
import com.megatim.common.utilities.MessagesBundle;
import com.megatim.security.enumerations.EnumStatutAutorisation;
import com.megatim.security.model.Autorisation;


/**
 * Modele de tableau AutorisationModel

 * @since Sun Sep 18 21:53:38 CEST 2016
 * 
 */
public class AutorisationModel
    extends AbstractTableBaseListModel<Autorisation>
{

    protected MessagesBundle bundle;

    public AutorisationModel() {
        super() ; 
    }

    /**
     * Methode permettant de retourner le nom de la colonne
     * 
     * @param columnIndex
     * @return
     *     java.lang.String
     */
    @Override
    public String getColumnName(int columnIndex) {
        switch ((columnIndex)) {
//            case  0 :
//                return (MessagesBundle.getMessage("autorisation.id"));
//            case  1 :
//                return (MessagesBundle.getMessage("autorisation.nomautorisation").toUpperCase());
            case  0 :
                return (MessagesBundle.getMessage("autorisation.description").toUpperCase());
            case  1 :
                return (MessagesBundle.getMessage("autorisation.statut").toUpperCase());
            default:
                return ("");
        }
    }

    /**
     * /**  **<!---->/Methode permettant de retourner la valeur de la colonne
     * 
     * @param data
     * @param columnIndex
     * @return
     *     void
     */
    @Override
    public Object getColoumnValue(Autorisation data, int columnIndex) {
        switch ((columnIndex)) {
//            case  0 :
//                return (data.getId());
//            case  1 :
//                return (data.getNomAutorisation());
            case  0 :
                return (data.getDescription());
            case  1 :
                return (data.getStatut());
            default:
                return ("");
        }
    }

    /**
     * Methode permettant de retourner la classe de la colonne
     * 
     * @param columnIndex
     * @return
     *     java.lang.Class
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        switch ((columnIndex)) {
            case  1 :
                return (EnumStatutAutorisation.class);
            default:
                return (String.class);
        }
    }

    /**
     * Methode permettant de retourner le nombre de colonnes
     * 
     * @return
     *     int
     */
    @Override
    public int getColumnCount() {
        return  2;
    }
    
    @Override
   public boolean isCellEditable(int li, int co){
       
       if(getColumnClass(co).equals(EnumStatutAutorisation.class)){
           return true;
       }
       
       return false;
   }
   
   @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
        Autorisation selected = this.elements.get(rowIndex);
        selected.setStatut((EnumStatutAutorisation) aValue);
        this.elements.set(rowIndex, selected);
       
    }
}
