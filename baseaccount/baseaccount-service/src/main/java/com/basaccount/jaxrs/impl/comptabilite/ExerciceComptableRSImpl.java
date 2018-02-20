
package com.basaccount.jaxrs.impl.comptabilite;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.comptabilite.ExerciceComptableManagerRemote;
import com.basaccount.jaxrs.ifaces.comptabilite.ExerciceComptableRS;
import com.basaccount.model.comptabilite.ExerciceComptable;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.commons.DateHelper;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Sun Dec 24 09:57:47 WAT 2017
 * 
 */
@Path("/exercicecomptable")
public class ExerciceComptableRSImpl
    extends AbstractGenericService<ExerciceComptable, Long>
    implements ExerciceComptableRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "ExerciceComptableManagerImpl", interf = ExerciceComptableManagerRemote.class)
    protected ExerciceComptableManagerRemote manager;

    public ExerciceComptableRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<ExerciceComptable, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            return MetaDataUtil.getMetaData(new ExerciceComptable(), new HashMap<String, MetaData>(),new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(ExerciceComptableRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ExerciceComptableRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ExerciceComptable> filter(HttpHeaders headers, int firstResult, int maxResult) {
        return super.filter(headers, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ExerciceComptable> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExerciceComptable find(String propertyName, Long id) {
        return super.find(propertyName, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processBeforeUpdate(ExerciceComptable data) {
        System.out.println(ExerciceComptableRSImpl.class.getName()+" ================"+data);
        ExerciceComptable entity = (ExerciceComptable) data;
        if(entity.getDebut()==null||entity.getFin()==null){
            RuntimeException excep = new RuntimeException("La date de debut et de fin ne peut être null");
            throw new WebApplicationException(excep,Response.Status.NOT_MODIFIED);
        }else if(DateHelper.numberOfMonth(entity.getDebut(), entity.getFin())<12){
            RuntimeException excep = new RuntimeException("Un exercice comptable doit contenir 12 Mois");
            throw new WebApplicationException(excep,Response.Status.NOT_MODIFIED);
        }
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processBeforeSave(ExerciceComptable data) {
         ExerciceComptable entity = (ExerciceComptable) data;
        if(entity.getDebut()==null||entity.getFin()==null){
            RuntimeException excep = new RuntimeException("La date de debut et de fin ne peut être null");
            throw new WebApplicationException(excep,Response.Status.NOT_MODIFIED);
        }else if(DateHelper.numberOfMonth(entity.getDebut(), entity.getFin())<12){
            RuntimeException excep = new RuntimeException("Un exercice comptable doit contenir 12 Mois");
            throw new WebApplicationException(excep,Response.Status.NOT_MODIFIED);
        }
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }

    
}
