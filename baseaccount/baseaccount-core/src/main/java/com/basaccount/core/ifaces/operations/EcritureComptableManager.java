
package com.basaccount.core.ifaces.operations;

import com.basaccount.model.operations.EcritureComptable;
import com.basaccount.model.search.EcritureSearch;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import java.util.List;


/**
 * Interface etendue par les interfaces locale et remote du manager

 * @since Sat Dec 23 09:07:30 WAT 2017
 * 
 */
public interface EcritureComptableManager
    extends GenericManager<EcritureComptable, Long>
{

    public final static String SERVICE_NAME = "EcritureComptableManager";
    
    public List<EcritureComptable> somethings(EcritureSearch critere);

}
