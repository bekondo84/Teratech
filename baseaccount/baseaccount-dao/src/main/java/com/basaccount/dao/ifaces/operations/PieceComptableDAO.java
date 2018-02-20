
package com.basaccount.dao.ifaces.operations;

import com.basaccount.model.operations.PieceComptable;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Sat Dec 23 09:07:30 WAT 2017
 * 
 */
public interface PieceComptableDAO
    extends GenericDAO<PieceComptable, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "PieceComptableDAO";

}
