
package com.basaccount.jaxrs.impl.comptabilite;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.comptabilite.SectionAnalytiqueManagerRemote;
import com.basaccount.jaxrs.ifaces.comptabilite.SectionAnalytiqueRS;
import com.basaccount.model.comptabilite.SectionAnalytique;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Fri Dec 01 21:45:53 WAT 2017
 * 
 */
@Path("/sectionanalytique")
public class SectionAnalytiqueRSImpl
    extends AbstractGenericService<SectionAnalytique, Long>
    implements SectionAnalytiqueRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "SectionAnalytiqueManagerImpl", interf = SectionAnalytiqueManagerRemote.class)
    protected SectionAnalytiqueManagerRemote manager;

    public SectionAnalytiqueRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<SectionAnalytique, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(@Context HttpHeaders headers) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            return MetaDataUtil.getMetaData(new SectionAnalytique(), new HashMap<String,MetaData>(),new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(SectionAnalytiqueRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SectionAnalytiqueRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    

}
