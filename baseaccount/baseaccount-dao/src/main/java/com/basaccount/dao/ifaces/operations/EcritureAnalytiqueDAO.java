
package com.basaccount.dao.ifaces.operations;

import com.basaccount.model.operations.EcritureAnalytique;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Wed Dec 27 12:41:45 WAT 2017
 * 
 */
public interface EcritureAnalytiqueDAO
    extends GenericDAO<EcritureAnalytique, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "EcritureAnalytiqueDAO";

}
