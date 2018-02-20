
package com.teratech.stock.jaxrs.impl.banques;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.teratech.stock.core.ifaces.banques.CompteBancaireManagerRemote;
import com.teratech.stock.jaxrs.ifaces.banques.CompteBancaireRS;
import com.teratech.stock.jaxrs.impl.base.EntrepotRSImpl;
import com.teratech.stock.model.banques.CompteBancaire;
import com.teratech.stock.model.base.Entrepot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Tue Feb 20 00:07:33 GMT+01:00 2018
 * 
 */
@Path("/comptebancaire")
public class CompteBancaireRSImpl
    extends AbstractGenericService<CompteBancaire, Long>
    implements CompteBancaireRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "teratechstock", name = "CompteBancaireManagerImpl", interf = CompteBancaireManagerRemote.class)
    protected CompteBancaireManagerRemote manager;

    public CompteBancaireRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<CompteBancaire, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("teratechstock");
    }
    
    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            return MetaDataUtil.getMetaData(new CompteBancaire(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(EntrepotRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EntrepotRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
