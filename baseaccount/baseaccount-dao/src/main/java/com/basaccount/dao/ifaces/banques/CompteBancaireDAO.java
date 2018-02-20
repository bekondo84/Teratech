
package com.basaccount.dao.ifaces.banques;

import com.basaccount.model.banques.CompteBancaire;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Dec 01 14:04:40 WAT 2017
 * 
 */
public interface CompteBancaireDAO
    extends GenericDAO<CompteBancaire, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "CompteBancaireDAO";

}
