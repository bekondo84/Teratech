
package com.basaccount.core.ifaces.tiers;

import com.basaccount.model.tiers.ConditionPaiement;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Dec 01 14:04:41 WAT 2017
 * 
 */
public interface ConditionPaiementManager
    extends GenericManager<ConditionPaiement, Long>
{

    public final static String SERVICE_NAME = "ConditionPaiementManager";

}
