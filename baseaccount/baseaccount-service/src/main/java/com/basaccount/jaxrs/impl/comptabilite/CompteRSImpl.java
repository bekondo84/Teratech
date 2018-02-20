
package com.basaccount.jaxrs.impl.comptabilite;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.comptabilite.CompteManagerRemote;
import com.basaccount.jaxrs.ifaces.comptabilite.CompteRS;
import com.basaccount.model.comptabilite.Compte;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.core.menus.MenuAction;
import com.core.menus.MenuActionManagerRemote;
import com.google.gson.Gson;
import com.kerem.core.CommonTools;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Fri Dec 01 14:04:41 WAT 2017
 * 
 */
@Path("/compte")
public class CompteRSImpl
    extends AbstractGenericService<Compte, Long>
    implements CompteRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "CompteManagerImpl", interf = CompteManagerRemote.class)
    protected CompteManagerRemote manager;
    
    @Manager(application = "kerencore", name = "MenuActionManagerImpl", interf = MenuActionManagerRemote.class)
    protected MenuActionManagerRemote actionmanager;

    public CompteRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Compte, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

     @Override
    public MetaData getMetaData(@Context HttpHeaders headers) {       
        try {
            Gson gson = new Gson();
            MenuAction action = null;
            if(headers.getRequestHeader("action")!=null){
                action = gson.fromJson(headers.getRequestHeader("action").get(0),MenuAction.class);
            }
            if(action==null||action.getId()<=0){
                return MetaDataUtil.getMetaData(new Compte(),new HashMap<String, MetaData>(),new ArrayList<String>());
            }else{
                action = actionmanager.find("id", action.getId());
                return CommonTools.xmlViewParser(Compte.class, action.getFormView(), action.getTreeView());
            }
            
        } catch (Exception ex) {
           throw new WebApplicationException(ex);
        }  
    }

    @Override
    public List<Compte> filter(HttpHeaders headers, int firstResult, int maxResult) {
        return super.filter(headers, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
