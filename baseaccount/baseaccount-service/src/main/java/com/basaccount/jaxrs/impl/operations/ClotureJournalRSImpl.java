
package com.basaccount.jaxrs.impl.operations;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.operations.ClotureJournalManagerRemote;
import com.basaccount.jaxrs.ifaces.operations.ClotureJournalRS;
import com.basaccount.model.operations.ClotureJournal;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.megatimgroup.generic.jax.rs.layer.impl.RSNumber;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Thu Dec 28 12:13:51 WAT 2017
 * 
 */
@Path("/cloturejournal")
public class ClotureJournalRSImpl
    extends AbstractGenericService<ClotureJournal, Long>
    implements ClotureJournalRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "ClotureJournalManagerImpl", interf = ClotureJournalManagerRemote.class)
    protected ClotureJournalManagerRemote manager;

    public ClotureJournalRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<ClotureJournal, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        //To change body of generated methods, choose Tools | Templates.
        try {
            //To change body of generated methods, choose Tools | Templates.
            return MetaDataUtil.getMetaData(new ClotureJournal(), new HashMap<String, MetaData>(),new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(ClotureExerciceComptableRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ClotureExerciceComptableRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public RSNumber count(HttpHeaders headers) {
        return new RSNumber(0); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ClotureJournal> filter(HttpHeaders headers, int firstResult, int maxResult) {
        return new ArrayList<ClotureJournal>(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ClotureJournal> findAll() {
        return new ArrayList<ClotureJournal>(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClotureJournal find(String propertyName, Long id) {
        return new ClotureJournal(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(HttpHeaders headers) {
       //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClotureJournal delete(Long id) {
        return new ClotureJournal(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
