
package com.teratech.stock.jaxrs.impl.base;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.teratech.stock.core.ifaces.base.SocieteManagerRemote;
import com.teratech.stock.jaxrs.ifaces.base.SocieteRS;
import com.teratech.stock.model.base.Societe;


/**
 * Classe d'implementation du Web Service JAX-RS
 * @since Tue Feb 20 00:26:45 GMT+01:00 2018
 * 
 */
@Path("/societe")
public class SocieteRSImpl
    extends AbstractGenericService<Societe, Long>
    implements SocieteRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "teratechstock", name = "SocieteManagerImpl", interf = SocieteManagerRemote.class)
    protected SocieteManagerRemote manager;

    public SocieteRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Societe, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("teratechstock");
    }

}
