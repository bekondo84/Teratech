
package com.basaccount.dao.ifaces.comptabilite;

import com.basaccount.model.comptabilite.NiveauAnalyse;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Dec 01 17:32:13 WAT 2017
 * 
 */
public interface NiveauAnalyseDAO
    extends GenericDAO<NiveauAnalyse, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "NiveauAnalyseDAO";

}
