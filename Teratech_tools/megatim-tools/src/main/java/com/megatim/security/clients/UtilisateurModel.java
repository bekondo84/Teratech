
package com.megatim.security.clients;

import com.megatim.common.clients.AbstractTableBaseListModel;
import com.megatim.common.utilities.MessagesBundle;
import com.megatim.security.model.Utilisateur;


/**
 * Modele de tableau UtilisateurModel

 * @since Sun Sep 18 21:53:38 CEST 2016
 * 
 */
public class UtilisateurModel
    extends AbstractTableBaseListModel<Utilisateur>
{

    protected MessagesBundle bundle;

    public UtilisateurModel() {
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
//                return (MessagesBundle.getMessage("utilisateur.login").toUpperCase());
            case  0 :
                return (MessagesBundle.getMessage("utilisateur.nom").toUpperCase());
            case  1 :
                return (MessagesBundle.getMessage("utilisateur.prenom").toUpperCase());
//            case  3 :
//                return (MessagesBundle.getMessage("utilisateur.password").toUpperCase());
            case  2:
                return (MessagesBundle.getMessage("utilisateur.profil").toUpperCase());
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
    public Object getColoumnValue(Utilisateur data, int columnIndex) {
        switch ((columnIndex)) {
//            case  0 :
//                return (data.getLogin());
            case 0 :
                return (data.getNom());
            case  1 :
                return (data.getPrenom());
//            case  3 :
//                return (data.getPassword());
            case  2 :
                return (data.getProfil());
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
            case  2 :
                return (com.megatim.security.model.Profil.class);
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
        return  3;
    }

}
