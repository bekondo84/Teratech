
package com.basaccount.core.ifaces.comptabilite;

import com.basaccount.model.comptabilite.NiveauAnalyse;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Dec 01 17:32:13 WAT 2017
 * 
 */
public interface NiveauAnalyseManager
    extends GenericManager<NiveauAnalyse, Long>
{

    public final static String SERVICE_NAME = "NiveauAnalyseManager";

}
