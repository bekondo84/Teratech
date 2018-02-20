
package com.basaccount.dao.ifaces.comptabilite;

import com.basaccount.model.comptabilite.SectionAnalytique;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Dec 01 21:45:52 WAT 2017
 * 
 */
public interface SectionAnalytiqueDAO
    extends GenericDAO<SectionAnalytique, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "SectionAnalytiqueDAO";

}
