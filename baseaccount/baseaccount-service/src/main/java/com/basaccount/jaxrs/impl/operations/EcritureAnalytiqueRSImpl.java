
package com.basaccount.jaxrs.impl.operations;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.operations.EcritureAnalytiqueManagerRemote;
import com.basaccount.jaxrs.ifaces.operations.EcritureAnalytiqueRS;
import com.basaccount.model.operations.EcritureAnalytique;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Wed Dec 27 12:41:45 WAT 2017
 * 
 */
@Path("/ecritureanalytique")
public class EcritureAnalytiqueRSImpl
    extends AbstractGenericService<EcritureAnalytique, Long>
    implements EcritureAnalytiqueRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "EcritureAnalytiqueManagerImpl", interf = EcritureAnalytiqueManagerRemote.class)
    protected EcritureAnalytiqueManagerRemote manager;

    public EcritureAnalytiqueRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<EcritureAnalytique, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            return MetaDataUtil.getMetaData(new EcritureAnalytique(), new HashMap<String, MetaData>(),new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(EcritureTierRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EcritureTierRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
