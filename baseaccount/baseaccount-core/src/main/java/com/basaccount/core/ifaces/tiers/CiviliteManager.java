
package com.basaccount.core.ifaces.tiers;

import com.basaccount.model.tiers.Civilite;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Dec 01 14:04:41 WAT 2017
 * 
 */
public interface CiviliteManager
    extends GenericManager<Civilite, Long>
{

    public final static String SERVICE_NAME = "CiviliteManager";

}
