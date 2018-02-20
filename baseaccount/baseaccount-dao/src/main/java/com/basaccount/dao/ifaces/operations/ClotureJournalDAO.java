
package com.basaccount.dao.ifaces.operations;

import com.basaccount.model.operations.ClotureJournal;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Thu Dec 28 12:13:51 WAT 2017
 * 
 */
public interface ClotureJournalDAO
    extends GenericDAO<ClotureJournal, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "ClotureJournalDAO";

}
