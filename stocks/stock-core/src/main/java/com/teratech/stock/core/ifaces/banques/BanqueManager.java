
package com.teratech.stock.core.ifaces.banques;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.teratech.stock.model.banques.Banque;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Tue Feb 20 00:07:32 GMT+01:00 2018
 * 
 */
public interface BanqueManager
    extends GenericManager<Banque, Long>
{

    public final static String SERVICE_NAME = "BanqueManager";

}
