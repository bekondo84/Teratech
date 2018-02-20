
package com.basaccount.jaxrs.impl.tiers;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.tiers.ContactManagerRemote;
import com.basaccount.jaxrs.ifaces.tiers.ContactRS;
import com.basaccount.model.tiers.Contact;
import com.basaccount.model.tiers.Tier;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Fri Dec 01 14:04:41 WAT 2017
 * 
 */
@Path("/contact")
public class ContactRSImpl
    extends AbstractGenericService<Contact, Long>
    implements ContactRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "ContactManagerImpl", interf = ContactManagerRemote.class)
    protected ContactManagerRemote manager;

    public ContactRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Contact, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }
    
     @Override
    public MetaData getMetaData(@Context HttpHeaders headers) {
        try {
            return MetaDataUtil.getMetaData(new Contact(), new HashMap<String , MetaData>(),new ArrayList<String>());            
        } catch (Exception ex) {
           throw new WebApplicationException(ex);
        }  
    }

}
