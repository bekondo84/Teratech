
package com.teratech.stock.jaxrs.impl.base;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.teratech.stock.core.ifaces.base.CategorieProduitManagerRemote;
import com.teratech.stock.jaxrs.ifaces.base.CategorieProduitRS;
import com.teratech.stock.model.base.CategorieProduit;
import com.teratech.stock.model.base.UniteGestion;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Mon Feb 19 16:55:37 GMT+01:00 2018
 * 
 */
@Path("/categorieproduit")
public class CategorieProduitRSImpl
    extends AbstractGenericService<CategorieProduit, Long>
    implements CategorieProduitRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "teratechstock", name = "CategorieProduitManagerImpl", interf = CategorieProduitManagerRemote.class)
    protected CategorieProduitManagerRemote manager;

    public CategorieProduitRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<CategorieProduit, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("teratechstock");
    }
    
     @Override
    public MetaData getMetaData(HttpHeaders headers) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            return MetaDataUtil.getMetaData(new CategorieProduit(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (Exception ex) {
            throw new WebApplicationException(Response.serverError().entity(new String("MetaData parse error")).build());
        }
       
    }

}
