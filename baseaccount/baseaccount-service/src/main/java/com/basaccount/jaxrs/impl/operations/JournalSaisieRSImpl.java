
package com.basaccount.jaxrs.impl.operations;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.operations.JournalSaisieManagerRemote;
import com.basaccount.jaxrs.ifaces.operations.JournalSaisieRS;
import com.basaccount.model.operations.JournalSaisie;
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

 * @since Sun Dec 24 09:57:47 WAT 2017
 * 
 */
@Path("/journalsaisie")
public class JournalSaisieRSImpl
    extends AbstractGenericService<JournalSaisie, Long>
    implements JournalSaisieRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "JournalSaisieManagerImpl", interf = JournalSaisieManagerRemote.class)
    protected JournalSaisieManagerRemote manager;

    public JournalSaisieRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<JournalSaisie, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            return MetaDataUtil.getMetaData(new JournalSaisie(), new HashMap<String, MetaData>(),new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(JournalSaisieRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JournalSaisieRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   
}
