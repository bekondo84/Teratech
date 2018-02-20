
package com.basaccount.core.ifaces.comptabilite;

import com.basaccount.model.comptabilite.SectionAnalytique;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Dec 01 21:45:52 WAT 2017
 * 
 */
public interface SectionAnalytiqueManager
    extends GenericManager<SectionAnalytique, Long>
{

    public final static String SERVICE_NAME = "SectionAnalytiqueManager";

}
