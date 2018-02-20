
package com.basaccount.core.ifaces.operations;

import com.basaccount.model.operations.JournalSaisie;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Sun Dec 24 09:57:47 WAT 2017
 * 
 */
public interface JournalSaisieManager
    extends GenericManager<JournalSaisie, Long>
{

    public final static String SERVICE_NAME = "JournalSaisieManager";

}
