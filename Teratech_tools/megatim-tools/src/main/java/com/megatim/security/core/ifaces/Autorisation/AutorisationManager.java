
package com.megatim.security.core.ifaces.Autorisation;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.security.model.Autorisation;


/**
 * Interface etendue par les interfaces locale et remote du manager

 * @since Sun Sep 18 21:53:32 CEST 2016
 * 
 */
public interface AutorisationManager
    extends GenericManager<Autorisation, Long>
{

    public final static String SERVICE_NAME = "AutorisationManager";

}
