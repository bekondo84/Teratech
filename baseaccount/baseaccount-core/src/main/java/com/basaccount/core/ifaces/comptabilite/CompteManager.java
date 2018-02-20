
package com.basaccount.core.ifaces.comptabilite;

import com.basaccount.model.comptabilite.Compte;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Dec 01 14:04:41 WAT 2017
 * 
 */
public interface CompteManager
    extends GenericManager<Compte, Long>
{

    public final static String SERVICE_NAME = "CompteManager";

}
