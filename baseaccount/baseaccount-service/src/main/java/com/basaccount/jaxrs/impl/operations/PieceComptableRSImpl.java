
package com.basaccount.jaxrs.impl.operations;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.operations.PieceComptableManagerRemote;
import com.basaccount.jaxrs.ifaces.operations.PieceComptableRS;
import com.basaccount.model.operations.EcritureComptable;
import com.basaccount.model.operations.PieceComptable;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Sat Dec 23 09:07:30 WAT 2017
 * 
 */
@Path("/piececomptable")
public class PieceComptableRSImpl
    extends AbstractGenericService<PieceComptable, Long>
    implements PieceComptableRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "PieceComptableManagerImpl", interf = PieceComptableManagerRemote.class)
    protected PieceComptableManagerRemote manager;

    public PieceComptableRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<PieceComptable, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            return MetaDataUtil.getMetaData(new PieceComptable(), new HashMap<String, MetaData>(),new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(PieceComptableRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PieceComptableRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void processBeforeUpdate(PieceComptable entity) {
        BigDecimal totaldebit = BigDecimal.ZERO;
        BigDecimal totalcredit = BigDecimal.ZERO;
        PieceComptable piece = (PieceComptable) entity;
        for(EcritureComptable ecrit:piece.getEcritures()){
            if(ecrit.getDebit()!=null){
                totaldebit = totaldebit.add(ecrit.getDebit());
            }//end if(ecrit.getDebit()!=null)
            if(ecrit.getCredit()!=null){
                totalcredit = totalcredit.add(ecrit.getCredit());
            }//end if(ecrit.getCredit()!=null)            
        }
        if(totalcredit.compareTo(totaldebit)!=0){
            throw new WebApplicationException(Response.status(Response.Status.PRECONDITION_FAILED)
                    .header("cause" , new String("Opération comptable non équilibrée")).build());
        }
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processBeforeSave(PieceComptable entity) {
        BigDecimal totaldebit = BigDecimal.ZERO;
        BigDecimal totalcredit = BigDecimal.ZERO;
        PieceComptable piece = (PieceComptable) entity;
        for(EcritureComptable ecrit:piece.getEcritures()){
            if(ecrit.getDebit()!=null){
                totaldebit = totaldebit.add(ecrit.getDebit());
            }//end if(ecrit.getDebit()!=null)
            if(ecrit.getCredit()!=null){
                totalcredit = totalcredit.add(ecrit.getCredit());
            }//end if(ecrit.getCredit()!=null)            
        }
        
        if(totalcredit.compareTo(totaldebit)!=0){
            throw new WebApplicationException(Response.status(Response.Status.PRECONDITION_FAILED)
                    .header("cause" , new String("Opération comptable non équilibrée")).build());
        }
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
