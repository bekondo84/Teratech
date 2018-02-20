
package com.basaccount.dao.ifaces.comptabilite;

import com.basaccount.model.comptabilite.ExerciceComptable;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO

 * @since Sun Dec 24 09:57:47 WAT 2017
 * 
 */
public interface ExerciceComptableDAO
    extends GenericDAO<ExerciceComptable, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "ExerciceComptableDAO";

    public ExerciceComptable getOpenExercice() ;
}
