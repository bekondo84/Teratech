
package com.megatim.security.clients;

import com.megatim.common.clients.AbstractTableBaseListModel;
import com.megatim.common.utilities.MessagesBundle;
import com.megatim.security.model.Profil;


/**
 * Modele de tableau ProfilModel

 * @since Sun Sep 18 21:53:38 CEST 2016
 * 
 */
public class ProfilModel
    extends AbstractTableBaseListModel<Profil>
{

    protected MessagesBundle bundle;

    public ProfilModel() {
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
            case  0 :
                return (MessagesBundle.getMessage("profil.nom").toUpperCase());
            case  1 :
                return (MessagesBundle.getMessage("profil.description").toUpperCase());
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
    public Object getColoumnValue(Profil data, int columnIndex) {
        switch ((columnIndex)) {
            case  0 :
                return (data.getNom());
            case  1 :
                return (data.getDescription());
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

}
