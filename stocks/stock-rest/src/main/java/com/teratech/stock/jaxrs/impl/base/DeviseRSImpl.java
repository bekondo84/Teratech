
package com.teratech.stock.jaxrs.impl.base;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.teratech.stock.core.ifaces.base.DeviseManagerRemote;
import com.teratech.stock.jaxrs.ifaces.base.DeviseRS;
import com.teratech.stock.model.base.Devise;


/**
 * Classe d'implementation du Web Service JAX-RS
 * @since Tue Feb 20 00:26:45 GMT+01:00 2018
 * 
 */
@Path("/devise")
public class DeviseRSImpl
    extends AbstractGenericService<Devise, Long>
    implements DeviseRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "teratechstock", name = "DeviseManagerImpl", interf = DeviseManagerRemote.class)
    protected DeviseManagerRemote manager;

    public DeviseRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Devise, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("teratechstock");
    }

}
