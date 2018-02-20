
package com.teratech.stock.core.ifaces.banques;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.teratech.stock.model.banques.CompteBancaire;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Tue Feb 20 00:07:32 GMT+01:00 2018
 * 
 */
public interface CompteBancaireManager
    extends GenericManager<CompteBancaire, Long>
{

    public final static String SERVICE_NAME = "CompteBancaireManager";

}
