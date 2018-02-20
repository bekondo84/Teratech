
package com.core.securites;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Tue Nov 21 10:34:43 WAT 2017
 * 
 */
public interface GroupeManager
    extends GenericManager<Groupe, Long>
{

    public final static String SERVICE_NAME = "GroupeManager";

}
