
package com.teratech.stock.core.ifaces.base;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.teratech.stock.model.base.Devise;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Tue Feb 20 00:26:45 GMT+01:00 2018
 * 
 */
public interface DeviseManager
    extends GenericManager<Devise, Long>
{

    public final static String SERVICE_NAME = "DeviseManager";

}
