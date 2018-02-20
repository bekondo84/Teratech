
package com.teratech.stock.dao.ifaces.comptabilite;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.teratech.stock.model.comptabilite.Compte;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Tue Feb 20 00:07:32 GMT+01:00 2018
 * 
 */
public interface CompteDAO
    extends GenericDAO<Compte, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "CompteDAO";

}
