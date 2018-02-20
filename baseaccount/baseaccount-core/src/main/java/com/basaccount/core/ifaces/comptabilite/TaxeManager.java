
package com.basaccount.core.ifaces.comptabilite;

import com.basaccount.model.comptabilite.Taxe;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Dec 01 14:04:41 WAT 2017
 * 
 */
public interface TaxeManager
    extends GenericManager<Taxe, Long>
{

    public final static String SERVICE_NAME = "TaxeManager";

}
