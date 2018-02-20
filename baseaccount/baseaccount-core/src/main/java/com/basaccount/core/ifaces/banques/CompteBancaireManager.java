
package com.basaccount.core.ifaces.banques;

import com.basaccount.model.banques.CompteBancaire;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Dec 01 14:04:41 WAT 2017
 * 
 */
public interface CompteBancaireManager
    extends GenericManager<CompteBancaire, Long>
{

    public final static String SERVICE_NAME = "CompteBancaireManager";

}
