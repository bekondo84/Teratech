
package com.teratech.stock.dao.ifaces.base;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.teratech.stock.model.base.Civilite;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Tue Feb 20 00:07:32 GMT+01:00 2018
 * 
 */
public interface CiviliteDAO
    extends GenericDAO<Civilite, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "CiviliteDAO";

}
