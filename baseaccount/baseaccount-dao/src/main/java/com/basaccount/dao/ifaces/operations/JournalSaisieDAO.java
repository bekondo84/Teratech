
package com.basaccount.dao.ifaces.operations;

import com.basaccount.model.operations.JournalSaisie;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Sun Dec 24 09:57:47 WAT 2017
 * 
 */
public interface JournalSaisieDAO
    extends GenericDAO<JournalSaisie, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "JournalSaisieDAO";

}
