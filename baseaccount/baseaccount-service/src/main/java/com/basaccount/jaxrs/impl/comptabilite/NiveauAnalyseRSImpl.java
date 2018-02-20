
package com.basaccount.jaxrs.impl.comptabilite;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.comptabilite.NiveauAnalyseManagerRemote;
import com.basaccount.jaxrs.ifaces.comptabilite.NiveauAnalyseRS;
import com.basaccount.model.comptabilite.NiveauAnalyse;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;


/**
 * Classe d'implementation du Web Service JAX-RS
 * @since Fri Dec 01 17:32:13 WAT 2017
 * 
 */
@Path("/niveauanalyse")
public class NiveauAnalyseRSImpl
    extends AbstractGenericService<NiveauAnalyse, Long>
    implements NiveauAnalyseRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "NiveauAnalyseManagerImpl", interf = NiveauAnalyseManagerRemote.class)
    protected NiveauAnalyseManagerRemote manager;

    public NiveauAnalyseRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<NiveauAnalyse, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

}
