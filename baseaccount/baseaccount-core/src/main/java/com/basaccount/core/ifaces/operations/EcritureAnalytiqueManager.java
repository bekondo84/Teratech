
package com.basaccount.core.ifaces.operations;

import com.basaccount.model.operations.EcritureAnalytique;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Wed Dec 27 12:41:45 WAT 2017
 * 
 */
public interface EcritureAnalytiqueManager
    extends GenericManager<EcritureAnalytique, Long>
{

    public final static String SERVICE_NAME = "EcritureAnalytiqueManager";

}
