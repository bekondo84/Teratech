
package com.teratech.stock.dao.ifaces.comptabilite;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.teratech.stock.model.comptabilite.Taxe;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Tue Feb 20 00:07:32 GMT+01:00 2018
 * 
 */
public interface TaxeDAO
    extends GenericDAO<Taxe, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "TaxeDAO";

}
