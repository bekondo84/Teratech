
package com.teratech.stock.dao.ifaces.base;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.teratech.stock.model.base.Societe;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Tue Feb 20 00:26:45 GMT+01:00 2018
 * 
 */
public interface SocieteDAO
    extends GenericDAO<Societe, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "SocieteDAO";

}
