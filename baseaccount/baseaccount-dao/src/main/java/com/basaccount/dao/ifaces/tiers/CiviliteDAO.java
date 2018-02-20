
package com.basaccount.dao.ifaces.tiers;

import com.basaccount.model.tiers.Civilite;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Dec 01 14:04:40 WAT 2017
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
