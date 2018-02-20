
package com.teratech.stock.jaxrs.impl.comptabilite;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.teratech.stock.core.ifaces.comptabilite.TaxeManagerRemote;
import com.teratech.stock.jaxrs.ifaces.comptabilite.TaxeRS;
import com.teratech.stock.jaxrs.impl.base.EntrepotRSImpl;
import com.teratech.stock.model.base.Entrepot;
import com.teratech.stock.model.comptabilite.Taxe;
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
@Path("/taxe")
public class TaxeRSImpl
    extends AbstractGenericService<Taxe, Long>
    implements TaxeRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "teratechstock", name = "TaxeManagerImpl", interf = TaxeManagerRemote.class)
    protected TaxeManagerRemote manager;

    public TaxeRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Taxe, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("teratechstock");
    }
    
    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            return MetaDataUtil.getMetaData(new Taxe(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(EntrepotRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EntrepotRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
