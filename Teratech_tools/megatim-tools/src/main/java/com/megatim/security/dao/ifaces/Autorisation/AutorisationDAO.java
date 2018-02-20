
package com.megatim.security.dao.ifaces.Autorisation;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.megatim.security.model.Autorisation;


/**
 * Interface etendue par les interfaces locale et remote de la DAO

 * @since Sun Sep 18 21:53:29 CEST 2016
 * 
 */
public interface AutorisationDAO
    extends GenericDAO<Autorisation, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "AutorisationDAO";

}
