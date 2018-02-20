
package com.basaccount.core.ifaces.comptabilite;

import com.basaccount.model.comptabilite.ExerciceComptable;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Sun Dec 24 09:57:47 WAT 2017
 * 
 */
public interface ExerciceComptableManager
    extends GenericManager<ExerciceComptable, Long>
{

    public final static String SERVICE_NAME = "ExerciceComptableManager";

}
